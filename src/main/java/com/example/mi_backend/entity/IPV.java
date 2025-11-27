package com.example.mi_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ipvs")
public class IPV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String descripcion;
    
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"ipvs"}) // ← Evita mostrar los IPVs del usuario
    private Usuario usuario;
    
    
    @ManyToMany
    @JoinTable(
        name = "ipv_productos",
        joinColumns = @JoinColumn(name = "ipv_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    @JsonIgnoreProperties({"ipvs"}) // ← Evita mostrar los IPVs del producto
    private List<Producto> productos = new ArrayList<>();
    
    // Constructores y métodos...
    public IPV() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public IPV(String nombre, String descripcion, Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
        producto.getIpvs().add(this);
    }
    
    public void removerProducto(Producto producto) {
        this.productos.remove(producto);
        producto.getIpvs().remove(this);
    }
    
    // Getters y Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
}