package pe.gob.indecopi.service;

import pe.gob.indecopi.bean.ClsFiltroUbigeoBean;
import pe.gob.indecopi.result.ClsUbigeoResult;

public interface ClsUbigeoServiceI {

	public ClsUbigeoResult doUbigeo(ClsFiltroUbigeoBean objFiltro);
	
}
