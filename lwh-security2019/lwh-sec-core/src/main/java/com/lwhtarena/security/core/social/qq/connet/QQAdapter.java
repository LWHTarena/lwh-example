package com.lwhtarena.security.core.social.qq.connet;

import com.lwhtarena.security.core.social.qq.api.QQ;
import com.lwhtarena.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author liwh
 * @Title: QQAdapter
 * @Package com.lwhtarena.security.core.social.qq.connet
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 16:48
 */
public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do noting
    }

}
