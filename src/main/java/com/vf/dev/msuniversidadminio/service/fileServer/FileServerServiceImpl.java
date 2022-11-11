package com.vf.dev.msuniversidadminio.service.fileServer;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import com.vf.dev.msuniversidadminio.service.FileObject.IFileObjectService;
import com.vf.dev.msuniversidadminio.service.archivo.IArchivoService;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import com.vf.dev.msuniversidadminio.util.emuns.EBuckets;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
@Slf4j
public class FileServerServiceImpl implements IFileServerService {
    @Autowired
    private IFileObjectService iFileObjectService;

    @Autowired
    private IArchivoService iArchivoService;

    @Autowired
    private MinioClient minioClient;

    @Override
    public String saveFile(MultipartFile file, UsuarioEntity pUsario, String pTipoArchivo, Integer pIdBucket, String pExtencion, String pContentType) throws MsUniversidadException {
        try {
            String mBucketName = obtenerBucket(pIdBucket);
            if (mBucketName != null) {
                String mRutaStr = "";
                mRutaStr += pUsario.getMatricula().concat("/");
                mRutaStr += pUsario.getCurp().concat("/");
                mRutaStr += pTipoArchivo.concat("/");
                mRutaStr += file.getOriginalFilename();

                String mRutaFinal = this.iFileObjectService.saveFile(file.getInputStream(), mBucketName, mRutaStr, pContentType);
                ArchivoEntity mArchivoEntity = ArchivoEntity.builder()
                        .usuario(pUsario)
                        .bucket(mBucketName)
                        .extencionArchivo(pExtencion)
                        .nombreArchivo(file.getOriginalFilename())
                        .tipoArchivo(pTipoArchivo)
                        .ruta(mRutaFinal)
                        .build();
                mArchivoEntity.setFechaAlta(new Date());
                mArchivoEntity.setIndActivo(true);
                this.iArchivoService.save(mArchivoEntity);
                return mRutaFinal;

            } else {
                throw new MsUniversidadException("No se encontro el Bucket Con El Id: " + pIdBucket, " ");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new MsUniversidadException("Error al obtener el Input Stream del MultipartFile");
        }

    }


    private String obtenerBucket(Integer pIdBucket) {
        return switch (pIdBucket) {
            case 1 -> EBuckets.BUCKET_REPORTES.getName();
            case 2 -> EBuckets.BUCKET_FORMATOS.getName();
            case 3 -> EBuckets.BUCKETS_INFORMACION_PERSONAL.getName();
            case 4 -> EBuckets.BUCKET_TAREAS.getName();
            default -> null;
        };
    }


}
