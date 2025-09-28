package Controller;

import Excepciones.ResourceNotFoundException;
import Service.PropuestaService;
import iiss.api_culturarte.Propuesta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propuestas")
public class PropuestaController {

    private final PropuestaService service;

    public PropuestaController(PropuestaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Propuesta> list(@RequestParam(required = false) String titulo) {
        return service.listAll(titulo);
    }

    @PostMapping
    public ResponseEntity<Propuesta> create(@RequestBody Propuesta p) {
        Propuesta created = service.create(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Propuesta> get(@PathVariable String titulo) {
        return service.findByTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Propuesta no encontrada: " + titulo));
    }


    @PutMapping("/{titulo}")
    public ResponseEntity<Propuesta> update(@PathVariable String titulo, @RequestBody Propuesta p) {
        if (service.findByTitulo(titulo).isEmpty()) {
            throw new ResourceNotFoundException("Propuesta no encontrada: " + titulo);
        }
        return ResponseEntity.ok(service.update(titulo, p));
    }


    @DeleteMapping("/{titulo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String titulo) {
        if (service.findByTitulo(titulo).isEmpty()) {
            throw new ResourceNotFoundException("Propuesta no encontrada: " + titulo);
        }
        service.delete(titulo);
    }
}
