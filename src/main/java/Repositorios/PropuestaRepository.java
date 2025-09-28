/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorios;

/**
 *
 * @author faxcundo
 */

import iiss.api_culturarte.Propuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropuestaRepository extends JpaRepository<Propuesta, String> {
    List<Propuesta> findByTituloContainingIgnoreCase(String titulo);
}
