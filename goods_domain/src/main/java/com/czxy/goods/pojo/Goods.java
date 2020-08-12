package com.czxy.goods.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_goods")
public class Goods {
    /**
     * `goods_id` INT(10) NOT NULL AUTO_INCREMENT,
     `goods_name` VARCHAR(20) DEFAULT NULL,
     `goods_number` INT(10) DEFAULT NULL,
     `goods_price` DOUBLE DEFAULT NULL,
     `guarantee_period` DATE DEFAULT NULL COMMENT '保质期',
     `cid` VARCHAR(10) DEFAULT NULL COMMENT '所属分类ID',
     */
    @Id
    @Column(name = "goods_id")
    private Integer goodsId;
    private String goods_name;
    private Integer goods_number;
    private Double goods_price;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date guarantee_period;

    private String cid;
    private Category category;
}
