package com.atguigu.gulimail.coupon.dao;

import com.atguigu.gulimail.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author lipengxun
 * @email lipengxun38@gmail.com
 * @date 2024-01-25 20:34:49
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}