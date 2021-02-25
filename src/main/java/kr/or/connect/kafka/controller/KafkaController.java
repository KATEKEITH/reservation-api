package kr.or.connect.kafka.controller;

import kr.or.connect.kafka.consumer.ConsumerWorker;
import kr.or.connect.kafka.producer.KafkaProducer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final KafkaProducer producer;

    private final ConsumerWorker worker;

    @Autowired
    KafkaController(KafkaProducer producer, ConsumerWorker worker) {
        this.producer = producer;
        this.worker = worker;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody String req) {
        this.producer.sendMessage(req);
        this.worker.run(req);
    }
}
