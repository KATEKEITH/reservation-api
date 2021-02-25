package kr.or.connect.kafka.consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import kr.or.connect.kafka.consumer.ConsumerWorker;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private static String TOPIC_NAME = "click-log";
    private String GROUP_ID = "log-consumer";
    private String BOOTSTRAP_SERVERS = "localhost:9092";
    private static int CONSUMER_COUNT = 5;

    private ConsumerWorker cWorker;

    // @Autowired
    // private ConsumerWorker worker;

    @Bean
    public ConsumerFactory<String, Object> logConsumerFactory() {
        System.out.println(">>> ConsumerFactory");
        // Runtime.getRuntime().addShutdownHook(new ShutdownThread());

        Map<String, Object> configProps = new HashMap<>();
        // Properties configProps = new Properties();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        System.out.println("ConcurrentKafkaListenerContainerFactory");
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(logConsumerFactory());
        return factory;
    }

    // static class ShutdownThread extends Thread {
    // public void run() {
    // workerThreads.forEach(ConsumerWorker::shutdown);
    // System.out.println("Bye");
    // }
    // }
}
