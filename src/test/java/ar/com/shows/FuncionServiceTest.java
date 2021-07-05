package ar.com.shows;

import ar.com.shows.filter.FuncionFilter;
import ar.com.shows.filter.ShowOrder;
import ar.com.shows.model.Funcion;
import ar.com.shows.model.Sala;
import ar.com.shows.service.FuncionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@Sql({"/data.sql"})
@SpringBootTest(classes = SpringBootApplication.class)
public class FuncionServiceTest {


    @Autowired
    FuncionService funcionService;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFuncionesTest() {

        List<Funcion> res = funcionService.getFunciones(
                new Funcion(),
                new FuncionFilter(),
                new ShowOrder(),
                0,
                10);

        assertEquals(4, res.size());

    }

    @Test
    public void getFuncionesByFuncion() {

        Funcion funcion = new Funcion();
        Sala sala = new Sala();
        sala.setId(2L);
        funcion.setSala(sala);

        List<Funcion> res = funcionService.getFunciones(
                funcion,
                new FuncionFilter(),
                new ShowOrder(),
                0,
                10);

        assertEquals(2, res.size());
        for (Funcion f : res) {
            assertTrue(Arrays.asList(2L,3L).contains(f.getId()));
        }

    }

}


