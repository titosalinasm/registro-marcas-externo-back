package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsArchivoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String vcRuta;
	private String vcNomOriginal;
	private String vcNomFinal;
	private String vcExtension;
	private Integer nuIdRepositorio;
	private Integer nuIdTipoArchivo;
	private Long nuLong;
	
	private String vcFileBase64;

	public ClsArchivoBean() {
		// TODO Auto-generated constructor stub
	}

	
	public String getVcFileBase64() {
		return vcFileBase64;
	}
	public void setVcFileBase64(String vcFileBase64) {
		this.vcFileBase64 = vcFileBase64;
	}
	public Integer getNuIdTipoArchivo() {
		return nuIdTipoArchivo;
	}

	public void setNuIdTipoArchivo(Integer nuIdTipoArchivo) {
		this.nuIdTipoArchivo = nuIdTipoArchivo;
	}

	public String getVcRuta() {
		return vcRuta;
	}

	public void setVcRuta(String vcRuta) {
		this.vcRuta = vcRuta;
	}

	public String getVcNomOriginal() {
		return vcNomOriginal;
	}

	public void setVcNomOriginal(String vcNomOriginal) {
		this.vcNomOriginal = vcNomOriginal;
	}

	public String getVcNomFinal() {
		return vcNomFinal;
	}

	public void setVcNomFinal(String vcNomFinal) {
		this.vcNomFinal = vcNomFinal;
	}

	public String getVcExtension() {
		return vcExtension;
	}

	public void setVcExtension(String vcExtension) {
		this.vcExtension = vcExtension;
	}

	public Integer getNuIdRepositorio() {
		return nuIdRepositorio;
	}

	public void setNuIdRepositorio(Integer nuIdRepositorio) {
		this.nuIdRepositorio = nuIdRepositorio;
	}

	public Long getNuLong() {
		return nuLong;
	}

	public void setNuLong(Long nuLong) {
		this.nuLong = nuLong;
	}


}
