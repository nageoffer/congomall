/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.congomall.biz.bff.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.anno.Cached;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.bff.dao.entity.PanelDO;
import org.opengoofy.congomall.biz.bff.dao.entity.PanelProductRelationDO;
import org.opengoofy.congomall.biz.bff.dao.mapper.PanelMapper;
import org.opengoofy.congomall.biz.bff.dao.mapper.PanelProductRelationMapper;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.GoodsAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.GoodsResultAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.HomePanelAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.HomePanelContentAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.HomeProductDetailAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.service.GoodsService;
import org.opengoofy.congomall.biz.bff.remote.ProductRemoteService;
import org.opengoofy.congomall.biz.bff.remote.resp.ProductRespDTO;
import org.opengoofy.congomall.biz.bff.remote.resp.ProductSpuRespDTO;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 商品接口层实现
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    
    private final PanelMapper panelMapper;
    private final PanelProductRelationMapper panelProductRelationMapper;
    private final ProductRemoteService productRemoteService;
    
    private static final int PRODUCT_LIMIT_CART = 1;
    private static final List<String> TYPE_TWO_LIST = Lists.newArrayList("1647777981810081792", "1647788115844136960", "1647794693754322944");
    
    private final String cache = "[\n" +
            "        {\n" +
            "            \"id\":7,\n" +
            "            \"name\":\"轮播图\",\n" +
            "            \"type\":0,\n" +
            "            \"sortOrder\":0,\n" +
            "            \"position\":0,\n" +
            "            \"limitNum\":5,\n" +
            "            \"status\":1,\n" +
            "            \"remark\":\"\",\n" +
            "            \"created\":1523766787000,\n" +
            "            \"updated\":1523766787000,\n" +
            "            \"panelContents\":[\n" +
            "                {\n" +
            "                    \"id\":70,\n" +
            "                    \"panelId\":7,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150635087972564,\n" +
            "                    \"sortOrder\":1,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://ooo.0o0.ooo/2019/09/30/CAZ6QrXPBoh5aIT.png\",\n" +
            "                    \"picUrl2\":\"https://ooo.0o0.ooo/2019/09/30/9Y5MHc8sfhJLk3u.png\",\n" +
            "                    \"picUrl3\":\"https://ooo.0o0.ooo/2019/09/30/lLv8xp3IWqa7Oz6.png\",\n" +
            "                    \"created\":1569839898000,\n" +
            "                    \"updated\":1569843454000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
            "                    \"subTitle\":\"此仅为支付测试商品 拍下不会发货\",\n" +
            "                    \"productImageBig\":\"https://ooo.0o0.ooo/2019/09/30/CAZ6QrXPBoh5aIT.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":33,\n" +
            "                    \"panelId\":7,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432835,\n" +
            "                    \"sortOrder\":2,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://ooo.0o0.ooo/2018/11/04/5bdeba4028e90.png\",\n" +
            "                    \"picUrl2\":\"https://ooo.0o0.ooo/2018/11/04/5bdebb109a29a.png\",\n" +
            "                    \"picUrl3\":\"https://ooo.0o0.ooo/2018/11/04/5bdeba6753403.png\",\n" +
            "                    \"created\":1523970502000,\n" +
            "                    \"updated\":1524192439000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"捐赠商品\",\n" +
            "                    \"subTitle\":\"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
            "                    \"productImageBig\":\"https://ooo.0o0.ooo/2018/11/04/5bdeba4028e90.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":34,\n" +
            "                    \"panelId\":7,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150635087972564,\n" +
            "                    \"sortOrder\":3,\n" +
            "                    \"fullUrl\":null,\n" +
            "                    \"picUrl\":\"https://s1.ax1x.com/2018/05/19/Ccdiid.png\",\n" +
            "                    \"picUrl2\":\"\",\n" +
            "                    \"picUrl3\":\"\",\n" +
            "                    \"created\":1523970510000,\n" +
            "                    \"updated\":1523970512000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
            "                    \"subTitle\":\"此仅为支付测试商品 拍下不会发货\",\n" +
            "                    \"productImageBig\":\"https://s1.ax1x.com/2018/05/19/Ccdiid.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":8,\n" +
            "            \"name\":\"活动版块\",\n" +
            "            \"type\":1,\n" +
            "            \"sortOrder\":1,\n" +
            "            \"position\":0,\n" +
            "            \"limitNum\":4,\n" +
            "            \"status\":1,\n" +
            "            \"remark\":\"\",\n" +
            "            \"created\":1523790300000,\n" +
            "            \"updated\":1523790300000,\n" +
            "            \"panelContents\":[\n" +
            "                {\n" +
            "                    \"id\":25,\n" +
            "                    \"panelId\":8,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432835,\n" +
            "                    \"sortOrder\":1,\n" +
            "                    \"fullUrl\":\"https://www.smartisan.com/jianguo3-accessories\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/6/610400xinpinpeijian.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1523790463000,\n" +
            "                    \"updated\":1524151234000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"捐赠商品\",\n" +
            "                    \"subTitle\":\"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/6/610400xinpinpeijian.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":26,\n" +
            "                    \"panelId\":8,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150635087972564,\n" +
            "                    \"sortOrder\":2,\n" +
            "                    \"fullUrl\":\"https://www.smartisan.com/service/#/tradein\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/6/610400yijiuhuanxin.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1523790480000,\n" +
            "                    \"updated\":1524151248000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
            "                    \"subTitle\":\"此仅为支付测试商品 拍下不会发货\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/6/610400yijiuhuanxin.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":27,\n" +
            "                    \"panelId\":8,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432835,\n" +
            "                    \"sortOrder\":3,\n" +
            "                    \"fullUrl\":\"https://www.smartisan.com/category?id=69\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/4/489673079577637073.png\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1523790504000,\n" +
            "                    \"updated\":1524151261000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"捐赠商品\",\n" +
            "                    \"subTitle\":\"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/4/489673079577637073.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":28,\n" +
            "                    \"panelId\":8,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150635087972564,\n" +
            "                    \"sortOrder\":4,\n" +
            "                    \"fullUrl\":\"https://www.smartisan.com/\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/fe6ab43348a43152b4001b4454d206ac.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1523790538000,\n" +
            "                    \"updated\":1524151273000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
            "                    \"subTitle\":\"此仅为支付测试商品 拍下不会发货\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/fe6ab43348a43152b4001b4454d206ac.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":1,\n" +
            "            \"name\":\"热门商品\",\n" +
            "            \"type\":2,\n" +
            "            \"sortOrder\":2,\n" +
            "            \"position\":0,\n" +
            "            \"limitNum\":3,\n" +
            "            \"status\":1,\n" +
            "            \"remark\":\"\",\n" +
            "            \"created\":1524066553000,\n" +
            "            \"updated\":1523790316000,\n" +
            "            \"panelContents\":[\n" +
            "                {\n" +
            "                    \"id\":22,\n" +
            "                    \"panelId\":1,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150635087972564,\n" +
            "                    \"sortOrder\":1,\n" +
            "                    \"fullUrl\":null,\n" +
            "                    \"picUrl\":\"https://ooo.0o0.ooo/2018/07/13/5b48a7f468bf2.png\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508682391000,\n" +
            "                    \"updated\":1508682391000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
            "                    \"subTitle\":\"此仅为支付测试商品 拍下不会发货\",\n" +
            "                    \"productImageBig\":\"https://ooo.0o0.ooo/2018/07/13/5b48a7f468bf2.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":23,\n" +
            "                    \"panelId\":1,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432835,\n" +
            "                    \"sortOrder\":2,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://ooo.0o0.ooo/2020/07/24/6BV9uTwaqModbYL.png\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508682400000,\n" +
            "                    \"updated\":1523969975000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"捐赠商品\",\n" +
            "                    \"subTitle\":\"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
            "                    \"productImageBig\":\"https://ooo.0o0.ooo/2020/07/24/6BV9uTwaqModbYL.png\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":2,\n" +
            "            \"name\":\"官方精选\",\n" +
            "            \"type\":3,\n" +
            "            \"sortOrder\":3,\n" +
            "            \"position\":0,\n" +
            "            \"limitNum\":8,\n" +
            "            \"status\":1,\n" +
            "            \"remark\":\"\",\n" +
            "            \"created\":null,\n" +
            "            \"updated\":1524108059000,\n" +
            "            \"panelContents\":[\n" +
            "                {\n" +
            "                    \"id\":29,\n" +
            "                    \"panelId\":2,\n" +
            "                    \"type\":2,\n" +
            "                    \"productId\":150642571432843,\n" +
            "                    \"sortOrder\":0,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/1/1220858shoujilouceng.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1523794475000,\n" +
            "                    \"updated\":1524195687000,\n" +
            "                    \"salePrice\":1999,\n" +
            "                    \"productName\":\"坚果 3\",\n" +
            "                    \"subTitle\":\"漂亮得不像实力派\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/1/1220858shoujilouceng.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":8,\n" +
            "                    \"panelId\":2,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432837,\n" +
            "                    \"sortOrder\":1,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/3a7522290397a9444d7355298248f197.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1506330228000,\n" +
            "                    \"updated\":1524151406000,\n" +
            "                    \"salePrice\":49,\n" +
            "                    \"productName\":\"坚果 3 前屏钢化玻璃保护膜\",\n" +
            "                    \"subTitle\":\"超强透光率、耐刮花、防指纹\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/3a7522290397a9444d7355298248f197.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":9,\n" +
            "                    \"panelId\":2,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432838,\n" +
            "                    \"sortOrder\":2,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/63ea40e5c64db1c6b1d480c48fe01272.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1506330275000,\n" +
            "                    \"updated\":1524192497000,\n" +
            "                    \"salePrice\":79,\n" +
            "                    \"productName\":\"坚果 3 绒布国旗保护套\",\n" +
            "                    \"subTitle\":\"质感精良、完美贴合、周到防护\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/63ea40e5c64db1c6b1d480c48fe01272.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":14,\n" +
            "                    \"panelId\":2,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432839,\n" +
            "                    \"sortOrder\":3,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/5e4b1feddb13639550849f12f6b2e202.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508681641000,\n" +
            "                    \"updated\":1524192509000,\n" +
            "                    \"salePrice\":29,\n" +
            "                    \"productName\":\"坚果 3 TPU 软胶透明保护套\",\n" +
            "                    \"subTitle\":\"轻薄透明、完美贴合、TPU 环保材质\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/5e4b1feddb13639550849f12f6b2e202.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":15,\n" +
            "                    \"panelId\":2,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432840,\n" +
            "                    \"sortOrder\":4,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/10525c4b21f039fc8ccb42cd1586f5cd.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508681692000,\n" +
            "                    \"updated\":1524192523000,\n" +
            "                    \"salePrice\":89,\n" +
            "                    \"productName\":\"Smartisan 半入耳式耳机\",\n" +
            "                    \"subTitle\":\"经典配色、专业调音、高品质麦克风\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/10525c4b21f039fc8ccb42cd1586f5cd.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":16,\n" +
            "                    \"panelId\":2,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432841,\n" +
            "                    \"sortOrder\":5,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/b899d9b82c4bc2710492a26af021d484.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508681751000,\n" +
            "                    \"updated\":1524192542000,\n" +
            "                    \"salePrice\":49,\n" +
            "                    \"productName\":\"坚果 3 TPU 软胶保护套\",\n" +
            "                    \"subTitle\":\"TPU 环保材质、完美贴合、周到防护\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/b899d9b82c4bc2710492a26af021d484.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":17,\n" +
            "                    \"panelId\":2,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432842,\n" +
            "                    \"sortOrder\":6,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/abb6986430536cd9365352b434f3c568.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508681821000,\n" +
            "                    \"updated\":1524192557000,\n" +
            "                    \"salePrice\":79,\n" +
            "                    \"productName\":\"坚果 3 \\\"足迹\\\"背贴 乐高创始人出生\",\n" +
            "                    \"subTitle\":\"1891 年 4 月 7 日\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/abb6986430536cd9365352b434f3c568.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":10,\n" +
            "            \"name\":\"品牌周边\",\n" +
            "            \"type\":3,\n" +
            "            \"sortOrder\":4,\n" +
            "            \"position\":0,\n" +
            "            \"limitNum\":7,\n" +
            "            \"status\":1,\n" +
            "            \"remark\":null,\n" +
            "            \"created\":1524066632000,\n" +
            "            \"updated\":1524066635000,\n" +
            "            \"panelContents\":[\n" +
            "                {\n" +
            "                    \"id\":40,\n" +
            "                    \"panelId\":10,\n" +
            "                    \"type\":3,\n" +
            "                    \"productId\":null,\n" +
            "                    \"sortOrder\":0,\n" +
            "                    \"fullUrl\":\"http://xmall.exrick.cn/#/goods?cid=1184\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/z/zhoubianshangpin1220858web.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524067373000,\n" +
            "                    \"updated\":1524194159000,\n" +
            "                    \"salePrice\":null,\n" +
            "                    \"productName\":null,\n" +
            "                    \"subTitle\":null,\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/z/zhoubianshangpin1220858web.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":41,\n" +
            "                    \"panelId\":10,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432845,\n" +
            "                    \"sortOrder\":1,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/2f9a0f5f3dedf0ed813622003f1b287b.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524067376000,\n" +
            "                    \"updated\":1524155076000,\n" +
            "                    \"salePrice\":199,\n" +
            "                    \"productName\":\"Smartisan 帆布鞋\",\n" +
            "                    \"subTitle\":\"一双踏实、舒适的帆布鞋\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/2f9a0f5f3dedf0ed813622003f1b287b.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":42,\n" +
            "                    \"panelId\":10,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432836,\n" +
            "                    \"sortOrder\":2,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/2b05dbca9f5a4d0c1270123f42fb834c.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524067380000,\n" +
            "                    \"updated\":1524155101000,\n" +
            "                    \"salePrice\":149,\n" +
            "                    \"productName\":\"Smartisan T恤 伍迪·艾伦出生\",\n" +
            "                    \"subTitle\":\"一件内外兼修的舒适T恤\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/2b05dbca9f5a4d0c1270123f42fb834c.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":43,\n" +
            "                    \"panelId\":10,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432846,\n" +
            "                    \"sortOrder\":3,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/804edf579887b3e1fae4e20a379be5b5.png\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524067384000,\n" +
            "                    \"updated\":1524155117000,\n" +
            "                    \"salePrice\":149,\n" +
            "                    \"productName\":\"Smartisan T恤 任天堂发售“红白机”\",\n" +
            "                    \"subTitle\":\"100% 美国 SUPIMA 棉、舒适拉绒质地\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/804edf579887b3e1fae4e20a379be5b5.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":44,\n" +
            "                    \"panelId\":10,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432848,\n" +
            "                    \"sortOrder\":4,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/a1c53b5f12dd7ef790cadec0eaeaf466.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524067390000,\n" +
            "                    \"updated\":1524192952000,\n" +
            "                    \"salePrice\":199,\n" +
            "                    \"productName\":\"Smartisan 牛津纺衬衫\",\n" +
            "                    \"subTitle\":\"一件无拘无束的舒适衬衫\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/a1c53b5f12dd7ef790cadec0eaeaf466.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":45,\n" +
            "                    \"panelId\":10,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432847,\n" +
            "                    \"sortOrder\":5,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/daa975651d6d700c0f886718c520ee19.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524067395000,\n" +
            "                    \"updated\":1524192896000,\n" +
            "                    \"salePrice\":249,\n" +
            "                    \"productName\":\"Smartisan Polo衫 经典款\",\n" +
            "                    \"subTitle\":\"一件表里如一的舒适 POLO 衫\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/daa975651d6d700c0f886718c520ee19.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":46,\n" +
            "                    \"panelId\":10,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432849,\n" +
            "                    \"sortOrder\":6,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/3973d009d182d8023bea6250b9a3959e.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524067400000,\n" +
            "                    \"updated\":1524192903000,\n" +
            "                    \"salePrice\":9.9,\n" +
            "                    \"productName\":\"Smartisan 明信片\",\n" +
            "                    \"subTitle\":\"优质卡纸、包装精致、色彩饱满\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/3973d009d182d8023bea6250b9a3959e.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":3,\n" +
            "            \"name\":\"品牌精选\",\n" +
            "            \"type\":3,\n" +
            "            \"sortOrder\":5,\n" +
            "            \"position\":0,\n" +
            "            \"limitNum\":7,\n" +
            "            \"status\":1,\n" +
            "            \"remark\":\"\",\n" +
            "            \"created\":1524066559000,\n" +
            "            \"updated\":1523962455000,\n" +
            "            \"panelContents\":[\n" +
            "                {\n" +
            "                    \"id\":30,\n" +
            "                    \"panelId\":3,\n" +
            "                    \"type\":2,\n" +
            "                    \"productId\":150642571432850,\n" +
            "                    \"sortOrder\":0,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/a/acillouceng1220856.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1523794518000,\n" +
            "                    \"updated\":1524194283000,\n" +
            "                    \"salePrice\":199,\n" +
            "                    \"productName\":\"ACIL E1 颈挂式蓝牙耳机\",\n" +
            "                    \"subTitle\":\"无感佩戴，一切变得简单\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/a/acillouceng1220856.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":2,\n" +
            "                    \"panelId\":3,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432851,\n" +
            "                    \"sortOrder\":1,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/7ac3af5a92ad791c2b38bfe1e38ee334.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1506096182000,\n" +
            "                    \"updated\":1524155020000,\n" +
            "                    \"salePrice\":2699,\n" +
            "                    \"productName\":\"优点智能 E1 推拉式智能指纹密码锁\",\n" +
            "                    \"subTitle\":\"推拉一下，轻松开关\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/7ac3af5a92ad791c2b38bfe1e38ee334.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":7,\n" +
            "                    \"panelId\":3,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":816448,\n" +
            "                    \"sortOrder\":2,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/41cb562a47d4831e199ed7e2057f3b61.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1506178691000,\n" +
            "                    \"updated\":1524154469000,\n" +
            "                    \"salePrice\":2799,\n" +
            "                    \"productName\":\"极米无屏电视 CC\",\n" +
            "                    \"subTitle\":\"720P 高清分辨率、JBL 音响、两万毫安续航力\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/41cb562a47d4831e199ed7e2057f3b61.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":18,\n" +
            "                    \"panelId\":3,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":847276,\n" +
            "                    \"sortOrder\":3,\n" +
            "                    \"fullUrl\":null,\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/99c548bfc9848a8c95f4e4f7f2bc1095.png\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508682172000,\n" +
            "                    \"updated\":1508682172000,\n" +
            "                    \"salePrice\":1499,\n" +
            "                    \"productName\":\"FIIL Diva Pro 全场景无线降噪耳机\",\n" +
            "                    \"subTitle\":\"智能语音交互、高清无损本地存储播放\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/99c548bfc9848a8c95f4e4f7f2bc1095.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":19,\n" +
            "                    \"panelId\":3,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432844,\n" +
            "                    \"sortOrder\":4,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/71432ad30288fb860a4389881069b874.png\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508682215000,\n" +
            "                    \"updated\":1524194738000,\n" +
            "                    \"salePrice\":2999,\n" +
            "                    \"productName\":\"畅呼吸智能空气净化器超级除甲醛版\",\n" +
            "                    \"subTitle\":\"购新空净 赠价值 699 元活性炭滤芯\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/71432ad30288fb860a4389881069b874.png\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":20,\n" +
            "                    \"panelId\":3,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":847276,\n" +
            "                    \"sortOrder\":5,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/804b82e4c05516e822667a23ee311f7c.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508682294000,\n" +
            "                    \"updated\":1524154503000,\n" +
            "                    \"salePrice\":1499,\n" +
            "                    \"productName\":\"FIIL Diva Pro 全场景无线降噪耳机\",\n" +
            "                    \"subTitle\":\"智能语音交互、高清无损本地存储播放\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/804b82e4c05516e822667a23ee311f7c.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":21,\n" +
            "                    \"panelId\":3,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432852,\n" +
            "                    \"sortOrder\":6,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/367d93db1d58f9f3505bc0ec18efaa44.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1508682328000,\n" +
            "                    \"updated\":1524155051000,\n" +
            "                    \"salePrice\":499,\n" +
            "                    \"productName\":\"FIIL Driifter 脖挂蓝牙耳机\",\n" +
            "                    \"subTitle\":\"全天佩戴 抬手就听 HEAC稳连技术\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/367d93db1d58f9f3505bc0ec18efaa44.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":9,\n" +
            "            \"name\":\"活动版块2\",\n" +
            "            \"type\":1,\n" +
            "            \"sortOrder\":7,\n" +
            "            \"position\":0,\n" +
            "            \"limitNum\":4,\n" +
            "            \"status\":1,\n" +
            "            \"remark\":\"\",\n" +
            "            \"created\":null,\n" +
            "            \"updated\":1524110267000,\n" +
            "            \"panelContents\":[\n" +
            "                {\n" +
            "                    \"id\":65,\n" +
            "                    \"panelId\":9,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150635087972564,\n" +
            "                    \"sortOrder\":1,\n" +
            "                    \"fullUrl\":\"\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/f82c9e2774ce0e391a17f1b20c1e0c90.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1554871004000,\n" +
            "                    \"updated\":1554871004000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
            "                    \"subTitle\":\"此仅为支付测试商品 拍下不会发货\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/f82c9e2774ce0e391a17f1b20c1e0c90.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":37,\n" +
            "                    \"panelId\":9,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432835,\n" +
            "                    \"sortOrder\":2,\n" +
            "                    \"fullUrl\":\"https://www.smartisan.com/os/#/4-x\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/5ea6f0905535d7b11258e9a0f9b1abeb.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524066711000,\n" +
            "                    \"updated\":1524196999000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"捐赠商品\",\n" +
            "                    \"subTitle\":\"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/5ea6f0905535d7b11258e9a0f9b1abeb.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":38,\n" +
            "                    \"panelId\":9,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150635087972564,\n" +
            "                    \"sortOrder\":3,\n" +
            "                    \"fullUrl\":\"https://www.smartisan.com/pr/#/video/changhuxi-jinghuaqi\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/c245ada282824a4a15e68bec80502ad4.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524066718000,\n" +
            "                    \"updated\":1524197011000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"支付测试商品 IPhone X 全面屏 全面绽放\",\n" +
            "                    \"subTitle\":\"此仅为支付测试商品 拍下不会发货\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/c245ada282824a4a15e68bec80502ad4.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":39,\n" +
            "                    \"panelId\":9,\n" +
            "                    \"type\":0,\n" +
            "                    \"productId\":150642571432835,\n" +
            "                    \"sortOrder\":4,\n" +
            "                    \"fullUrl\":\"https://www.smartisan.com/pr/#/video/onestep-introduction\",\n" +
            "                    \"picUrl\":\"https://resource.smartisan.com/resource/m/minibanner_03.jpg\",\n" +
            "                    \"picUrl2\":null,\n" +
            "                    \"picUrl3\":null,\n" +
            "                    \"created\":1524066722000,\n" +
            "                    \"updated\":1524197021000,\n" +
            "                    \"salePrice\":1,\n" +
            "                    \"productName\":\"捐赠商品\",\n" +
            "                    \"subTitle\":\"您的捐赠将用于本站维护 给您带来更好的体验\",\n" +
            "                    \"productImageBig\":\"https://resource.smartisan.com/resource/m/minibanner_03.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]";
    
    @Override
    @Cached(name = "goods:", key = "'home-panel'", expire = 24, timeUnit = TimeUnit.HOURS)
    public List<HomePanelAdapterRespDTO> listHomePanel() {
        List<PanelDO> listAllPanel = panelMapper.listAllPanel();
        List<HomePanelAdapterRespDTO> result = BeanUtil.convert(listAllPanel, HomePanelAdapterRespDTO.class);
        result.forEach(each -> {
            List<PanelProductRelationDO> panelProductRelationList = panelProductRelationMapper.listPanelProductRelationByPanelId(each.getId());
            if (CollUtil.isNotEmpty(panelProductRelationList)) {
                List<HomePanelContentAdapterRespDTO> panelContents = new ArrayList<>();
                panelProductRelationList.forEach(item -> {
                    Result<ProductRespDTO> productResult = productRemoteService.getProductBySpuId(String.valueOf(item.getProductId()));
                    if (productResult.isSuccess() && productResult.getData() != null) {
                        HomePanelContentAdapterRespDTO productRespDTO = new HomePanelContentAdapterRespDTO();
                        ProductRespDTO resultData = productResult.getData();
                        ProductSpuRespDTO productSpu = resultData.getProductSpu();
                        productRespDTO.setProductName(productSpu.getName());
                        productRespDTO.setProductId(String.valueOf(productSpu.getId()));
                        productRespDTO.setSalePrice(productSpu.getPrice().intValue());
                        productRespDTO.setId(String.valueOf(productSpu.getId()));
                        productRespDTO.setSortOrder(item.getSort());
                        productRespDTO.setSubTitle(productSpu.getSubTitle());
                        productRespDTO.setPanelId(each.getId());
                        productRespDTO.setType(0);
                        productRespDTO.setCreated(new Date());
                        productRespDTO.setUpdated(new Date());
                        List<String> pics = StrUtil.split(Optional.ofNullable(item.getPic()).orElse(productSpu.getPic()), ",");
                        if (CollUtil.isNotEmpty(pics)) {
                            productRespDTO.setProductImageBig(Optional.ofNullable(item.getBigPic()).orElse(pics.get(0)));
                            productRespDTO.setPicUrl(pics.get(0));
                            if (pics.size() > 1) {
                                productRespDTO.setPicUrl2(pics.get(1));
                            }
                            if (pics.size() > 2) {
                                productRespDTO.setPicUrl3(pics.get(2));
                            }
                        }
                        if (TYPE_TWO_LIST.contains(String.valueOf(productSpu.getId()))) {
                            productRespDTO.setType(2);
                        }
                        panelContents.add(productRespDTO);
                    }
                });
                each.setPanelContents(panelContents);
            }
        });
        return result;
    }
    
    @Override
    @Cached(name = "goods:", key = "'detail-'+#productId", expire = 24, timeUnit = TimeUnit.HOURS)
    public HomeProductDetailAdapterRespDTO goodsDetail(String productId) {
        Result<ProductRespDTO> productResult = productRemoteService.getProductBySpuId(productId);
        HomeProductDetailAdapterRespDTO result = new HomeProductDetailAdapterRespDTO();
        if (productResult.isSuccess() && productResult.getData() != null) {
            ProductRespDTO resultData = productResult.getData();
            ProductSpuRespDTO productSpu = resultData.getProductSpu();
            result.setProductId(productId);
            result.setDetail(productSpu.getDetail());
            result.setLimitNum(PRODUCT_LIMIT_CART);
            result.setSalePrice(productSpu.getPrice().intValue());
            result.setProductName(productSpu.getName());
            result.setSubTitle(productSpu.getSubTitle());
            result.setProductImageBig(productSpu.getPic());
            String[] split = productSpu.getPhotoAlbum().split(",");
            result.setProductImageSmall(Arrays.asList(Optional.ofNullable(split).get()));
        }
        return result;
    }
    
    @Override
    @Cached(name = "goods:", key = "'recommend'", expire = 24, timeUnit = TimeUnit.HOURS)
    public HomePanelAdapterRespDTO recommend() {
        PanelDO recommend = panelMapper.getRecommend();
        HomePanelAdapterRespDTO result = BeanUtil.convert(recommend, HomePanelAdapterRespDTO.class);
        List<PanelProductRelationDO> panelProductRelationList = panelProductRelationMapper.listPanelProductRelationByPanelId(recommend.getId());
        if (CollUtil.isNotEmpty(panelProductRelationList)) {
            List<HomePanelContentAdapterRespDTO> panelContents = new ArrayList<>();
            panelProductRelationList.forEach(item -> {
                Result<ProductRespDTO> productResult = productRemoteService.getProductBySpuId(String.valueOf(item.getProductId()));
                if (productResult.isSuccess() && productResult.getData() != null) {
                    ProductRespDTO resultData = productResult.getData();
                    ProductSpuRespDTO productSpu = resultData.getProductSpu();
                    HomePanelContentAdapterRespDTO productRespDTO = new HomePanelContentAdapterRespDTO();
                    productRespDTO.setProductId(String.valueOf(productSpu.getId()));
                    productRespDTO.setProductName(productSpu.getName());
                    productRespDTO.setId(String.valueOf(productSpu.getId()));
                    productRespDTO.setSalePrice(productSpu.getPrice().intValue());
                    productRespDTO.setSortOrder(item.getSort());
                    productRespDTO.setSubTitle(productSpu.getSubTitle());
                    productRespDTO.setPanelId(recommend.getId());
                    productRespDTO.setType(0);
                    productRespDTO.setCreated(new Date());
                    productRespDTO.setUpdated(new Date());
                    List<String> pics = StrUtil.split(productSpu.getPic(), ",");
                    if (pics.size() == 1) {
                        productRespDTO.setProductImageBig(pics.get(0));
                        productRespDTO.setPicUrl(pics.get(0));
                    }
                    panelContents.add(productRespDTO);
                }
            });
            result.setPanelContents(panelContents);
        }
        return result;
    }
    
    @Override
    @Cached(name = "goods:", key = "'all-goods-page-'+#page+'-'+#size+'-'+#sort+'-'+#priceGt+'-'+#priceLte", expire = 24, timeUnit = TimeUnit.HOURS)
    public GoodsResultAdapterRespDTO allGoods(Integer page, Integer size, Integer sort, Integer priceGt, Integer priceLte) {
        Result<PageResponse<ProductRespDTO>> pageResponseResult = null;
        try {
            pageResponseResult = productRemoteService.pageQueryProduct(page, size, sort, priceGt, priceLte);
            if (!pageResponseResult.isSuccess() || pageResponseResult.getData() == null) {
                throw new ServiceException("调用商品服务分页查询商品失败");
            }
        } catch (Throwable ex) {
            log.error("调用商品服务分页查询商品失败", ex);
        }
        PageResponse<ProductRespDTO> pageResponse = pageResponseResult.getData();
        List<ProductRespDTO> records = pageResponse.getRecords();
        List<GoodsAdapterRespDTO> goodsAdapter = new ArrayList<>();
        records.stream().map(ProductRespDTO::getProductSpu).forEach(each -> {
            GoodsAdapterRespDTO item = new GoodsAdapterRespDTO();
            item.setProductId(String.valueOf(each.getId()));
            item.setProductName(each.getName());
            item.setSubTitle(each.getSubTitle());
            item.setSalePrice(each.getPrice().intValue());
            item.setProductImageBig(each.getPic());
            goodsAdapter.add(item);
        });
        return new GoodsResultAdapterRespDTO(pageResponse.getTotal(), goodsAdapter);
    }
}
