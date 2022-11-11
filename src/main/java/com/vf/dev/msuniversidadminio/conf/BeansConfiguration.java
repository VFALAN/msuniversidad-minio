package com.vf.dev.msuniversidadminio.conf;

import io.minio.MinioClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Value("${minio.credentials.username}")
    private String MINIO_USERNAME;
    @Value("${minio.credentials.password}")
    private String MINIO_PASSWORD;
    @Value("${minio.url}")
    private String MINIO_URL;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
@Bean
    MinioClient minioClient(){

        return MinioClient.builder()
                .endpoint(this.MINIO_URL , 9000 ,false)
                .credentials(this.MINIO_USERNAME , this.MINIO_PASSWORD)
                .build();
    }
}
