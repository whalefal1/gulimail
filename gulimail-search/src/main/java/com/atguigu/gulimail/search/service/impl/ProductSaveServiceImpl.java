package com.atguigu.gulimail.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.gulimail.search.config.GulimailElasticSearchConfig;
import com.atguigu.gulimail.search.constant.EsConstant;
import com.atguigu.gulimail.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * Description
     *  上架商品
     * @param skuEsModels
     * @author 李朋逊
     * @date 2024/03/27
     */
    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
        //1、给es中保存这些数据
        //给es建立索引，product，建立好映射关系

        //给es中保存数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            //构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsModel.getSkuId().toString());
            String s = JSON.toJSONString(skuEsModel);
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        try {
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, GulimailElasticSearchConfig.COMMON_OPTIONS);
            //TODO 如果批量错误
            boolean b = bulk.hasFailures();
            List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
                return item.getId();

            }).collect(Collectors.toList());
            return b;
        }catch (Exception e){
            log.error("商品上架错误：{}", e);
            return false;
        }
    }
}
