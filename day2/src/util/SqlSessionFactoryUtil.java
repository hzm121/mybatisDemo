package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {
    private static SqlSessionFactory factory = null;

    public static  SqlSessionFactory getSqlSessionFactory(String resourceName) {
        if (factory == null){
            synchronized (SqlSessionFactoryUtil.class){
                if (factory == null){
                    InputStream res = null;
                    try {
                        res = Resources.getResourceAsStream(resourceName);
                        factory = new SqlSessionFactoryBuilder().build(res);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return factory;
    }
}
