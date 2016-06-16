package org.example.kafka.KafkaProject.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

    public class KafkaConsumer {
       private ConsumerConnector consumerConnector = null;
       private final String topic = "test";
       private List<Message> list = new ArrayList<Message>();
       private List<String> strList = new ArrayList<String>();
       public void initialize() {
             Properties props = new Properties();
             props.put("zookeeper.connect", "localhost:2181");
             props.put("group.id", "testgroup");
             props.put("zookeeper.session.timeout.ms", "400");
             props.put("zookeeper.sync.time.ms", "300");
             props.put("auto.commit.interval.ms", "1000");
             ConsumerConfig conConfig = new ConsumerConfig(props);
             consumerConnector = Consumer.createJavaConsumerConnector(conConfig);
       }

       public void consume() {
    	   System.out.println("Inside consume");
             //Key = topic name, Value = No. of threads for topic
             Map<String, Integer> topicCount = new HashMap<String, Integer>();       
             topicCount.put(topic, new Integer(1));
            
             //ConsumerConnector creates the message stream for each topic
             Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams =
                   consumerConnector.createMessageStreams(topicCount);         
             
             // Get Kafka stream for topic 'mytopic'
             List<KafkaStream<byte[], byte[]>> kStreamList =
                                                  consumerStreams.get(topic);
             // Iterate stream using ConsumerIterator
             for (final KafkaStream<byte[], byte[]> kStreams : kStreamList) {
                    ConsumerIterator<byte[], byte[]> consumerIte = kStreams.iterator();                   
                    while (consumerIte.hasNext())
                    {
                    	String str = new String(consumerIte.next().message());
                    	str = str.trim();
                    	strList.add(str);
                    	try{
                     		File file =new File("output.txt");
                     		//if file doesnt exists, then create it
                     		if(!file.exists()){
                     			file.createNewFile();
                     		}
                     		//true = append file
                     		FileWriter fileWritter = new FileWriter(file.getName(),true);
                     	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                     	        bufferWritter.write(str);
                     	        bufferWritter.close();
                     	}catch(IOException e){
                     		e.printStackTrace();
                     		System.out.println(e);
                     	}
                    	System.out.println(str);
                    }
             }
             //Shutdown the consumer connector
             if (consumerConnector != null)   consumerConnector.shutdown();          
       }
       
       public List<Message> getList() {
		return list;
	}
       public List<String> getstrList() {
    	   return strList;
       }

	public static void main(String[] args) throws InterruptedException {
             KafkaConsumer kafkaConsumer = new KafkaConsumer();
             // Configure Kafka consumer
             kafkaConsumer.initialize();
             // Start consumption
             kafkaConsumer.consume();
       }
   }