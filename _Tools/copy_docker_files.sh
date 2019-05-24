echo Start script started!
echo

cp -Rf ../docker_samples/. ../DriverSystem/DriverApplication/
cp -Rf ../docker_samples/. ../GovernmentSystem/GovernmentAdmin/
cp -Rf ../docker_samples/. ../InvoiceSystem/InvoiceSystem/
cp -Rf ../docker_samples/. ../MovementSystem/MovementProxy/
cp -Rf ../docker_samples/. ../MovementSystem/MovementRegistrationService/
cp -Rf ../docker_samples/. ../UserSystem/UserSystem/
cp -Rf ../docker_samples/. ../DriverSystem/DriverApplication/

sed -i 's/_DATABASE_/driverapplicationdatabase/g' ../DriverSystem/DriverApplication/domain.xml
sed -i 's/_DATABASE_/governmentdatabase/g' ../GovernmentSystem/GovernmentAdmin/domain.xml
sed -i 's/_DATABASE_/invoicedatabase/g' ../InvoiceSystem/InvoiceSystem/domain.xml
sed -i 's/_DATABASE_/invaliddatadatabase/g' ../MovementSystem/MovementProxy/domain.xml
sed -i 's/_DATABASE_/movementdatabase/g' ../MovementSystem/MovementRegistrationService/domain.xml
sed -i 's/_DATABASE_/userdatabase/g' ../UserSystem/UserSystem/domain.xml
sed -i 's/_DATABASE_/driverapplicationdatabase/g' ../DriverSystem/DriverApplication/domain.xml
