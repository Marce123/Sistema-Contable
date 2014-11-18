/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.EstadosFinancierosBd;
import ContaProject.modelo.Cuenta;
import ContaProject.modelo.TipoDeCuenta;
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
public class EstadoresultadoController extends AnchorPane {

    @FXML
    private TableView<Cuenta> tablaIngresos;
    @FXML
    private TableColumn<Cuenta, String> colCodigoI;
    @FXML
    private TableColumn<Cuenta, String> colCuentasI;
    @FXML
    private TableColumn<Cuenta, Double> colSaldoI;
    @FXML
    private TableColumn<Cuenta, Double> colTotalI;
    @FXML
    private TableView<Cuenta> tablaEgresos;
    @FXML
    private TableColumn<Cuenta, String> colCodigoE;
    @FXML
    private TableColumn<Cuenta, String> colCuentasE;
    @FXML
    private TableColumn<Cuenta, Double> colSaldoE;
    @FXML
    private TableColumn<Cuenta, Double> colTotalE;
    @FXML
    private Label lblTotalIngresos;
    @FXML
    private Label lblTotalEgresos;
    @FXML
    private Label lblUtilidades;

    private ContaProyectMain mainApp;
    
    Double ingresos = 0.0, egresos = 0.0, total = 0.0;
    Cuenta utilidades;

    public void setMainApp(ContaProyectMain mainApp) {
        EstadosFinancierosBd estadosFinancierosBd = new EstadosFinancierosBd();
        this.mainApp = mainApp;

        estadosFinancierosBd.consultaEstadoDeResultado();
        colCodigoI.setCellValueFactory(e -> e.getValue().idCuentaProperty());
        colCuentasI.setCellValueFactory(e -> e.getValue().NombreProperty());
        colSaldoI.setCellValueFactory(e -> e.getValue().SalAcreedoProperty());
        tablaIngresos.setItems(estadosFinancierosBd.getListaResultadoIngreso());
        for (Cuenta cuenta : estadosFinancierosBd.getListaResultadoIngreso()) {
            ingresos += cuenta.getSalAcreedor();
        }
        lblTotalIngresos.setText(String.valueOf(ingresos));

        colCodigoE.setCellValueFactory(e -> e.getValue().idCuentaProperty());
        colCuentasE.setCellValueFactory(e -> e.getValue().NombreProperty());
        colSaldoE.setCellValueFactory(e -> e.getValue().SalDuedorProperty());
        tablaEgresos.setItems(estadosFinancierosBd.getListaResultadoEgreso());
        for (Cuenta cuenta : estadosFinancierosBd.getListaResultadoEgreso()) {
            egresos += cuenta.getSalDuedor();
        }
        lblTotalEgresos.setText(String.valueOf(egresos));
        total = ingresos + egresos;
        if (total < 0) {
            lblUtilidades.setText(String.valueOf(total));
            lblUtilidades.textFillProperty().set(Color.RED);
        } else {
            lblUtilidades.setText(String.valueOf(total));
            lblUtilidades.textFillProperty().set(Color.BLUE);
        }
        if (total < 0) {
            utilidades = new Cuenta("331", "Utilidades", new TipoDeCuenta("Capital", 3), total, 0.0);
        } else {
            utilidades = new Cuenta("331", "Utilidades", new TipoDeCuenta("Capital", 3), 0.0, total);

        }
        

    }

}
