package cn.kpic.juwin.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by bjsunqinwen on 2016/4/5.
 */
@Component
public class ReplyUpdQueueMessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("replyUpdQueue")
    private Destination destination;

    public void send(final Long replyId) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(replyId);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }
}
