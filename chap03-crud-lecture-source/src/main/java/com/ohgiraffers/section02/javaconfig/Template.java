package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/menudb";
    private static String USER = "ohgiraffers";
    private static String PASSWORD = "ohgiraffers";

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {

        if(sqlSessionFactory == null) {
            Environment environment = new Environment("dev",    //환경 정보 이름(구분하기 위한 ID값)
                    new JdbcTransactionFactory(),                   //트랜잭션 매니저 종류 결정
                    new PooledDataSource(DRIVER, URL, USER, PASSWORD)); //ConnectionPool 사용 유무 결정

            Configuration configuration = new Configuration(environment);
            /* 설정 객체에 매퍼 등록 (어디에 쿼리를 작성했는지를 등록 매퍼에 대한 메타정보로 전달) */
            configuration.addMapper(MenuMapper.class);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        }
        return sqlSessionFactory.openSession(false);
    }
}
