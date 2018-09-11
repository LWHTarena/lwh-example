package com.lwhtarena.pxe.util.ebs;

/**
 * @author： liwh
 * @Date: 2017/3/27.
 * @Description：<p></P>
 */
public class TokenURI {
    private long deadline;
    private String uri;

    public TokenURI() {
    }

    public TokenURI(long deadline, String uri) {
        this.deadline = deadline;
        this.uri = uri;
    }

    public long getDeadlin() {
        return deadline;
    }

    public void setDeadlin(long deadlin) {
        this.deadline = deadlin;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "{\"deadline\":"+deadline+", \"uri\":\""+uri+"\"}";
    }
}
