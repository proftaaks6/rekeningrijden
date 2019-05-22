#!/usr/bin/env bash
echo Start script started!
echo
echo RUNNING IN DEVELOPMENT
echo
echo "##################"
echo "#### Please note: Make sure you have recompiled the sources. This script only instructs payara to load new classes"
echo "###################"
echo

#cp -Rf ../docker_samples/. ../DriverSystem/DriverApplication/
#cp -Rf ../docker_samples/. ../GovernmentSystem/GovernmentAdmin/
#cp -Rf ../docker_samples/. ../InvoiceSystem/InvoiceSystem/
#cp -Rf ../docker_samples/. ../MovementSystem/MovementProxy/
#cp -Rf ../docker_samples/. ../MovementSystem/MovementRegistrationService/
#cp -Rf ../docker_samples/. ../UserSystem/UserSystem/
#cp -Rf ../docker_samples/. ../DriverSystem/DriverApplication/
#cp -Rf ../docker_samples/. ../InvoiceSystem/InvoiceSystem/


docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml up -d db
docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml up -d rabbit_node_1

#echo
#echo "# building images, except the frontend ones"
#echo
#
#docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build movement_registration_service
#docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build movement_proxy
#docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build user_system
#docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build government_admin
#docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build driver_application
#docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml build invoice_system

#echo
#echo "# Sleep for 15 seconds"
#echo
#
#sleep 15

docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml up -d
