import mappers.RoleMapper;
import org.apache.ibatis.scripting.xmltags.SetSqlNode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Role;
import util.SqlSessionFactoryUtil;

import java.util.List;
import java.util.logging.Logger;

public class DemoMain {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("DemoMain");
        SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory("mybatis-config.xml");
        SqlSession sqlSession = factory.openSession();
        try {
            RoleMapper mapper = (RoleMapper) sqlSession.getMapper(RoleMapper.class);
            Role role = new Role();
            role.setRoleName("hzm");
            role.setNote("你号");
            mapper.insertRole(role);
            Role insertRole = mapper.getRole(role.getId());
            insertRole.setRoleName("hzm2");
            mapper.updateRole(insertRole);
            int i = mapper.deleteRole(1L);
            int i1 = mapper.insertRole(role);
            List<Role> roles = mapper.findRoles("hzm");
            logger.info(roles.toString());
            sqlSession.commit();
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
