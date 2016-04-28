package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/4/27.
 */
public class JmsSystemMsg implements java.io.Serializable {

    private String title;

    private String content;

    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
