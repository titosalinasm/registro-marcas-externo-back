package pe.gob.indecopi.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.gob.indecopi.bean.ClsUbigeoBean;

public class ClsUbigeoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8446522444772201224L;

	private List<ClsUbigeoBean> lstUbigeo;
	
	public ClsUbigeoResult() {
		this.setLstUbigeo(new ArrayList<ClsUbigeoBean>());
	}

	public List<ClsUbigeoBean> getLstUbigeo() {
		return lstUbigeo;
	}

	public void setLstUbigeo(List<ClsUbigeoBean> lstUbigeo) {
		this.lstUbigeo = lstUbigeo;
	}
	
	
}
