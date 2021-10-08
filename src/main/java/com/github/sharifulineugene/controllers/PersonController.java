package com.github.sharifulineugene.controllers;

import com.github.sharifulineugene.dto.PersonDto;
import com.github.sharifulineugene.excception_handling.person.NoSuchPersonException;
import com.github.sharifulineugene.excception_handling.person.PersonIncorrectData;
import com.github.sharifulineugene.service.IPersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @ExceptionHandler
    public ResponseEntity<PersonIncorrectData> handleException(
            NoSuchPersonException ex) {
        PersonIncorrectData data = new PersonIncorrectData();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<PersonIncorrectData>(data, HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public List<PersonDto> showAllPersons() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDto show(@PathVariable("id") int id) {
        return personService.get(id);
    }
}
