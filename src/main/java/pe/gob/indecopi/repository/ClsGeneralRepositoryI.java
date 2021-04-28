package pe.gob.indecopi.repository;

import pe.gob.indecopi.bean.ClsFiltroClaseBean;
import pe.gob.indecopi.bean.ClsFiltroValidacionBean;
import pe.gob.indecopi.util.ClsResultDAO;

public interface ClsGeneralRepositoryI {
	public ClsResultDAO doLstGeneral();
	public ClsResultDAO doLstClase(ClsFiltroClaseBean objFiltroClase);
	public ClsResultDAO doValidaExpediente(ClsFiltroValidacionBean objFiltro);
	public ClsResultDAO doValidaCertificado(ClsFiltroValidacionBean objFiltro);

}
