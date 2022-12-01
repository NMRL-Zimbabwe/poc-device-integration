echo "Building poc device integration app for production ....";
mvn clean install -DskipTests -Dspring.profiles.active=dev
docker-compose build pocdeviceintegration
docker-compose up -d pocdeviceintegration
echo "Done buiding poc device integration:)";
docker-compose ps
