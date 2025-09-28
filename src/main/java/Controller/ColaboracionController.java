/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author faxcundo
 */

import Service.ColaboracionService;
import iiss.api_culturarte.Colaboracion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colaboraciones")
public class ColaboracionController {

    private final ColaboracionService service;

    public ColaboracionController(ColaboracionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Colaboracion> listAll() {
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<Colaboracion> create(@RequestBody Colaboracion c) {
        Colaboracion created = service.create(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/total")
    public ResponseEntity<Object> total() {
        float total = service.total();
        return ResponseEntity.ok(java.util.Map.of("acumulado", total));
    }
}

