import mappers.RoleMapper;
import mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Role;
import pojo.User;
import util.SqlSessionFactoryUtil;

import java.util.List;
import java.util.logging.Logger;

public class DemoMain {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("DemoMain");
        SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory("mybatis-config.xml");
        SqlSession sqlSession = factory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(1L);
            sqlSession.commit();
            logger.info(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession!= null){
                sqlSession.close();
            }
        }
    }
}
