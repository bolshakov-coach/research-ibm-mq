package pro.bolshakov.research.ibmmq.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    @Value("${ibm.mq.queue}")
    private String queueName;

    @Value("${ibm.mq.queue2}")
    private String queueName2;

    private JmsTemplate jmsTemplate;

    public MainController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/send")
    public String pushMessage(){
        try{
            String message = "Hello with UUID " + UUID.randomUUID().toString();
            System.out.println("Going to put message -> " + message);
            jmsTemplate.convertAndSend(queueName, message);
            return "OK";
        }
        catch (JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }

    }

    @GetMapping("/pull")
    public String pullMessage(){
        try{
            Object message = jmsTemplate.receiveAndConvert(queueName);
            return message == null ? "NULL" : message.toString();
        }
        catch (JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }
    }

    @GetMapping("/send2")
    public String pushMessage2(){
        try{
            String message = "Second queue with UUID " + UUID.randomUUID().toString();
            System.out.println("Going to put message in 2 queue-> " + message);
            jmsTemplate.convertAndSend(queueName2, message);
            return "OK";
        }
        catch (JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }

    }



}
