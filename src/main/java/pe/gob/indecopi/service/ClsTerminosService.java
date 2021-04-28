package pe.gob.indecopi.service;

import pe.gob.indecopi.bean.ClsFiltroTerminosBean;
import pe.gob.indecopi.bean.ClsFiltroVerificarTermsBean;
import pe.gob.indecopi.result.ClsTerminosResult;
import pe.gob.indecopi.result.ClsVerifTermsResult;

public interface ClsTerminosService {

	public ClsTerminosResult doConsultaTerminos(ClsFiltroTerminosBean objFiltroTerminos);
	public ClsVerifTermsResult doVerificarTerminos(ClsFiltroVerificarTermsBean objVerificarTerms);
	public ClsVerifTermsResult doAceptaTerminos(ClsFiltroTerminosBean objFiltroTerminos);

}
