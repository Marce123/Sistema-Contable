/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.modelo.Cuenta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Martinez
 */
public class DatosCuentaController extends AnchorPane {

   @FXML private Label lblNombre;
   @FXML private Label lblCodigo;
   @FXML private Label lblTipo;
   @FXML private Label lblDescripcion;
    
    private ContaProyectMain main;
    
    public void setMain(ContaProyectMain main,Cuenta cuenta) {
        this.main = main;
        lblNombre.setText(cuenta.getNombre());
        lblCodigo.setText(cuenta.getIdCuenta());
        lblTipo.setText(cuenta.getTipo().getNombreTipo());
        
    }
    
    
     
    
}
