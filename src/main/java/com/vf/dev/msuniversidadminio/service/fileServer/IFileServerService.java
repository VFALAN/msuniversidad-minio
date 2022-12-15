package com.vf.dev.msuniversidadminio.service.fileServer;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IFileServerService {


    String saveFile(MultipartFile file, UsuarioEntity pUsario, Integer pTipoArchivo, Integer pIdBucket, String pExtencion, String pContentType,String ruta) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, MsUniversidadException;

    InputStream getFile(ArchivoEntity pArchivo) throws MsUniversidadException;

    String updateFile(MultipartFile pFile, ArchivoEntity pArchivoEntity) throws MsUniversidadException;

    void deleteFile(ArchivoEntity pArchivoEntity) throws MsUniversidadException;
}
