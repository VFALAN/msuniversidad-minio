package com.vf.dev.msuniversidadminio.service.fileServer;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import com.vf.dev.msuniversidadminio.model.entity.TipoArchivoEntity;
import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import com.vf.dev.msuniversidadminio.model.model.FileEvidenceDTO;
import com.vf.dev.msuniversidadminio.repository.IArchivoRepository;
import com.vf.dev.msuniversidadminio.service.FileObject.IFileObjectService;
import com.vf.dev.msuniversidadminio.service.archivo.IArchivoService;
import com.vf.dev.msuniversidadminio.service.tipoArchivo.ITipoArchivoService;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import com.vf.dev.msuniversidadminio.util.emuns.EBuckets;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FileServerServiceImpl implements IFileServerService {
    @Autowired
    private IFileObjectService iFileObjectService;

    @Autowired
    private IArchivoService iArchivoService;
    @Autowired
    private ITipoArchivoService iTipoArchivoService;
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private IArchivoRepository iArchivoRepository;

    @Override
    public String saveFile(MultipartFile file, UsuarioEntity pUsario, Integer pTipoArchivo, Integer pIdBucket, String pExtencion, String pContentType, String ruta) throws MsUniversidadException {
        try {
            String mBucketName = obtenerBucket(pIdBucket);
            TipoArchivoEntity mTipoArchivo = this.iTipoArchivoService.findById(pTipoArchivo);
            if (mBucketName != null) {
                ruta += mTipoArchivo.getClave().concat(".").concat(pExtencion);

                String mRutaFinal = this.iFileObjectService.saveFile(file.getInputStream(), mBucketName, ruta, pContentType);
                ArchivoEntity mArchivoEntity = ArchivoEntity.builder()
                        .usuario(pUsario)
                        .idTipoArchivo(mTipoArchivo)
                        .tipoArchivo(mTipoArchivo.getClave())
                        .bucket(mBucketName)
                        .extencionArchivo(pExtencion)
                        .mineType(pContentType)
                        .nombreArchivo(mTipoArchivo.getClave().concat(pExtencion))
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

    @Override
    public InputStream getFile(ArchivoEntity pArchivo) throws MsUniversidadException {
        try {
            return this.minioClient.getObject(GetObjectArgs.builder()
                    .bucket(pArchivo.getBucket())
                    .object(pArchivo.getRuta())
                    .build());
        } catch (Exception e) {
            throw new MsUniversidadException("Error al obtener el archivo " + pArchivo.getNombreArchivo(), "");
        }
    }

    @Override
    public String updateFile(MultipartFile pFile, ArchivoEntity pArchivoEntity) throws MsUniversidadException {
        try {
            this.deleteFile(pArchivoEntity);
            return this.minioClient.putObject(PutObjectArgs.builder()
                    .bucket(pArchivoEntity.getBucket())
                    .contentType(pFile.getContentType())
                    .stream(pFile.getInputStream(), -1, 10485760)
                    .object(pArchivoEntity.getRuta())
                    .build()).object();
        } catch (Exception e) {
            throw new MsUniversidadException("No se logro actualizar le archivo: " + pArchivoEntity.getIdArchivo(), "");
        }
    }

    @Override
    public void deleteFile(ArchivoEntity pArchivoEntity) throws MsUniversidadException {
        try {
            this.minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(pArchivoEntity.getBucket())
                    .object(pArchivoEntity.getRuta())
                    .build());
        } catch (Exception e) {
            throw new MsUniversidadException("Error al eliminar el archivo : " + pArchivoEntity.getIdArchivo(), "");
        }
    }

    @Override
    public List<FileEvidenceDTO> getByUserTypeFileAndLimit(Integer pIdUsuario, Integer pIdTipoArchivo, Integer pLimit) {
        List<ArchivoEntity> mArchivoEntityList = this.iArchivoRepository.findByUserAndTipoArchivoWhitLimit(pIdUsuario, pIdTipoArchivo, pLimit);
        return this.archivoListToDtoList(mArchivoEntityList);

    }

    @Override
    public List<FileEvidenceDTO> getByUser(UsuarioEntity pUsuarioEntity) {
        var listaArchivos = this.iArchivoRepository.findByUser(pUsuarioEntity);
        return this.archivoListToDtoList(listaArchivos);
    }

    private List<FileEvidenceDTO> archivoListToDtoList(List<ArchivoEntity> pArchivoEntity) {
        List<FileEvidenceDTO> fileEvidenceDTOS = new ArrayList<>();
        pArchivoEntity.forEach(a -> {
            try {
                String strDownload = this.iFileObjectService.getUrl(a);
                fileEvidenceDTOS.add(FileEvidenceDTO.builder()
                        .downloadUrl(strDownload)
                        .id(a.getIdArchivo())
                        .extencion(a.getExtencionArchivo())
                        .nombre(a.getNombreArchivo())
                        .tipoArchivo(a.getTipoArchivo())
                        .idTipoArchivo(a.getIdTipoArchivo().getIdTipoArchivo())
                        .mineType(a.getMineType())
                        .path(a.getRuta())
                        .build());
            } catch (ServerException e) {
                throw new RuntimeException(e);
            } catch (InsufficientDataException e) {
                throw new RuntimeException(e);
            } catch (ErrorResponseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            } catch (InvalidResponseException e) {
                throw new RuntimeException(e);
            } catch (XmlParserException e) {
                throw new RuntimeException(e);
            } catch (InternalException e) {
                throw new RuntimeException(e);
            }
        });

        return fileEvidenceDTOS;
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
