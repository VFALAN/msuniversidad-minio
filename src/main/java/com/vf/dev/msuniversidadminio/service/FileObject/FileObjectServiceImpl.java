package com.vf.dev.msuniversidadminio.service.FileObject;

import com.vf.dev.msuniversidadminio.service.archivo.IArchivoService;
import com.vf.dev.msuniversidadminio.service.bucket.IBucketService;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class FileObjectServiceImpl implements IFileObjectService {
    @Autowired
    private IBucketService iBucketService;
    @Autowired
    private IArchivoService iArchivoService;
    @Autowired
    private MinioClient minioClient;


    @Override
    public String saveFile(InputStream pInput, String pBucket, String file, String pContentType) throws MsUniversidadException {
        if (!this.iBucketService.existBucket(pBucket)) {
            this.iBucketService.crearBucket(pBucket);
        }
        try {
            ObjectWriteResponse response = this.minioClient.putObject(PutObjectArgs.builder()
                    .bucket(pBucket)
                    .object(file)

                    .stream(pInput, -1, 10485760)
                    .contentType(pContentType)
                    .build());
            return response.object();
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
