/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.CuentaBd;
import ContaProject.modelo.Cuenta;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Martinez
 */
public class CuentasAElegirController extends AnchorPane {

    @FXML private TableView<Cuenta> TablaCuentas;
    @FXML private TableColumn<Cuenta,String> ColumnasCuentas;
    
    
    private ContaProyectMain mainApp;
    private TransaccionController trans;
    private Stage dialogoStage;


    public void setMainApp(ContaProyectMain App,Stage dialogoStage) {
        CuentaBd cuentaBd = new CuentaBd();
        this.mainApp = App;
        this.dialogoStage = dialogoStage;
        ColumnasCuentas.setCellValueFactory(cellData -> cellData.getValue().NombreProperty());
        if(cuentaBd.getListaCuenta().size()==0){
        TablaCuentas.setDisable(true);
        }
        if(cuentaBd.getListaCuenta().size()!=0){
            TablaCuentas.setItems(cuentaBd.getListaCuenta());
        }
        else{
            JOptionPane.showMessageDialog(null, "Catalogo De Cuentas vacio, debera ingresar una nueva cuenta para realizar esta accion");
        }
        TablaCuentas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
    @FXML private void cancelar(){
        dialogoStage.close();
    }
    
    @FXML private void Seleccionar(){
        ObservableList<Cuenta> cuentasSeleccionadas = TablaCuentas.getSelectionModel().getSelectedItems();
        if(cuentasSeleccionadas.size()>=2)
        {
            mainApp.registrarTransaccion(TablaCuentas.getSelectionModel().getSelectedItems());
        }
        else{
            
            JOptionPane.showMessageDialog(null, "Debe de seleccionar al menos 2 tablas ");
        }
        if(cuentasSeleccionadas.size()!=0){
        dialogoStage.close();
        }
        
    }
    


    
    
    
    
    
}
