package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsTipoPresentacionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1585498325059515781L;

	private Integer nuIdTipoPresentacion;
	private Integer nuIdTipoSolicitud;
	private String vcDesPresentacion;
	private String vcUrlIcono;
	private String vcTooltip;
	
	
	private boolean blSeleccionado;
	private boolean  blDenominacion;
	private boolean  blAdjuntarFigura;
	private boolean  blReinvindicaColores;
	private boolean  blPalFrase;
	
	
	
	public boolean isBlSeleccionado() {
		return blSeleccionado;
	}
	public void setBlSeleccionado(boolean blSeleccionado) {
		this.blSeleccionado = blSeleccionado;
	}
	public boolean isBlPalFrase() {
		return blPalFrase;
	}
	public void setBlPalFrase(boolean blPalFrase) {
		this.blPalFrase = blPalFrase;
	}
	public boolean isBlDenominacion() {
		return blDenominacion;
	}
	public void setBlDenominacion(boolean blDenominacion) {
		this.blDenominacion = blDenominacion;
	}
	public boolean isBlAdjuntarFigura() {
		return blAdjuntarFigura;
	}
	public void setBlAdjuntarFigura(boolean blAdjuntarFigura) {
		this.blAdjuntarFigura = blAdjuntarFigura;
	}
	public boolean isBlReinvindicaColores() {
		return blReinvindicaColores;
	}
	public void setBlReinvindicaColores(boolean blReinvindicaColores) {
		this.blReinvindicaColores = blReinvindicaColores;
	}
	public Integer getNuIdTipoPresentacion() {
		return nuIdTipoPresentacion;
	}
	public void setNuIdTipoPresentacion(Integer nuIdTipoPresentacion) {
		this.nuIdTipoPresentacion = nuIdTipoPresentacion;
	}
	public Integer getNuIdTipoSolicitud() {
		return nuIdTipoSolicitud;
	}
	public void setNuIdTipoSolicitud(Integer nuIdTipoSolicitud) {
		this.nuIdTipoSolicitud = nuIdTipoSolicitud;
	}
	public String getVcDesPresentacion() {
		return vcDesPresentacion;
	}
	public void setVcDesPresentacion(String vcDesPresentacion) {
		this.vcDesPresentacion = vcDesPresentacion;
	}
	public String getVcUrlIcono() {
		return vcUrlIcono;
	}
	public void setVcUrlIcono(String vcUrlIcono) {
		this.vcUrlIcono = vcUrlIcono;
	}
	public String getVcTooltip() {
		return vcTooltip;
	}
	public void setVcTooltip(String vcTooltip) {
		this.vcTooltip = vcTooltip;
	}
	
	
}
