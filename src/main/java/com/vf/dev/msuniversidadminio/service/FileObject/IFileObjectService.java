package com.vf.dev.msuniversidadminio.service.FileObject;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import io.minio.errors.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IFileObjectService {
    public String saveFile(InputStream pInput, String pBucket, String file,String pContentType) throws MsUniversidadException;

    public String getUrl(ArchivoEntity pArchivoEntity) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
