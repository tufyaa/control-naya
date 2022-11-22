package com.example.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controler {
    private final List<Tema> temas = new ArrayList<>();
    // curl -X POST -H 'Content-Type: application/json'   http://localhost:8080/addtema -d "{\"name\": \"afagzg\"}"
    @PostMapping("addtema")
    public ResponseEntity<Void> addtema(@RequestBody String name) {
        Tema tema = new Tema();
        tema.setname(name);
        temas.add(tema);
        return ResponseEntity.accepted().build();
    }

    // curl -X DELETE -H 'Content-Type: application/json'   http://localhost:8080/deletetema/1
    @DeleteMapping("deletetema/{index}")
    public ResponseEntity<Void> deletetema(@PathVariable("index") Integer number) {
        temas.remove(number - 1);
        return ResponseEntity.noContent().build();
    }

    // curl -X GET -H 'Content-Type: application/json'   http://localhost:8080/gettemas
    @GetMapping("gettemas")
    public ResponseEntity<List<String>> gettemas() {
        List<String> names = new ArrayList<>();
        for (int i =0; i<temas.size(); i++){
           names.add(temas.get(i).getname());
        }
        return ResponseEntity.ok(names);
    }

    // curl -X PUT -H 'Content-Type: application/json'   http://localhost:8080/puttema/1 -d "{\"name\": \"afagzg\"}"
    @PutMapping("puttema/{index}")
    public ResponseEntity<Void> puttema(@RequestBody String name, @PathVariable("index") Integer index) {
        temas.remove((int) index);
        Tema tema = new Tema();
        tema.setname(name);
        temas.add(index ,tema);
        return ResponseEntity.noContent().build();
    }

    // curl -X GET -H 'Content-Type: application/json'   http://localhost:8080/getcount
    @GetMapping("getcount")
    public ResponseEntity<Integer> getcount(){
        return ResponseEntity.ok(temas.size());
    }

    // curl -X DELETE -H 'Content-Type: application/json'   http://localhost:8080/deletetemas
    @DeleteMapping("deletetemas")
    public ResponseEntity<Void> deletetemas(){
        temas.clear();
        return ResponseEntity.noContent().build();
    }

    // curl -X GET -H 'Content-Type: application/json'   http://localhost:8080/getname/1
    @GetMapping("getname/{index}")
    public ResponseEntity<String> getname(@PathVariable("index") Integer number) {
        return ResponseEntity.ok(temas.get(number).getname());
    }

    // curl -X POST -H 'Content-Type: application/json'   http://localhost:8080/addcomment/1 -d "{\"comment\": \"afagzg\"}"
    @PostMapping("addcomment/{index}")
    public ResponseEntity<Void> addcomment(@RequestBody String comment,@PathVariable("index") Integer index) {
        temas.get(index-1).getComments().add(comment);
        return ResponseEntity.accepted().build();
    }

    // curl -X DELETE -H 'Content-Type: application/json'   http://localhost:8080/deletecomment/1/1
    @DeleteMapping("deletecomment/{index1}/{index2}")
    public ResponseEntity<Void> deletecomment(@PathVariable("index1") Integer index1,@PathVariable("index2") Integer index2) {
        temas.get(index1-1).getComments().remove(index2-1);
        return ResponseEntity.noContent().build();
    }
    // curl -X PUT -H 'Content-Type: application/json'   http://localhost:8080/putcomment/1/1 -d "{\"comment\": \"afagzg\"}"
    @PutMapping("putcomment/{index1}/{index2}")
    public ResponseEntity<Void> putcomment(@PathVariable("index1") Integer index1,@PathVariable("index2") Integer index2, @RequestBody String comment) {
        temas.get(index1-1).getComments().remove(index2-1);
        temas.get(index1-1).getComments().add(index2-1, comment);
        return ResponseEntity.accepted().build();
    }

    // curl -X GET -H 'Content-Type: application/json'   http://localhost:8080/getcomments/1
    @GetMapping("getcomments/{index}")
    public ResponseEntity<List<String>> getcomments(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(temas.get(index-1).getComments());
    }


}
