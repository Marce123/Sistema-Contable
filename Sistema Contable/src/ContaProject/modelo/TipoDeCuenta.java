/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Martinez
 */
public class TipoDeCuenta {

    private StringProperty nombreTipo;
    private IntegerProperty idTipo;

    public TipoDeCuenta(String nombreTipo) {
        this.nombreTipo = new SimpleStringProperty(nombreTipo);

    }

    public TipoDeCuenta(String nombre, Integer id) {
        this.nombreTipo = new SimpleStringProperty(nombre);
        this.idTipo = new SimpleIntegerProperty(id);
    }

    public final StringProperty nombreTipoProperty() {
        return nombreTipo;
    }

    public final String getNombreTipo() {
        return nombreTipo.get();
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo.set(nombreTipo);
    }

    public IntegerProperty idTipoProperty() {
        return idTipo;
    }

    public Integer getIdTipo() {
        return idTipo.get();
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo.set(idTipo);
    }

    @Override
    public String toString() {
        String cadena = getNombreTipo();
        return cadena;
    }

}
