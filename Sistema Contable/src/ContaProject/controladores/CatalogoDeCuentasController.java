/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.CuentaBd;
import ContaProject.modelo.Cuenta;
import ContaProject.modelo.TipoDeCuenta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Martinez
 */
public class CatalogoDeCuentasController extends AnchorPane  {

    @FXML private TableView<Cuenta> TablaCuentas;
    @FXML private TableColumn<Cuenta,String> ColumnaCod;
    @FXML private TableColumn<Cuenta,String> ColumnaNombre;
    @FXML private TableColumn<Cuenta, String> ColumnaDescripcion;
    @FXML private TableColumn<Cuenta,TipoDeCuenta> ColumnaTipo;    
    
    
    public CatalogoDeCuentasController() {
    }

    private ContaProyectMain mainApp;
    //metodo que inicializa los respectivos campos de la tabla
    public void setMainApp(ContaProyectMain application){
        CuentaBd cuentaBd = new CuentaBd();
        this.mainApp = application;
        cuentaBd.consultaCatalogoDeCuentas();
        ColumnaCod.setCellValueFactory(cellData -> cellData.getValue().idCuentaProperty());
        ColumnaNombre.setCellValueFactory(cellData -> cellData.getValue().NombreProperty());
        ColumnaTipo.setCellValueFactory(cellData -> cellData.getValue().TipoProperty());
        TablaCuentas.setItems(cuentaBd.getListaCuenta());
        TablaCuentas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    try {
                        Cuenta cuenta = TablaCuentas.getSelectionModel().getSelectedItem();
                        mainApp.DatosCuenta(cuenta);
                    } catch (Exception ex) {
                        Logger.getLogger(CatalogoDeCuentasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                };
            }
        });
    }
    
    @FXML private void handleCancerTrans(){
        mainApp.MenuPrincipal();
    }
    
   
}
