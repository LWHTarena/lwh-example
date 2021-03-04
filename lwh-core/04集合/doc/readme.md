# 容器

## Collection 的概念

    Collection 表示一组对象，函数库是在java.util包下的一些接口和类，类是用来产生对象存放数据用的，而接口是访问数据的方式。
    
Collection函数库与数组的两点不同：

1、数组的容量是有限制的，而collection库没有这样的限制，它容量可以自动调节。

2、collection函数库只能用来存放对象，而数组没有这样的限制。

Collection 接口：定义了存放一组对象的方法，其子接口Set和List分别定义了存储方式。
   
  - Set 中的数据对象没有顺序且不可以重复(无序不重复)
  
    - HashSet
    - TreeSet
    
  - List 中的数据对象有顺序且可以重复(有序可重复)
  
    - ArrayList：底层实现数组，线程不安全，效率高。所以，查询快。修改、插入、删除慢。
    - LinkedList: 底层实现是链表，线程不安全，效率高。所以，查询慢。修改、插入、删除快。
    - Vector：线程安全的，效率低
  
Map 接口定义了存储“键（key）—— 值（value）映射对”的方法

   - HashMap
   - TreeMap