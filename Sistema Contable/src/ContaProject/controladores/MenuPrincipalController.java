/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.controladores;


import ContaProject.bd.CuentaBd;
import ContaProject.bd.EmpresaBd;
import ContaProject.bd.TransaccionBd;
import java.util.Calendar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Martinez
 */
public class MenuPrincipalController extends AnchorPane {

        
    public MenuPrincipalController() {
    }
    
    @FXML private Button btnIniciarPeriodo;
    @FXML private Button btnFinalizarPeriodo;
    @FXML private Label lblInicioPeriodo;
    @FXML private Label lblFinPeriodo;

    private EmpresaBd empresaBd = new EmpresaBd();
    private ContaProyectMain mainApp;
    private final Calendar Calendario = Calendar.getInstance();
    
    public void setApp(ContaProyectMain application){
        this.mainApp = application;
        lblInicioPeriodo.setText(empresaBd.consultaFechaInicio());
    }
    
    @FXML private void handleRTransaccion(){
        mainApp.registrarTransaccion();
    }
    
    @FXML private void handleNcuenta() throws Exception{
       mainApp.IngresarCuenta();
    }
    
    @FXML private void handleCatalogoCuentas(){
        mainApp.CatalogoDeCuentas();
    }
    
    @FXML private void estadoResultado(){
       mainApp.EstadoResultado();
    }
    
    @FXML private void estadoCapital(){
       mainApp.estadoCapital();
    }
    
    /**
     * Metodo para finalizar el periodo y asignarle true al estado
     */
    @FXML private void finalizarPeriodo(){
        empresaBd.actualizarPeriodoEstado(true);
        String dia = Integer.toString(Calendario.get(Calendar.DATE));
        String mes = Integer.toString(Calendario.get(Calendar.MONTH));
        String año = Integer.toString(Calendario.get(Calendar.YEAR));
        System.out.println(dia+"/"+mes+"/"+"/"+año);
        empresaBd.actualizarFechaFin(dia+"/"+mes+"/"+"/"+año);
    }
    
    /**
     * Metodo para iniciar el periodo y asignarle false al estado
     */
    @FXML private void IniciarPeriodo(){
        CuentaBd cuentaBd = new CuentaBd();
        TransaccionBd transaccionBd = new TransaccionBd();
        empresaBd.actualizarPeriodoEstado(false);
        String dia = Integer.toString(Calendario.get(Calendar.DATE));
        String mes = Integer.toString(Calendario.get(Calendar.MONTH));
        String año = Integer.toString(Calendario.get(Calendar.YEAR));
        lblInicioPeriodo.setText(dia+"/"+mes+"/"+"/"+año);
        empresaBd.actualizarFechaInicio(dia+"/"+mes+"/"+"/"+año);
        cuentaBd.EliminarCuentasAfectadas();
        transaccionBd.EliminarTransacciones();
    }
    
    @FXML private void balanceComprobacion(){
        mainApp.BalanceComprobacion();
    }

}
