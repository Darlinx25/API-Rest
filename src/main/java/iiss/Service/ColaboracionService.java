/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iiss.Service;

/**
 *
 * @author faxcundo
 */

import iiss.Repositorios.ColaboracionRepository;
import iiss.Repositorios.PropuestaRepository;
import iiss.api_culturarte.Colaboracion;
import iiss.api_culturarte.Propuesta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColaboracionService {

    private final ColaboracionRepository colRepo;
    private final PropuestaRepository propRepo;

    public ColaboracionService(ColaboracionRepository colRepo, PropuestaRepository propRepo) {
        this.colRepo = colRepo;
        this.propRepo = propRepo;
    }

    public List<Colaboracion> listAll() {
        return colRepo.findAll();
    }

    @Transactional
    public Colaboracion create(Colaboracion c) {
        Colaboracion saved = colRepo.save(c);

        Propuesta p = propRepo.findById(c.getPropuestaTitulo()).orElse(null);
        if (p != null) {
            float actual = p.getDineroRecaudado();
            p.setDineroRecaudado(actual + c.getMonto());
            propRepo.save(p);
        }

        return saved;
    }

    public float total() {
        return colRepo.totalMonto();
    }
}
