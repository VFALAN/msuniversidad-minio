package com.vf.dev.msuniversidadminio.service.usuario;

import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import com.vf.dev.msuniversidadminio.repository.IUsuarioRepository;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public UsuarioEntity findById(Integer pUsuarioId) throws MsUniversidadException {
        Optional<UsuarioEntity> mOptionalUsaurioEntity = this.iUsuarioRepository.findById(pUsuarioId);
        if (mOptionalUsaurioEntity.isPresent()) {
            return mOptionalUsaurioEntity.get();
        } else {
            throw new MsUniversidadException("No se encontro el usaurio con el Id: " + pUsuarioId, "");
        }
    }
}
