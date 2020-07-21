package com.lwhtarena.security.core.social.view;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author liwh
 * @Title: ImoocConnectView
 * @Package com.lwhtarena.security.core.social.view
 * @Description: 绑定结果视图
 * @Version 1.0.0
 * @date 2020/7/20 17:09
 */
public class ImoocConnectView extends AbstractView {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
     * (java.util.Map, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        if (model.get("connections") == null) {
            response.getWriter().write("<h3>解绑成功</h3>");
        } else {
            response.getWriter().write("<h3>绑定成功</h3>");
        }

    }

}

