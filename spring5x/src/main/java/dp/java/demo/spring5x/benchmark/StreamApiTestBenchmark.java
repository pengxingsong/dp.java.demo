package dp.java.demo.spring5x.benchmark;

import dp.java.demo.spring5x.model.po.ProductCategoryPO;
import dp.java.demo.spring5x.model.po.ProductSpuPO;
import dp.java.demo.spring5x.util.AppUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.util.StopWatch;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StreamApiTestBenchmark {
    //region 初始化

    int categoryCount = 20000;
    int productCount = 20000;

    private List<ProductCategoryPO> initProductCategory(){
        List<ProductCategoryPO> productCategoryPOList =new ArrayList<>();
        for (int i = 1; i <= categoryCount; i++) {
            ProductCategoryPO po= getNewProductCategoryPO(i+1,String.format("第 %s 个商品类目",i));
            productCategoryPOList.add(po);
        }
        return productCategoryPOList;
    }
    private List<ProductSpuPO> initProductSpu(){
        List<ProductSpuPO> productCategoryPOList =new ArrayList<>();
        for (int i = 1; i <= productCount; i++) {
            int randomNumber = (int) (Math.random() * categoryCount);
            ProductSpuPO po= getNewProductSpuPO(i+1,String.format("第 %s 个商品",i),randomNumber);
            productCategoryPOList.add(po);
        }
        return productCategoryPOList;
    }

    private ProductCategoryPO getNewProductCategoryPO(Integer id,String name){
        ProductCategoryPO po= new ProductCategoryPO();
        po.setId(id);
        po.setName(name);
        po.setRemark("");
        po.setCreateTime(LocalDateTime.now());
        po.setCreateUserId(0);
        po.setUpdateTime(LocalDateTime.now());
        po.setUpdateUserId(0);
        po.setDeleteFlag(0);
        return po;
    }

    private ProductSpuPO getNewProductSpuPO(Integer id,String name,Integer categoryId){
        ProductSpuPO po= new ProductSpuPO();
        po.setId(id);
        po.setName(name);
        po.setCategory(categoryId);
        po.setRemark("");
        po.setCreateTime(LocalDateTime.now());
        po.setCreateUserId(0);
        po.setUpdateTime(LocalDateTime.now());
        po.setUpdateUserId(0);
        po.setDeleteFlag(0);
        return po;
    }
    //endregion

    @Benchmark
    public void wellHelloThere() {
        // this method was intentionally left blank.
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StreamApiTestBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
    //@Benchmark
    public void streamApiQueryBenchmark() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<ProductCategoryPO> categoryPOList = initProductCategory();
        List<ProductSpuPO> productSpuPOList = initProductSpu();
        stopWatch.stop();
        System.out.println(String.format("类目数量 %s，商品数量 %s，初始化数据耗时：%s",categoryCount,productCount, AppUtil.formatTimeSpanToZhStr(stopWatch.getLastTaskTimeMillis())));

        System.out.println("需求1：查询商品类目下有多少商品");
        stopWatch.start();
        for (ProductCategoryPO categoryPO : categoryPOList) {
            List<ProductSpuPO> spuList = productSpuPOList.stream()
                    .filter(x-> Objects.equals(x.getCategory(),categoryPO.getId()))
                    .collect(Collectors.toList());
        }
        stopWatch.stop();
        System.out.println("使用StreamAPI的耗时："+AppUtil.formatTimeSpanToZhStr(stopWatch.getLastTaskTimeMillis()));

        stopWatch.start();
        for (ProductCategoryPO categoryPO : categoryPOList) {
            List<ProductSpuPO> spuListTemp =new ArrayList<>();
            for (ProductSpuPO spuPO: productSpuPOList ){
                if( Objects.equals(spuPO.getCategory(),categoryPO.getId())){
                    spuListTemp.add(spuPO);
                }
            }
        }
        stopWatch.stop();
        System.out.println("使用for循环的耗时："+AppUtil.formatTimeSpanToZhStr(stopWatch.getLastTaskTimeMillis()));

        stopWatch.start();
        Map<Integer,List<ProductSpuPO>> categoryMapSpuList= productSpuPOList.stream()
                .collect(Collectors.groupingBy(ProductSpuPO::getCategory,Collectors.toList()));
        for (ProductCategoryPO categoryPO : categoryPOList) {
            List<ProductSpuPO> spuListTemp = categoryMapSpuList.get(categoryPO.getId());
        }
        stopWatch.stop();
        System.out.println("将productSpuPOList转成Map后的耗时："+AppUtil.formatTimeSpanToZhStr(stopWatch.getLastTaskTimeMillis()));
    }
}
