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

package org.opengoofy.congomall.springboot.starter.minio;

import org.opengoofy.congomall.springboot.starter.minio.config.MinioProperties;
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
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
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
