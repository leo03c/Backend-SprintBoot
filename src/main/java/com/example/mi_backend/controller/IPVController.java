package com.example.mi_backend.controller;

import com.example.mi_backend.entity.IPV;
import com.example.mi_backend.service.IPVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ipvs")
public class IPVController {
    
    @Autowired
    private IPVService ipvService;
    
    @PostMapping("/usuario/{usuarioId}")
    public IPV crearIPV(@PathVariable Long usuarioId, @RequestBody IPV ipv) {
        return ipvService.crearIPV(usuarioId, ipv);
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public List<IPV> obtenerIPVsPorUsuario(@PathVariable Long usuarioId) {
        return ipvService.obtenerIPVsPorUsuario(usuarioId);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<IPV> obtenerIPVPorId(@PathVariable Long id) {
        Optional<IPV> ipv = ipvService.obtenerIPVPorId(id);
        return ipv.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{ipvId}/productos/{productoId}")
    public ResponseEntity<IPV> agregarProductoAIPV(@PathVariable Long ipvId, @PathVariable Long productoId) {
        try {
            IPV ipv = ipvService.agregarProductoAIPV(ipvId, productoId);
            return ResponseEntity.ok(ipv);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{ipvId}/productos/{productoId}")
    public ResponseEntity<IPV> removerProductoDeIPV(@PathVariable Long ipvId, @PathVariable Long productoId) {
        try {
            IPV ipv = ipvService.removerProductoDeIPV(ipvId, productoId);
            return ResponseEntity.ok(ipv);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<IPV> actualizarIPV(@PathVariable Long id, @RequestBody IPV ipv) {
        try {
            IPV ipvActualizado = ipvService.actualizarIPV(id, ipv);
            return ResponseEntity.ok(ipvActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarIPV(@PathVariable Long id) {
        try {
            ipvService.eliminarIPV(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}