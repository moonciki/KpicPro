package cn.kpic.juwin.domain.vo;

/**
 * Created by Administrator on 2016/5/1 0001.
 */
public class JmsPublishBlog implements java.io.Serializable {

    private Long pbarId;

    private Long blogId;

    public Long getPbarId() {
        return pbarId;
    }

    public void setPbarId(Long pbarId) {
        this.pbarId = pbarId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
