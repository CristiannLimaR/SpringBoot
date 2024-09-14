package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import com.cristianlima.webapp.biblioteca.model.Empleado;
import com.cristianlima.webapp.biblioteca.util.MethodType;

public interface IEmpleadoService {
    public List<Empleado> listarEmpleados();
    
    public Empleado buscarEmpleadoPorId(Long id);

    public Boolean guardarEmpleado(Empleado empleado, MethodType methodType);

    public void eliminarEmpleado(Empleado empleado);

    public Boolean verificarpDpiDuplicado(Empleado newEmpleado);

}
