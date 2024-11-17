package ru.kolivim.estimates.calculator.api.resource.front;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.estimates.calculator.api.dto.front.PersonDTO;

import java.util.List;

@RestController
@RequestMapping("front")
public interface FrontResource {

    @GetMapping()
    public ResponseEntity<PersonDTO> create();

    @GetMapping("/p")
    public PersonDTO createp();

    @GetMapping("/plist")
    public List<PersonDTO> createpl();

}


