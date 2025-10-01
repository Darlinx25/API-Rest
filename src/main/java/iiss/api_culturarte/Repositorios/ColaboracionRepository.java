/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iiss.api_culturarte.Repositorios;
/**
 *
 * @author faxcundo
 */
import iiss.api_culturarte.Colaboracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboracionRepository extends JpaRepository<Colaboracion, Long> {
    
}

