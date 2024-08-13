package test;

import dao.imp.OdontologoDaoH2;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceH2Test {
    private static final Logger LOGGER = Logger.getLogger(OdontologoServiceH2Test.class);
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void creatTable(){
        Connection connection=null;
        try{
            Class.forName("org.h2.Driver");
            connection= DriverManager.getConnection("jdbc:h2:./consultorioOdontologico;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");

        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

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
        assertEquals("GIRALDO", odontologoGuardado.getApellido());

    }


    @Test
    @DisplayName("Obtener todos los odontologos")
    void test4(){
        List<Odontologo> odontologoGuardados = odontologoService.obtenerTodos();
        assertTrue(odontologoGuardados.size()>0);


    }

}