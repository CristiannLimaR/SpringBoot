package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianlima.webapp.biblioteca.model.Empleado;
import com.cristianlima.webapp.biblioteca.repository.EmpleadoRepository;
import com.cristianlima.webapp.biblioteca.util.LibreriaAlert;
import com.cristianlima.webapp.biblioteca.util.MethodType;

import javafx.scene.control.ButtonType;

@Service

public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    LibreriaAlert libreriaAlert;


    @Override
    public List<Empleado> listarEmpleados(){
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarEmpleado(Empleado empleado, MethodType methodType) {
        try {
            if(methodType == MethodType.POST){
                if(!verificarpDpiDuplicado(empleado)){
                    empleadoRepository.save(empleado);
                    libreriaAlert.mostrarAlertaInfo(401);
                    return true;
                }else{
                    libreriaAlert.mostrarAlertaInfo(406);
                }
            }else{
                if(libreriaAlert.mostrarAlertaConfirmacion(106).get() == ButtonType.OK){
                    if(!verificarpDpiDuplicado(empleado)){
                        empleadoRepository.save(empleado);
                        libreriaAlert.mostrarAlertaInfo(401);
                        return true;
                    }else{
                        libreriaAlert.mostrarAlertaInfo(406);
                    }
                }
            }
        } catch (Exception e) {
            libreriaAlert.mostrarAlertaInfo(404);
            return false;
        }
        return null;
        
        

    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        try {
            if(libreriaAlert.mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                empleadoRepository.delete(empleado);
            }
        } catch (Exception e) {
            libreriaAlert.mostrarAlertaInfo(404);
        }
        
        
    }

    @Override
    public Boolean verificarpDpiDuplicado(Empleado newEmpleado) {
        List<Empleado> empleados = listarEmpleados();
        Boolean flag = false;
        for (Empleado empleado : empleados) {
            if(empleado.getDpi().equals(newEmpleado.getDpi()) && !empleado.getId().equals(newEmpleado.getId())){
                flag = true;
            }
        }
        return flag;
    }

    
}
