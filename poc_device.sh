echo "Building Central Repository app for production ....";
mvn clean install -DskipTests -Dspring.profiles.active=prod
docker-compose build centralrepo
docker-compose up -d centralrepo
echo "Done buiding central repo :)";
docker-compose ps
