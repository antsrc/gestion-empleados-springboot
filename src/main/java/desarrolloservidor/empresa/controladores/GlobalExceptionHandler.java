package desarrolloservidor.empresa.controladores;

import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFound(NoResourceFoundException ex, Model model) {
        model.addAttribute("mensaje", "Recurso no encontrado");
        return "error";
    }
    
    @ExceptionHandler(TransactionSystemException.class)
    public String handleTransactionSystemException(TransactionSystemException ex, Model model) {
        model.addAttribute("mensaje", "Problema al procesar la solicitud, es posible contuviera datos no válidos");
        ex.printStackTrace();
        return "error";
    }
	
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
    	System.out.println(ex);
        model.addAttribute("mensaje", "Error inesperado, consulte con el soporte técnico");
        return "error";
    }
    
    
}
