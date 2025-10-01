/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iiss.api_culturarte.Service;

/**
 *
 * @author faxcundo
 */



import iiss.api_culturarte.Excepciones.ResourceNotFoundException;
import iiss.api_culturarte.Repositorios.PropuestaRepository;
import iiss.api_culturarte.Propuesta;
import java.io.IOException;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

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

    public Propuesta create(Propuesta propuesta, MultipartFile imagen) {
        if (propuesta.getEstado() == null) {
            propuesta.setEstado("publicada");
            propuesta.setFechaPublicacion(LocalDate.now());
        }
        if (imagen != null && !imagen.isEmpty()) {
            try {
                propuesta.setImagen(imagen.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar imagen", e);
            }
        }
        return repo.save(propuesta);
    }

    public Optional<Propuesta> findByTitulo(String titulo) {
    List<Propuesta> resultados = repo.findByTituloContainingIgnoreCase(titulo);
    if (resultados.isEmpty()) {
        return Optional.empty();
    } else {
        return Optional.of(resultados.get(0)); // devuelve el primero que coincida
    }
    }


    public Propuesta update(String titulo, Propuesta propuestaUpdate, MultipartFile imagen) {
        Propuesta propuesta = repo.findById(titulo)
                .orElseThrow(() -> new ResourceNotFoundException("Propuesta no encontrada: " + titulo));
        
        if (propuestaUpdate != null) {
            if (propuestaUpdate.getDescripcion() != null) propuesta.setDescripcion(propuestaUpdate.getDescripcion());
            if (propuestaUpdate.getLugar() != null) propuesta.setLugar(propuestaUpdate.getLugar());
            if (propuestaUpdate.getFechaRealizar() != null) propuesta.setFechaRealizar(propuestaUpdate.getFechaRealizar());
            if (propuestaUpdate.getCategoria() != null) propuesta.setCategoria(propuestaUpdate.getCategoria());
            if (propuestaUpdate.getEstado() != null) propuesta.setEstado(propuestaUpdate.getEstado());
            if (propuestaUpdate.getTiposRetorno() != null) propuesta.setTiposRetorno(propuestaUpdate.getTiposRetorno());
        }

    if (imagen != null && !imagen.isEmpty()) {
        try {
            propuesta.setImagen(imagen.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar imagen", e);
        }
    }

    return repo.save(propuesta);
}

    public void delete(String titulo) {
        repo.deleteById(titulo);
    }
}
