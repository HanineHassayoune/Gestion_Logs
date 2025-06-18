package edu.polytech.springappb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/logs_app_b")
public class App_B_Controller {
    private static final Logger logger = LoggerFactory.getLogger(App_B_Controller.class);
    @GetMapping("/error")
    public String errorLog() {
        try {
            // Simuler une exception
            throw new NullPointerException("Exemple d'exception pour test Fluent Bit");
        } catch (Exception e) {
            LogModel logModel = new LogModel("Ceci est un log ERROR depuis Spring Boot");
            // Ajouter les détails de l'exception dans le message
            logger.error("{} - Exception: {}", logModel, e.getClass().getSimpleName(), e);
        }

        return "Log ERROR généré avec exception";
    }
}






