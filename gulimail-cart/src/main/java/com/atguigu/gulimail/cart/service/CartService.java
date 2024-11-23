package com.atguigu.gulimail.cart.service;

import com.atguigu.gulimail.cart.vo.CartItemVo;
import com.atguigu.gulimail.cart.vo.CartVo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CartService {
    void clearCartInfo(String cartKey) ;
    CartItemVo addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    CartItemVo getCartItem(Long skuId);

    CartVo getCart() throws ExecutionException, InterruptedException;

    void checkItem(Long skuId, Integer checked);

    List<CartItemVo> getUserCartItems();

    void changeItemCount(Long skuId, Integer num);

    void deleteIdCartInfo(Integer skuId);
}
