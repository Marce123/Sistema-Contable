/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.bd;

import ContaProject.controladores.ContaProyectMain;
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
public class TipoDeCuentaBd {

    private final ObservableList<String> listaTipo = FXCollections.observableArrayList();

    /**
     *
     * @param nombreTipo
     * @return un entero para identificar el id_tipo de la cuenta seleccionada
     * en el combo box
     */
    public Integer ConsultaIdTipo(String nombreTipo) {
        Integer idTipo = null;
        try {
            String sentenciaSql = "SELECT id_tipo FROM tipo WHERE nombre_tipo = ?";
            PreparedStatement preparedStatement = Conexion.getConexion().prepareStatement(sentenciaSql);
            preparedStatement.setString(1, nombreTipo);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                idTipo = resultado.getInt("id_tipo");
            }
            return idTipo;
        } catch (SQLException e) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, e);
            return null;

        }
    }

    //Sirve para llenar el combo box de la nueva cuenta
    public void llenarListaTipoCuenta() {
        try {
            String sentenciaSql = "SELECT * FROM tipo";
            Statement statement = Conexion.getConexion().createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                String nombreTipo = resultado.getString("nombre_tipo");
                Integer idTipo = resultado.getInt("id_tipo");
                if (nombreTipo != null) {
                    TipoDeCuenta tipo = new TipoDeCuenta(nombreTipo, idTipo);
                    listaTipo.add(tipo.getNombreTipo());
                } else {
                    System.out.println("el tipo es nulo");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaProyectMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<String> getListaTipo() {
        return listaTipo;
    }
}
