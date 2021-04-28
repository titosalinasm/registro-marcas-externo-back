package pe.gob.indecopi.repository;

import pe.gob.indecopi.bean.ClsFiltroTerminosBean;
import pe.gob.indecopi.bean.ClsFiltroVerificarTermsBean;
import pe.gob.indecopi.util.ClsResultDAO;

public interface ClsTerminosRepositoryI {
	public ClsResultDAO doConsultaTerminos(ClsFiltroTerminosBean objFiltroTerminos);
    public String doVerificarTerminos(ClsFiltroVerificarTermsBean objVerificarTerms);
    public String saveAceptacionConsideracion(ClsFiltroTerminosBean objFiltroTerminos);
}
