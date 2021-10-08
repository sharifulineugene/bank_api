package com.github.sharifulineugene.controllers;

import com.github.sharifulineugene.dto.CardDto;
import com.github.sharifulineugene.excception_handling.card.CardIncorrectData;
import com.github.sharifulineugene.excception_handling.card.NoSuchCardException;
import com.github.sharifulineugene.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final ICardService service;

    @Autowired
    public CardController(ICardService service) {
        this.service = service;
    }

    @ExceptionHandler
    public ResponseEntity<CardIncorrectData> handleException(
            NoSuchCardException ex) {
        CardIncorrectData data = new CardIncorrectData();
        data.setInfo(ex.getMessage());
        return new ResponseEntity<CardIncorrectData>(data, HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public List<CardDto> index() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CardDto show(@PathVariable int id) {
        return service.get(id);
    }
}
