package test;

import dao.imp.OdontologoDaoMemoria;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceMemoriaTest {
    private static final Logger LOGGER = Logger.getLogger(OdontologoServiceMemoriaTest.class);
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoMemoria());

    @Test
    @DisplayName("Guardar Odontologo")
    void test1(){
        Odontologo odontologo = new Odontologo("123458","Camila","Valencia");
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);
        assertNotNull(odontologoGuardado.getId());

    }

    @Test
    @DisplayName("Obtener odontologo por id")
    void test2(){
        Odontologo odontologoGuardado = odontologoService.obetnerPorId(2);
        assertEquals(2, odontologoGuardado.getId());
        assertEquals("Ortiz", odontologoGuardado.getApellido());

    }


    @Test
    @DisplayName("Obtener todos los odontologos")
    void test4(){
        List<Odontologo> odontologoGuardados = odontologoService.obtenerTodos();
        assertTrue(odontologoGuardados.size()>0);


    }

}