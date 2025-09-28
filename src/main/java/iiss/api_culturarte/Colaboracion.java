/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iiss.api_culturarte;

/**
 *
 * @author faxcundo
 */

import jakarta.persistence.*;

@Entity
@Table(name = "colaboraciones")
public class Colaboracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propuestaTitulo;

    private float monto;
    
    @ManyToOne
    private Propuesta propuestaColaborada;
     
    public Colaboracion() {
    }

    public Colaboracion(String propuestaTitulo, float monto) {
        this.propuestaTitulo = propuestaTitulo;
        this.monto = monto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropuestaTitulo() {
        return propuestaTitulo;
    }

    public void setPropuestaTitulo(String propuestaTitulo) {
        this.propuestaTitulo = propuestaTitulo;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
}
