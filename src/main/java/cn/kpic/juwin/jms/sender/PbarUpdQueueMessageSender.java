package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by bjsunqinwen on 2016/4/9.
 */
@Component
public class PbarUpdQueueMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("pbarUpdQueue")
    private Destination destination;

    public void send(final JmsUpdPbar jmsUpdPbar) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(jmsUpdPbar);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }

}
