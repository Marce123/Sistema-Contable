/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.EstadosFinancierosBd;
import ContaProject.modelo.Cuenta;
import ContaProject.modelo.TipoDeCuenta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class EstadocapitalController extends AnchorPane {

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
    private Label lblTotalInversion;
    @FXML
    private Label lblTotalDesinversion;
    @FXML
    private Label lblCapital;

    private ContaProyectMain mainApp;
    Double ingresos, egresos, total;
    Cuenta utilidades;

    public void setMainApp(ContaProyectMain mainApp) {
        EstadosFinancierosBd estadosFinancierosBd = new EstadosFinancierosBd();
        this.mainApp = mainApp;
        ingresos = 0.0;
        egresos = 0.0;
        total = 0.0;
        estadosFinancierosBd.consultaEstadoCapital();
        colCodigoI.setCellValueFactory(e -> e.getValue().idCuentaProperty());
        colCuentasI.setCellValueFactory(e -> e.getValue().NombreProperty());
        colSaldoI.setCellValueFactory(e -> e.getValue().SalDuedorProperty());
        tablaIngresos.setItems(estadosFinancierosBd.getListaInversion());
        for (Cuenta cuenta : estadosFinancierosBd.getListaInversion()) {
            ingresos += cuenta.getSalDuedor();
        }
        lblTotalInversion.setText(String.valueOf(ingresos));

        colCodigoE.setCellValueFactory(e -> e.getValue().idCuentaProperty());
        colCuentasE.setCellValueFactory(e -> e.getValue().NombreProperty());
        colSaldoE.setCellValueFactory(e -> e.getValue().SalDuedorProperty());
        tablaEgresos.setItems(estadosFinancierosBd.getListaDesinversion());
        if (estadosFinancierosBd.getListaDesinversion().size() == 0) {
            lblTotalDesinversion.setText(String.valueOf(egresos = 0.0));
            lblTotalDesinversion.setText(String.valueOf(egresos));
            total = ingresos - egresos;
            if (total < 0) {
                lblCapital.setText(String.valueOf(total));
                lblCapital.textFillProperty().set(Color.RED);
            } else {
                lblCapital.setText(String.valueOf(total));
                lblCapital.textFillProperty().set(Color.BLUE);
            }
        } else {
            for (Cuenta cuenta : estadosFinancierosBd.getListaResultadoEgreso()) {
                egresos += cuenta.getSalDuedor();
            }
            lblTotalDesinversion.setText(String.valueOf(egresos));
            total = ingresos - egresos;
            if (total < 0) {
                lblCapital.setText(String.valueOf(total));
                lblCapital.textFillProperty().set(Color.RED);
            } else {
                lblCapital.setText(String.valueOf(total));
                lblCapital.textFillProperty().set(Color.BLUE);
            }
        }
    }
}
