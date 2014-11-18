/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContaProject.modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Martinez
 */
public class Empresa {
    private StringProperty nit;
    private StringProperty nombre;
    private StringProperty creditoFiscal;
    private StringProperty representante;
    private StringProperty contador;
    private StringProperty auditor;
    private StringProperty tipoEmpresa;
    private StringProperty periodoContable;
    private StringProperty fechaInicio;
    private StringProperty fechaFin;
    private ObjectProperty<Boolean> periodoTerminado;    

    public Empresa() {
    }

    public Empresa(String nit, String nombre, String creditoFiscal, String representante, String contador, String auditor, String tipoEmpresa, String periodoContable, String fechaInicio, String fechaFin, Boolean periodoTerminado) {
        this.nit = new SimpleStringProperty(nit);
        this.nombre = new SimpleStringProperty(nombre);
        this.creditoFiscal = new SimpleStringProperty(creditoFiscal);
        this.representante = new SimpleStringProperty(representante);
        this.contador = new SimpleStringProperty(contador);
        this.auditor = new SimpleStringProperty(auditor);
        this.tipoEmpresa = new SimpleStringProperty(tipoEmpresa);
        this.periodoContable = new SimpleStringProperty(periodoContable);
        this.fechaInicio = new SimpleStringProperty(fechaInicio);
        this.fechaFin = new SimpleStringProperty(fechaFin);
        this.periodoTerminado = new SimpleObjectProperty<Boolean>(periodoTerminado);
    }

    public StringProperty NitProperty() {
        return nit;
    }

    public StringProperty NombreProperty() {
        return nombre;
    }

    public StringProperty CreditoFiscalProperty() {
        return creditoFiscal;
    }

    public StringProperty RepresentanteProperty() {
        return representante;
    }

    public StringProperty ContadorProperty() {
        return contador;
    }

    public StringProperty AuditorProperty() {
        return auditor;
    }

    public StringProperty TipoEmpresaProperty() {
        return tipoEmpresa;
    }

    public StringProperty PeriodoContableProperty() {
        return periodoContable;
    }

    public StringProperty FechaInicioProperty() {
        return fechaInicio;
    }

    public StringProperty FechaFinProperty() {
        return fechaFin;
    }

    public ObjectProperty<Boolean> PeriodoTerminadoPropertyProperty() {
        return periodoTerminado;
    }

    public void setNit(String nit) {
        this.nit.set(nit);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setCreditoFiscal(String creditoFiscal) {
        this.creditoFiscal.set(creditoFiscal);
    }

    public void setRepresentante(String representante) {
        this.representante.set(representante);
    }

    public void setContador(String contador) {
        this.contador.set(contador);
    }

    public void setAuditor(String auditor) {
        this.auditor.set(auditor);
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa.set(tipoEmpresa);
    }

    public void setPeriodoContable(String periodoContable) {
        this.periodoContable.set(periodoContable);
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio.set(fechaInicio);
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin.set(fechaFin);
    }

    public void setPeriodoTerminado(Boolean periodoTerminado) {
        this.periodoTerminado.set(periodoTerminado);
    }

    public String getNit() {
        return nit.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getCreditoFiscal() {
        return creditoFiscal.get();
    }

    public String getRepresentante() {
        return representante.get();
    }

    public String getContador() {
        return contador.get();
    }

    public String getAuditor() {
        return auditor.get();
    }

    public String getTipoEmpresa() {
        return tipoEmpresa.get();
    }

    public String getPeriodoContable() {
        return periodoContable.get();
    }

    public String getFechaInicio() {
        return fechaInicio.get();
    }

    public String getFechaFin() {
        return fechaFin.get();
    }

    public Boolean getPeriodoTerminado() {
        return periodoTerminado.get();
    }
    
    
    
    
    
}
