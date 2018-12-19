package cn.hft.FunSaleServerTest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

public class FunSaleServerTest {
    @Test
    public void findAll() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("applicationContext.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        sqlSession.close();
    }
}
