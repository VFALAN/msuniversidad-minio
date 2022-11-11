package com.vf.dev.msuniversidadminio.service.usuario;

import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import com.vf.dev.msuniversidadminio.util.MsUniversidadException;

public interface IUsuarioService {

    public UsuarioEntity findById(Integer pUsuarioId) throws MsUniversidadException;
}
