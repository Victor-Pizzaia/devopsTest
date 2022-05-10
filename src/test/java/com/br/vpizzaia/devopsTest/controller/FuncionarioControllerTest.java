package com.br.vpizzaia.devopsTest.controller;

import com.br.vpizzaia.controller.funcionarioController;
import com.br.vpizzaia.model.Funcionario;
import com.br.vpizzaia.repository.FuncionarioRepository;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.xml.bind.ValidationException;

public class FuncionarioControllerTest {
    @Mock
    private FuncionarioRepository funcRepo;

    @InjectMocks
    private funcionarioController controller;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void shouldNotSaveEmployeeWithoutName() {
        val func = new Funcionario();
        func.setCpf("12334445555");
        func.setIdCargo(1);
        try {
            controller.save(func);
            Assert.fail("Error");
        } catch (ValidationException e) {
            Assert.assertEquals("Preencha o campo nome!", e.getMessage());
        }
    }

    @Test
    public void shouldNotSaveEmployeeWithoutCpf() {
        val func = new Funcionario();
        func.setNome("majin boo");
        func.setIdCargo(1);
        try {
            controller.save(func);
            Assert.fail("Error");
        } catch (ValidationException e) {
            Assert.assertEquals("Preencha o campo cpf!", e.getMessage());
        }
    }

    @Test
    public void shouldNotSaveEmployeeWithoutIdCargo() {
        val func = new Funcionario();
        func.setNome("majin boo");
        func.setCpf("12334445555");
        try {
            controller.save(func);
            Assert.fail("Error");
        } catch (ValidationException e) {
            Assert.assertEquals("Cargo inválido!", e.getMessage());
        }
    }

    @Test
    public void shouldSaveTaskWithSuccess() throws ValidationException {
        val func = new Funcionario();
        func.setNome("majin boo");
        func.setCpf("12334445555");
        func.setIdCargo(1);
        controller.save(func);
        Mockito.verify(funcRepo).save(func);
    }
}
