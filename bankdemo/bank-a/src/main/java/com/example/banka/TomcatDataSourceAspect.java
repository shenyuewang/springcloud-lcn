//package com.example.banka;
//
//import com.codingapi.txlcn.tc.aspect.weave.DTXResourceWeaver;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//
///**
// * @Description TODO
// * @Author shen
// * @Date 2019/2/26 18:28
// **/
//@Component
//@Aspect
//public class TomcatDataSourceAspect {
//    @Autowired
//    private DTXResourceWeaver dtxResourceWeaver;//TX-LCN 资源切面处理对象
//
//    @Around("execution(public java.sql.Connection com.zaxxer.hikari.HikariDataSource.getConnection(..) )")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        return dtxResourceWeaver.getConnection(() -> (Connection) point.proceed());
//    }
//}
