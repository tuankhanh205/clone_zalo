package org.example.clonezalo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Tet {
    @GetMapping("/api/user")
    public String a(){
        return "Hello user đến với zalo clone";
    }
    @GetMapping("/api/admin")
    public String b(){
        return "Hello admin đến với zalo clone";
    }
}
