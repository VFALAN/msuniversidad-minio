package com.vf.dev.msuniversidadminio.service.FileObject;

import com.vf.dev.msuniversidadminio.util.MsUniversidadException;

import java.io.InputStream;

public interface IFileObjectService {
    public String saveFile(InputStream pInput, String pBucket, String file,String pContentType) throws MsUniversidadException;
}
