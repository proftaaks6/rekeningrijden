echo Stop script started!
echo
echo RUNNING IN DEVELOPMENT
echo
docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml down --remove-orphans
