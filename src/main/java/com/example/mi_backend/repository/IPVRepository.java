package com.example.mi_backend.repository;

import com.example.mi_backend.entity.IPV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IPVRepository extends JpaRepository<IPV, Long> {
    List<IPV> findByUsuarioId(Long usuarioId);
    List<IPV> findByNombreContainingIgnoreCase(String nombre);
}