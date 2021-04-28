package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsTipoSolicitud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2530491578770049199L;

	private Integer nuIdTipoSolicitud;
	private String vcDesTipoSolicitud;
	private String vcUrlIcono;
	private String vcTooltip;
	
	private boolean blSeleccionado;

	
	
	public boolean isBlSeleccionado() {
		return blSeleccionado;
	}
	public void setBlSeleccionado(boolean blSeleccionado) {
		this.blSeleccionado = blSeleccionado;
	}
	
	public Integer getNuIdTipoSolicitud() {
		return nuIdTipoSolicitud;
	}
	public void setNuIdTipoSolicitud(Integer nuIdTipoSolicitud) {
		this.nuIdTipoSolicitud = nuIdTipoSolicitud;
	}
	public String getVcDesTipoSolicitud() {
		return vcDesTipoSolicitud;
	}
	public void setVcDesTipoSolicitud(String vcDesTipoSolicitud) {
		this.vcDesTipoSolicitud = vcDesTipoSolicitud;
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
