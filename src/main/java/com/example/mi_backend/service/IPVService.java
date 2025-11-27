package com.example.mi_backend.service;

import com.example.mi_backend.entity.IPV;
import com.example.mi_backend.entity.Producto;
import com.example.mi_backend.entity.Usuario;
import com.example.mi_backend.repository.IPVRepository;
import com.example.mi_backend.repository.ProductoRepository;
import com.example.mi_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IPVService {
    
    @Autowired
    private IPVRepository ipvRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public IPV crearIPV(Long usuarioId, IPV ipv) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        ipv.setUsuario(usuario);
        return ipvRepository.save(ipv);
    }
    
    public List<IPV> obtenerIPVsPorUsuario(Long usuarioId) {
        return ipvRepository.findByUsuarioId(usuarioId);
    }
    
    public Optional<IPV> obtenerIPVPorId(Long id) {
        return ipvRepository.findById(id);
    }
    
    public IPV agregarProductoAIPV(Long ipvId, Long productoId) {
        IPV ipv = ipvRepository.findById(ipvId)
            .orElseThrow(() -> new RuntimeException("IPV no encontrado"));
        
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        ipv.agregarProducto(producto);
        return ipvRepository.save(ipv);
    }
    
    public IPV removerProductoDeIPV(Long ipvId, Long productoId) {
        IPV ipv = ipvRepository.findById(ipvId)
            .orElseThrow(() -> new RuntimeException("IPV no encontrado"));
        
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        ipv.removerProducto(producto);
        return ipvRepository.save(ipv);
    }
    
    public IPV actualizarIPV(Long id, IPV ipvActualizado) {
        return ipvRepository.findById(id)
            .map(ipv -> {
                ipv.setNombre(ipvActualizado.getNombre());
                ipv.setDescripcion(ipvActualizado.getDescripcion());
                return ipvRepository.save(ipv);
            })
            .orElseThrow(() -> new RuntimeException("IPV no encontrado"));
    }
    
    public void eliminarIPV(Long id) {
        ipvRepository.deleteById(id);
    }
}