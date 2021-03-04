# 测试工具使用

Mockito无法实现对静态函数、构造函数、私有函数、Final 等函数的模拟，
PowerMock是一个Java模拟框架，可用于解决通常被认为很难甚至无法测试的
测试问题。 使用PowerMock，可以模拟静态方法，删除静态初始化程序，允许
模拟而不依赖注入等等。 PowerMock通过在执行测试时在运行时修改字节代码
来完成这些技巧。

## Mockito

Mockito 是一个简单流行的 Mock 框架。它能够帮我们创建 Mock 对象，保持单元测试的独立性。

### Mock 的两个概念
- Mock 对象

模拟对象的概念就是我们想要创建一个可以替代实际对象的对象，这个模拟对象要可以通过特定参数调用特定的方法,并且能返回预期结果。

- Stub（桩）

桩指的是用来替换具体功能的程序段。桩程序可以用来模拟已有程序的行为或是对未完成开发程序的一种临时替代。也就是对调用方法的模拟。


## PowerMock

在做单元测试的时候，可能会用到Mockito来测试，但是Mockito也有不足，不能mock静态、final、私有方法等。而PowerMock能够完美的弥补Mockito的不足。

PowerMock使用一个自定义类加载器和字节码操作来模拟静态方法，构造函数，final类和方法，私有方法，去除静态初始化器等等。通过使用自定义的类加载器，简化采用的IDE或持续集成服务器不需要做任何改变。目前PowerMock支持EasyMock和Mockito。


## JUnit

什么是Junit5 ?
先看来个公式：

JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

这看上去比Junit4 复杂，实际上在导入包时也会复杂一些。

JUnit Platform是在JVM上启动测试框架的基础。

JUnit Jupiter是JUnit5扩展的新的编程模型和扩展模型，用来编写测试用例。Jupiter子项目为在平台上运行Jupiter的测试提供了一个TestEngine （测试引擎）。

JUnit Vintage提供了一个在平台上运行JUnit 3和JUnit 4的TestEngine 。

## JMockit