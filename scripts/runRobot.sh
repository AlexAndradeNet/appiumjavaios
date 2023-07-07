#!/bin/sh

PROJECTS_PATH=$(dirname "$(PWD)")'/'
AUTOMATION_API_PROJECT_PROFILE=''
AUTOMATION_API_PROJECT_ROOT_PATH="$PROJECTS_PATH"'bocc-occiauto-qa-api'
AUTOMATION_API_REPORT_PATH="$AUTOMATION_API_PROJECT_ROOT_PATH"'/target/site/serenity/index.html'
BACKEND_PROJECT_ROOT_PATH="$PROJECTS_PATH"'bocc-occiauto-backend-new'
CASTLEMOCK_PROJECT_ROOT_PATH="$PROJECTS_PATH"'bocc-occiauto-castlemock-mocks'
URLDATABASE=""
URLBACKEND=""

function fnRun(){
  start_time=`date +%s`
  start_date=`date '+%Y-%m-%d %H:%M:%S'`

  killall "Google Drive" 2> /dev/null

  case "$AUTOMATION_API_PROJECT_PROFILE" in
    "local")
      URLDATABASE="http://127.0.0.1:3306"
      URLBACKEND="http://127.0.0.1:8082/occiauto/api/business/tyc"
    ;;
    "mixed")
      URLDATABASE="http://127.0.0.1:3306"
      URLBACKEND="http://127.0.0.1:8082/occiauto/api/business/tyc"
    ;;
    "stg")
      URLDATABASE="http://aurora-occiauto.adl-occidente-stg.net:3306"
      URLBACKEND="http://bb-stg-alb-ecs-ext-902953221.us-east-2.elb.amazonaws.com/occiauto/api/business/tyc"
    ;;
  esac

  if [ -z $(wget -O - -T 2 "$URLDATABASE" 2>&1 | grep -o " connected") ]; then
    echo "PLEASE, CHECK YOUR VPN CONNECTION OR YOUR DATABASE"
    exit 1
  fi

  if [ -z $(wget -O - -T 2 "$URLBACKEND" 2>&1 | grep -o " connected") ]; then
    echo "PLEASE, CHECK YOUR YOUR BACKEND"
    exit 1
  fi

  if [ "$AUTOMATION_API_PROJECT_PROFILE" != "stg" ]; then
    echo "Cleaning redis"
    docker exec -it redis redis-cli FLUSHDB > /dev/null
  fi
  
  if [ "$AUTOMATION_API_PROJECT_PROFILE" = "local" ]; then
    eval $CASTLEMOCK_PROJECT_ROOT_PATH"/castlemockfix.sh -l"
  fi

  # Some local mocks goto STG
  eval $CASTLEMOCK_PROJECT_ROOT_PATH"/castlemockfix.sh -d"

  build_occiauto_app
}

function print_info(){
  TYPE="$1"
  case $TYPE in
    header)
      echo "[INFO] ------------------------------------------------------------------------"
      ;;
    line)
      echo "[INFO] $2"
      ;;
    *)
      printf "[INFO] "
      ;;
  esac
}

function mvn_run(){
  MVN_PROJECT_PATH=$1
  MVN_CMD=$2
  cd $MVN_PROJECT_PATH
  print_info header
  print_info line "########################### Building $MVN_PROJECT_PATH - $MVN_CMD ###########################"
  mvn $MVN_CMD
}

function build_occiauto_app() {
  CUCUMBER_TAGS="\"-Dcucumber.options=--tags 'not @sample'\""
  mvn_run $AUTOMATION_API_PROJECT_ROOT_PATH "clean verify -P $AUTOMATION_API_PROJECT_PROFILE $CUCUMBER_TAGS" -Dboot.run.profiles=$AUTOMATION_API_PROJECT_PROFILE
  open "$AUTOMATION_API_REPORT_PATH"
  exit 0
}

function fnPrepareBackendForLocalToStageLambdas(){
  # Is needed use workinformation in local, because in STG they are in VPCE
  sed -i.bak -r "/JcFP3O|mareigua|soi|ael/! s/http:\/\/castlemock:8080\/castlemock\/mock\/rest\/project\/.{6}\/application\/.{6}\//https:\/\/j4dggvv1eg.execute-api.us-east-2.amazonaws.com\/stg\//" $BACKEND_PROJECT_ROOT_PATH/bocc-occiauto-starter/src/main/resources/application.yml
  sed -i.bak -r "s/castlemock:8080/bb-dev-alb-ecs-ext-487187305.us-east-2.elb.amazonaws.com/" $BACKEND_PROJECT_ROOT_PATH/bocc-occiauto-starter/src/main/resources/application.yml
  rm $BACKEND_PROJECT_ROOT_PATH/bocc-occiauto-starter/src/main/resources/application.yml.bak
  
  echo "Your local backend now is pointing to STG. Please restart your backend"
}

for ARG in $1; do
  case $ARG in
    -l|--local)
      AUTOMATION_API_PROJECT_PROFILE='local'
      fnRun
      ;;
    -m|--mixed)
      AUTOMATION_API_PROJECT_PROFILE='mixed'
      fnRun
      ;;
    -s|--stg)
      AUTOMATION_API_PROJECT_PROFILE='stg'
      fnRun
      ;;
    -r|--report)
      open target/site/serenity/index.html
      ;;
    -g|--gherkin-linter)
      node_modules/gherkin-lint/dist/main.js -c .gherkin-lintrc
      ;;
    -p|--prepare-backend-for-local-to-stage-lambdas)
      fnPrepareBackendForLocalToStageLambdas
      ;;
    *)
      echo "Unknown Argument $ARG"
      echo "Usage: sh runRobot.sh -l"
      exit 1
      ;;
  esac
done
