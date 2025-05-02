package edu.polytech.springappa;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/logs")
public class LogControllerr {

    private static final Logger logger = LoggerFactory.getLogger(LogControllerr.class);

    @GetMapping("/info")
    public String infoLog() throws JsonProcessingException {
        LogModel logModel = new LogModel("Ceci est un log INFO depuis Spring Boot");
        //ObjectMapper objectMapper = new ObjectMapper();
        //String log = objectMapper.writeValueAsString(logModel);

        logger.info("{}",logModel);
        return "Log INFO généré";
    }

    @GetMapping("/error")
    public String errorLog() throws JsonProcessingException {
        LogModel logModel = new LogModel("Ceci est un log ERROR depuis Spring Boot");
        //ObjectMapper objectMapper = new ObjectMapper();
        //String log = objectMapper.writeValueAsString(logModel);

        logger.error("{}",logModel);
        return "Log ERROR généré";
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
