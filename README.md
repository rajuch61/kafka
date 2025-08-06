https://kafka.apache.org/downloads
	Scala 2.13  - kafka_2.13-3.9.1.tgz (asc, sha512)

Start Zookeeper and Kafka:
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties

Create a Kafka Topic
.\bin\windows\kafka-topics.bat --create --topic demo-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092


<!-- Spring Kafka -->
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>

<!-- AWS SDK v2 - Kafka -->
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>kafka</artifactId>
</dependency>

    public String getBootstrapServers() {
        try (KafkaClient kafkaClient = KafkaClient.create()) {
            GetBootstrapBrokersRequest request = GetBootstrapBrokersRequest.builder()
                    .clusterArn(clusterArn)
                    .build();

            GetBootstrapBrokersResponse response = kafkaClient.getBootstrapBrokers(request);
            return response.bootstrapBrokerStringSaslIam(); // IAM auth port 9098
        }
    }

    String bootstrapServers = getBootstrapServers();
