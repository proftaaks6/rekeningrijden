echo Stop script started!
echo
echo RUNNING IN DEVELOPMENT
echo
docker-compose -f "../docker-compose.yml" -f "../docker-compose.dev.yml" build $1
docker-compose -f "../docker-compose.yml" -f "../docker-compose.dev.yml" up --no-deps -d $1
