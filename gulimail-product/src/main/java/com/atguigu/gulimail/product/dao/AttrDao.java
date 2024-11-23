package com.atguigu.gulimail.product.dao;

import com.atguigu.gulimail.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

/**
 * 商品属性
 * 
 * @author lipengxun
 * @email lipengxun38@gmail.com
 * @date 2024-01-26 09:35:50
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {


    List<Long> selectSearchAttrsIds(@Param("attrIds") List<Long> attrIds);
}
