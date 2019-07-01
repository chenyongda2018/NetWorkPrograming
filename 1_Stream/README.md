## 第一章 流(Stream)  
![](http://img.it610.com/image/info5/9107a4f82d3f49888cde3f339517a19a.jpg)  


### OutputStream  
- flush() :  
 强迫把缓冲区中的数据发送出去，即使缓冲区还没被填满。在关闭流之前也要flush(),来刷新输出所有流，否则直接close()会导致流在缓冲区中的数据丢失。  
 
 
- close() :
在try-catch块外声明输出流的变量，在块内进行初始化，在finally中检查输出流是否为null然后close() 

```
OutputStream out = null;
try {
    out = new FileOutputStream("xxx.txt");
    
} catch(IOException e) {
    //处理异常
} finally {
    if (out != null) {
        try {
            out.close()\;
        } catch(IOException e) {
            //处理异常
        }
    }
} 
```  

Java 7之后也可以这样写 : 
```
try (OutputStream out = new FileOutputStream("xxx.txt")) {
    //处理输出流
}
catch(IOException e) {
    //处理异常
}    
```   



### InputStream  
- int read(byte[],int offset,int length) :   

只调用一次`read(...)` 方法可能会得不到全部的数据，所以要把read方法放在循环中。 返回-1代表读取结束
 
- int available()  
返回不堵塞的情况下可以读取多少数据  

- skip(long i)  
跳过一定数量的字节，通常用于重新指定文件指针位置。  

- mark(int i)  
在当前位置进行标记，配合reset()方法把流重置到之前标记的位置。能重置的位置跨度由 `readAheadLimit` 限制。一个流只能有一个标记  


- boolean markSupport()  
判断流是否支持mark。  


### 过滤器  
将原始数据作为字节处理。  
过滤器以链的形式组织，链中的每一个过滤器都接受前一个过滤器/流的数据,再传递给下一个环节。

![](http://ww1.sinaimg.cn/large/9c347cably1g4jc9poseaj20lf0mtqaj.jpg)    
链式调用:  
```
InputStream in = new FileInputStream("data.txt");
in = new BufferedInputStream(in);
```  

```
DataOutputStream dout = new DataOutputStream(
        new BufferedOutputStream(
        new FileOutputStream("data.txt")
        )
);
```  

### 缓冲流  
①. `BufferedOutputStream` 类将数据写入缓冲区中,知道缓冲区满或者flush()刷新输出缓冲区流。  
> 缓冲网络输出通常会带来巨大的性能提升。不过会因为网络传送数据的速度所限制。

 
②. `buf` 缓冲区:当 `BufferenInputStream` 调用 `read()` 方法时，首先尝试从缓冲区读取数据，缓冲区没有再从底层的数据源中读取尽可能多的数据到缓冲区。    
- BufferedOutputStream(InputStream in)  
- BufferedOutputStream(InputStream in,int bufferSize) :第二个参数指定缓冲区的大小。 
  
③ PrintStream  
![](https://ws1.sinaimg.cn/large/9c347cably1g4jj00xdh1j20lm0je7bf.jpg)  
`println()` 打印出的换行符在 UNIX下是`\n` ,在Mac下是`\n`或者`\r`,在Windows下是`\r\n`。  
> 避免在网络服务器中使用PrintStream。  


### 数据流  
![](https://ws1.sinaimg.cn/large/9c347cably1g4jj7jkcm9j20jy0890x1.jpg)  



### 阅读器与书写器
处理多种编码文本的特殊情况.  
`InputStreamReader`和`OutputStreamWriter` 可以读取数据的原始字节,通过构造参数传入指定的编码格式。  
,它们俩是基于字节的，内部使用一个字节数组当作缓冲区。  
`BufferedWriter` `BufferedReader`是基于字符的，内部使用字符数组当作缓冲区
![](https://ws1.sinaimg.cn/large/9c347cably1g4jjd2zqgij20m908pmzs.jpg)  





