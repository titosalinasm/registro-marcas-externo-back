package pe.gob.indecopi.repository;

import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.util.ClsResultDAO;

public interface ClsConfiguracionRepositoryI {
	public ClsResultDAO doConfiguracion(ClsFiltroConfiguracionBean objFiltro);
}
