package pe.gob.indecopi.result;

import java.io.Serializable;

import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.util.ClsErrorResult;

public class ClsUsuarioResult extends ClsErrorResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5462651987750867796L;

	private ClsUsuarioBean objUsuario;
	
	public ClsUsuarioResult() {
		this.setObjUsuario(new ClsUsuarioBean());
	}

	public ClsUsuarioBean getObjUsuario() {
		return objUsuario;
	}

	public void setObjUsuario(ClsUsuarioBean objUsuario) {
		this.objUsuario = objUsuario;
	}
	
	

}
