package cn.hd.rout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * route 模型 topic
 */
@Component
public class TopicComsumer {


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = "user.*",
            exchange = @Exchange(name = "rout_topic_logs", type = "topic")
    ))
    public void getMQMessage1(String message) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TopicComsumer-1 get MQMessage=:" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = "user.#",
            exchange = @Exchange(name = "rout_topic_logs", type = "topic")
    ))
    public void getMQMessage2(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TopicComsumer-2 get MQMessage=:" + message);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = "#.user.*",
            exchange = @Exchange(name = "rout_topic_logs", type = "topic")
    ))
    public void getMQMessage3(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TopicComsumer-3 get MQMessage=:" + message);
    }
}
