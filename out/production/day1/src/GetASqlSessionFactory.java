import mapper.RoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.scripting.xmltags.SetSqlNode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Role;

import java.io.IOException;
import java.io.InputStream;

public class GetASqlSessionFactory {
    private static  SqlSessionFactory sqlfactory = null;
    public static void main(String[] args) throws IOException {
        getSSFFromXMl();
        //测试添加
        testInsert();
    }

    /**
     * 测试添加
     */
    private static void testInsert() {

        SqlSession sqlSession = sqlfactory.openSession();
        try {
            Role role = new Role();
            role.setNote("测试提娜佳");
            role.setRoleName("1号");
//            int res = sqlSession.update("mapper.RoleMapper.insertRole",role);
            //mapper接口方式
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            int i = mapper.insertRole(role);
            sqlSession.commit();

            System.out.println("--------"+role.getId());
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {

            if (sqlSession!= null){
                sqlSession.close();
            }
        }
    }

    /**
     * 通过xml文件生成SqlSessionFactory
     * @throws IOException
     */
    public static void getSSFFromXMl() throws IOException {
        InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        sqlfactory = factory;
        SqlSession sqlSession = factory.openSession();
        try {
            //数据处理，如insert一条数据(SQL方式)
//            Role o = (Role)sqlSession.selectOne("mapper.RoleMapper.getRole", 1L);
            //Mapper接口方式
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            Role role = (Role)mapper.getRole(1L);
            //提交事务
            sqlSession.commit();
        } catch (Exception ex) {
            //事务回滚
            sqlSession.rollback();
        } finally {
            //关闭资源
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
