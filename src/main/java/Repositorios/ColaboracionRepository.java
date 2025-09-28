/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositorios;
/**
 *
 * @author faxcundo
 */
import iiss.api_culturarte.Colaboracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ColaboracionRepository extends JpaRepository<Colaboracion, Long> {

    @Query("SELECT COALESCE(SUM(c.monto), 0) FROM Colaboracion c")
    float totalMonto();
}

