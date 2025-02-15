package dao;

import java.util.List;

public interface IDao <T>{

    T guardar(T t);
    T obtenerPorId(int id);
    List<T> obtenerTodos();
}
