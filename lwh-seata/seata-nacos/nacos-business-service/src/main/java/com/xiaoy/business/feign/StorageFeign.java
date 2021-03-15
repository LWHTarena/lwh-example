package com.xiaoy.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liwh
 * @since 2021-3-2 16:51
 */
@FeignClient(name = "Storage-Service")
public interface StorageFeign {

    @PostMapping("/storage/subStorage/{commodityCode}")
    boolean subStorage(@PathVariable("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
