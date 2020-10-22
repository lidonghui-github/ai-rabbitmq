package workquene2;

import cn.hd.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class SecondCustomer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work2", true, false, false, null);

        channel.basicQos(1);
        channel.basicConsume("work2", false, new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-22: " + new String(body));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        });
    }
}
