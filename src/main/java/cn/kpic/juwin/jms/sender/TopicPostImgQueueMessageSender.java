package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.vo.JmsTopicImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by bjsunqinwen on 2016/3/10.
 */
@Component
public class TopicPostImgQueueMessageSender  {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("topicPostImgQueue")
    private Destination destination;

    public void send(final JmsTopicImg jmsTopicImg) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(jmsTopicImg);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }
}
