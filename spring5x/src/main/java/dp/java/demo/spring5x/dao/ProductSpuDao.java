package dp.java.demo.spring5x.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dp.java.demo.spring5x.model.po.ProductSpuPO;
import org.springframework.stereotype.Repository;

@Repository
@DS("TProductDB")
public interface ProductSpuDao extends BaseMapper<ProductSpuPO> {
}
