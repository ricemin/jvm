# 走进java
## 什么是虚拟机
java 虚拟机是可以执行java字节码的虚拟机程序，多平台兼容的关键，一次编译，多平台运行
## jvm由哪些部分组成
### 类加载器
在jvm启动或者类运行时将需要的class 加载到jvm中
### 运行时内存区域
将内存划分为若干个区以模拟实际物理机的存储，记录/调度模块
#### 内存区域划分与作用
<table>
<tr>
<td>模块</td>
<td>程序计数器</td>
<td>虚拟机栈</td>
<td>堆</td>
<td>方法区</td>
<td>元数据区</td>
</tr>
<tr>
<td>作用</td>
<td>线程私有，字节码的行号指示器</td>
<td>线程私有，存储基本数据类型变量，<br/>引用数据类型的引用变量，栈信息</td>
<td>线程不安全，java中大部分对象都分配在堆中，引用数据类型的变量信息</td>
<td>jdk1.7之前方法区实现为堆中永久带</td>
<td>jdk1.7之后方法区实现为元数据去，直接内存，运行时常量池还在堆中</td>
</tr>
<tr>
<td>内存溢出</td>
<td>不会发生内存溢出</td>
<td>栈内存溢出，由于栈深度过深引起</td>
<td>堆内存溢出，最常见内存溢出 heap space</td>
<td>方法区内存溢出，一般由于动态代理引起 perspace?</td>
<td>元数据区是否还存在内存溢出？</td>
</tr>
<tr>
<td>垃圾回收方式</td>
<td>需要垃圾回收吗？</td>
<td>需要垃圾回收吗？</td>
<td>新生代：标记-复制-清除，老年代：标记-清除/标记-整理-清除</td>
<td>方法区垃圾回收方式</td>
<td>元数据区如何进行垃圾回收？</td>
</tr>
</table>

### 执行引擎
执行class文件中的字节码指令
### 本地接口
调用c或c++的本地接口并返回
