/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.bd;

import ContaProject.controladores.ContaProyectMain;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Martinez
 */
public class TransaccionBd {
    
    
     /**
     *
     * @param monto
     * @param id_transaccion
     * @param fecha
     */
    public void guardarTransaccion(Double monto, Integer id_transaccion, String fecha) {
        try {
            String sentenciaSql = "INSERT INTO transaccion(id_transaccion, monto, fecha)VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, id_transaccion);
            preparedStatement.setDouble(2, monto);
            preparedStatement.setString(3, fecha);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Posible codigo de transaccion ya existente, ingrese otro");
        }
    }
    
    /**
     *
     * @param id_cuenta
     * @param id_transaccion
     * @param sal_deudor
     * @param saldo_acreedor
     * @param nombre_cuenta
     * @param fecha
     */
    public void guardarTransaccionCuentas(String id_cuenta, Integer id_transaccion, Double sal_deudor, Double saldo_acreedor, String nombre_cuenta, String fecha) {
        try {
            String sentenciaSql = "INSERT INTO cuenta_transaccion(id_cuenta, id_transaccion, sal_deudor, sal_acreedor, nombre_cuenta, fecha)VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, id_cuenta);
            preparedStatement.setInt(2, id_transaccion);
            preparedStatement.setDouble(3, sal_deudor);
            preparedStatement.setDouble(4, saldo_acreedor);
            preparedStatement.setString(5, nombre_cuenta);
            preparedStatement.setString(6, fecha);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void EliminarTransacciones() {
        try {
            String sentenciaSql = "DELETE FROM \"cuentas_afectadas_periodo\";";
            PreparedStatement preparedStatement;
            preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
