package cn.mall4j.springboot.starter.minio;

import cn.mall4j.springboot.starter.minio.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Minio 模板操作
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@AllArgsConstructor
public class MinioTemplate implements MinioOperations {
    
    private final MinioClient minioClient;
    
    private final MinioProperties minioProperties;
    
    @SneakyThrows
    @Override
    public ObjectWriteResponse upload(PutObjectArgs args) {
        return minioClient.putObject(args);
    }
    
    @SneakyThrows
    @Override
    public String upload(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String[] fileNameSplit = fileName.split("\\.");
        fileName = fileNameSplit.length > 1 ? fileNameSplit[0] + "_" + System.currentTimeMillis() + "." + fileNameSplit[1] : fileName + "_" + System.currentTimeMillis();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(fileName)
                    .stream(inputStream, inputStream.available(), -1L)
                    .contentType(multipartFile.getContentType())
                    .build();
            minioClient.putObject(objectArgs);
        }
        return fileName;
    }
}
