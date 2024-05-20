# estimates-calculator

# Запуск среды окружения

1. Скачать и установить Docker Desktop, для вашей операционной системы [Docker-Desktop](https://www.docker.com/products/docker-desktop/)
2. Выполнить команду:
   (параметр -d запустит контейнер в фоновом режиме)
   docker run -it -d --name estimates_calculator -e POSTGRES_PASSWORD=estimates -e POSTGRES_USER=estimates -e POSTGRES_DB=estimates --network estimates -p 5432:5432 postgres:15.4

docker run -it -d --name estimates_calculator -e POSTGRES_PASSWORD=estimates -e POSTGRES_USER=estimates -e POSTGRES_DB=estimates -p 5432:5432 postgres:15.4
