package iiss.api_culturarte.Controller;

import iiss.api_culturarte.Excepciones.ResourceNotFoundException;
import iiss.api_culturarte.Service.PropuestaService;
import iiss.api_culturarte.Propuesta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Propuesta> create(
            @RequestPart("propuesta") Propuesta propuesta,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        
        Propuesta created = service.create(propuesta, imagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping(value = "/{titulo}", produces = "application/json")
    public ResponseEntity<Propuesta> getJson(@PathVariable String titulo) {
        return service.findByTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Propuesta no encontrada: " + titulo));
    }

    @GetMapping(value = "/{titulo}", produces = "image/jpeg")
    public ResponseEntity<byte[]> getImage(@PathVariable String titulo) {
        Propuesta p = service.findByTitulo(titulo)
                .orElseThrow(() -> new ResourceNotFoundException("Propuesta no encontrada: " + titulo));

        if (p.getImagen() == null) {
            throw new ResourceNotFoundException("La propuesta no tiene imagen: " + titulo);
        }

        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(p.getImagen());
    }
    
    @PatchMapping(value = "/{titulo}", consumes = "multipart/form-data")
    public ResponseEntity<Propuesta> update(
            @PathVariable String titulo,
            @RequestPart(value = "propuesta", required = false) Propuesta propuesta,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {

        return ResponseEntity.ok(service.update(titulo, propuesta, imagen));
    }

    @GetMapping("/{titulo}/total-recaudado")
    public ResponseEntity<?> getTotalRecaudado(@PathVariable String titulo) {
        return service.findByTitulo(titulo)
                .map(p -> ResponseEntity.ok(
                        Map.of("titulo", p.getTitulo(), "dineroRecaudado", p.getDineroRecaudado())
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Propuesta no encontrada: " + titulo));
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
