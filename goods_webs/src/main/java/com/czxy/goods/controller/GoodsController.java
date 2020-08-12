package com.czxy.goods.controller;

import com.czxy.goods.pojo.Category;
import com.czxy.goods.pojo.Goods;
import com.czxy.goods.service.CategoryAndGoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.net.www.http.HttpClient;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private CategoryAndGoodsService categoryAndGoodsService;

    //查询所有分类
    @GetMapping("/findByCategory")
    public ResponseEntity<List<Category>> findByCategory(){
        List<Category> clist = null;
        try {
            clist = categoryAndGoodsService.findByCategoryList();
            //System.err.println(clist);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(clist);
    }
    //添加商品
    @PostMapping("/addShopGoods")
    public ResponseEntity<Void> checkByName(@RequestBody Goods goods){
        try {
            categoryAndGoodsService.insertGoods(goods);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    //校验商品名称
    @PostMapping("/checkByName")
    public @ResponseBody Boolean checkCname(@RequestBody Goods goods){
        //调用service校验是否可用
        Boolean flag = categoryAndGoodsService.checkGname(goods);
        //System.err.println("校验用户名："+flag);
        //校验结果返回浏览器
        return flag;
    }
    //查询所有商品
    @GetMapping("/findAllGoods")
    public ResponseEntity<List<Goods>> findAllGoods(){
        List<Goods> glist = null;
        try {
            glist = categoryAndGoodsService.findByGoodsList();
        } catch (Exception e) {
            return new ResponseEntity<List<Goods>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(glist);
    }
}
