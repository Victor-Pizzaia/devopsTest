package com.br.vpizzaia.controller;

import com.br.vpizzaia.model.Funcionario;
import com.br.vpizzaia.repository.FuncionarioRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class funcionarioController {
    @Autowired
    private FuncionarioRepository func;

    @GetMapping
    public List<Funcionario> findAll() {
        return func.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Integer id) {
        val funcionario = func.findById(id);

        if (funcionario.isPresent()) {
            return  ResponseEntity.ok(funcionario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario) throws ValidationException {
        if(funcionario.getNome() == null || funcionario.getNome() == "") {
            throw new ValidationException("Preencha o campo nome!");
        }
        if(funcionario.getCpf() == null || funcionario.getCpf() == "") {
            throw new ValidationException("Preencha o campo cpf!");
        }
        if(funcionario.getIdCargo() == null) {
            throw new ValidationException("Cargo inválido!");
        }
        val newFunc = func.save(funcionario);
        return new ResponseEntity<Funcionario>(newFunc, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        func.deleteById(id);
    }
}
