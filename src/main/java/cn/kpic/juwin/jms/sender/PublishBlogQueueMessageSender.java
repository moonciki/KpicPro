package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.vo.JmsPublishBlog;
import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by Administrator on 2016/5/1 0001.
 */
@Component
public class PublishBlogQueueMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("publishBlogQueue")
    private Destination destination;

    public void send(final JmsPublishBlog jmsPublishBlog) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(jmsPublishBlog);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }

}
