package org.opengoofy.congomall.bff.biz.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * 支付方式枚举
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RequiredArgsConstructor
public enum PayTypeEnum {
    
    /**
     * 阿里支付宝
     */
    ALI_PAY(0, "AliPay");
    
    @Getter
    private final int code;
    
    @Getter
    private final String name;
    
    public static String getNameByCode(int code) {
        return Arrays.stream(PayTypeEnum.values())
                .filter(each -> Objects.equals(code, each.getCode()))
                .findFirst()
                .map(PayTypeEnum::getName)
                .orElse(null);
    }
}
