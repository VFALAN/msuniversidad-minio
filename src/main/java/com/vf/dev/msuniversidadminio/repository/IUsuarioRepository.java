package com.vf.dev.msuniversidadminio.repository;

import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
}
