# tensquare
十次方项目\
[远程配置中心](https://github.com/scqzyq/tensquare_config.git)
## 版本
jdk 1.8\
spring-boot 2.5.2\
spring-cloud 2020.0.3\
mysql 5.7\
rabbitmq、redis、mongodb使用的当前docker latest版本\
**elastic-search 7.12.1**\
es版本必须选择对应springcloud版本框架中指定的版本，否则会出现玄学问题
## 内容
本项目完成了十次方中各技术栈的测试使用（除阿里云短信发送），并未完全开发（时间短也懒得写那些curd和业务），
补充了一些template的常规配置，定义了寥寥几个异常类。

在eureka中配置了log4j2的日志打印并指定打印路径，因此在docker中要看日志的话需要挂载一下日志路径：\
docker run -di --name=eureka -p 8761:8761 -v /logs:/logs ip:port/tensquare_eureka:1.0-SNAPSHOT 
其他服务照搬配置即可，均打印在/logs/{application.name}下；\
项目中hystrix已停止维护，在qa服务中改用了resilience4j作为熔断器，不过没做什么配置，引入即可使用；\
项目中zuul网关也已停止维护，使用了spring-cloud-gateway作为网关，gateway服务中配置了
token过滤，配置文件中配置了cros跨域（未测试），git配置中心保存了gateway的相关配置信息。