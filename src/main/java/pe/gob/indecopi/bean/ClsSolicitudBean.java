package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClsSolicitudBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6709970203377372973L;
	
	private String vcUsuario;
	private String vcHostName;
	private ClsAsesoriaBean objAsesoria;
	private ClsDatosSignoBean objDatosSigno;
	private List<ClsClaseSolBean> lstClases;
	private ClsPrioridadExtBean objPrioridadExtr;
	private List<ClsPersonaBean> lstPersona;
	private List<ClsPagoBean> lstPago;
	
	public ClsSolicitudBean() {
		this.setObjAsesoria(new ClsAsesoriaBean());
		this.setObjDatosSigno(new ClsDatosSignoBean());
		this.setObjPrioridadExtr(new ClsPrioridadExtBean());
		this.setLstClases(new ArrayList<ClsClaseSolBean>());
		this.setLstPersona(new ArrayList<ClsPersonaBean>());
		this.setLstPago(new ArrayList<ClsPagoBean>());
	}
	

	public List<ClsPagoBean> getLstPago() {
		return lstPago;
	}

	public void setLstPago(List<ClsPagoBean> lstPago) {
		this.lstPago = lstPago;
	}

	public String getVcUsuario() {
		return vcUsuario;
	}

	public void setVcUsuario(String vcUsuario) {
		this.vcUsuario = vcUsuario;
	}


	public String getVcHostName() {
		return vcHostName;
	}


	public void setVcHostName(String vcHostName) {
		this.vcHostName = vcHostName;
	}


	public List<ClsPersonaBean> getLstPersona() {
		return lstPersona;
	}
	public void setLstPersona(List<ClsPersonaBean> lstPersona) {
		this.lstPersona = lstPersona;
	}
	public ClsPrioridadExtBean getObjPrioridadExtr() {
		return objPrioridadExtr;
	}

	public void setObjPrioridadExtr(ClsPrioridadExtBean objPrioridadExtr) {
		this.objPrioridadExtr = objPrioridadExtr;
	}

	public List<ClsClaseSolBean> getLstClases() {
		return lstClases;
	}
	public void setLstClases(List<ClsClaseSolBean> lstClases) {
		this.lstClases = lstClases;
	}

	public ClsAsesoriaBean getObjAsesoria() {
		return objAsesoria;
	}

	public void setObjAsesoria(ClsAsesoriaBean objAsesoria) {
		this.objAsesoria = objAsesoria;
	}

	public ClsDatosSignoBean getObjDatosSigno() {
		return objDatosSigno;
	}

	public void setObjDatosSigno(ClsDatosSignoBean objDatosSigno) {
		this.objDatosSigno = objDatosSigno;
	}
	
	
	

}
