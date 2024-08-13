package dao.imp;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {

    private static final Logger LOGGER= Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologos;

    public OdontologoDaoMemoria() {
        this.odontologos = new ArrayList<>(){
            {
                add(new Odontologo(1,"123451","Camila","Valencia"));
                add(new Odontologo(2,"123452","Juan","Ortiz"));
                add(new Odontologo(3,"123453","Luisa","Aguirre"));
                add( new Odontologo(4,"123454","Felipe","Mesa"));
                add( new Odontologo(5,"123455","Marlon","Giraldo"));
            }


        };

    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId(odontologos.size()+1);
        odontologos.add(odontologo);
        LOGGER.info("Odontologo Guardado "+ odontologo);
        return odontologo;
    }

    @Override
    public Odontologo obtenerPorId(int id) {
        Odontologo odontologo = odontologos.stream().filter(value -> value.getId() == id).findFirst().get();
        if(odontologo != null){
            LOGGER.info("Odontologo encontrado "+ odontologo);
        }else{
            LOGGER.info("Odontologo no encontrado");
        }
        return odontologo;
    }


    @Override
    public List<Odontologo> obtenerTodos() {
        LOGGER.info("Odontologos encontrado "+ odontologos);
        return odontologos;
    }
}
