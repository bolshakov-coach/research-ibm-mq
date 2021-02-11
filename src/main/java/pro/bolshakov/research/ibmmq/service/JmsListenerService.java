package pro.bolshakov.research.ibmmq.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "listen.jms", havingValue = "true")
public class JmsListenerService {

    public JmsListenerService() {
        System.out.println("JmsListenerService created");
    }

    @JmsListener(destination = "${ibm.mq.queue2}")
    public void receiveMessage(String message){
        System.out.println("Listener got a message -> " + message);
    }

}
