package com.vf.dev.msuniversidadminio.service.tipoArchivo;

import com.vf.dev.msuniversidadminio.model.entity.TipoArchivoEntity;
import com.vf.dev.msuniversidadminio.repository.ITipoArchivoRespository;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoArchivoServiceImpl implements ITipoArchivoService {
    @Autowired
    ITipoArchivoRespository iTipoArchivoRespository;

    @Override
    public TipoArchivoEntity findById(Integer pTipoArchivo) throws MsUniversidadException {
        Optional<TipoArchivoEntity> mTipoArchivoOptional = this.iTipoArchivoRespository.findById(pTipoArchivo);
        if (mTipoArchivoOptional.isPresent()) {
            return mTipoArchivoOptional.get();
        } else {
            throw new MsUniversidadException("No se encrontro ningun tipo de archivo con el id: " + pTipoArchivo, "");
        }
    }
}
