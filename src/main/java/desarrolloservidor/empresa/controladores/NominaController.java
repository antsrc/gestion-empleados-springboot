package desarrolloservidor.empresa.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import desarrolloservidor.empresa.servicios.NominaService;


@Controller
@RequestMapping("/empresa/nominas")
public class NominaController {

    @Autowired
    private NominaService nominaService;

    @GetMapping("/buscar")
    public String buscarSalario() {
        return "buscarSalario";
    }

    @PostMapping("/resultado-busqueda")
    public String mostrarSalario(@RequestParam("dni") String dni, Model model) {
        Integer salario = nominaService.obtenerSalario(dni);
        model.addAttribute("salario", salario);
        model.addAttribute("dni", dni);
        return "mostrarSalario";
    }
}
