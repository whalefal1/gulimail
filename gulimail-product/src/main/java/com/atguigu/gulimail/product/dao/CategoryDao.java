package com.atguigu.gulimail.product.dao;

import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author lipengxun
 * @email lipengxun38@gmail.com
 * @date 2024-01-26 09:36:25
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
