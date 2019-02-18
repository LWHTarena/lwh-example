package com.lwhtarena.jpa.service;

import com.lwhtarena.jpa.domain.User;
import com.lwhtarena.jpa.domain.Weibo;
import com.lwhtarena.jpa.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
@Service
public class WeiboService {

    @Autowired
    private WeiboRepository weiboRepository;

    @Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED)
    public List<Weibo> importWeiboList(List<Weibo> weibos, User user){
        int index = 0;
        Date nowDateTime = new Date(System.currentTimeMillis());
        for (Weibo weiboItem: weibos) {
            weiboItem.setUser(user);
            weiboItem.setCreateDate(nowDateTime);
            if(5<=index++){
                throw new RuntimeException("Weibo out of limit!!!");
            }
            this.weiboRepository.save(weiboItem);
        }
        return weibos;
    }
}
