package desarrolloservidor.empresa.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import desarrolloservidor.empresa.modelo.Empleado;
import desarrolloservidor.empresa.servicios.EmpleadoService;

import java.util.List;

@Controller
@RequestMapping("/empresa/empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/lista")
	public String listaEmpleados(@RequestParam(required = false) String cambios, Model model) {
		List<Empleado> empleados = empleadoService.obtenerEmpleados();
		model.addAttribute("empleados", empleados);
	    if (cambios != null && cambios.equals("guardados")) {
	        model.addAttribute("exito", true);
	    }
		return "listaEmpleados";
	}

	@GetMapping("/buscar")
	public String buscarEmpleados() {
		return "buscarEmpleados";
	}

	@PostMapping("/resultado-busqueda")
	public String mostrarEmpleadosFiltrados(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String dni, @RequestParam(required = false) String sexo,
			@RequestParam(required = false) Integer categoria, @RequestParam(required = false) Integer antiguedad,
			Model model) {
		List<Empleado> empleados = empleadoService.obtenerEmpleadosFiltrados(nombre, dni, sexo, categoria, antiguedad);
		model.addAttribute("empleados", empleados);
		return "listaEmpleados";
	}

	@GetMapping("/modificar/{dni}")
	public String modificarEmpleado(@PathVariable String dni, Model model) {
		Empleado empleado = empleadoService.obtenerEmpleado(dni);
		model.addAttribute("empleado", empleado);
		return "modificarEmpleado";
	}

	@PostMapping("/enviar-cambios")
	public String enviarCambios(@RequestParam String dni, @RequestParam String nombre, @RequestParam String sexo,
	                             @RequestParam Integer categoria, @RequestParam Integer antiguedad, Model model) {
	    empleadoService.actualizarEmpleado(dni, nombre, sexo, categoria, antiguedad);
	    return "redirect:/empresa/empleados/lista?cambios=guardados";
	}

}
