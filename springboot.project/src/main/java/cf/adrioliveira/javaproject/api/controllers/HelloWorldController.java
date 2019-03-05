package cf.adrioliveira.javaproject.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello-world")
@CrossOrigin(origins = "*")
public class HelloWorldController {

    @GetMapping("/")
    public ResponseEntity<String> hello(@RequestParam(name = "nome", defaultValue = "Sem Nome") String nome){
        return new ResponseEntity<>("Olá " + nome, HttpStatus.OK);
    }
}
