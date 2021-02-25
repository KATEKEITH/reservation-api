package kr.or.connect.kafka.producer;

import kr.or.connect.dto.PageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    // @Value(value = "${message.topic.name}")
    private String TOPIC_NAME = "click-log";

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String req) {
        System.out.println(">>> Producer");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, req);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println(">>> onSuccess" + result);
                logger.info("Sent message= ]" + req);

            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("Message 전달 오류" + req);
            }

        });

    }

}
