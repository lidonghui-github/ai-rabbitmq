package cn.hd.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * hello world 直连模型
 */
@Component
@RabbitListener(queuesToDeclare =@Queue("hello"))
public class HelloConsumer {

    @RabbitHandler
    public void getMQMessage(String message){
        System.out.println("HelloConsumer get MQMessage=:"+message);
    }

}
