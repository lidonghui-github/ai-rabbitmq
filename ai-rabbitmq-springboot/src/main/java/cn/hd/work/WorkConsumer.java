package cn.hd.work;


import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * work queue 平均消费模型
 */
@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void getMQMessage1(String message) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("WorkConsumer-1 get MQMessage=:" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void getMQMessage2(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("WorkConsumer-2 get MQMessage=:" + message);
    }
}
