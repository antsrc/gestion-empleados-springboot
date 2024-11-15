package desarrolloservidor.empresa.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "empleados")
public class Empleado implements Persona {

    @Id
    @NotNull
    private String dni;

    @NotNull
    private String nombre;

    @NotNull
    @Pattern(regexp = "^[FfMm]$")
    private String sexo;

    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private Integer categoria;

    @NotNull
    @Min(value = 0)
    private Integer antiguedad;
    
    @OneToOne(mappedBy = "empleado")
    private Nomina nomina;

    public Empleado() {
    }

    public Empleado(String nombre, String dni, String sexo, Integer categoria, Integer antiguedad) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        this.antiguedad = antiguedad;
    }

    public Empleado(String nombre, String dni, String sexo) {
        this(nombre, dni, sexo, 1, 0);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getDni() {
        return dni;
    }

    @Override
    public String getSexo() {
        return sexo;
    }

    @Override
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }
}
