https://kafka.apache.org/downloads
	Scala 2.13  - kafka_2.13-3.9.1.tgz (asc, sha512)

Start Zookeeper and Kafka:
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties

Create a Kafka Topic
.\bin\windows\kafka-topics.bat --create --topic demo-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
