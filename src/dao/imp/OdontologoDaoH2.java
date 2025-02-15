package dao.imp;

import dao.IDao;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    private static final String INSERT="INSERT INTO ODONTOLOGOS VALUES( DEFAULT, ?,?,?)";

    private  static final String SELECT_BY_ID= "SELECT * FROM ODONTOLOGOS WHERE ID=? ";
    private  static final String SELECT_ALL= "SELECT * FROM ODONTOLOGOS";
    private Connection connection= null;

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Odontologo odontologoRetornar = null;
        try {
            connection= H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet= preparedStatement.getGeneratedKeys();
            Integer id = 0;
            if(resultSet.next()){
                id= resultSet.getInt(1);
                odontologoRetornar = new Odontologo(id, odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }
            LOGGER.info("Odontologo guardado  "+ odontologoRetornar);

        }catch (Exception e){
            LOGGER.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return odontologoRetornar;
    }

    @Override
    public Odontologo obtenerPorId(int id) {
        Odontologo odontologoResult = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                odontologoResult = new Odontologo(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                LOGGER.info("Odontologo obtenido "+ odontologoResult);
            }
            if (odontologoResult == null){
                LOGGER.info("No se encontro el odontologo");
            }


        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return odontologoResult;
    }


    @Override
    public List<Odontologo> obtenerTodos() {
        Odontologo odontologo = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                odontologo = new Odontologo(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                odontologos.add(odontologo);
            }
            if(!odontologos.isEmpty()){
                LOGGER.info("Lista de odontologos encontrados"+ odontologos);
            }else{
                LOGGER.info("No se encontraron odontologos");
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return odontologos;
    }
}
