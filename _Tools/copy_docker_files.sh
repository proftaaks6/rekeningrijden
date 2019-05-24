echo Start script started!
echo

cp -Rf ../docker_samples/. ../DriverSystem/DriverApplication/
cp -Rf ../docker_samples/. ../GovernmentSystem/GovernmentAdmin/
cp -Rf ../docker_samples/. ../InvoiceSystem/InvoiceSystem/
cp -Rf ../docker_samples/. ../MovementSystem/MovementProxy/
cp -Rf ../docker_samples/. ../MovementSystem/MovementRegistrationService/
cp -Rf ../docker_samples/. ../UserSystem/UserSystem/
cp -Rf ../docker_samples/. ../DriverSystem/DriverApplication/

cd ../DriverSystem/DriverApplication/ && sed -i '' 's/_DATABASE_/driverapplicationdatabase/g' domain.xml && cd ../../_Tools
cd ../GovernmentSystem/GovernmentAdmin/ && sed -i '' 's/_DATABASE_/governmentdatabase/g' domain.xml && cd ../../_Tools
cd ../InvoiceSystem/InvoiceSystem/ && sed -i '' 's/_DATABASE_/invoicedatabase/g' domain.xml && cd ../../_Tools
cd ../MovementSystem/MovementProxy/ && sed -i '' 's/_DATABASE_/invaliddatadatabase/g' domain.xml && cd ../../_Tools
cd ../MovementSystem/MovementRegistrationService/ && sed -i '' 's/_DATABASE_/movementdatabase/g' domain.xml && cd ../../_Tools
cd ../UserSystem/UserSystem/ && sed -i '' 's/_DATABASE_/userdatabase/g' domain.xml && cd ../../_Tools
cd ../DriverSystem/DriverApplication/ && sed -i '' 's/_DATABASE_/driverapplicationdatabase/g' domain.xml && cd ../../_Tools
