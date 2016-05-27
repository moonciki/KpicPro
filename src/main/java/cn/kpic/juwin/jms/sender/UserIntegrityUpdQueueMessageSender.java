package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.vo.JmsUserIntegrityUpd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
@Component
public class UserIntegrityUpdQueueMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("userIntegrityUpdQueue")
    private Destination destination;

    public void send(final JmsUserIntegrityUpd jmsUserIntegrityUpd) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(jmsUserIntegrityUpd);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }

}
