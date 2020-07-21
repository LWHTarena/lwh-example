package com.lwhtarena.security.browser.session;

import com.lwhtarena.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liwh
 * @Title: ImoocInvalidSessionStrategy
 * @Package com.lwhtarena.security.browser.session
 * @Description: 默认的session失效处理策略
 * @Version 1.0.0
 * @date 2020/7/20 22:12
 */
public class ImoocInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public ImoocInvalidSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        onSessionInvalid(request, response);
    }

}
