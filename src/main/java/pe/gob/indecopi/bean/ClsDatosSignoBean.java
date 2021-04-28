package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClsDatosSignoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8862113753853618856L;
	
	private Integer nuIdTipoSolicitud;
    private Integer nuFlagInteresReal;
    private String vcNroExpediente;
    private Integer nuFlaDerechoPreferente;
    private String vcNroCertificado;
    private Integer nuIdTipoSigno;
    private String vcDenominacion;
    private Integer nuFlagReinvindicaCol;
    private List<ClsArchivoBean> lstArchivosSigno;
    
    private String vcCertificadoLema;
    private String vcNroExpedienteLema;
    private Integer nuClaseLema;
    private String vcNroExpedienteNombreComercial;
    
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
    private Date vcFechaPrimerUsoNombreComercial;
    
    
    
    public ClsDatosSignoBean() {
    	this.setLstArchivosSigno(new ArrayList<ClsArchivoBean>());
    }
    
    
	public String getVcCertificadoLema() {
		return vcCertificadoLema;
	}


	public void setVcCertificadoLema(String vcCertificadoLema) {
		this.vcCertificadoLema = vcCertificadoLema;
	}


	public String getVcNroExpedienteLema() {
		return vcNroExpedienteLema;
	}


	public void setVcNroExpedienteLema(String vcNroExpedienteLema) {
		this.vcNroExpedienteLema = vcNroExpedienteLema;
	}


	public Integer getNuClaseLema() {
		return nuClaseLema;
	}


	public void setNuClaseLema(Integer nuClaseLema) {
		this.nuClaseLema = nuClaseLema;
	}


	public String getVcNroExpedienteNombreComercial() {
		return vcNroExpedienteNombreComercial;
	}


	public void setVcNroExpedienteNombreComercial(String vcNroExpedienteNombreComercial) {
		this.vcNroExpedienteNombreComercial = vcNroExpedienteNombreComercial;
	}


	public Date getVcFechaPrimerUsoNombreComercial() {
		return vcFechaPrimerUsoNombreComercial;
	}


	public void setVcFechaPrimerUsoNombreComercial(Date vcFechaPrimerUsoNombreComercial) {
		this.vcFechaPrimerUsoNombreComercial = vcFechaPrimerUsoNombreComercial;
	}


	public String getVcDenominacion() {
		return vcDenominacion;
	}


	public void setVcDenominacion(String vcDenominacion) {
		this.vcDenominacion = vcDenominacion;
	}


	public Integer getNuFlagReinvindicaCol() {
		return nuFlagReinvindicaCol;
	}
	public void setNuFlagReinvindicaCol(Integer nuFlagReinvindicaCol) {
		this.nuFlagReinvindicaCol = nuFlagReinvindicaCol;
	}
	public List<ClsArchivoBean> getLstArchivosSigno() {
		return lstArchivosSigno;
	}
	public void setLstArchivosSigno(List<ClsArchivoBean> lstArchivosSigno) {
		this.lstArchivosSigno = lstArchivosSigno;
	}
	public Integer getNuIdTipoSolicitud() {
		return nuIdTipoSolicitud;
	}
	public void setNuIdTipoSolicitud(Integer nuIdTipoSolicitud) {
		this.nuIdTipoSolicitud = nuIdTipoSolicitud;
	}
	public Integer getNuFlagInteresReal() {
		return nuFlagInteresReal;
	}
	public void setNuFlagInteresReal(Integer nuFlagInteresReal) {
		this.nuFlagInteresReal = nuFlagInteresReal;
	}
	public String getVcNroExpediente() {
		return vcNroExpediente;
	}
	public void setVcNroExpediente(String vcNroExpediente) {
		this.vcNroExpediente = vcNroExpediente;
	}
	public Integer getNuFlaDerechoPreferente() {
		return nuFlaDerechoPreferente;
	}
	public void setNuFlaDerechoPreferente(Integer nuFlaDerechoPreferente) {
		this.nuFlaDerechoPreferente = nuFlaDerechoPreferente;
	}
	public String getVcNroCertificado() {
		return vcNroCertificado;
	}
	public void setVcNroCertificado(String vcNroCertificado) {
		this.vcNroCertificado = vcNroCertificado;
	}
	public Integer getNuIdTipoSigno() {
		return nuIdTipoSigno;
	}
	public void setNuIdTipoSigno(Integer nuIdTipoSigno) {
		this.nuIdTipoSigno = nuIdTipoSigno;
	}

    
	
}
