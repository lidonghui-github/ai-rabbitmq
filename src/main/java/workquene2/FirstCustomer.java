package workquene2;

import cn.hd.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class FirstCustomer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work2", true, false, false, null);

        //每次只获取一条信息进行处理
        channel.basicQos(1);
        //参数2：false 不在自动确认
        channel.basicConsume("work2", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-11: " + new String(body));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //第二个参数： false 不同时确认多条消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }

        });
    }
}
