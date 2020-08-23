package com.lwhtarena.jd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwh
 * @Title: Content
 * @Package com.lwhtarena.esapi.pojo
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/23 13:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    private String title;
    private String img;
    private String price;

    // 可以自行扩张
}
