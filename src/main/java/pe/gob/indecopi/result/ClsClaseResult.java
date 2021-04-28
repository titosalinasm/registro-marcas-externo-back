package pe.gob.indecopi.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.gob.indecopi.bean.ClsClaseBean;
import pe.gob.indecopi.bean.ClsNizaBean;

public class ClsClaseResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9163441513166175574L;
	
	public ClsClaseResult() {
		this.setLstClase(new ArrayList<ClsNizaBean>());
	}
	
	private List<ClsNizaBean> lstClase;

	public List<ClsNizaBean> getLstClase() {
		return lstClase;
	}

	public void setLstClase(List<ClsNizaBean> lstClase) {
		this.lstClase = lstClase;
	}
	
	
	

}
