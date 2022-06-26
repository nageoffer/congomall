package cn.mall4j.springboot.starter.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Minio 配置类
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
@ConfigurationProperties(prefix = MinioProperties.PREFIX)
public class MinioProperties {
    
    public static final String PREFIX = "minio";
    
    /**
     * 端点，minio 地址
     */
    private String endpoint;
    
    /**
     * accessKey
     */
    private String accessKey;
    
    /**
     * secretKey
     */
    private String secretKey;
    
    /**
     * bucket
     */
    private String bucket;
}
