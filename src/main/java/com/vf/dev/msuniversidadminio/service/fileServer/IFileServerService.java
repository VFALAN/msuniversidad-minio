package com.vf.dev.msuniversidadminio.service.fileServer;

import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IFileServerService {

    public String saveFile(MultipartFile file, UsuarioEntity pUsario , String pTipoArchivo , Integer pIdBucket ,String pExtencion ,String pContentType) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, MsUniversidadException;
}
