package com.jielim36.devgram.enumTypeHandler;

import com.jielim36.devgram.enums.PostVisibilityDurationEnum;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostVisibilityDurationEnumTypeHandler implements TypeHandler<PostVisibilityDurationEnum> {

    private final EnumOrdinalTypeHandler delegate = new EnumOrdinalTypeHandler(PostVisibilityDurationEnum.class);

    @Override
    public void setParameter(PreparedStatement ps, int i, PostVisibilityDurationEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getDuration());
    }

    @Override
    public PostVisibilityDurationEnum getResult(ResultSet rs, String columnName) throws SQLException {
        return PostVisibilityDurationEnum.fromDuration(rs.getInt(columnName));
    }

    @Override
    public PostVisibilityDurationEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return PostVisibilityDurationEnum.fromDuration(rs.getInt(columnIndex));
    }

    @Override
    public PostVisibilityDurationEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return PostVisibilityDurationEnum.fromDuration(cs.getInt(columnIndex));
    }
}
