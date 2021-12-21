package co.com.softtek.prueba.service;

import co.com.softtek.prueba.entity.Persona;
import co.com.softtek.prueba.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository repository;

    @Autowired
    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }

    public String creaPersona(Persona persona){
        this.repository.save(persona);
        return "Operacion Exitosa";
    }

    public Optional<Persona> obtenerPersona(String id){
        return this.repository.findById(id);
    }

    public String modificaPersona( Persona persona){
        this.repository.save(persona);
        return "Operacion Exitosa";
    }

    public String borraPersona(String id){
        this.repository.deleteById(id);
        return "Operacion Exitosa";
    }
}
