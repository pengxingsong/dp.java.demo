package dp.java.demo.spring5x.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dp.java.demo.spring5x.dao.ProductCategoryDao;
import dp.java.demo.spring5x.model.po.ProductCategoryPO;
import dp.java.demo.spring5x.service.ProductCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDao, ProductCategoryPO>
        implements ProductCategoryService {

}
