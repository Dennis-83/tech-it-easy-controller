package nl.springcontroller.techiteasy.controllers;

import nl.springcontroller.techiteasy.exceptions.RecordNotFoundException;
import nl.springcontroller.techiteasy.model.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TelevisionController {

    List<Television> televisions = new ArrayList<>();

    public TelevisionController() {
        Television t = new Television();
        t.setBrand("LG");
        t.setName("OLED EVO C3-77");
        t.setPrice(1500);
        televisions.add(t);
    }

    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> getTelevisions() {
        return new ResponseEntity<>(televisions, HttpStatus.OK);
    }

    @GetMapping("/televisions/{index}")
    public ResponseEntity<Television> getTelevision(@PathVariable int index) {
        if (index >= 0 && index < televisions.size()) {
            return new ResponseEntity<>(televisions.get(index), HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("Television cannot be found!");
        }
    }

    @PostMapping("/televisions")
    public ResponseEntity<Television> addTelevision(@RequestBody Television newTelevision) {
        televisions.add(newTelevision);
        return new ResponseEntity<>(newTelevision, HttpStatus.CREATED);
    }

    @PutMapping("/televisions/{index}")
    public ResponseEntity<Television> updateTelevision(@PathVariable int index, @RequestBody double price) {
        if (index >= 0 && index < televisions.size()) {
            televisions.get(index).setPrice(price);
            return new ResponseEntity<>(televisions.get(index), HttpStatus.NO_CONTENT);
        } else {
            throw new RecordNotFoundException("Television cannot be found!");
        }
    }

    @DeleteMapping("/televisions/{index}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable int index) {
        if (index >= 0 && index < televisions.size()) {
            televisions.set(index, null);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new RecordNotFoundException("Television cannot be found!");
        }
    }
}
