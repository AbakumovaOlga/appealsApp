# appealsApp


Возможности:

	admin может создавать manager и employee

	employee может создать appeal
	employee может удалить appeal, если appeal.status==new

	manager может получить список appeals
	manager может утвердить appeal - отправка в кафку
	manager может отклонить appeal - отправка в рэббит
	

Раз 3мин посылать на почту manager кол-во appeals where appeal.status==new

Swagger - http://localhost:8080/swagger-ui.html


https://github.com/AbakumovaOlga/appealsApp_docker

docker-compose up -d


Kafka topics - http://localhost:8088/kafka-ui/ui/clusters/TEST/topics

Rabbit - http://localhost:15672/#/
