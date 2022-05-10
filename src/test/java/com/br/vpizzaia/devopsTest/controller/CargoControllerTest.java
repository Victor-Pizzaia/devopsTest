package com.br.vpizzaia.devopsTest.controller;

import com.br.vpizzaia.controller.cargoController;
import com.br.vpizzaia.model.Cargo;
import com.br.vpizzaia.repository.CargoRepository;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.xml.bind.ValidationException;

public class CargoControllerTest {
    @Mock
    private CargoRepository cargoRepo;

    @InjectMocks
    private cargoController controller;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void shouldNotSaveJobWithoutJob() {
        val carg = new Cargo();
        try {
            controller.save(carg);
            Assert.fail("Error");
        } catch (ValidationException e) {
            Assert.assertEquals("Preencha o campo cargo!", e.getMessage());
        }
    }

    @Test
    public void shouldSaveJobWithSuccess() throws ValidationException {
        val carg = new Cargo();
        carg.setCargo("Desenvolvedor");
        controller.save(carg);
        Mockito.verify(cargoRepo).save(carg);
    }
}
