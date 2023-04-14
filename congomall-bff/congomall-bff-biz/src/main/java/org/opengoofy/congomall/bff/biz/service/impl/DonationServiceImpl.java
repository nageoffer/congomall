package org.opengoofy.congomall.bff.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.bff.biz.common.PageAdapter;
import org.opengoofy.congomall.bff.biz.common.PayTypeEnum;
import org.opengoofy.congomall.bff.biz.dao.entity.DonationDO;
import org.opengoofy.congomall.bff.biz.dao.mapper.DonationMapper;
import org.opengoofy.congomall.bff.biz.dto.resp.adapter.DonationAdapterRespDTO;
import org.opengoofy.congomall.bff.biz.service.DonationService;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 捐赠接口层实现
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
    
    private final DonationMapper donationMapper;
    
    @Override
    public PageAdapter<List<DonationAdapterRespDTO>> pageQueryDonation(int page, int size) {
        IPage<DonationDO> donationDOPage = donationMapper.selectPage(new Page<>(page, size), Wrappers.emptyWrapper());
        IPage<DonationAdapterRespDTO> resultPage = donationDOPage.convert(each -> {
            DonationAdapterRespDTO convert = BeanUtil.convert(each, DonationAdapterRespDTO.class);
            convert.setPayType(PayTypeEnum.getNameByCode(each.getPayType()));
            return convert;
        });
        PageAdapter pageAdapter = new PageAdapter();
        pageAdapter.setData(resultPage.getRecords());
        pageAdapter.setSuccess(true);
        pageAdapter.setDraw(0);
        pageAdapter.setRecordsFiltered(0);
        pageAdapter.setRecordsTotal(donationDOPage.getTotal());
        return pageAdapter;
    }
}
