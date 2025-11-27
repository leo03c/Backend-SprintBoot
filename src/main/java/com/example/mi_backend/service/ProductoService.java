package com.example.mi_backend.service;

import com.example.mi_backend.entity.Producto;
import com.example.mi_backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }
    
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }
    
    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }
    
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
            .map(producto -> {
                producto.setNombre(productoActualizado.getNombre());
                producto.setDescripcion(productoActualizado.getDescripcion());
                producto.setCategoria(productoActualizado.getCategoria());
                producto.setPrecioBase(productoActualizado.getPrecioBase());
                return productoRepository.save(producto);
            })
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}