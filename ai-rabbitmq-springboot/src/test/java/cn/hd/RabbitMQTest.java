package cn.hd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class RabbitMQTest {
    @Resource
    private RabbitTemplate rabbitTemplate;

    //1.测试 hello world 模型
    @Test
    public void testHelloModel() {
        System.out.println("rabbitTemplate=:" + rabbitTemplate);
        rabbitTemplate.convertAndSend("hello", "hello world");

    }

    //2.测试 work queue 模型
    @Test
    public void testWorkQueueModel() {
        System.out.println("rabbitTemplate=:" + rabbitTemplate);
        for (int k = 1; k <= 100; k++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rabbitTemplate.convertAndSend("work", "work queue message" + k);
        }
    }

    //3.测试 fanout 广播模型
    @Test
    public void testFanoutModel() {
        System.out.println("rabbitTemplate=:" + rabbitTemplate);
        for (int k = 1; k <= 100; k++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rabbitTemplate.convertAndSend("logs", "", "fanout message" + k);
        }
    }

    //4.测试 route 之 direct 路由模型
    @Test
    public void testRouteDirectModel() {
        System.out.println("rabbitTemplate=:" + rabbitTemplate);
        for (int k = 1; k <= 100; k++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] routs = {"info", "error", "warn"};
            double random = Math.random();
            int i = (int) (random * 10);
            if (i <= 3) {
                rabbitTemplate.convertAndSend("rout_direct_logs", routs[0], "RouteDirectModel message route=" + routs[0] + k);
            } else if (i <= 6) {
                rabbitTemplate.convertAndSend("rout_direct_logs", routs[1], "RouteDirectModel message route=" + routs[1] + k);
            } else {
                rabbitTemplate.convertAndSend("rout_direct_logs", routs[2], "RouteDirectModel message route=" + routs[2] + k);
            }
        }
    }

    //5.测试 route 之 topic 路由模型
    @Test
    public void testRouteTopicModel() {
        System.out.println("rabbitTemplate=:" + rabbitTemplate);
        for (int k = 1; k <= 100; k++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] routs = {"user.info", "user.error", "user.warn","user.save.delete"};
            double random = Math.random();
            int i = (int) (random * 10);
            if (i <= 3) {
                rabbitTemplate.convertAndSend("rout_topic_logs", routs[0], "RouteTopicModel message route=" + routs[0] + k);
            } else if (i <= 6) {
                rabbitTemplate.convertAndSend("rout_topic_logs", routs[1], "RouteTopicModel message route=" + routs[1] + k);
            } else if (i <= 8) {
                rabbitTemplate.convertAndSend("rout_topic_logs", routs[2], "RouteTopicModel message route=" + routs[2] + k);
            }else {
                rabbitTemplate.convertAndSend("rout_topic_logs", routs[3], "RouteTopicModel message route=" + routs[3] + k);

            }
        }
    }
}
