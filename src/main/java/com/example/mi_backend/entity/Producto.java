package com.example.mi_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    private String descripcion;
    
    @Column(nullable = false)
    private String categoria;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal precioBase;
    
    private String imagenUrl;
    
    // ✅ AGREGAR @JsonIgnore PARA EVITAR RECURSIÓN
    @JsonIgnore
    @ManyToMany(mappedBy = "productos")
    private List<IPV> ipvs = new ArrayList<>();
    
    // Constructores, Getters y Setters...
    public Producto() {}
    
    public Producto(String nombre, String descripcion, String categoria, BigDecimal precioBase) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioBase = precioBase;
        
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }
    public List<IPV> getIpvs() { return ipvs; }
    public void setIpvs(List<IPV> ipvs) { this.ipvs = ipvs; }
}