package co.com.softtek.prueba.controller;

import co.com.softtek.prueba.entity.Persona;
import co.com.softtek.prueba.service.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("api/personas")
public class PersonaController {

    private final PersonaService service;

    String salida = "" ;
    String resultado = "";
    Map<String,String> respuesta = null;

    @Autowired
    public PersonaController(PersonaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> consultaPersona(@PathVariable("id") @NotBlank String id ){
        return this.service.obtenerPersona(id).map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Map<String, String> crearPersona(@Valid Persona persona){
        this.respuesta = new HashMap<>();
        this.resultado = "Operacion Fallida, Persona ya registrada ";
        this.salida = "Creacion";
        Optional<Persona> compare = this.service.obtenerPersona(persona.getDni());
        if (!compare.isPresent() ){
            this.resultado = this.service.creaPersona(persona);
        }
        this.respuesta.put(this.salida,this.resultado);
        return  this.respuesta;
    }

    @PutMapping("")
    public Map<String, String> modificaPersona(@Valid Persona persona){
        this.respuesta = new HashMap<>();
        this.salida = "Modificacion";
        this.resultado = "Operacion Fallida, no existe o no hay cambio en datos ";
        Optional<Persona> compare = this.service.obtenerPersona(persona.getDni());
        if (compare.isPresent() && !compare.get().equals(persona)){
            this.resultado = this.service.modificaPersona(persona);
        }
        this.respuesta.put(this.salida,this.resultado);
        return  this.respuesta;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> borrarPersona(@PathVariable("id") @NotBlank String id){
        this.respuesta = new HashMap<>();
        this.salida = "Borrado ";
        this.resultado = "Operacion Fallida";
        this.resultado =  this.service.borraPersona(id);
        this.respuesta.put(this.salida,this.resultado);
        return  this.respuesta;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Map<String, String> handleValidationExceptions(
            BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
            Exception.class)
    public Map<String, String> Exception(
            Exception ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "Servicio no disponible";
        String errorMessage = "Estamos presentando fallos, Por favor comunicarse con el proveedor de servicios";
        errors.put(fieldName, errorMessage);

        return errors;
    }


}
