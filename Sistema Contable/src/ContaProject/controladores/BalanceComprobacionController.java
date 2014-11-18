/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.EmpresaBd;
import ContaProject.bd.EstadosFinancierosBd;
import ContaProject.modelo.Cuenta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Martinez
 */
public class BalanceComprobacionController extends AnchorPane {
    @FXML
    private TableView<Cuenta> TablaBalance;
    @FXML
    private TableColumn<Cuenta, String> ColumnaCodigo;
    @FXML
    private TableColumn<Cuenta, String> ColumnaNombre;
    @FXML
    private TableColumn<Cuenta, Double> ColumnaDebe;
    @FXML
    private TableColumn<Cuenta, Double> ColumnaHaber;
    @FXML
    private Label lblTotalHaber;
    @FXML
    private Label lblTotalDebe;
    @FXML
    private Label lblError;
    @FXML
    private Label lblNombreEmpresa;
    @FXML
    private Label lblFechaInicio;
    @FXML
    private Label lblFechaFin;

    private ContaProyectMain mainApp;
    private Double saldoAcreedor = 0.0;
    private Double saldoDeudor = 0.0;

    public void setMainApp(ContaProyectMain mainApp) {
        EstadosFinancierosBd estadosFinancierosBd = new EstadosFinancierosBd();
        EmpresaBd empresaBd = new EmpresaBd();
        this.mainApp = mainApp;
        Cuenta cuentaNueva;
        
        
        lblNombreEmpresa.setText(empresaBd.consultarNombreEmpresa());
        lblFechaInicio.setText(empresaBd.consultaFechaInicio());
        lblFechaFin.setText(empresaBd.consultaFechaFin());
        estadosFinancierosBd.consultaBalanceComprobacion();
        ColumnaCodigo.setCellValueFactory(e -> e.getValue().idCuentaProperty());
        ColumnaNombre.setCellValueFactory(e->e.getValue().NombreProperty());
        ColumnaDebe.setCellValueFactory(e->e.getValue().SalDuedorProperty());
        ColumnaHaber.setCellValueFactory(e->e.getValue().SalAcreedoProperty());
        TablaBalance.setItems(estadosFinancierosBd.getListaBalanceComprobacion());
        
        for (Cuenta cuenta : estadosFinancierosBd.getListaBalanceComprobacion()) {
            saldoAcreedor += cuenta.getSalAcreedor();
            saldoDeudor += cuenta.getSalDuedor();
        }
        
        lblTotalHaber.setText(String.valueOf(saldoAcreedor));
        lblTotalDebe.setText(String.valueOf(saldoDeudor));
        
        if(!saldoAcreedor.equals(saldoDeudor)){
            lblError.setText("Los valores de los saldos son incorrectos deberia revisar las transacciones");
            lblError.textFillProperty().setValue(Color.RED);
        }
    }
    
    
}
