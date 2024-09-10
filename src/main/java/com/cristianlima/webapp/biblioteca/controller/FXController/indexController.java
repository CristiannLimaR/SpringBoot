package com.cristianlima.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.system.Main;

import javafx.fxml.Initializable;
import lombok.Setter;

@Component
public class indexController implements Initializable {
    
    @Setter
    private Main stage;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       
    }
    
    
}
