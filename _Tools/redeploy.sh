echo Redeploy script started!
echo
echo "##################"
echo "#### Please note: Make sure you have recompiled the sources. This script only instructs payara to load new classes"
echo "###################"
echo
id=`docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml ps -q $1`
case $1 in
    government_admin)
        target='GovernmentSystem/GovernmentAdmin/target/Rekeningrijden.war'
        ;;
    movement_registration_service)
        target='MovementSystem/MovementRegistrationService/target/Rekeningrijden.war'
        ;;
    movement_proxy)
        target='MovementSystem/MovementProxy/target/Rekeningrijden.war'
        ;;
    user_system)
            target='UserSystem/UserSystem/target/Rekeningrijden.war'
            ;;
    driver_application)
                target='DriverSystem/DriverApplication/target/Rekeningrijden.war'
                ;;
    invoice_system)
                target='InvoiceSystem/InvoiceSystem/target/Rekeningrijden.war'
                ;;

esac
target=../$target
echo `docker cp $target $id:/deploy.war`
echo $target has been copied over to the container
echo
echo Start redeploy command, you\'ll get a message when it\'s completed
docker-compose -f ../docker-compose.yml -f ../docker-compose.dev.yml exec -u 0 $1 /opt/payara/appserver/bin/asadmin  --passwordfile=/opt/payara/passwordFile  --interactive=false  deploy --force=true /deploy.war
echo Redeploy command has been executed