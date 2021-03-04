# 数组

## 字符串

- 可变字符序列

    - StringBuilder(线程不安全，效率高)
    - StringBuffer(线程安全，效率低)
    
- String 不可变字符串序列

## 自动装箱和自动拆箱

`Integer a =1000;//jdk5.0之后，自动封箱，编译器帮助我们改进代码：Integer a =new Integer(1000);`

`int c =new Integer(1500);//自动拆箱 编译器改进：new Integer(1500).intValue();`

## 数组的基本概念

- 一维数组的声明方式有两种：

    `type[] arr_name;`
    
    `type arr_name[];`
    
- 二维数组