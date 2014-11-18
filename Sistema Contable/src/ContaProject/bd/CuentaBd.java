/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.bd;

import ContaProject.controladores.ContaProyectMain;
import ContaProject.modelo.Cuenta;
import ContaProject.modelo.TipoDeCuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Martinez
 */
public class CuentaBd {

    private final ObservableList<Cuenta> listaCuenta = FXCollections.observableArrayList();

    public void consultaCatalogoDeCuentas() {
        listaCuenta.clear();
        try {
            String sentenciaSql = "SELECT id_cuenta, id_tipo, nombre_tipo, nombre_cuenta  FROM cuenta_tipo order by id_cuenta;";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareCall(sentenciaSql);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Cuenta cuenta = new Cuenta(resultado.getString("id_cuenta"), resultado.getString("nombre_cuenta"),
                        new TipoDeCuenta(resultado.getString("nombre_tipo")), 0.0, 0.0);
                listaCuenta.add(cuenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param idTipo
     * @param nombreTipo
     * @param nombreCuenta
     * @param idCuenta
     *
     */
    public void guardarCuentas(Integer idTipo, String nombreTipo, String nombreCuenta, String idCuenta) {
        try {
            TipoDeCuenta temTipo = new TipoDeCuenta(nombreTipo, idTipo);
            Integer id_tipo = temTipo.getIdTipo();
            Cuenta cuenta = new Cuenta(idCuenta, nombreCuenta, temTipo, 0.0, 0.0);
            String sentenciaSql = "Insert into datos_cuenta (id_cuenta,nombre_cuenta) values (?,?);"
                    + "insert into cuenta_tipo(id_cuenta,id_tipo,nombre_tipo,nombre_cuenta)"
                    + "values(?,?,?,?);";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            //TABLA CUENTA
            preparedStatement.setString(1, idCuenta);
            preparedStatement.setString(2, nombreCuenta);
            //TABLA CUENTA_TIPO            
            preparedStatement.setString(3, idCuenta);
            preparedStatement.setInt(4, idTipo);
            preparedStatement.setString(5, nombreTipo);
            preparedStatement.setString(6, nombreCuenta);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     *
     * @param id_Cuenta
     * @return Cuenta si la cuenta existe la regresa null si la cuenta no exista
     */
    public Cuenta consultarSaldoCuentas(String id_Cuenta) {
        Cuenta cuenta = null;
        try {
            if (!id_Cuenta.isEmpty()) {
                String sentenciaSql = "SELECT id_cuenta, saldo_total_acreedor, saldo_total_deudor FROM \"cuentas_saldos_totales\" WHERE id_cuenta = ?;";
                PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
                preparedStatement.setString(1, id_Cuenta);
                ResultSet resultado = preparedStatement.executeQuery();
                if (resultado.next()) {
                    Double saldoAcreedor = resultado.getDouble("saldo_total_acreedor");
                    Double saldoDeudor = (resultado.getDouble("saldo_total_deudor"));
                    cuenta = new Cuenta(id_Cuenta, null, null, saldoDeudor, saldoAcreedor);
                }
            } else {
                JOptionPane.showMessageDialog(null, "el id_de la cuenta es nulo");
                cuenta = null;
            }
            return cuenta;
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     *
     * @param cuenta
     *
     * Guarda las cuentas Afectadas del periodo
     */
    public void guardarCuentaAfectada(Cuenta cuenta) {
        String id_cuenta = cuenta.getIdCuenta();
        try {
            String sentenciaSql = "INSERT INTO \"cuentas_afectadas_periodo\"(id_cuenta)VALUES (?);";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, id_cuenta);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param cuenta
     *
     * Ingresa el saldo afectado de una cuenta
     */
    public void guardarSaldo(Cuenta cuenta) {
        String id_cuenta = cuenta.getIdCuenta();
        Double saldo_Acreedor = cuenta.getSalAcreedor();
        Double saldo_Deudor = cuenta.getSalDuedor();
        try {
            String sentenciaSql = "INSERT INTO \"cuentas_saldos_totales\"(id_cuenta, saldo_total_acreedor, saldo_total_deudor)VALUES (?, ?, ?);";
            PreparedStatement preparedStatement;
            preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, id_cuenta);
            preparedStatement.setDouble(2, saldo_Acreedor);
            preparedStatement.setDouble(3, saldo_Deudor);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param idCuenta
     * @param SaldoAcreedor
     * @param SaldoDeudor
     */
    public void actualizarDatosSaltos(String idCuenta, Double SaldoAcreedor, Double SaldoDeudor) {
        try {
            System.out.println("Acreedor " + SaldoAcreedor + "Deudor " + SaldoDeudor + "idCuenta " + idCuenta);
            String sentenciaSql = "UPDATE \"cuentas_saldos_totales\"  SET saldo_total_acreedor=?, saldo_total_deudor=? WHERE id_cuenta = ?;";
            PreparedStatement preparedStatement;
            preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setDouble(1, SaldoAcreedor);
            preparedStatement.setDouble(2, SaldoDeudor);
            preparedStatement.setString(3, idCuenta);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarCuentasAfectadas() {
        try {
            String sentenciaSql = "DELETE FROM \"cuentas_afectadas_periodo\";";
            PreparedStatement preparedStatement;
            preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * @param id_cuenta
     * @return true si la cuenta afectada en la transaccion existe false si la
     * cuenta afectada en la transaccion no existe
     */
    public boolean validarCuentaAfectada(String id_cuenta) {
        boolean existe;
        try {
            String sentenciaSql = "SELECT id_cuenta FROM \"cuentas_afectadas_periodo\" WHERE id_cuenta = ?";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, id_cuenta);
            ResultSet resultado = preparedStatement.executeQuery();
            existe = resultado.next();
            return existe;
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * @param id_cuenta
     * @return true si la cuenta con saldo existe false si la cuenta con saldo
     * no existe en la tabla
     *
     */
    public boolean validarCuentaConSaldo(String id_cuenta) {
        boolean existe;
        try {
            String sentenciaSql = "SELECT id_cuenta FROM \"cuentas_saldos_totales\" WHERE id_cuenta = ?";
            PreparedStatement preparedStatement;
            preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, id_cuenta);
            ResultSet resultado = preparedStatement.executeQuery();
            existe = resultado.next();
            return existe;
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    

    public ObservableList<Cuenta> getListaCuenta() {
        return listaCuenta;
    }
}
