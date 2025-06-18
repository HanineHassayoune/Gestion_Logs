package edu.polytech.springappa;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
@RequestMapping("/api/logs")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    @GetMapping("/info")
    public String infoLog() throws JsonProcessingException {
        LogModel logModel = new LogModel("Ceci est un log INFO depuis Spring Boot");
        //ObjectMapper objectMapper = new ObjectMapper();
        //String log = objectMapper.writeValueAsString(logModel);

        logger.info("{}",logModel);
        return "Log INFO généré";
    }

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


    @GetMapping("/bad-request")
    public ResponseEntity<?> badRequestExample() {
        try {
            throw new IllegalArgumentException("Paramètre invalide fourni.");
        } catch (IllegalArgumentException e) {
            LogModel log = new LogModel("Erreur de type BadRequest");
            logger.error("CustomLog - {} - Exception: {}", log, e.getMessage(), e);
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }

    @GetMapping("/division-error")
    public ResponseEntity<?> divisionError() {
        try {
            int result = 10 / 0;
            return ResponseEntity.ok(result);
        } catch (ArithmeticException e) {
            LogModel log = new LogModel("Erreur de division par zéro");
            logger.error("CustomLog - {} - Exception: {}", log, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne: " + e.getMessage());
        }
    }

    @GetMapping("/index-error")
    public ResponseEntity<?> indexError() {
        try {
            int[] numbers = {1, 2, 3};
            int number = numbers[5]; // out of bounds
            return ResponseEntity.ok(number);
        } catch (IndexOutOfBoundsException e) {
            LogModel log = new LogModel("Erreur d’index hors limites");
            logger.error("CustomLog - {} - Exception: {}", log, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne: " + e.getMessage());
        }
    }

    private final Map<Long, String> fakeDb = Map.of(
            1L, "Objet 1",
            2L, "Objet 2"
    );

    @GetMapping("/{id}")
    public ResponseEntity<?> getObjectById(@PathVariable Long id) {
        String result = fakeDb.get(id);

        if (result == null) {
            String message = "Objet avec ID " + id + " introuvable";

            // Log avant de lever l'exception
            LogModel log = new LogModel(message);
            logger.error("CustomLog - {} - Niveau: ERROR - Projet: spring-app-a", log);

            // 👉 Lancer une vraie exception
            throw new ResourceNotFoundException(message);
        }

        return ResponseEntity.ok(result);
    }


   /* @GetMapping("/error")
    public String throwException() {
        try {
            String test = null;
            test.length(); // Ceci provoque un NullPointerException
        } catch (Exception e) {
            logger.error("Exception capturée pour test : ", e);
        }
        return "Exception générée et loggée";
    }*/

    @GetMapping("/test")
    public String test() {
        return "Ça marche !";
    }

    @GetMapping("/warn")
    public String warnLog() throws JsonProcessingException {
        LogModel logModel = new LogModel("Ceci est un log WARN depuis Spring Boot");
        //ObjectMapper objectMapper = new ObjectMapper();
        //String log = objectMapper.writeValueAsString(logModel);

        logger.warn("{}",logModel);
        return "Log WARN généré";
    }
}
