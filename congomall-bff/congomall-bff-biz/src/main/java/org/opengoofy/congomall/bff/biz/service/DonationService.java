package org.opengoofy.congomall.bff.biz.service;

import org.opengoofy.congomall.bff.biz.common.PageAdapter;
import org.opengoofy.congomall.bff.biz.dto.resp.adapter.DonationAdapterRespDTO;

import java.util.List;

/**
 * 捐赠接口层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface DonationService {
    
    /**
     * 分页查询捐赠列表
     *
     * @param page 当前页
     * @param size 每页多少条
     * @return 返回捐赠列表
     */
    PageAdapter<List<DonationAdapterRespDTO>> pageQueryDonation(int page, int size);
}
