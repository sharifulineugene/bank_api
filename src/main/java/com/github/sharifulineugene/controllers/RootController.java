package com.github.sharifulineugene.controllers;

import com.github.sharifulineugene.excception_handling.root.BadRequest;
import com.github.sharifulineugene.excception_handling.root.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/")
public class RootController {
    @RequestMapping("")
    public ResponseEntity<Void> redirect(Map<String,String> input) {
        return ResponseEntity.status(HttpStatus.FOUND).location(
                URI.create("/accounts")).build();
    }

    @RequestMapping("*")
    public void exception() {
        throw new BadRequestException("Bad Request");
    }

    @ExceptionHandler
    public ResponseEntity<BadRequest> badRequest(BadRequestException ex) {
        return new ResponseEntity<BadRequest>(BadRequest.builder()
                .info(ex.getMessage()).build()
                , HttpStatus.BAD_REQUEST);
    }




}
