package com.vf.dev.msuniversidadminio.service.bucket;

import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Slf4j
public class BucketServiceImpl implements IBucketService {
    @Autowired
    private MinioClient minioClient;


    @Override
    public boolean existBucket(String pBucketName) throws MsUniversidadException {

        try {
            return this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(pBucketName).build());
        } catch (ErrorResponseException | InvalidResponseException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido un error de Conexion con el Servidor Minio", "");
        } catch (InsufficientDataException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido Un Error con la informacion para crear el nuevo Bucket", "");
        } catch (InternalException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido un error Interno", "");
        } catch (InvalidKeyException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido Un Error de Credenciales para conectar con el servidor de minio", "");
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de Input / Output", "");
        } catch (NoSuchAlgorithmException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de Algoritmo", "");
        } catch (ServerException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de conexion al servidor", "");
        } catch (XmlParserException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error en XML", "");
        }
    }



    @Override
    public List<Bucket> listAllBuckets() throws MsUniversidadException {

        try {
            return this.minioClient.listBuckets();
        } catch (ErrorResponseException | InvalidResponseException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido un error de Conexion con el Servidor Minio", "");
        } catch (InsufficientDataException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido Un Error con la informacion para crear el nuevo Bucket", "");
        } catch (InternalException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido un error Interno", "");
        } catch (InvalidKeyException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido Un Error de Credenciales para conectar con el servidor de minio", "");
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de Input / Output", "");
        } catch (NoSuchAlgorithmException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de Algoritmo", "");
        } catch (ServerException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de conexion al servidor", "");
        } catch (XmlParserException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error en XML", "");
        }
    }

    @Override
    public void crearBucket(String pBucketName) throws MsUniversidadException {

        try {
            this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(pBucketName).build());

        } catch (ErrorResponseException | InvalidResponseException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido un error de Conexion con el Servidor Minio", "");
        } catch (InsufficientDataException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido Un Error con la informacion para crear el nuevo Bucket", "");
        } catch (InternalException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido un error Interno", "");
        } catch (InvalidKeyException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido Un Error de Credenciales para conectar con el servidor de minio", "");
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de Input / Output", "");
        } catch (NoSuchAlgorithmException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de Algoritmo", "");
        } catch (ServerException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de conexion al servidor", "");
        } catch (XmlParserException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error en XML", "");
        }

    }

    @Override
    public void removeBucket(String pBucketName) throws MsUniversidadException {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(pBucketName).build());
        } catch (ErrorResponseException | InvalidResponseException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido un error de Conexion con el Servidor Minio", "");
        } catch (InsufficientDataException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido Un Error con la informacion para crear el nuevo Bucket", "");
        } catch (InternalException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido un error Interno", "");
        } catch (InvalidKeyException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Ha Ocurrido Un Error de Credenciales para conectar con el servidor de minio", "");
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de Input / Output", "");
        } catch (NoSuchAlgorithmException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de Algoritmo", "");
        } catch (ServerException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error de conexion al servidor", "");
        } catch (XmlParserException e) {
            log.info(e.getMessage());
            throw new MsUniversidadException("Error en XML", "");
        }
    }
}
