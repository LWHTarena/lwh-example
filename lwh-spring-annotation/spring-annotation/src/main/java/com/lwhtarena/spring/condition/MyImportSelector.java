package com.lwhtarena.spring.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liwh
 * @Title: MyImportSelector
 * @Package com.lwhtarena.spring.condition
 * @Description: 自定义逻辑返回需要导入的组件
 * @Version 1.0.0
 * @date 2020/5/22 14:32
 */
public class MyImportSelector implements ImportSelector {
    /**
     * 返回值，就是到导入到容器中的组件全类名
     *
     * AnnotationMetadata: 当前标注@Import注解的类的所有注解信息
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // TODO Auto-generated method stub
        //importingClassMetadata
        //方法不要返回null值
        return new String[]{"com.lwhtarena.spring.bean.Blue","com.lwhtarena.spring.bean.Yellow"};
    }
}
