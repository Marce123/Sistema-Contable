/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.modelo;

import java.time.LocalDate;
import java.util.Collection;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Martinez
 */
public class Transaccion {
     private final DoubleProperty monto;
     ObservableList<Cuenta> listaCuentas = FXCollections.observableArrayList();
     private final ObjectProperty<LocalDate> fecha;

    public Transaccion() {
        this.monto = null;
        this.fecha = null;
    }

    public Transaccion(Double monto) {
        this.monto = new SimpleDoubleProperty(monto);
        this.fecha = new SimpleObjectProperty<LocalDate>();
    }

    private DoubleProperty montoProperty(){
        return monto;
    } 
    
    private Double getMonto(){
        return monto.get();
    }
    
    private void setMonto(Double monto){
        this.monto.set(monto);
    }

    public ObservableList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }
    
    public void setListaCuenta(Collection<Cuenta> cuenta){
        this.listaCuentas.setAll(cuenta);
    }
    
    public LocalDate getFecha() {
        return fecha.get();
    }

    public void setFecha(LocalDate fecha) {
        this.fecha.set(fecha);
    }
    
    public ObjectProperty<LocalDate> fechaProperty(){
        return fecha;
    }
    
    
    
     
}
