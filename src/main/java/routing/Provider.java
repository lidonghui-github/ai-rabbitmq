package routing;

import cn.hd.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * routing模型的消息生产者
 * time:2020-10-23 22:02:30
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //1.获取连接
        Connection connection = RabbitMQUtils.getConnection();
        //2.获取通道
        Channel channel = connection.createChannel();
        //3.声明交换机
        channel.exchangeDeclare("logs_direct","direct");
        String routingKey="info";

        //4.发布消息
        channel.basicPublish("logs_direct",routingKey,null,"logs_direct message".getBytes());
        //5.关闭资源
        RabbitMQUtils.closeChannelAndConnection(channel,connection);
    }
}
