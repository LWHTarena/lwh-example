package com.lwhtarena.常见面试题;

/**
 * @author lwh
 * @folder com.lwhtarena.常见面试题
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

/**
 * 金额转换，阿拉伯数字的金额转换成中国传统的形式如：（￥11001111）－>>（一千零一拾   一元整）输出。
 *
 * 去零的代码：
 *  return sb.reverse().toString().replaceAll("零[拾佰仟]","零")
 *  .replaceAll("零 +万","万").replaceAll("零+元","元").replaceAll("零+","零");
 */
public class 金额转换 {

    private static final char[] data =
            new char[]{'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
    private static final char[] units =
            new char[]{ '元','拾','佰','仟','万','拾','佰','仟','亿' };
    public static void main(String[] args){
        System.out.println(convert(135689123));
    }

    private static String convert(int money) {
        StringBuffer sbf =new StringBuffer();
        int unit =0;
        while(money!=0){
            sbf.insert(0, units[unit++]);
            System.out.println(sbf.toString());
            int number =money%10;
            sbf.insert(0,data[number]);
            money/=10;
        }
        return sbf.toString();
    }

}
