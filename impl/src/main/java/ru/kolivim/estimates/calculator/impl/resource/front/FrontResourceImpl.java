package ru.kolivim.estimates.calculator.impl.resource.front;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kolivim.estimates.calculator.api.dto.front.PersonDTO;
import ru.kolivim.estimates.calculator.api.resource.front.FrontResource;
import ru.kolivim.estimates.calculator.impl.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FrontResourceImpl implements FrontResource {

//    private final UserService userService;

    @Override
    public ResponseEntity<PersonDTO> create() {
        log.info("FrontResourceImpl:create() startMethod");

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId("942");
        personDTO.setEmail("NGover@mattis.net");
        personDTO.setFirstName("Croco");
        personDTO.setLastName("Velasco");
        personDTO.setPhone("(186)049-8265");

        return ResponseEntity.ok(personDTO);
    }

    @Override
    public PersonDTO createp() {
        log.info("FrontResourceImpl:createp() startMethod");

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId("942");
        personDTO.setEmail("NGover@mattis.net");
        personDTO.setFirstName("Croco");
        personDTO.setLastName("Velasco");
        personDTO.setPhone("(186)049-8265");

        return personDTO;
    }


    @Override
    public List<PersonDTO> createpl() {
        log.info("FrontResourceImpl:createpList() startMethod");

        List<PersonDTO> personDTOList = new ArrayList<>();

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId("942");
        personDTO.setEmail("NGover@mattis.net");
        personDTO.setFirstName("Croco");
        personDTO.setLastName("Velasco");
        personDTO.setPhone("(186)049-8265");
        personDTO.setInfo("1infos3");

        personDTOList.add(personDTO);

        log.info("FrontResourceImpl:createpList() endMethod, to return personDTOList = {}", personDTOList);
        return personDTOList;
    }

}
