package org.opengoofy.congomall.bff.biz.common;

import lombok.Data;

import java.util.List;

/**
 * 分页适配
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class PageAdapter<T> {
    
    private Integer recordsFiltered;
    
    private Long recordsTotal;
    
    private Integer draw;
    
    private Boolean success;
    
    private List<T> data;
    
    private Object error;
}
