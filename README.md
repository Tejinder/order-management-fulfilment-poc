# order-management-fulfilment-poc


This project demonstrates the integration of Apache KAFKA with Spring Boot for the order management system.

Steps to run Kafka :

1. First we need to run APACHE KAFKA Instance. Here are the steps for the same, assuming JDK 1.8 is already configured on your system:

Download APACHE KAFKA from https://kafka.apache.org/download and extract the zip file. This Kafka installation comes with an inbuilt zookeeper. Zookeeper is mainly used to track status of nodes present in Kafka cluster and also to keep track of Kafka topics, messages, etc.

2. Open command prompt and go to the extracted folder and start the zookeeper C:\kafka_2.12-0.10.2.1>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

3. Open a new command prompt and start the Apache Kafka. C:\kafka_2.12-0.10.2.1>.\bin\windows\kafka-server-start.bat .\config\server.properties

4. Open a new command prompt and create 2 topics with name order-topic and order-status-topic, that has only one partition & one replica.

C:\kafka_2.12-0.10.2.1>.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-topic



C:\kafka_2.12-0.10.2.1>.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-status-topic

And use the below command to see all the topics created :

C:\kafka_2.12-0.10.2.1>.\bin\windows\kafka-topics.bat --list --zookeeper localhost:2181


There are 2 applications which I have created using Spring Boot and integrated with above Apache Kafka topics:

1. order-management :
	
	Steps to run : Run these maven commands :
		a. mvn clean install
		b. java -jar target/order-management-0.0.1-SNAPSHOT.jar
	
	-It will run the order management on port 8080. There is one REST web service created which will take the order details. The URL to invoke that is :http://localhost:8080/ordermanagement/producer?noOfBooks=2&bookName=OReilly&user=Tejinder&orderType=Book
	- It will send an Order using order-topic 
	
2. order-fulfillment :
	
	Steps to run : Run these maven commands :
		a. mvn clean install
		b. java -jar target/order-fulfillment-0.0.1-SNAPSHOT.jar
	
	-It will run the order fulfillment on port 8081. It is listening to the order-topic and when it gets a order message, it will create an order and sends the status back and the order details, by putting a message on order-status-topic. 
	- order-management application has the consumer for order-status-topic, using which it will get the updates on the order status.
