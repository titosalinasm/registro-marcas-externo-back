package pe.gob.indecopi.service;

import pe.gob.indecopi.bean.ClsFiltroClaseBean;
import pe.gob.indecopi.bean.ClsFiltroValidacionBean;
import pe.gob.indecopi.result.ClsClaseResult;
import pe.gob.indecopi.result.ClsLstGeneralResult;
import pe.gob.indecopi.result.ClsValidaResult;

public interface ClsGeneralService {
	public ClsLstGeneralResult doGeneral();
	public ClsClaseResult dolstClase(ClsFiltroClaseBean objFiltroClase);
	public ClsValidaResult doValidarExpediente(ClsFiltroValidacionBean objFiltro);
	public ClsValidaResult doValidarCertificado(ClsFiltroValidacionBean objFiltro) ;
}
