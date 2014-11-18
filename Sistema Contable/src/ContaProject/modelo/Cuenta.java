/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.modelo;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Martinez
 */
public class Cuenta {
    private  StringProperty idCuenta;
   
    private  StringProperty nombre;
    private  ObjectProperty<TipoDeCuenta> tipo;
    private  ObjectProperty<Double> salDuedor;
    private  ObjectProperty<Double> salHaber;
    

    /**
     * 
     * @param idCuenta
     * @param nombre
     * @param tipo
     * @param sDeudor
     * @param SHaber 
     */
    public Cuenta(String idCuenta, String nombre, TipoDeCuenta tipo ,Double sDeudor,Double SHaber) {
        this.idCuenta = new SimpleStringProperty(idCuenta);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleObjectProperty<TipoDeCuenta>(tipo);
        this.salDuedor = new SimpleObjectProperty<Double>(sDeudor); 
        this.salHaber = new SimpleObjectProperty<Double>(SHaber); 
    }

    public Cuenta() {
        this.idCuenta = null;
        this.nombre = null;
        this.tipo = null;
        this.salDuedor = null; 
        this.salHaber = null;
    }

    public final StringProperty idCuentaProperty() {
        return idCuenta;
    }

    public final StringProperty NombreProperty() {
        return nombre;
    }

    public final ObjectProperty<TipoDeCuenta> TipoProperty() {
        return tipo;
    }

    public final ObjectProperty<Double> SalDuedorProperty() {
        return salDuedor;
    }

    public final ObjectProperty<Double> SalAcreedoProperty() {
        return salHaber;
    }
    
    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    public void setIdCuenta(String codigo){
        this.idCuenta.set(codigo);
    }
    
    public void setTipo(TipoDeCuenta tipo){
        this.tipo.set(tipo);
    }

    public void setSalDuedor(Double salDuedor) {
        this.salDuedor.set(salDuedor);
    }

    public void setSalAcreedor(Double salAcreedor) {
        this.salHaber.set(salAcreedor); ;
    }

    public String getNombre(){
       return this.nombre.get();
    }
    
    public String getIdCuenta(){
        return this.idCuenta.get();
    }
    
    public TipoDeCuenta getTipo(){
       return this.tipo.get();
    }

    public Double getSalDuedor() {
        return salDuedor.get();
    }

    public Double getSalAcreedor() {
        return salHaber.get();
    }
    
    @Override
    public String toString() {
        return  "codigo=" + getIdCuenta() + ", nombre=" + getNombre()+ ", tipo=" + getTipo()+ "saldoAcreedor " + getSalAcreedor() + "saldoDeudor " + getSalDuedor();
    }
    
    

    


    
    

    
    

    



}
