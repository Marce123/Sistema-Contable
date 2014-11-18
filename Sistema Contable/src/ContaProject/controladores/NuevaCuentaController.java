/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.CuentaBd;
import ContaProject.bd.TipoDeCuentaBd;
import ContaProject.modelo.Cuenta;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Martinez
 */
public class NuevaCuentaController extends AnchorPane {
    
    
    
    @FXML private TextField txtCod;
    @FXML private TextField txtNombre;
    @FXML private ChoiceBox<String> CBTipoCuenta;
    @FXML private Label LblErrorMessage;
    
    private TipoDeCuentaBd tipoDeCuentaBd;
    private ContaProyectMain mainApp ;
    private Cuenta cuenta;
    private boolean GuardarClicked  = false;
    
     public void setApp(ContaProyectMain main){
        tipoDeCuentaBd = new TipoDeCuentaBd();
        tipoDeCuentaBd.llenarListaTipoCuenta();
        this.mainApp = main;
        CBTipoCuenta.setItems(tipoDeCuentaBd.getListaTipo());
        CBTipoCuenta.getSelectionModel().selectFirst();
        LblErrorMessage.setDisable(true);
    } 

     public boolean entradaValida(){
        String MensajeDeError = "";
        if(txtCod.getText()==null || txtCod.getText().length()==0){
            MensajeDeError +="Debe ingresar un codigo\n ";
        }
        if(txtNombre.getText()==null || txtNombre.getText().length()==0){
            MensajeDeError +="Debe ingresar un nombre\n ";
        }
        if("Seleccione Tipo De Cuenta".equals(CBTipoCuenta.getValue()))
        {
         MensajeDeError += "Debe Ingresar El tipo de la Cuenta\n ";
        }
        if(MensajeDeError.length()==0)
        {
            return true;
        }
        else {
            {
                LblErrorMessage.setText(MensajeDeError);
                return false;}
        }
    }
     

     
     
     
     
   

    
    @FXML private void handleCancelar(){
        mainApp.MenuPrincipal();
    }
    //Integer idTipo,String nombreTipo,String nombreCuenta,String idCuenta,String descripcion
    @FXML private void handleGuardar(){
        CuentaBd cuentaBd = new CuentaBd();
                if(entradaValida())
                {
                    String nombreTipo = CBTipoCuenta.getValue();
                    Integer idTipo = tipoDeCuentaBd.ConsultaIdTipo(nombreTipo);
                    String idCuenta = txtCod.getText();
                    String nombreCuenta = txtNombre.getText();
                    
                    cuentaBd.guardarCuentas(idTipo, nombreTipo, nombreCuenta, idCuenta);
                    mainApp.CatalogoDeCuentas();    
                 }
    }
    

}
