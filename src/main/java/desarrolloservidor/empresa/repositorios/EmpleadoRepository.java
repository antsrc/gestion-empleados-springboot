package desarrolloservidor.empresa.repositorios;

import desarrolloservidor.empresa.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
	List<Empleado> findByNombreContaining(String nombre);

	List<Empleado> findByDniContaining(String dni);

	List<Empleado> findBySexo(Character sexo);

	List<Empleado> findByCategoria(Integer categoria);

	List<Empleado> findByAntiguedad(Integer antiguedad);

	List<Empleado> findByDniContainingIgnoreCase(String dni);

	List<Empleado> findByNombreContainingIgnoreCase(String nombre);
}
