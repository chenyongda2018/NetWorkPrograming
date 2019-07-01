## 第四章 URL和URI  

`URL` 可以唯一标识一个资源再Internet上的位置。  
`URI` 是统一资源定位符,URL是最常见的URI.  


### URI(Uniform Resource Identifier)  
统一资源定位符,采用一种特定的语法标识一个资源的字符串.  
- URI语法:  

```
模式:模式特定的部分
```
![](https://ws1.sinaimg.cn/large/9c347cably1g4kfoel6z4j20ih0pl45h.jpg)  


很多资源都采用层次结构的模式:  
```
//authority/path?query
```  

### URL  
是URI的一种,不仅可以标识一个资源，也为这个资源提供了在网络中的位置.  



![](https://ws1.sinaimg.cn/large/9c347cably1g4kfslo7i6j20fq02h0t7.jpg)  

- 协议部分,如 file,ftp,http,https,magnet,telnet...  
- host部分:是提供所需资源的服务器的名字,可以是主机名,也可以是ip地址。  
- 用户信息(可选),是进入这个网站的登录信息.  
- 端口号(可选),通常有默认指定的端口号。   
- 路径:指向服务器上的一个特定的目录,类似文件系统的路径。  
- 查询:是向服务器提供的附加的参数,一半用于http url中表单提价时使用.  
- fragment:远程资源的某个特定的部分,相当于锚点。
如 :  
![](https://ws1.sinaimg.cn/large/9c347cably1g4kfzwey0ij20fq023t92.jpg)  

---

### URL类    
`java.net.URL`是一个final类,不能对其派生,不可变,线程安全。  

- 构造函数  

![](https://ws1.sinaimg.cn/large/9c347cably1g4kg5qrpfxj20kz047gna.jpg)  

根据你已知的信息选择其中一个构造函数,如果语法不正确,则抛出一个`MalformedURLException` 异常. 

- 通过字符串构造URL:  
![](https://ws1.sinaimg.cn/large/9c347cably1g4kg966rxrj20h603d3za.jpg)  


- 通过多个信息构造URL:  
![](https://ws1.sinaimg.cn/large/9c347cably1g4kgaoaxwkj20kz03jabc.jpg)  


### 从URL中获取数据  
api:  
![](https://ws1.sinaimg.cn/large/9c347cably1g4kh2u9xg8j20kf03l3zy.jpg)   

#### 1.openStream()   

调用URL的openStream()方法,可以与对应的网络服务器完成一次握手，返回一个`InputStream` ,通过这个输入流可以读取到URL指向的未经解释的数据.  

如下:放入一个京东页面的网址.  
![](https://ws1.sinaimg.cn/large/9c347cably1g4khkw9d51j212z0pawow.jpg)  


```
public static void main(String[] args) throws MalformedURLException {

    try {
        URL url = new URL("https://item.jd.com/10380592313.html");
        InputStream in = url.openStream();
        int c;
        while ((c = in.read())!= -1) {
            System.out.print((char)(c));
        }
        in.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
}
```


输出: 
```
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <!--new book-->
    <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
    <title>¡¶JavaÍøÂç±à³Ì(µÚ4°æ)¡·¡¾ÕªÒª ÊéÆÀ ÊÔ¶Á¡¿- ¾©¶«Í¼Êé</title>
    <meta name="keywords" content="JavaÍøÂç±à³Ì(µÚ4°æ),,ÖÐ¹úµçÁ¦³ö°æÉç,9787512361881,,ÔÚÏß¹ºÂò,ÕÛ¿Û,´òÕÛ"/>
    <meta name="description" content="¾©¶«JD.COMÍ¼ÊéÆµµÀÎªÄúÌá¹©¡¶JavaÍøÂç±à³Ì(µÚ4°æ)¡·ÔÚÏßÑ¡¹º£¬±¾Êé×÷Õß£º£¬³ö°æÉç£ºÖÐ¹úµçÁ¦³ö°æÉç¡£ÂòÍ¼Êé£¬µ½¾©¶«¡£Íø¹ºÍ¼Êé£¬ÏíÊÜ×îµÍÓÅ»ÝÕÛ¿Û!" />
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="mobile-agent" content="format=xhtml; url=//item.m.jd.com/product/10380592313.html">
    <meta http-equiv="mobile-agent" content="format=html5; url=//item.m.jd.com/product/10380592313.html">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    ...
    省略..
```  

可以看到其中有乱码,我们以gbk格式再次读取这个资源:  
```
public static void main(String[] args) throws MalformedURLException {

        InputStream in = null;

        try {
            URL url = new URL("https://item.jd.com/10380592313.html");
            in = url.openStream();
            in = new BufferedInputStream(in);
            Reader reader = new InputStreamReader(in,"gbk");
            int c = 0;
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
```  

输出: 
```
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <!--new book-->
    <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
    <title>《Java网络编程(第4版)》【摘要 书评 试读】- 京东图书</title>
    <meta name="keywords" content="Java网络编程(第4版),,中国电力出版社,9787512361881,,在线购买,折扣,打折"/>
    <meta name="description" content="京东JD.COM图书频道为您提供《Java网络编程(第4版)》在线选购，本书作者：，出版社：中国电力出版社。买图书，到京东。网购图书，享受最低优惠折扣!" />
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="mobile-agent" content="format=xhtml; url=//item.m.jd.com/product/10380592313.html">
    <meta http-equiv="mobile-agent" content="format=html5; url=//item.m.jd.com/product/10380592313.html">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">  
    
    //省略...
```  


可以看到文字可以正常地显示。





