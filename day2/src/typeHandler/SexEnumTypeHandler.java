package typeHandler;

import enums.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.lang.reflect.Type;
import java.sql.*;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(SexEnum.class)
public class SexEnumTypeHandler implements TypeHandler<SexEnum> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,sexEnum.getId());
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, String s) throws SQLException {
        int sexId = resultSet.getInt(s);
        return SexEnum.getEnum(sexId);
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, int i) throws SQLException {
        int sexId = resultSet.getInt(i);
        return SexEnum.getEnum(sexId);
    }

    @Override
    public SexEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        int sexId = callableStatement.getInt(i);
        return SexEnum.getEnum(sexId);
    }
}
