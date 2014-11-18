/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;

import ContaProject.bd.Conexion;
import ContaProject.bd.EmpresaBd;
import ContaProject.modelo.Cuenta;
import ContaProject.modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public final class ContaProyectMain extends Application {

//************************************Variables para conexion**************************************************************************************************
    private Connection con;
    private Statement statement;
    private ResultSet resultado;
//**********************************************************************************************************************************************************
    private Usuario UsuarioLogeado;
    private Stage primaryStage;
    private ObservableList<Cuenta> listaCuentaParaTransaccion = FXCollections.observableArrayList();

    public ContaProyectMain() {

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            primaryStage = stage;
            primaryStage.setTitle("Conta Proyect");
            iniciarSesion();
//            MenuPrincipal();
            primaryStage.show();
        } catch (Exception e) {
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        try {
            Conexion.getConexion().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al cerrar la conexión a la base de datos");
        }
    }

//***************************************************PANTALLAS PARA MOSTRAR********************************************************************************
//----------------------------------------------------------------------------------------------------------------------------------------------------------
    private void iniciarSesion() {
        try {
            //Crea un objeto de tipo controlador
            LoginController login = (LoginController) replaceSceneContent("/ContaProject/vistas/Login.fxml");
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MenuPrincipal() {
        try {
            MenuPrincipalController Datos = (MenuPrincipalController) replaceSceneContent("/ContaProject/vistas/MenuPrincipal.fxml");
            Datos.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void IngresarCuenta() {
        try {
            NuevaCuentaController Cuentas = (NuevaCuentaController) replaceSceneContent("/ContaProject/vistas/Nuevacuenta.fxml");
            Cuentas.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarTransaccion() {
        try {
            TransaccionController transaccion = (TransaccionController) replaceSceneContent("/ContaProject/vistas/Transaccion.fxml");
            transaccion.setMainApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarTransaccion(ObservableList<Cuenta> lista) {
        try {
            listaCuentaParaTransaccion.clear();
            lista.stream().forEach((lista1) -> {
                listaCuentaParaTransaccion.add(lista1);
            });
            TransaccionController transaccion = (TransaccionController) replaceSceneContent("/ContaProject/vistas/Transaccion.fxml");
            transaccion.setMainApp(this, listaCuentaParaTransaccion);
        } catch (Exception ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CatalogoDeCuentas() {
        try {
            CatalogoDeCuentasController catalogo = (CatalogoDeCuentasController) replaceSceneContent("/ContaProject/vistas/CatalogoDeCuentas.fxml");
            catalogo.setMainApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RegistrarEmpresa() {
        try {
            RegistroDeEmpresaController registro = (RegistroDeEmpresaController) replaceSceneContent("/ContaProject/vistas/RegistroDeEmpresa.fxml");
            registro.setMainApp(this);
        } catch (Exception ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DatosCuenta(Cuenta cuenta) throws Exception {
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(ContaProyectMain.class.getResource("/ContaProject/vistas/DatosCuenta.fxml"));
            AnchorPane panel = (AnchorPane) cargador.load();

            Stage Escenario = new Stage();
            Escenario.setTitle("Datos De Cuenta");
            Escenario.initModality(Modality.WINDOW_MODAL);
            Escenario.initOwner(primaryStage);
            Scene EscenaRTtran = new Scene(panel);
            Escenario.setScene(EscenaRTtran);
            DatosCuentaController controlador = cargador.getController();
            controlador.setMain(this, cuenta);

            Escenario.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BalanceComprobacion() {
        EmpresaBd empresaBd = new EmpresaBd();
        try {
            if (empresaBd.ValidarEstadoPeriodo()) {
                FXMLLoader cargador = new FXMLLoader();
                cargador.setLocation(ContaProyectMain.class.getResource("/ContaProject/vistas/BalanceComprobacion.fxml"));
                AnchorPane panel = (AnchorPane) cargador.load();
                Stage escenario = new Stage();
                escenario.setTitle("Balance De comprobacion");
                escenario.initModality(Modality.WINDOW_MODAL);
                escenario.initOwner(primaryStage);
                Scene escena = new Scene(panel);
                escenario.setScene(escena);
                BalanceComprobacionController controlador = cargador.getController();
                controlador.setMainApp(this);
                escenario.showAndWait();
            } else {
                JOptionPane.showMessageDialog(null, "todavia no el fin de periodo contable");
            }
        } catch (IOException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EstadoResultado() {
        EmpresaBd empresaBd = new EmpresaBd();
        try {
            if (empresaBd.ValidarEstadoPeriodo()) {
                FXMLLoader cargador = new FXMLLoader();
                cargador.setLocation(ContaProyectMain.class.getResource("/ContaProject/vistas/Estadoresultado.fxml"));
                AnchorPane panel = (AnchorPane) cargador.load();
                Stage escenario = new Stage();
                escenario.setTitle("Estado De Resultado");
                escenario.initModality(Modality.WINDOW_MODAL);
                escenario.initOwner(primaryStage);
                Scene escena = new Scene(panel);
                escenario.setScene(escena);
                EstadoresultadoController controlador = cargador.getController();
                controlador.setMainApp(this);
                escenario.showAndWait();
            } else {
                JOptionPane.showMessageDialog(null, "todavia no el fin de periodo contable");
            }
        } catch (IOException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void estadoCapital() {
        EmpresaBd empresaBd = new EmpresaBd();
        try {
            if (empresaBd.ValidarEstadoPeriodo()) {
                FXMLLoader cargador = new FXMLLoader();
                cargador.setLocation(ContaProyectMain.class.getResource("/ContaProject/vistas/Estadocapital.fxml"));
                AnchorPane panel = (AnchorPane) cargador.load();
                Stage escenario = new Stage();
                escenario.setTitle("Capital");
                escenario.initModality(Modality.WINDOW_MODAL);
                escenario.initOwner(primaryStage);
                Scene escena = new Scene(panel);
                escenario.setScene(escena);
                EstadocapitalController controlador = cargador.getController();
                controlador.setMainApp(this);
                escenario.showAndWait();
            } else {
                JOptionPane.showMessageDialog(null, "todavia no el fin de periodo contable");
            }
        } catch (IOException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DatosASeleccionar() throws Exception {
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(ContaProyectMain.class.getResource("/ContaProject/vistas/CuentasAElegir.fxml"));
            AnchorPane panel = (AnchorPane) cargador.load();

            Stage Escenario = new Stage();
            Escenario.setTitle("Elija Una Cuenta");
            Escenario.initModality(Modality.WINDOW_MODAL);
            Escenario.initOwner(primaryStage);
            Scene EscenaRTtran = new Scene(panel);
            Escenario.setScene(EscenaRTtran);
            CuentasAElegirController controlador = cargador.getController();
            controlador.setMainApp(this, Escenario);
            Escenario.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//+++++++++++++++++++++++++++++++++++++++++++++++reemplaza la pantalla por otra+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Node replaceSceneContent(String fxml) throws Exception {
        //objeto de tipo Fxmllouder
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ContaProyectMain.class.getResource(fxml));
        AnchorPane page = (AnchorPane) loader.load();

        // Store the stage width and height in case the user has resized the window
        double stageWidth = primaryStage.getWidth();
        if (!Double.isNaN(stageWidth)) {
            stageWidth -= (primaryStage.getWidth() - primaryStage.getScene().getWidth());
        }

        double stageHeight = primaryStage.getHeight();
        if (!Double.isNaN(stageHeight)) {
            stageHeight -= (primaryStage.getHeight() - primaryStage.getScene().getHeight());
        }

        Scene scene = new Scene(page);
        scene.setFill(Color.BROWN);
        if (!Double.isNaN(stageWidth)) {
            page.setPrefWidth(stageWidth);
        }
        if (!Double.isNaN(stageHeight)) {
            page.setPrefHeight(stageHeight);
        }

        primaryStage.setScene(scene);
        primaryStage.setX(500);
        primaryStage.setY(10);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        return (Node) loader.getController();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
