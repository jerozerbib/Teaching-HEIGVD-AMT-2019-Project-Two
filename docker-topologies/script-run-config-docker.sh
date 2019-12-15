run ../docker-images/user-manager-server/build-docker-images.sh

docker-compose down
docker-compose up --build
