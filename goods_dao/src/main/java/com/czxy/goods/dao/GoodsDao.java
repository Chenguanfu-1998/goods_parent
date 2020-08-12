package com.czxy.goods.dao;

import com.czxy.goods.pojo.Goods;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface GoodsDao extends Mapper<Goods> {
    //查询所有根据cid进行填充
    @Select("select * from tbl_goods")
    @Results(id = "u1",value = {
            @Result( property = "category", column = "cid" ,one = @One(select = "com.czxy.goods.dao.CategoryDao.selectByPrimaryKey")),
            @Result( property = "cid", column = "cid")
    })
    List<Goods> selectAllGlist();

    @Select("<script>select * from tbl_goods\n" +
            "\t\t<where>\n" +
            "\t\t\t<if test=\"goods_name!='%null%' and goods_name!='%%'\">\n" +
            "\t\t\t\tand goods_name like #{goods_name}\n" +
            "\t\t\t</if>\n" +
            "\t\t</where></script>")
    @ResultMap("u1")
    Goods searchGoodsByName(Goods goods);
}
