package com.vf.dev.msuniversidadminio.conf;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j

public class BeansConfiguration {

    @Value("${minio.credentials.username}")
    private String MINIO_USERNAME;
    @Value("${minio.credentials.password}")
    private String MINIO_PASSWORD;
    @Value("${minio.url}")
    private String MINIO_URL;

    @Value("${msuniversidad.version}")
    private String version;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    MinioClient minioClient() {
        log.info("Iniciando MSUniversidad-minio Version: ->" + this.version);
        return MinioClient.builder()
                .endpoint(this.MINIO_URL, 9000, false)
                .credentials(this.MINIO_USERNAME, this.MINIO_PASSWORD)
                .build();
    }
}
