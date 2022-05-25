package cn.mall4j.springboot.starter.convention;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 分页返回对象
 */
@Data
public class Page<T> {
    
    /**
     * 当前页
     */
    private Long current;
    
    /**
     * 每页显示条数
     */
    private Long size = 10L;
    
    /**
     * 总数
     */
    private Long total;
    
    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();
    
    public Page(long current, long size) {
        this(current, size, 0);
    }
    
    public Page(long current, long size, long total) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
    }
}
