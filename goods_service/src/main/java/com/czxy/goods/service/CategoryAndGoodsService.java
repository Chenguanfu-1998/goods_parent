package com.czxy.goods.service;

import com.czxy.goods.dao.CategoryDao;
import com.czxy.goods.dao.GoodsDao;
import com.czxy.goods.pojo.Category;
import com.czxy.goods.pojo.Goods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CategoryAndGoodsService {
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private GoodsDao goodsDao;


    /**
     *查询所有分类
     * @return
     */
    public List<Category> findByCategoryList() {
        return categoryDao.findAll();
    }

    /**
     * 校验用户名
     * @param
     * @return
     */
    public Boolean checkGname(Goods goods) {
        Example example = new Example(Goods.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("goods_name",goods.getGoods_name());
        List<Goods> result = goodsDao.selectByExample(example);
        if (result.size() == 0){
            return true;
        }
        return false;
    }
    /**
     * 添加商品
     * @param goods
     */
    public int insertGoods(Goods goods) {
        int insert = goodsDao.insert(goods);
        return insert;
    }

    /**
     * 查询所有商品
     * @return
     */
    public List<Goods> findByGoodsList() {
        return goodsDao.selectAllGlist();
    }


}
