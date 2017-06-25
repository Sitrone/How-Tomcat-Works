### 一个简单的```servlet```容器 
1. 等待```HTTP```请求 
2. 创建```ServletRequest```和```ServletResponse```对象 
3. 若请求静态资源则调用```StaticResourceProcessor```对象的```process()```方法，传入```ServletRequest```和```ServletResponse```对象 
4. 若请求```servlet```,则载入相应的```servlet```类，调用其```service()```方法，传入```ServletRequest```和```ServletResponse```对象   

只支持两种形式的uri: 
1. /servlet/servletName 
2. /staticResource