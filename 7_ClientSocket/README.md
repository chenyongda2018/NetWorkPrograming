# 第七章 客户端Socket  


> 网络上的数据以大小有限的包的形式传输,称为数据报(Datagram),一个数据报吧包含一个首部(header),一个有效载荷(payload).  
> header :包含目的地,起始地的地址和端口,检测数据是否被破坏的检验和,以及用于保证可靠传输的其他信息。  
> payload:包含着数据本身。

> 通常数据报在传输时需要经过分解，再在目的地进行组合的过程。  

### 一,  使用Socket
Socket:是两台主机之间的一个全双工的连接，它可以完成的操作有:  

![](https://ws1.sinaimg.cn/large/9c347cably1g4lk4hqjhej20cz0egacc.jpg)  

客户端的Socket只会用到前4个方法，服务端的Socket能够用到全部7个方法。由`ServerSocket`实现。



### 二,  用Telnet研究协议


### 三,  构造和连接Socket  
Socket的构造函数 : 
```
Socket(String host,int port) throws UnknownHostException ,IOException
Socket(InetAddress host,int port) throws IOException
```  


### 四,  设置Socket选项


### 五,  Socket异常


### 六,  GUI中应用Socket

