package com.vf.dev.msuniversidadminio.service.archivo;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import com.vf.dev.msuniversidadminio.repository.IArchivoRepository;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class ArchivoServiceImpl implements IArchivoService {
    @Autowired
    private IArchivoRepository iArchivoRepository;

    @Override
    public ArchivoEntity findById(Integer pIdArchivo) throws MsUniversidadException {
        Optional<ArchivoEntity> mOptionalArchivoEntity = iArchivoRepository.findById(pIdArchivo);
        if (mOptionalArchivoEntity.isPresent()) {
            return mOptionalArchivoEntity.get();
        } else {
            throw new MsUniversidadException("No se encuentra el archivo con el Id", "");
        }
    }

    @Override
    public ArchivoEntity save(ArchivoEntity pArchivoEntity) {
        return this.iArchivoRepository.save(pArchivoEntity);
    }

    @Override
    public boolean disabled(Integer pIdArchivo) throws MsUniversidadException {
        ArchivoEntity mArchivoEntity = this.findById(pIdArchivo);
        mArchivoEntity.setIndActivo(false);
        mArchivoEntity.setFechaBaja(new Date());
        this.iArchivoRepository.save(mArchivoEntity);
        return true;
    }
}
