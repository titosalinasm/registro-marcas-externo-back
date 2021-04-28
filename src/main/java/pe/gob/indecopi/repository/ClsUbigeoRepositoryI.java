package pe.gob.indecopi.repository;

import pe.gob.indecopi.bean.ClsFiltroUbigeoBean;
import pe.gob.indecopi.util.ClsResultDAO;

public interface ClsUbigeoRepositoryI {
	
	public ClsResultDAO doUbigeo(ClsFiltroUbigeoBean objFiltro);
	public ClsResultDAO doUbigeoMatch(String vcUbigeo);

}
