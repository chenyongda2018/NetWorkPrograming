## 第二章 流(Stream)  

## OutputStream  
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



## InputStream  
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


## 过滤器  
将原始数据作为字节处理。  

![](http://ww1.sinaimg.cn/large/9c347cably1g4jc9poseaj20lf0mtqaj.jpg)  


## 阅读器  
处理多种编码文本的特殊情况




