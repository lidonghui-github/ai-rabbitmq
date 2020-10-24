package cn.hd.rout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * route 模型 direct
 */
@Component
public class DirectComsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = "info",
            exchange = @Exchange(name = "rout_direct_logs", type = "direct")
    ))
    public void getMQMessage1(String message) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("DirectComsumer-1 get MQMessage=:" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = "error",
            exchange = @Exchange(name = "rout_direct_logs", type = "direct")
    ))
    public void getMQMessage2(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("DirectComsumer-2 get MQMessage=:" + message);
    }



    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = "warn",
            exchange = @Exchange(name = "rout_direct_logs", type = "direct")
    ))
    public void getMQMessage3(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("DirectComsumer-3 get MQMessage=:" + message);
    }
}
