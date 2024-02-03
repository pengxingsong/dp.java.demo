package dp.java.demo.spring5x.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dp.java.demo.spring5x.model.po.UserPO;
import org.springframework.stereotype.Repository;

@Repository
@DS("TBaseDB")
public interface UserDao extends BaseMapper<UserPO> {

}
