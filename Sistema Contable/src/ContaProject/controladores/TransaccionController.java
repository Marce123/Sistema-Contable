/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.CuentaBd;
import ContaProject.bd.TransaccionBd;
import ContaProject.modelo.Cuenta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Martinez
 */
public class TransaccionController extends AnchorPane {

    public TransaccionController() {
    }

    @FXML
    private TableView<Cuenta> TablaCuentas;
    @FXML
    private TableColumn<Cuenta, String> ColumnaNombre;
    @FXML
    private TableColumn<Cuenta, String> ColumnaCodigo;
    @FXML
    private TableColumn<Cuenta, Double> ColumnaHaber;
    @FXML
    private TableColumn<Cuenta, Double> ColumnaDebe;
    @FXML
    private TextField txtTotalHaber;
    @FXML
    private TextField txtTotalDeudor;
    @FXML
    private TextField txtNombreCuenta;
    @FXML
    private TextField txtSaldoDeudor;
    @FXML
    private TextField txtCodigoCuenta;
    @FXML
    private TextField txtSaldoAcreedor;
    @FXML
    private TextField txtMonto;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtCodigoTransaccion;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label lblError;
    @FXML
    private Button btnModificar;

    private ObservableList<Cuenta> listaCuentaParaTran = FXCollections.observableArrayList();
    private ContaProyectMain mainApp;
    private Cuenta cuentaVieja;
    private Cuenta cuentaSaldosViejos;
    private Double haber = 0.0, deudor = 0.0;

    public void setMainApp(ContaProyectMain App) {
        this.mainApp = App;
        ColumnaCodigo.setCellValueFactory(e -> e.getValue().idCuentaProperty());
        ColumnaNombre.setCellValueFactory(e -> e.getValue().NombreProperty());
        ColumnaHaber.setCellValueFactory(e -> e.getValue().SalAcreedoProperty());
        ColumnaDebe.setCellValueFactory(e -> e.getValue().SalDuedorProperty());
        txtSaldoAcreedor.setDisable(true);
        txtSaldoDeudor.setDisable(true);
        datePicker.setDisable(true);
        txtCodigoTransaccion.setDisable(true);
        btnGuardar.setDisable(true);
        txtMonto.setDisable(true);
        btnModificar.setDisable(true);

    }

