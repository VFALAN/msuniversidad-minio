package com.vf.dev.msuniversidadminio.controller;

import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import com.vf.dev.msuniversidadminio.service.fileServer.IFileServerService;
import com.vf.dev.msuniversidadminio.service.usuario.IUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    private IFileServerService iFileServerService;
    @Autowired
    private IUsuarioService iUsuarioService;

    @PostMapping
    ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile pFile,
                               @RequestParam("idUsuario") Integer pIdUsuario,
                               @RequestParam("tipoArchivo") String pTipoArchivo,
                               @RequestParam("idBucket") Integer pIdBucket,
                               @RequestParam("extencion") String pExtencion

    ) {
        try {
            UsuarioEntity usuarioEntity = this.iUsuarioService.findById(pIdUsuario);
            String resultado = this.iFileServerService.saveFile(pFile,usuarioEntity,pTipoArchivo,pIdBucket,pExtencion,pFile.getContentType());
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<String>("Algo Salio Mal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
