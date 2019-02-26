# springcloud-lcn
基于springcloud的lcn分布式事务处理

TX_LCN 官网地址：https://www.txlcn.org/zh-cn/index.html

demo环境
TM:
1、配置TM之前需要准备中间件 JRE1.8+, Mysql5.6+, Redis3.2+
2、创建MySQL数据库, 名称为: tx-manager sql问如下

CREATE TABLE `t_tx_exception`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `unit_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mod_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transaction_state` tinyint(4) NULL DEFAULT NULL,
  `registrar` tinyint(4) NULL DEFAULT NULL,
  `remark` varchar(4096) NULL DEFAULT  NULL,
  `ex_state` tinyint(4) NULL DEFAULT NULL COMMENT '0 未解决 1已解决',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

4、创建springboot工程，添加maven依赖
<dependency>
    <groupId>com.codingapi.txlcn</groupId>
    <artifactId>txlcn-tm</artifactId>
    <version>5.0.2.RELEASE</version>
</dependency>

5、然后在application中加入注解@EnableTransactionManagerServer

在TM中的properties配置中根据自己的需求配置
我的配置如下
/**
spring.application.name=tx-manager
server.port=7970

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/tx-manager?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=root
#指定注册中心地址
eureka.client.serviceUrl.defaultZone= http://localhost:8761/eureka/
eureka.instance.instance-id=${spring.cloud.client.ip-address}:${server.port}
eureka.instance.prefer-ip-address=true

mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.use-generated-keys=true

# TxManager Host Ip
tx-lcn.manager.host=127.0.0.1
# TxClient连接请求端口
tx-lcn.manager.port=8070
# 心跳检测时间(ms)
tx-lcn.manager.heart-time=15000
# 分布式事务执行总时间
tx-lcn.manager.dtx-time=30000
#参数延迟删除时间单位ms
tx-lcn.message.netty.attr-delay-time=10000
tx-lcn.manager.concurrent-level=128
# 开启日志
tx-lcn.logger.enabled=true
logging.level.com.codingapi=debug
#redis 主机
spring.redis.host=127.0.0.1
#redis 端口
spring.redis.port=6379
#redis 密码
spring.redis.password=shen123
**/
7、添加完以上配置后启动，浏览器访问TxManager管理后台http://localhost:7970，默认密码为:codingapi.
二、
创建bank-a服务和bank-b服务
所有TC,需要事务处理的都要加入如果maven依赖：
 <dependency>
     <groupId>com.codingapi.txlcn</groupId>
     <artifactId>txlcn-tc</artifactId>
     <version>5.0.2.RELEASE</version>
 </dependency>

 <dependency>
     <groupId>com.codingapi.txlcn</groupId>
     <artifactId>txlcn-txmsg-netty</artifactId>
     <version>5.0.2.RELEASE</version>
 </dependency>
 然后在启动类加上注解@EnableDistributedTransaction

三、
# 默认之配置为TM的本机默认端口 如果是默认可以不用写，demo中使用的默认地址，所有配置文件中没有
tx-lcn.client.manager-address=127.0.0.1:8070 

四、此demospringboot版本是springboot2.1.0，注册中心用的eureka,然后还用了 feign

#若有问题可以联系邮箱936527874@qq.com
