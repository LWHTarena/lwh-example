package com.lwhtarena.security.rbac.repository.support;

import org.springframework.core.convert.converter.Converter;

/**
 * @author liwh
 * @Title: Domain2InfoConverter
 * @Package com.lwhtarena.security.rbac.repository.support
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 21:27
 */
public interface Domain2InfoConverter<T, I> extends Converter<T, I> {

}
