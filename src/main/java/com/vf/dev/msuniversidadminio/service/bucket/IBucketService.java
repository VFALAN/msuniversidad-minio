package com.vf.dev.msuniversidadminio.service.bucket;

import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import io.minio.errors.*;
import io.minio.messages.Bucket;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IBucketService {

    public boolean existBucket(String pBucketName) throws MsUniversidadException;

    public List<Bucket> listAllBuckets() throws MsUniversidadException;

    public void crearBucket(String pBucketName) throws MsUniversidadException;

    public void removeBucket(String pBucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, MsUniversidadException;
}
