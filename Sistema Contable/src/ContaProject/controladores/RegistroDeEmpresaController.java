/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.EmpresaBd;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Martinez
 */
public class RegistroDeEmpresaController extends AnchorPane {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtAuditor;
    @FXML
    private TextField txtContador;
    @FXML
    private TextField txtNit;
    @FXML
    private TextField txtCreditoFiscal;
    @FXML
    private ChoiceBox<String> CBTipoEmpresa;
    @FXML
    private TextField txtRepresentante;
    @FXML
    private ChoiceBox<String> CBPeriodo;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnCerrar;

    private ContaProyectMain mainApp;
    EmpresaBd empresaBd = new EmpresaBd();

    public void setMainApp(ContaProyectMain application) {
        this.mainApp = application;
        CBTipoEmpresa.setItems(empresaBd.getListaTipoEmpresa());
        CBTipoEmpresa.getSelectionModel().selectFirst();
        CBPeriodo.setItems(empresaBd.getListaPeriodo());
        CBPeriodo.getSelectionModel().selectFirst();
    }

    @FXML
    private void GuardarEmpresa() {
        try {
            String nit = txtNit.getText();
            String nombreEmpresa = txtNombre.getText();
            String creditoFiscal = txtCreditoFiscal.getText();
            String representante = txtRepresentante.getText();
            String contador = txtContador.getText();
            String auditor = txtAuditor.getText();
            String tipoEmpresa = CBTipoEmpresa.getValue();
            String periodo = CBPeriodo.getValue();
            if(!nit.isEmpty()&&!nombreEmpresa.isEmpty()&&!creditoFiscal.isEmpty()&&!representante.isEmpty()&&!contador.isEmpty()&&!auditor.isEmpty()&&!tipoEmpresa.isEmpty()&&!periodo.isEmpty()){
                if(empresaBd.guardarEmpresa(nit, nombreEmpresa, creditoFiscal, representante, contador, auditor, tipoEmpresa, periodo)!=true){
                    JOptionPane.showMessageDialog(null, "La empresa no se guardo, revise los datos");
                }else{
                    JOptionPane.showMessageDialog(null, "La empresa se guardo con exito");
                    mainApp.MenuPrincipal();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        }
    }
}
