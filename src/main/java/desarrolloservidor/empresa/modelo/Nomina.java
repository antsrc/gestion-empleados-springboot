package desarrolloservidor.empresa.modelo;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "nominas")
public class Nomina {

    @Id
    @NotNull
    @Column(name = "empleado_dni", insertable = false, updatable = false)
    private String dni;

    @NotNull
    private Integer sueldo;
    
    @OneToOne
    private Empleado empleado;
    
    public Nomina() {
    }
    
    public Nomina(String dni, Integer sueldo) {
    	this.dni = dni;
    	this.sueldo = sueldo;
    }
   
    public String getDni() {
        return dni;
    }

    public Integer getSueldo() {
        return sueldo;
    }

}
