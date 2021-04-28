package pe.gob.indecopi.repository;

import pe.gob.indecopi.bean.ClsFilterRucBean;
import pe.gob.indecopi.util.ClsResultDAO;

public interface ClsSunatRepositoryI {
	public ClsResultDAO doConsultaRuc(ClsFilterRucBean objRuc);
}
