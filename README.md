# estimates-calculator

Скачать и установить Docker Desktop, для вашей операционной системы Docker-Desktop
Выполнить команду: (параметр -d запустит контейнер в фоновом режиме):
docker run -it -d --name estimates_calculator -e POSTGRES_PASSWORD=estimates -e POSTGRES_USER=estimates -e POSTGRES_DB=estimates -p 5432:5432 postgres:15.4