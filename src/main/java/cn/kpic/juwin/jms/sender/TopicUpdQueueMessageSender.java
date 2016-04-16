package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.vo.JmsTopicImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by Administrator on 2016/3/19 0019.
 */
@Component
public class TopicUpdQueueMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("topicUpdQueue")
    private Destination destination;

    public void send(final Long id) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(id);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }

}
