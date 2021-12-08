package com.springsecurity.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {



    @GetMapping("/")
    public ResponseEntity get(){
        return new ResponseEntity("Server is running", HttpStatus.OK);
    }

    @GetMapping("/testandoAutorizacao")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity testando(){
        return new ResponseEntity("Autorizado", HttpStatus.OK);
    }

    @GetMapping("/testandoAutorizacao2")
    public ResponseEntity autenticacaoComBanco(@AuthenticationPrincipal UserDetails userDetails){
        //pelo visto, nessa requisição o spring capta o dono da autenticacao
        return new ResponseEntity(userDetails.getAuthorities(), HttpStatus.OK);
    }

}
