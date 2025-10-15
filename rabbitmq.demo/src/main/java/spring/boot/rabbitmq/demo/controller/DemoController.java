package spring.boot.rabbitmq.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.rabbitmq.demo.model.Person;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/person/{name}")
    public String getPerson(@PathVariable("name") String name) {
        Person person = new Person(1L, name);
        rabbitTemplate.convertAndSend("Mobile", person);
//        rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", person);
//        rabbitTemplate.convertAndSend("Fanout-Exchange", "", person);
//        rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person);

        return "success";
    }
}
