package desarrolloservidor.empresa.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desarrolloservidor.empresa.modelo.Empleado;
import desarrolloservidor.empresa.repositorios.EmpleadoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	public List<Empleado> obtenerEmpleados() {
		return empleadoRepository.findAll();
	}

	public Empleado obtenerEmpleado(String dni) {
		Optional<Empleado> empleadoOpt = empleadoRepository.findById(dni);
		return empleadoOpt.orElse(null);
	}

	public List<Empleado> obtenerEmpleadosFiltrados(String nombre, String dni, String sexo, Integer categoria,
			Integer antiguedad) {
		List<Empleado> empleadosFiltrados = (!nombre.trim().isEmpty())
				? empleadoRepository.findByNombreContainingIgnoreCase(nombre)
				: empleadoRepository.findAll();

		if (!dni.trim().isEmpty()) {
			empleadosFiltrados.retainAll(empleadoRepository.findByDniContainingIgnoreCase(dni));
		}

		if (!sexo.trim().isEmpty()) {
			empleadosFiltrados.retainAll(empleadoRepository.findBySexo(sexo.charAt(0)));
		}

		if (categoria != null) {
			empleadosFiltrados.retainAll(empleadoRepository.findByCategoria(categoria));
		}

		if (antiguedad != null) {
			empleadosFiltrados.retainAll(empleadoRepository.findByAntiguedad(antiguedad));
		}

		return empleadosFiltrados;
	}

	public boolean actualizarEmpleado(String dni, String nombre, String sexo, Integer categoria, Integer antiguedad) {
		Optional<Empleado> empleadoOpt = empleadoRepository.findById(dni);
		if (empleadoOpt.isPresent()) {
			Empleado empleado = empleadoOpt.get();
			empleado.setNombre(nombre);
			empleado.setSexo(sexo);
			empleado.setCategoria(categoria);
			empleado.setAntiguedad(antiguedad);
			empleadoRepository.save(empleado);
			return true;
		} else {
			return false;
		}
	}
}