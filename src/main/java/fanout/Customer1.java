package fanout;

import cn.hd.util.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //3.声明交换机  参数1 ：交换机名称    参数2：交换机类型   fanout 广播类型
        channel.exchangeDeclare("logs","fanout");

        //4.获取临时队列
        String queue = channel.queueDeclare().getQueue();
        //5.将队列与交换机绑定
        channel.queueBind(queue,"logs","");
        //6.消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("customer -1 :"+new String(body));
            }
        });
    }
}
