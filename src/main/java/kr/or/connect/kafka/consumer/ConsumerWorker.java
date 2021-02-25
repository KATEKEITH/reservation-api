package kr.or.connect.kafka.consumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ConsumerWorker {

    private static final Logger log = LoggerFactory.getLogger(ConsumerWorker.class);

    // private static List<Object> workerThreads = new ArrayList<>();

    // private Map<String, Object> prop;
    // private String topic;
    // private String threadName;
    // // private KafkaConsumer<String, String> consumer;

    // public ConsumerWorker(Map<String, Object> prop, String topic, int number) {
    // this.prop = prop;
    // this.topic = topic;
    // this.threadName = "consumer-thread" + number;
    // }

    // private static final Logger logger =
    // LoggerFactory.getLogger(KafkaConsumer.class);

    // public void consume(String req) {
    // System.out.println(">>> test" + req);
    // logger.info(String.format("$$$ => Consumed message: %s", req));
    // }

    // @Override
    @KafkaListener(topics = "click-log", groupId = "log-consumer")
    public void run(String messages) {
        System.out.println(">>> ConsumerWorker:run" + messages);

        log.info("Kafka Consumer Listening");

        // ExecutorService executorService = Executors.newCachedThreadPool();

        // for (int i = 0; i < 5; i++) {
        // ConsumerWorker worker = new ConsumerWorker(configProps, TOPIC_NAME, i);
        // workerThreads.add(messages);
        // executorService.execute((Runnable) messages);

        // consumer = new KafkaConsumer<>(prop);
        // consumer.subscribe(Arrays.asList(topic));
        String filePath = "C:/Users/kate/click-log.txt";

        try {
            // while (true) {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            System.out.println("1" + fw);
            StringBuilder fileWriteBuffer = new StringBuilder();

            // ConsumerRecords<String, String> records =
            // consumer.poll(Duration.ofSeconds(1));
            // ConsumerRecords<String, String> records = message;

            // for (Object message : messages) {
            fileWriteBuffer.append(messages).append("\n");
            fw.write(fileWriteBuffer.toString());
            // }
            System.out.println("2" + fw);
            // consumer.commitSync();
            fw.flush();
            fw.close();
            // }
        } catch (IOException e) {
            System.err.println(" IOException" + e);
        } catch (WakeupException e) {
            System.out.println(" WakeupException");
        }
        // } finally {
        // consumer.commitSync();
        // consumer.close();
        // }
        // }
    }

    // public void shutdown() {
    // consumer.wakeup();
    // }
}
