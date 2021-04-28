package pe.gob.indecopi.service;

import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.result.ClsConfiguracionResult;

public interface ClsConfiguracionService {
	public ClsConfiguracionResult doConfiguracion(ClsFiltroConfiguracionBean objConfiguracion);
}
