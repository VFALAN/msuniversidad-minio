package com.vf.dev.msuniversidadminio.repository;

import com.vf.dev.msuniversidadminio.model.entity.ArchivoEntity;
import com.vf.dev.msuniversidadminio.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArchivoRepository extends JpaRepository<ArchivoEntity, Integer> {


@Query(value="SELECT a.* FROM ARCHIVOS a where a.ID_USUARIO = :idUsuario AND a.ID_TIPO_ARCHIVO = :idTipoArchivo AND a.IND_ACTIVO = 1   limit :limite" ,nativeQuery = true)
    List<ArchivoEntity> findByUserAndTipoArchivoWhitLimit(@Param("idUsuario") Integer idUsuario ,
                                                          @Param("idTipoArchivo")Integer idTipoArchivo,
                                                          @Param("limite")Integer limite);

@Query("SELECT a FROM ArchivoEntity a WHERE a.indActivo = 1 AND a.usuario = :usuario")
    List<ArchivoEntity> findByUser(@Param("usuario")UsuarioEntity pUsuarioEntity);
}
