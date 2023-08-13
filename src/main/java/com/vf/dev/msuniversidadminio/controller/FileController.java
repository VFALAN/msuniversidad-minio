package com.vf.dev.msuniversidadminio.controller;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import com.vf.dev.msuniversidadminio.model.model.FileEvidenceDTO;
import com.vf.dev.msuniversidadminio.service.archivo.IArchivoService;
import com.vf.dev.msuniversidadminio.service.fileServer.IFileServerService;
import com.vf.dev.msuniversidadminio.service.usuario.IUsuarioService;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import io.minio.errors.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileController {
    @Autowired
    private IFileServerService iFileServerService;
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IArchivoService iArchivoService;

    @PostMapping
    ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile pFile,
                               @RequestParam("idUsuario") Integer pIdUsuario,
                               @RequestParam("tipoArchivo") Integer pTipoArchivo,
                               @RequestParam("idBucket") Integer pIdBucket,
                               @RequestParam("extencion") String pExtencion,
                               @RequestParam("ruta") String pRuta

    ) throws MsUniversidadException, ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        UsuarioEntity usuarioEntity = this.iUsuarioService.findById(pIdUsuario);
        String resultado = this.iFileServerService.saveFile(pFile, usuarioEntity, pTipoArchivo, pIdBucket, pExtencion, pFile.getContentType(), pRuta);
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    @GetMapping
    ResponseEntity<?> getFile(@RequestParam("archivoId") Integer pArchivoId) throws MsUniversidadException {

        ArchivoEntity mArchivoiEntity = this.iArchivoService.findById(pArchivoId);
        InputStream mInputStream = this.iFileServerService.getFile(mArchivoiEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + mArchivoiEntity.getNombreArchivo());
        return new ResponseEntity<>(new InputStreamResource(mInputStream), headers, HttpStatus.OK);

    }

    @SneakyThrows
    @PutMapping
    ResponseEntity<?> updateFile(@RequestParam("file") MultipartFile pMultipartFile, @RequestParam("archivoId") Integer pIdArchivo) {

        ArchivoEntity mArchivoEntity = this.iArchivoService.findById(pIdArchivo);
        String ruta = this.iFileServerService.updateFile(pMultipartFile, mArchivoEntity);
        mArchivoEntity.setFechaActualizacion(new Date());
        this.iArchivoService.save(mArchivoEntity);
        return new ResponseEntity<String>(ruta, HttpStatus.OK);

    }

    @DeleteMapping("/disabledFile")
    ResponseEntity<Boolean> disabledFile(@RequestParam("idArchivo") Integer pIdArchivo) throws MsUniversidadException {
        var response = this.iArchivoService.disabled(pIdArchivo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping
    ResponseEntity<?> deleteFile(@RequestParam("idArchivo") Integer pIdArchivo) throws MsUniversidadException {

        ArchivoEntity mArchivoEntity = this.iArchivoService.findById(pIdArchivo);
        this.iFileServerService.deleteFile(mArchivoEntity);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @GetMapping("/getByUserAndType")
    ResponseEntity<List<FileEvidenceDTO>> getByUserAndType(@RequestParam("idTipoArchivo") Integer pIdTipoArchivo,
                                                           @RequestParam("idUsuario") Integer pIdUsuario,
                                                           @RequestParam(name = "limite", defaultValue = "1") Integer pLimite) {
        List<FileEvidenceDTO> response = this.iFileServerService.getByUserTypeFileAndLimit(pIdUsuario, pIdTipoArchivo, pLimite);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getFilesByUser")
    ResponseEntity<List<FileEvidenceDTO>> getFilesByUser(@RequestParam("idUsuario") Integer pIdUsuario) throws MsUniversidadException {
        var usuario = this.iUsuarioService.findById(pIdUsuario);
        var response = this.iFileServerService.getByUser(usuario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
