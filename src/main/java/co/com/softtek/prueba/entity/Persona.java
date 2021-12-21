package co.com.softtek.prueba.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "PERSONA")
@Data
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DNI") @NotBlank(message = "DNI no debe estar Vacío")
    private String dni ;

    @Column( name = "NOMBRE") @NotBlank(message = "Nombre no debe estar Vacío")
    private String nombre;

    @Column(name = "APELLIDO") @NotBlank(message = "Apellido no debe estar Vacío")
    private String apellido;

    @Column(name = "EMPLEADO")
    private boolean empleado;
}
