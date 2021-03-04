# JAVA8 新语法

## Lambda表达式和函数接口（Functional Interface）
``` java

    public static void DemoLambda() {
        // 不使用lambda表达式
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("do not use lambda expressions!!");
            }
        };
        runnable1.run();
        
        
        // 使用lambda表达式
        Runnable runnable2 = () -> {
            System.out.println("use lambda expressions!!");
        };
        runnable2.run();
    }
    
    /**
    *可以看到使用了lambda表达式后代码简洁了很多。lambda表达式实际就是匿名方法，他由两部分构成：
      参数和方法体——方法名和返回类型（通过函数体来推断）给省略了。例子中的参数为空，所以给了一个
      “()”代表参数为空；“->”代表使用了lambda表达式（c#中使用=>）；“{}”中的内容为方法体，如果
      只有一句代码可以省略掉“{}”，和for循环一样。
      
      另一个是函数接口。表示只有一个方法的接口就叫函数接口，可以使用注解 @FunctionalInterface
      来标识这个接口是函数接口，但不是必须的。比如 Runnable 这个接口就是函数接口（只有一个方法）
      ，所以lambda表达式所实现的方法就是run方法。
    */

```

## 方法引用

方法引用就是lambda的一种简化写法，相当于把方法当成一个变量赋值给另一个方法，这个方法接受
的参数是一个函数接口，他有3中实现：

+ 1. 方法是static的：类名::方法名，例如：Math::sin

+ 2. 方法不是static的：对象::方法名，例如：

`StringBuilder sb = new StringBuilder(); sb::append`

+ 3. 构造方法：类名::new

```java

public class MethodDemo {
    // 这个方法接受一个函数式接口
    public static void HasFunctionalInterface(FunctionalInterfaceDemo fid) {
        fid.doSoming("运行在：");
    }
    
    // 3中实现方法
    public static void main(String[] args) {
        // 第一种实现：new一个匿名的对象
        HasFunctionalInterface(new FunctionalInterfaceDemo() {
            @Override
            public void doSoming(String str) {
                System.out.println(str + "以前的实现");
            }
        });
        
        // 第二种实现：使用lambda表达式
        // e 是参数，System.out.println("运行在:lambda表达式"); 是方法体
        HasFunctionalInterface(e -> { System.out.println("运行在:lambda表达式"); });
        
        // 第三种实现：使用方法引用
        FunctionalInterfaceImplements fif = new FunctionalInterfaceImplements();
        HasFunctionalInterface(fif::doSoming);
    }
}


// 实现了函数式接口
class FunctionalInterfaceImplements implements FunctionalInterfaceDemo {
    public void doSoming(String str) {
        System.out.println(str + "方法的引用");
    }
}


// 一个函数式接口
@FunctionalInterface
interface FunctionalInterfaceDemo {
    void doSoming(String str);
}
```