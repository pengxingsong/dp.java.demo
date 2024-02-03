package dp.java.demo.spring5x.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dp.java.demo.spring5x.dao.ProductCategoryDao;
import dp.java.demo.spring5x.dao.ProductSpuDao;
import dp.java.demo.spring5x.model.po.ProductCategoryPO;
import dp.java.demo.spring5x.model.po.ProductSpuPO;
import dp.java.demo.spring5x.model.po.UserPO;
import dp.java.demo.spring5x.service.ProductCategoryService;
import dp.java.demo.spring5x.service.ProductSpuService;
import dp.java.demo.spring5x.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ProductSpuServiceImpl extends ServiceImpl<ProductSpuDao, ProductSpuPO> implements ProductSpuService {

    @Resource
    UserService userService;
    @Resource
    ProductCategoryService productCategoryService;
    @Resource
    ProductCategoryDao productCategoryDao;

    @Transactional
    @Override
    public Boolean addProductSpu() {

        UserPO insertUserPO = new UserPO();
        insertUserPO.setLoginPwd("admin");
        insertUserPO.setUserName("admin");
        userService.addUser(insertUserPO);

        Integer userId = insertUserPO.getId();
        ProductCategoryPO productCategoryPO = new ProductCategoryPO();
        productCategoryPO.setName("水果");
        productCategoryPO.setRemark("水果分类");
        productCategoryPO.setCreateUserId(userId);
        productCategoryPO.setUpdateUserId(userId);
        productCategoryDao.insert(productCategoryPO);

        ProductSpuPO productSpuPO = new ProductSpuPO();
        productSpuPO.setName("梨子");
        productSpuPO.setCategory(productCategoryPO.getId());
        productSpuPO.setRemark("湖北的梨子");
        save(productSpuPO);

        return true;
    }
}
