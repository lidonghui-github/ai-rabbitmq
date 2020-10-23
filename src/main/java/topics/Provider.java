package topics;

import cn.hd.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 动态路由  消息生产者
 * time:2020-10-23  22:40:30
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topics","topic");
        String routKey="user.save";

        channel.basicPublish("topics",routKey,null,("topics，routKey:["+routKey+"]").getBytes());
        RabbitMQUtils.closeChannelAndConnection(channel,connection);
    }
}
