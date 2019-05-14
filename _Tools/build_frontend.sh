echo Frontend script started!
echo
echo RUNNING IN DEVELOPMENT
echo

echo Building frontend images
echo

docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build government_frontend
docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build driver_frontend


echo Done!