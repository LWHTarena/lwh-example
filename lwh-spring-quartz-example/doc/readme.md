# Spring  Quartz

1、scheduler是一个计划调度器容器，容器里面可以盛放众多的JobDetail和trigger，当容器启动后，里面的每个JobDetail都会根据trigger按部就班自动去执行。

2、JobDetail是一个可执行的工作，它本身可能是有状态的。

3、Trigger触发器代表一个调度参数的配置，什么时候去调。

4、当JobDetail和Trigger在scheduler容器上注册后，形成了装配好的作业（JobDetail和Trigger所组成的一对儿），就可以伴随容器启动而调度执行了。

5、scheduler是个容器，容器中有一个线程池，用来并行调度执行每个作业，这样可以提高容器效率。

