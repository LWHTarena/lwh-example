package com.lwhtarena.jpa.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@Entity
@Table(name = "weibo")
public class Weibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long weiboId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "weibo_text")
    private String weiboText;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "weibo")
    private Set<Comment> comments;

    public Weibo() {
    }

    public Weibo(User user, String weiboText, Date createDate) {
        this.user = user;
        this.weiboText = weiboText;
        this.createDate = createDate;
    }


    public long getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(long weiboId) {
        this.weiboId = weiboId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWeiboText() {
        return weiboText;
    }

    public void setWeiboText(String weiboText) {
        this.weiboText = weiboText;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
