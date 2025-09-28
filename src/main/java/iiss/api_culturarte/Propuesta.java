/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iiss.api_culturarte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "propuestas")
public class Propuesta {

    @Id
    private String titulo;

    @Column(length = 4000)
    private String descripcion;

    private String lugar;

    private LocalDate fechaRealizar;

    @OneToMany(mappedBy = "propuestaColaborada", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Colaboracion> colaboraciones;
    
    private float precioEntrada;

    private float montoReunir;

    private LocalDate fechaPublicacion;

    private String categoria;

    private String estado;
    
    private float dineroRecaudado = 0;

    @ElementCollection
    @CollectionTable(name = "propuesta_tipos_retorno", joinColumns = @JoinColumn(name = "propuesta_titulo"))
    @Column(name = "tipo_retorno")
    private List<String> tiposRetorno;

    public Propuesta() {
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDate getFechaRealizar() {
        return fechaRealizar;
    }

    public void setFechaRealizar(LocalDate fechaRealizar) {
        this.fechaRealizar = fechaRealizar;
    }

    public float getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(float precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public float getMontoReunir() {
        return montoReunir;
    }

    public void setMontoReunir(float montoReunir) {
        this.montoReunir = montoReunir;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getDineroRecaudado() {
        return dineroRecaudado;
    }

    public void setDineroRecaudado(float dineroRecaudado) {
        this.dineroRecaudado = dineroRecaudado;
    }

    public List<String> getTiposRetorno() {
        return tiposRetorno;
    }

    public void setTiposRetorno(List<String> tiposRetorno) {
        this.tiposRetorno = tiposRetorno;
    }
}