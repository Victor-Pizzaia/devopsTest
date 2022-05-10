package com.br.vpizzaia.controller;

import com.br.vpizzaia.model.Cargo;
import com.br.vpizzaia.repository.CargoRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "/cargos")
public class cargoController {
    @Autowired
    private CargoRepository carg;

    @GetMapping
    public List<Cargo> findAll() {
        return carg.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cargo> findById(@PathVariable Integer id) {
        val cargo = carg.findById(id);

        if (cargo.isPresent()) {
            return  ResponseEntity.ok(cargo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cargo> save(@RequestBody Cargo cargo) throws ValidationException {
        if(cargo.getCargo() == null || cargo.getCargo() == "") {
            throw new ValidationException("Preencha o campo cargo!");
        }
        val newCarg = carg.save(cargo);
        return new ResponseEntity<Cargo>(newCarg, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        carg.deleteById(id);
    }
}
