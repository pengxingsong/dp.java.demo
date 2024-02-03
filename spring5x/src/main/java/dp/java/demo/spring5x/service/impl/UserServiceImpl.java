package dp.java.demo.spring5x.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dp.java.demo.spring5x.dao.UserDao;
import dp.java.demo.spring5x.model.po.UserPO;
import dp.java.demo.spring5x.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserPO> implements UserService {

    @Override
    public Integer addUser(UserPO userPO) {
        save(userPO);
        return userPO.getId();
    }
}
