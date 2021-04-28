package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClsPrioridadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7911020842964168738L;
	
	private Integer nuFlagTodos;
    private String vcNombreClases;
    private String vcClases;
    private String vcNroSolicitud;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
    private Date dtFechaPrioridad;
    private Integer nuIdUbigeoPais;
    private Integer nuTipoPrioridad;
    private String vcTipoPrioridad;
    private String vcProductoServicio;
    
	private List<ClsArchivoBean> lstArchivoPrioridad;
	
	public ClsPrioridadBean() {
		this.setLstArchivoPrioridad(new ArrayList<ClsArchivoBean>());
	}
    
    
	public String getVcProductoServicio() {
		return vcProductoServicio;
	}


	public void setVcProductoServicio(String vcProductoServicio) {
		this.vcProductoServicio = vcProductoServicio;
	}


	public List<ClsArchivoBean> getLstArchivoPrioridad() {
		return lstArchivoPrioridad;
	}
	public void setLstArchivoPrioridad(List<ClsArchivoBean> lstArchivoPrioridad) {
		this.lstArchivoPrioridad = lstArchivoPrioridad;
	}
	public Integer getNuFlagTodos() {
		return nuFlagTodos;
	}
	public void setNuFlagTodos(Integer nuFlagTodos) {
		this.nuFlagTodos = nuFlagTodos;
	}
	public String getVcNombreClases() {
		return vcNombreClases;
	}
	public void setVcNombreClases(String vcNombreClases) {
		this.vcNombreClases = vcNombreClases;
	}
	public String getVcClases() {
		return vcClases;
	}
	public void setVcClases(String vcClases) {
		this.vcClases = vcClases;
	}
	public String getVcNroSolicitud() {
		return vcNroSolicitud;
	}
	public void setVcNroSolicitud(String vcNroSolicitud) {
		this.vcNroSolicitud = vcNroSolicitud;
	}
	public Date getDtFechaPrioridad() {
		return dtFechaPrioridad;
	}
	public void setDtFechaPrioridad(Date dtFechaPrioridad) {
		this.dtFechaPrioridad = dtFechaPrioridad;
	}
	public Integer getNuIdUbigeoPais() {
		return nuIdUbigeoPais;
	}
	public void setNuIdUbigeoPais(Integer nuIdUbigeoPais) {
		this.nuIdUbigeoPais = nuIdUbigeoPais;
	}
	public Integer getNuTipoPrioridad() {
		return nuTipoPrioridad;
	}
	public void setNuTipoPrioridad(Integer nuTipoPrioridad) {
		this.nuTipoPrioridad = nuTipoPrioridad;
	}
	public String getVcTipoPrioridad() {
		return vcTipoPrioridad;
	}
	public void setVcTipoPrioridad(String vcTipoPrioridad) {
		this.vcTipoPrioridad = vcTipoPrioridad;
	}
    
    

}
