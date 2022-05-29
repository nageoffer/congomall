package cn.mall4j.springboot.starter.cache.toolkit;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 缓存工具类
 *
 * @author chen.ma
 * @github https://github.com/longtai-cns
 */
public class CacheUtil {
    
    private static final String SPLICING_OPERATOR = "_";
    
    /**
     * 构建缓存标识
     *
     * @param keys
     * @return
     */
    public static String buildKey(String... keys) {
        Stream.of(keys).forEach(each -> Optional.ofNullable(Strings.emptyToNull(each)).orElseThrow(() -> new RuntimeException("构建缓存 key 不允许为空")));
        return Joiner.on(SPLICING_OPERATOR).join(keys);
    }
}