    //**************************Se ejecuta cuenta se han seleccionado las cuentas a modificar****************************************
    public void setMainApp(ContaProyectMain aplication, ObservableList<Cuenta> lista) {
        mainApp = aplication;
        this.listaCuentaParaTran = lista;
        txtSaldoAcreedor.setDisable(true);
        txtSaldoDeudor.setDisable(true);
        txtMonto.setDisable(false);
        txtCodigoTransaccion.setDisable(false);
        datePicker.setDisable(false);

        //******************************Obtengo los datos de la cuenta*****************************
        ColumnaCodigo.setCellValueFactory(e -> e.getValue().idCuentaProperty());
        ColumnaNombre.setCellValueFactory(e -> e.getValue().NombreProperty());
        ColumnaDebe.setCellValueFactory(e -> e.getValue().SalDuedorProperty());
        ColumnaHaber.setCellValueFactory(e -> e.getValue().SalAcreedoProperty());

        //**************************************Si hay una modificacion captura el dato*************************************************
        TablaCuentas.itemsProperty().setValue(listaCuentaParaTran);
        TablaCuentas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                int mouseclick = event.getClickCount();
                if (mouseclick == 1) {
                    cuentaVieja = TablaCuentas.getSelectionModel().getSelectedItem();
                    txtCodigoCuenta.setText(cuentaVieja.getIdCuenta());
                    txtNombreCuenta.setText(cuentaVieja.getNombre());
                    txtSaldoAcreedor.setText(String.valueOf(cuentaVieja.getSalAcreedor()));
                    txtSaldoDeudor.setText(String.valueOf(cuentaVieja.getSalDuedor()));
                    txtSaldoDeudor.setDisable(false);
                    txtSaldoAcreedor.setDisable(false);
                    btnGuardar.setDisable(false);
                    btnModificar.setDisable(false);
                }
            }
        });

        txtSaldoAcreedor.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                modificarSaldos();

            }
        });
        txtSaldoDeudor.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                modificarSaldos();
            }
        });
    }

    @FXML
    private void handleCancerTrans() {
        mainApp.MenuPrincipal();
    }

    @FXML
    private void handleBuscarCuenta() {
        try {
            mainApp.DatosASeleccionar();
        } catch (Exception ex) {
            Logger.getLogger(TransaccionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modificarSaldos() {
        try {
            Double saldoA, saldoD;
            String codigo = txtCodigoCuenta.getText();
            String nombre = txtNombreCuenta.getText();

            if (!txtSaldoAcreedor.getText().isEmpty()) {
                saldoA = Double.parseDouble(txtSaldoAcreedor.getText());
            } else {
                saldoA = 0.0;
            }
            if (!txtSaldoDeudor.getText().isEmpty()) {
                saldoD = Double.parseDouble(txtSaldoDeudor.getText());
            } else {
                saldoD = 0.0;
            }
            if (!(saldoA > 0.0 && saldoD > 0.0)) {
                Cuenta nuevaCuenta = new Cuenta(codigo, nombre, null, saldoD, saldoA);
                listaCuentaParaTran.set(listaCuentaParaTran.indexOf(cuentaVieja), nuevaCuenta);
                txtSaldoAcreedor.setDisable(true);
                txtSaldoDeudor.setDisable(true);
                haber += saldoA - cuentaVieja.getSalAcreedor();
                deudor += saldoD - cuentaVieja.getSalDuedor();
                txtTotalDeudor.setText(String.valueOf(deudor));
                txtTotalHaber.setText(String.valueOf(haber));
            } else {
                JOptionPane.showMessageDialog(null, "usted intenta agregar dos saldos a la cuenta,solo puede ingresar uno, vuelva a intentarlo");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar primero una cuenta, Asignarla y despues apretar este Boton!");
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error en la entrada de salos, alguno no es un numero");
        }
        

    }

    @FXML
    private void guardarTransaccion() {
        CuentaBd cuentaBd = new CuentaBd();
        TransaccionBd transaccionBd = new TransaccionBd();
        try {
            Double deudorG = Double.parseDouble(txtTotalDeudor.getText());
            Double haberG = Double.parseDouble(txtTotalHaber.getText());
            String fecha = datePicker.getValue().toString();
            Integer id_transaccion = Integer.parseInt(txtCodigoTransaccion.getText());
            Double monto = Double.parseDouble(txtMonto.getText());
            if (Validar(deudorG, haberG, monto, fecha, id_transaccion)) {

                //Guarda en la tabla transaccion la transaccion
                transaccionBd.guardarTransaccion(monto, id_transaccion, fecha);

                //Se repetira segun el numero de cuentas en la transaccion
                listaCuentaParaTran.stream().forEach((cuenta) -> {
                    String id_cuenta = cuenta.getIdCuenta();
                    String nombre_cuenta = cuenta.getNombre();
                    Double saldo_Deudor = cuenta.getSalDuedor();
                    Double saldo_Acreedor = cuenta.getSalAcreedor();
                    if (cuentaBd.validarCuentaConSaldo(id_cuenta)) {
                        cuentaSaldosViejos = cuentaBd.consultarSaldoCuentas(id_cuenta);
                        Double AcreedorViejo = cuentaSaldosViejos.getSalAcreedor();
                        Double DeudorViejo = cuentaSaldosViejos.getSalDuedor();
                        Double AcreedorNuevo = AcreedorViejo + saldo_Acreedor;
                        Double DeudorNuevo = DeudorViejo + saldo_Deudor;
                        cuentaBd.actualizarDatosSaltos(id_cuenta, AcreedorNuevo, DeudorNuevo);
                    } else {
                        //Inserta los saldos en la cuenta si la cuenta no ha sido afectada antes
                        cuentaBd.guardarSaldo(cuenta);
                    }

                    if (!cuentaBd.validarCuentaAfectada(id_cuenta)) {
                        cuentaBd.guardarCuentaAfectada(cuenta);
                    }
                    //Guarda El Dato en la tabla cuenta_transaccion
                    transaccionBd.guardarTransaccionCuentas(id_cuenta, id_transaccion, saldo_Deudor, saldo_Acreedor, nombre_cuenta, fecha);
                }); //Fin del ForEach
                NuevaTransaccion();
            }//Fin del try
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Todos los Campos son Obligatorios");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Algun campo no es un numero");

        }

    }

    public boolean Validar(Double deudorG, Double haberG, Double monto, String fecha, Integer id_transaccion) {
        boolean guardar;

        if (deudorG.equals(haberG)) {
            if (monto.equals(deudorG) && monto.equals(haberG)) {
                JOptionPane.showMessageDialog(null, "Transaccion llevada con exito \n Para realizar una nueva transaccion, seleccione nuevas cuentas");
                guardar = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error Monto y saldos deben de ser iguales","error en los datos",JOptionPane.WARNING_MESSAGE);
                
                guardar = false;
            }
        } else {
             JOptionPane.showMessageDialog(null, "el debe y el haber deben de ser iguales","Error de partida doble",JOptionPane.WARNING_MESSAGE);
            
            guardar = false;
        }
        return guardar;

    }

    private void NuevaTransaccion() {
        txtCodigoCuenta.clear();
        txtNombreCuenta.clear();
        txtMonto.clear();
        txtCodigoTransaccion.clear();
        txtTotalDeudor.clear();
        txtTotalHaber.clear();
        txtSaldoAcreedor.clear();
        txtSaldoDeudor.clear();
        datePicker.setValue(null);
        listaCuentaParaTran.clear();
        txtCodigoCuenta.setDisable(true);
        txtNombreCuenta.setDisable(true);
        txtMonto.setDisable(true);
        datePicker.setDisable(true);
        TablaCuentas.itemsProperty().set(listaCuentaParaTran);
    }

}//Fin de la clase
