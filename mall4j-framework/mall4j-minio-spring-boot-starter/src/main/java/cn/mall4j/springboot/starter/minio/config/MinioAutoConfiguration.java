package cn.mall4j.springboot.starter.minio.config;

import cn.mall4j.springboot.starter.minio.MinioTemplate;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Minio 自动装配配置
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@AllArgsConstructor
@EnableConfigurationProperties(MinioProperties.class)
public class MinioAutoConfiguration {
    
    private final MinioProperties minioProperties;
    
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
    
    @Bean
    public MinioTemplate minioTemplate(MinioClient minioClient) {
        return new MinioTemplate(minioClient, minioProperties);
    }
}
