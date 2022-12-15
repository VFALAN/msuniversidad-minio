package com.vf.dev.msuniversidadminio.repository;

import com.vf.dev.msuniversidadminio.model.entity.TipoArchivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoArchivoRespository extends JpaRepository<TipoArchivoEntity , Integer> {
}
