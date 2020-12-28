## JAVA代理模式

代理(Proxy)是一种设计模式,提供了对目标对象另外的访问方式;即通过代理对象访问目标对象.这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
这里使用到编程中的一个思想:不要随意去修改别人已经写好的代码或者方法,如果需改修改,可以通过代理的方式来扩展该方法

1.静态代理

* 可以做到在不修改目标对象的功能前提下,对目标功能扩展.
* 缺点:因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.

2.JDK动态代理

* 代理对象,不需要实现接口
* 代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
* 动态代理也叫做:JDK代理,接口代理
* 注意：代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理

3.Cglib代理  
以目标对象子类的方式类实现代理
* 也叫作子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展
* Cglib是一个强大的高性能的代码生成包,它可以在运行期扩展java类与实现java接口.它广泛的被许多AOP的框架使用,例如Spring AOP
  和synaop,为他们提供方法的interception(拦截)
* Cglib包的底层是通过使用一个小而块的字节码处理框架ASM来转换字节码并生成新的类.不鼓励直接使用ASM,因为它要求你必须对
  JVM内部结构包括class文件的格式和指令集都很熟悉.
 
 > 实现方式：
 * 需要引入cglib的jar文件
 * 代理的类不能为final,否则报错，因为cglib通过生成代理类的子类实现代理的
 * 目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法
 
 在Spring的AOP编程中:
 如果加入容器的目标对象有实现接口,用JDK代理
 如果目标对象没有实现接口,用Cglib代理
 
 ①远程代理(Remote Proxy) 给一个位于不同的地址空间的对象提供一个本地的代理对象，这个不同的地址空间可以是在同一台主机中，也可是在另一台主机中.
 
 ②虚拟代理(Virtual Proxy) 如果需要创建一个资源消耗较大的对象，先创建一个消耗相对较小的对象来表示，真实对象只在需要时才会被真正创建。
 
 ③保护代理(Protect Proxy) 控制对一个对象的访问，可以给不同的用户提供不同级别的使用权限。
 
 ④缓冲代理(Cache Proxy) 为某一个目标操作的结果提供临时的存储空间，以便多个客户端可以共享这些结果。
 
 ⑤智能引用代理(Smart Reference Proxy) 当一个对象被引用时，提供一些额外的操作，例如将对象被调用的次数记录下来等。
 
 ### AspectJ 
```text
    是一个基于 Java 语言的 AOP 框架。编译时增强的 AOP 框架，编译时，“自动编译”得到一个新类。
    AspectJ 是 Java 语言的一个 AOP 实现，其主要包括两个部分：第一个部分定义了如何表达、定义 AOP 编程中的
 语法规范，通过这套语言规范，我们可以方便地用 AOP 来解决 Java 语言中存在的交叉关注点问题；另一个部分是
 工具部分，包括编译器、调试工具等。
```
 
 ### Spring AOP
 
 ```text
    无需使用任何特殊命令对 Java 源代码进行编译，它采用运行时动态地、在内存中临时生成“代理类”的方式来生成 AOP 代理。
```

> 区别
    
    AspectJ 采用编译时生成 AOP 代理类，因此具有更好的性能，但需要使用特定的编译器进行处理；
    而 Spring AOP 则采用运行时生成 AOP 代理类，因此无需使用特定编译器进行处理。由于 Spring AOP 需要在每次运行时生成 
    AOP 代理，因此性能略差一些
 
 > 注意：
 
 1. 当 Spring 容器检测到某个 Bean 类使用了 @Aspect 标注之后，Spring 容器不会对该 Bean 类进行增强
 