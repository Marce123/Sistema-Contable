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

/**
 *
 * @author Martinez
 */
public class EstadosFinancierosBd {

    private final ObservableList<Cuenta> listaBalanceComprobacion = FXCollections.observableArrayList();
    private final ObservableList<Cuenta> listaResultadoIngreso = FXCollections.observableArrayList();
    private final ObservableList<Cuenta> listaResultadoEgreso = FXCollections.observableArrayList();
    private final ObservableList<Cuenta> listaInversion = FXCollections.observableArrayList();
    private final ObservableList<Cuenta> listaDesinversion = FXCollections.observableArrayList();

    public void consultaBalanceComprobacion() {
        listaBalanceComprobacion.clear();
        Double saldoCuenta;
        try {

            String sentenciaSql = "Select cuenta_tipo.id_cuenta, cuenta_tipo.nombre_cuenta, cuenta_tipo.nombre_tipo,cuenta_tipo.id_tipo , \"Cuentas_Saldos_Totales\".saldo_total_acreedor , \"Cuentas_Saldos_Totales\".saldo_total_deudor\n"
                    + "from cuenta_tipo inner join \"Cuentas_Saldos_Totales\" on cuenta_tipo.id_cuenta = \"Cuentas_Saldos_Totales\".id_cuenta inner join \"Cuentas_Afectadas_Periodo\" on\n"
                    + "cuenta_tipo.id_cuenta = \"Cuentas_Afectadas_Periodo\".id_cuenta order by id_cuenta;";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                String idcuenta = resultado.getString("id_cuenta");
                String nombreCuenta = resultado.getString("nombre_cuenta");
                String nombreTipo = resultado.getString("nombre_tipo");
                TipoDeCuenta tipo = new TipoDeCuenta(nombreTipo, resultado.getInt("id_tipo"));
                Double saldoAcreedor = resultado.getDouble("saldo_total_acreedor");
                Double saldoDeudor = resultado.getDouble("saldo_total_deudor");
                if (tipo.getIdTipo().equals(1)) {
                    saldoCuenta = saldoDeudor - saldoAcreedor;
                    if (saldoCuenta < 0) {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, 0.0, -saldoCuenta);
                        listaBalanceComprobacion.add(cuenta);
                    } else {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, saldoCuenta, 0.0);
                        listaBalanceComprobacion.add(cuenta);
                    }
                }
                if (tipo.getIdTipo().equals(2)) {
                    saldoCuenta = saldoAcreedor - saldoDeudor;
                    if (saldoCuenta < 0) {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, -saldoCuenta, 0.0);
                        listaBalanceComprobacion.add(cuenta);
                    } else {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, 0.0, saldoCuenta);
                        listaBalanceComprobacion.add(cuenta);
                    }
                }
                if (tipo.getIdTipo().equals(3)) {
                    saldoCuenta = saldoAcreedor - saldoDeudor;
                    if (saldoCuenta < 0) {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, -saldoCuenta, 0.0);
                        listaBalanceComprobacion.add(cuenta);
                    } else {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, 0.0, saldoCuenta);
                        listaBalanceComprobacion.add(cuenta);
                    }
                }
                if (tipo.getIdTipo().equals(4)) {
                    saldoCuenta = saldoAcreedor - saldoDeudor;
                    if (saldoCuenta < 0) {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, -saldoCuenta, 0.0);
                        listaBalanceComprobacion.add(cuenta);
                    } else {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, 0.0, saldoCuenta);
                        listaBalanceComprobacion.add(cuenta);
                    }
                }
                if (tipo.getIdTipo().equals(5)) {
                    saldoCuenta = saldoAcreedor - saldoDeudor;
                    if (saldoCuenta < 0) {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, -saldoCuenta, 0.0);
                        listaBalanceComprobacion.add(cuenta);
                    } else {
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, 0.0, saldoCuenta);
                        listaBalanceComprobacion.add(cuenta);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consultaEstadoDeResultado() {
        listaResultadoIngreso.clear();
        listaResultadoEgreso.clear();
        Double saldoCuenta;
        try {

            String sentenciaSql = "Select cuenta_tipo.id_cuenta, cuenta_tipo.nombre_cuenta, cuenta_tipo.nombre_tipo,cuenta_tipo.id_tipo , \"Cuentas_Saldos_Totales\".saldo_total_acreedor , \"Cuentas_Saldos_Totales\".saldo_total_deudor\n"
                    + "from cuenta_tipo inner join \"Cuentas_Saldos_Totales\" on cuenta_tipo.id_cuenta = \"Cuentas_Saldos_Totales\".id_cuenta inner join \"Cuentas_Afectadas_Periodo\" on\n"
                    + "cuenta_tipo.id_cuenta = \"Cuentas_Afectadas_Periodo\".id_cuenta order by id_cuenta;";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                String idcuenta = resultado.getString("id_cuenta");
                String nombreCuenta = resultado.getString("nombre_cuenta");
                String nombreTipo = resultado.getString("nombre_tipo");
                TipoDeCuenta tipo = new TipoDeCuenta(nombreTipo, resultado.getInt("id_tipo"));
                Double saldoAcreedor = resultado.getDouble("saldo_total_acreedor");
                Double saldoDeudor = resultado.getDouble("saldo_total_deudor");
                if (tipo.getNombreTipo().equals("Resultado Acreedoras")) {
                    saldoCuenta = saldoAcreedor - saldoDeudor;
                    Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, 0.0, saldoCuenta);
                    listaResultadoIngreso.add(cuenta);
                } else {
                    if (tipo.getNombreTipo().equals("Resultado Deudoras")) {
                        saldoCuenta = saldoDeudor - saldoAcreedor;
                        Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, saldoCuenta, 0.0);
                        listaResultadoEgreso.add(cuenta);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consultaEstadoCapital() {
        listaInversion.clear();
        listaDesinversion.clear();

        Double saldoCuenta;
        try {

            String sentenciaSql = "Select cuenta_tipo.id_cuenta, cuenta_tipo.nombre_cuenta, cuenta_tipo.nombre_tipo,cuenta_tipo.id_tipo , \"Cuentas_Saldos_Totales\".saldo_total_acreedor , \"Cuentas_Saldos_Totales\".saldo_total_deudor\n"
                    + "from cuenta_tipo inner join \"Cuentas_Saldos_Totales\" on cuenta_tipo.id_cuenta = \"Cuentas_Saldos_Totales\".id_cuenta inner join \"Cuentas_Afectadas_Periodo\" on\n"
                    + "cuenta_tipo.id_cuenta = \"Cuentas_Afectadas_Periodo\".id_cuenta order by id_cuenta;";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                String idcuenta = resultado.getString("id_cuenta");
                String nombreCuenta = resultado.getString("nombre_cuenta");
                String nombreTipo = resultado.getString("nombre_tipo");
                TipoDeCuenta tipo = new TipoDeCuenta(nombreTipo, resultado.getInt("id_tipo"));
                Double saldoAcreedor = resultado.getDouble("saldo_total_acreedor");
                Double saldoDeudor = resultado.getDouble("saldo_total_deudor");
                listaInversion.add(buscarCapitalSocial());
                if (tipo.getNombreTipo().equals("Desinversion")) {
                    saldoCuenta = saldoDeudor - saldoAcreedor;
                    Cuenta cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, saldoCuenta, 0.0);
                    listaDesinversion.add(cuenta);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Cuenta buscarCapitalSocial() {
        Double saldoCuenta = 0.0;
        Cuenta cuenta = null;
        try {

            String sentenciaSql = "Select cuenta_tipo.id_cuenta, cuenta_tipo.nombre_cuenta, cuenta_tipo.nombre_tipo,cuenta_tipo.id_tipo , \"Cuentas_Saldos_Totales\".saldo_total_acreedor , \"Cuentas_Saldos_Totales\".saldo_total_deudor\n"
                    + "from cuenta_tipo inner join \"Cuentas_Saldos_Totales\" on cuenta_tipo.id_cuenta = \"Cuentas_Saldos_Totales\".id_cuenta  order by id_cuenta;";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                String idcuenta = resultado.getString("id_cuenta");
                String nombreCuenta = resultado.getString("nombre_cuenta");
                String nombreTipo = resultado.getString("nombre_tipo");
                TipoDeCuenta tipo = new TipoDeCuenta(nombreTipo, resultado.getInt("id_tipo"));
                Double saldoAcreedor = resultado.getDouble("saldo_total_acreedor");
                Double saldoDeudor = resultado.getDouble("saldo_total_deudor");

                if (tipo.getNombreTipo().equalsIgnoreCase("Capital") || tipo.getNombreTipo().equalsIgnoreCase("Capital Social")) {
                    saldoCuenta = saldoAcreedor - saldoDeudor;
                    cuenta = new Cuenta(idcuenta, nombreCuenta, tipo, saldoCuenta, 0.0);
                }
            }
            System.out.println(cuenta);
        } catch (Exception e) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, e);
        }
        return cuenta;
    }
    
    public boolean validarExisteUtilidad() {
        boolean existe;
        try {
            String sentenciaSql = "select * from datos_cuenta where nombre_cuenta = ?";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, "Utilidades");
            ResultSet resultado = preparedStatement.executeQuery();
            existe = resultado.next();
            return existe;
        } catch (Exception e) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, e);
            return existe = false;
        }

    }

    public ObservableList<Cuenta> getListaBalanceComprobacion() {
        return listaBalanceComprobacion;
    }

    public ObservableList<Cuenta> getListaResultadoIngreso() {
        return listaResultadoIngreso;
    }

    public ObservableList<Cuenta> getListaResultadoEgreso() {
        return listaResultadoEgreso;
    }

    public ObservableList<Cuenta> getListaInversion() {
        return listaInversion;
    }

    public ObservableList<Cuenta> getListaDesinversion() {
        return listaDesinversion;
    }
}
