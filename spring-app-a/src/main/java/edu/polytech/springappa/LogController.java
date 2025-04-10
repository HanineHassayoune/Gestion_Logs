package edu.polytech.springappa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/info")
    public String infoLog() {
        logger.info("Ceci est un log INFO depuis Spring Boot.");
        return "Log INFO généré";
    }

    @GetMapping("/error")
    public String errorLog() {
        logger.error("Ceci est un log ERROR depuis Spring Boot.");
        return "Log ERROR généré";
    }

    @GetMapping("/warn")
    public String warnLog() {
        logger.warn("Ceci est un log WARN depuis Spring Boot.");
        return "Log WARN généré";
    }
}
