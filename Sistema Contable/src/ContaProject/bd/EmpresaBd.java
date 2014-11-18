/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.bd;

import ContaProject.controladores.ContaProyectMain;
import ContaProject.modelo.Empresa;
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
public class EmpresaBd {

    private final ObservableList<String> listaPeriodos = FXCollections.observableArrayList();
    private final ObservableList<String> listaTipoEmpresa = FXCollections.observableArrayList();

    public String consultaFechaInicio() {
        String cadena = null;
        try {
            String sentenciaSql = "SELECT inicio_periodo FROM \"empresa\";";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                cadena = resultado.getString("inicio_periodo");
            }
            return cadena;
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String consultaFechaFin() {
        String cadena = null;
        try {
            String sentenciaSql = "SELECT  fin_periodo FROM \"empresa\";";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                cadena = resultado.getString("fin_periodo");
            }
            return cadena;
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String consultarNombreEmpresa() {
        String cadena = null;
        try {
            String sentenciaSql = "SELECT nombre_empresa FROM \"empresa\";";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                cadena = resultado.getString("nombre_empresa");
            }
            return cadena;
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     *
     * @param nit
     * @param nombreEmpresa
     * @param creditoFiscal
     * @param representante
     * @param contador
     * @param auditor
     * @param tipoEmpresa
     * @param periodo
     * @return true si la empresa se guardo false si no se puedo guardar por
     * cualquier razon
     */
    public boolean guardarEmpresa(String nit, String nombreEmpresa, String creditoFiscal, String representante, String contador, String auditor, String tipoEmpresa, String periodo) {
        try {
            String sentenciaSql = "INSERT INTO \"empresa\"(\n"
                    + "            nit, nombre_empresa, credito_fiscal, representante, contador, \n"
                    + "            auditor, tipo_empresa, periodo_contable, id, \"estadoterminado\",inicio_periodo, fin_periodo)\n"
                    + "    VALUES (?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?,?,?);";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, nit);
            preparedStatement.setString(2, nombreEmpresa);
            preparedStatement.setString(3, creditoFiscal);
            preparedStatement.setString(4, representante);
            preparedStatement.setString(5, contador);
            preparedStatement.setString(6, auditor);
            preparedStatement.setString(7, tipoEmpresa);
            preparedStatement.setString(8, periodo);
            preparedStatement.setInt(9, 1);
            preparedStatement.setBoolean(10, false);
            preparedStatement.setString(11, " ");
            preparedStatement.setString(12, " ");
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void actualizarFechaInicio(String fechaInicio) {
        try {
            String sentenciaSql = "UPDATE \"empresa\" SET  inicio_periodo=? WHERE id='1';";
            PreparedStatement preparedStatement;
            preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, fechaInicio);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarFechaFin(String fechaFin) {
        try {
            String sentenciaSql = "UPDATE \"empresa\" SET  fin_periodo=? WHERE id='1';";
            PreparedStatement preparedStatement;
            preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, fechaFin);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param estado Actualiza el estado de periodo contable en la base de datos
     *
     */
    public void actualizarPeriodoEstado(boolean estado) {
        try {
            String sentenciaSql = "UPDATE \"empresa\" SET  \"estadoterminado\"=? WHERE id='1';";
            PreparedStatement preparedStatement;
            preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setBoolean(1, estado);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return true si la empresa existe false si la empresa no existe
     */
    public boolean ValidarEmpresa() {
        boolean existe;
        try {
            String sentenciaSql = "SELECT * FROM \"empresa\";";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            ResultSet resultado = preparedStatement.executeQuery();
            existe = resultado.next();
            return existe;
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * asigna el estado del periodo contable, true si ya termino y false si
     * todavia no ha terminado
     *
     * @return
     */
    public boolean ValidarEstadoPeriodo() {
        Empresa empresa = new Empresa();
        try {

            String sentenciaSql = "SELECT  \"estadoterminado\" FROM \"empresa\" WHERE id='1';";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareCall(sentenciaSql);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                empresa.setPeriodoTerminado(resultado.getBoolean("estadoterminado"));
            }
            return empresa.getPeriodoTerminado();
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * llena la lista de tipo de empresa que se ocupara para llenar el combobox
     */
    public void llenarListaTipoEmpresa() {
        try {
            String sentenciaSql = "SELECT nombre_tipo_empresa FROM tipo_empresa;";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                listaTipoEmpresa.add(resultado.getString("nombre_tipo_empresa"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * llena la lista lista tipo periodo para llenar el combobox del tipo de
     * periodo que llevara la empresa
     */
    public void llenarListaPeriodo() {
        try {
            String sentenciaSql = "SELECT nombre_periodo FROM periodo;";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                listaPeriodos.add(resultado.getString("nombre_periodo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<String> getListaPeriodo() {
        return listaPeriodos;
    }

    public ObservableList<String> getListaTipoEmpresa() {
        return listaTipoEmpresa;
    }
    
    

}
