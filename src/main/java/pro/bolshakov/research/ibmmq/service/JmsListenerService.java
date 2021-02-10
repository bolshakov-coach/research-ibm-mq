package pro.bolshakov.research.ibmmq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsListenerService {

    @JmsListener(destination = "${ibm.mq.queue2}")
    public void receiveMessage(String message){
        System.out.println("Listener got a message -> " + message);
    }

}
