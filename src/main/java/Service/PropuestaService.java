/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author faxcundo
 */



import Repositorios.PropuestaRepository;
import iiss.api_culturarte.Propuesta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropuestaService {

    private final PropuestaRepository repo;

    public PropuestaService(PropuestaRepository repo) {
        this.repo = repo;
    }

    public List<Propuesta> listAll(String tituloFilter) {
        if (tituloFilter == null || tituloFilter.isBlank()) {
            return repo.findAll();
        } else {
            return repo.findByTituloContainingIgnoreCase(tituloFilter);
        }
    }

    public Propuesta create(Propuesta p) {
        return repo.save(p);
    }

    public Optional<Propuesta> findByTitulo(String titulo) {
        return repo.findById(titulo);
    }

    public Propuesta update(String titulo, Propuesta updated) {
        updated.setTitulo(titulo);
        return repo.save(updated);
    }

    public void delete(String titulo) {
        repo.deleteById(titulo);
    }
}
