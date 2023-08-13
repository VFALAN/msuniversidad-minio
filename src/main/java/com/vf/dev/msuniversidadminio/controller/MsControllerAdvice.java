package com.vf.dev.msuniversidadminio.controller;

import com.vf.dev.msuniversidadminio.util.HttpServletUtil;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class MsControllerAdvice {
    @Value("${msuniversidad.version}")
    private String version;

    @ExceptionHandler({MsUniversidadException.class})
    public ResponseEntity<?> handlerException(MsUniversidadException ex, HttpServletRequest pHttpServletRequest) {
        log.info("Error in bakc in version: " + this.version + ex.getMessage());
        log.info(HttpServletUtil.requestData(pHttpServletRequest));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handlerException(Exception ex, HttpServletRequest pHttpServletRequest) {
        log.info("Error in bakc in version: " + this.version + ex.getMessage());
        log.info(HttpServletUtil.requestData(pHttpServletRequest));
        return new ResponseEntity<>("Ha Ocurrido un error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
