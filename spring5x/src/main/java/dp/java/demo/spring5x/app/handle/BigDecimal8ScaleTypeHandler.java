package dp.java.demo.spring5x.app.handle;

import org.apache.ibatis.type.BigDecimalTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 有以下几种场景会导致精度丢失，自动四舍五入，所以手动提高数字的精度，确保不会丢失精度。
 * 1.手动写批量 insert 语句中，某个字段是 BigDecimal，并且几条数据的精度有不一样的，按照最低精度的来进行四舍五入之后 insert。
 * 2.在 SQL 语句中进行算术运算的时候，任何一个元素是低精度的，都会以低精度的进行运算，然后落库。
 */
public class BigDecimal8ScaleTypeHandler extends BigDecimalTypeHandler {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BigDecimal parameter, JdbcType jdbcType) throws SQLException {
        ps.setBigDecimal(i, parameter.setScale(8, RoundingMode.HALF_UP));
    }
}
