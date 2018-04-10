# uac-titan
```text
|\  \|\  \|\   __  \|\   ____\             |\___   ___\\  \|\___   ___\\   __  \|\   ___  \
\ \  \\\  \ \  \|\  \ \  \___|  ___________\|___ \  \_\ \  \|___ \  \_\ \  \|\  \ \  \\ \  \
 \ \  \\\  \ \   __  \ \  \    |\____________\  \ \  \ \ \  \   \ \  \ \ \   __  \ \  \\ \  \
  \ \  \\\  \ \  \ \  \ \  \___\|____________|   \ \  \ \ \  \   \ \  \ \ \  \ \  \ \  \\ \  \
   \ \_______\ \__\ \__\ \_______\                \ \__\ \ \__\   \ \__\ \ \__\ \__\ \__\\ \__\
    \|_______|\|__|\|__|\|_______|                 \|__|  \|__|    \|__|  \|__|\|__|\|__| \|__|
```
##### 项目介绍
User Account Center. His name is Titan.

##### 项目技术
Spring Boot + MyBatis + Druid + PageHelper + JWT + Dubbo + Zookpeer
<br/>
需要自行配置Dubbo + Zookpeer。Dubbo-Admin可在Git上Clone并install。
<br/>
Dubbo端口：5500；项目端口：5501

##### UAC思路

> register:用户注册
<br/>
> （1）获取secretToken，使用AES加密用户密码
<br/>
> （2）将用户注册信息存入用户信息表
<br/>
> （3）将用户注册信息存入新用户注册表，可作为大数据分析使用


> login:用户登录
<br/>
> （1）获取secretToken，使用AES加密用户登录信息
<br/>
> （2）通过加密的用户信息的secretToken进行AES解密
<br/>
> （3）将解密后的数据重新加密MD5进行数据库校验
<br/>
> （4）记录用户登录日志
<br/>
> （5）返回BASE用户信息，以及JWT_TOKEN串


> interceptor:拦截器
<br/>
> （1）获取Authorization，获取JWT_TOKEN
<br/>
> （2）通过JWT_TOKEN获取用户BASE信息
<br/>
> （3）判断用户操作是否需要JWT_TOKEN续租
<br/>
> （4）校验是否为常规合法JWT_TOKEN


```text
JWT：头部 + 载荷 + 签名
     Header . Payload . Sign
头部（Header）：
    这里表明是JWT类型,所用的签名算法是HS256算法。
    对它也要进行Base64编码，之后的字符串就成了JWT的头。
载荷（Payload）：
    将上面的JSON对象进行[base64编码]可以得到下面的字符串。
    这个字符串我们将它称作JWT的载荷。
签名（Sign）：
    签名的过程，实际上是对头部以及载荷内容进行签名。
    将Header . Payload通过加密后得到的这个编码就是签名。
    如果Header和Payload被修改后，但签名不同，服务端会拒绝JWT_TOKEN。
```
