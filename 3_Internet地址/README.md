## 第三章 Internet地址 

 
1. 连接到Internet的设备称为节点 node.  

2. 计算机节点称为主机(host).  

3. ip地址用来唯一标识每个节点/主机.  

4. 4字节长的IP地址 -> IPV4地址.  
四段式,如 :`152.19.124.132`  .每个字节的范围0~255. 

5. 16字节长的IP地址 -> IPV6地址。  
以冒号为分割为8个区块,如 `2400:cb00:2018:1:0:0:6ca2:c665`.  

6. 由于ip地址不方便记忆，域名系统产生(DNS),将主机名与ip地址映射,一个主机名可能会关联多个ip地址，dns服务器负责随机选择一台机器来响应请求。  

7. 域名服务器(DNS)是运行着特殊DNS软件的UNIX主机。  


 ![](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561977411599&di=b3884ab9b40b5d727c82b5817e4ba70f&imgtype=0&src=http%3A%2F%2Fimg2.zol.com.cn%2Fproduct%2F15_450x337%2F414%2FceIZvfzozhNk.jpg)    

---  


### InetAddress类    

InetAddress包含了一个节点的ip和主机地址。

- 通过主机名获得InetAddress类  
```
InetAddress inetAddress = null;
try {
    inetAddress = InetAddress.getByName("www.oreilly.com");
    System.out.println(inetAddress.getHostAddress());
} catch (UnknownHostException e) {
    System.out.println("找不到地址");
}  


输出:  
23.79.167.40
```

- 也可传入ip地址,获得InetAddress对象

```
try {
    inetAddress = InetAddress.getByName("23.79.167.40");
    System.out.println(inetAddress.getHostName());
} catch (UnknownHostException e) {
    e.printStackTrace();
}  

输出:  
a23-79-167-40.deploy.static.akamaitechnologies.com
```  

- getLocalHost() 可以获得本地的节点对象.  

- DNS查找是一个开销比较大的操作,InetAddress会缓存已经查找过的记录。


- InetAddress的四个主要方法  
![](https://ws1.sinaimg.cn/large/9c347cably1g4keevom8pj20m20550ui.jpg)  

-  判断地址类型  
![](https://ws1.sinaimg.cn/large/9c347cably1g4kegygm50j20kz065tbn.jpg)  

- 判断可达性  
![](https://ws1.sinaimg.cn/large/9c347cably1g4kej7fuw8j20l602ut9i.jpg)  


- 如果两个InetAddress是同一个ip地址的示例，那么这两个对象相等 equals()方法为true.  



---  

### NetWorkInterface类  
标识本地的ip地址,是一个物理接口。  

api:  

- getByInetAddress(InetAddress inet)  

- getByName()  

- getNetWorkInterfaces() 获得本地所有网络接口，返回一个枚举类型    

![](https://ws1.sinaimg.cn/large/9c347cably1g4kes9pmj1j20ig05576g.jpg)  

 - getInertAddress() 获得这个接口绑定的多个ip地址  
 
 - getName()  根据 'eth0' 这样的名字返回某个特定的NetWorkInterface对象.  
 
 - getDisplayName()  
 
 
 

