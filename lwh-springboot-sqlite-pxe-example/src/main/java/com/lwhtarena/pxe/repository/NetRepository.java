package com.lwhtarena.pxe.repository;

import com.lwhtarena.pxe.domain.Net;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

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
public interface NetRepository extends Repository<Net, String> {

    Page<Net> findAll(Pageable pageable);


}
