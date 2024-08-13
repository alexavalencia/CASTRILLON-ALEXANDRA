package service;

import dao.IDao;
import model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> veterinarioIDao) {
        this.odontologoIDao = veterinarioIDao;
    }

    public Odontologo guardar(Odontologo odontologo){
        return  odontologoIDao.guardar(odontologo);
    }

    public Odontologo obetnerPorId(int  id){
        return  odontologoIDao.obtenerPorId(id);
    }
    public List<Odontologo> obtenerTodos(){
        return  odontologoIDao.obtenerTodos();
    }

}
