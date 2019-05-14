echo Reload Config script started!
docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build $1
docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml up --no-deps -d $1
