echo Start script started!
echo
echo RUNNING IN DEVELOPMENT
echo
echo "##################"
echo "#### Please note: Make sure you have recompiled the sources. This script only instructs payara to load new classes"
echo "###################"
echo
docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml up -d db
sleep 20
docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml up --build -d
