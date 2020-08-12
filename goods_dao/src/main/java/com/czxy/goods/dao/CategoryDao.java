package com.czxy.goods.dao;

import com.czxy.goods.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CategoryDao extends Mapper<Category> {
    @Select("select * from tbl_category")
    public List<Category> findAll();
}
