package co.com.softtek.prueba.repository;

import co.com.softtek.prueba.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String>, JpaSpecificationExecutor<Persona> {
}
