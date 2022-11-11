package com.vf.dev.msuniversidadminio.repository;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArchivoRepository extends JpaRepository<ArchivoEntity, Integer> {
}
