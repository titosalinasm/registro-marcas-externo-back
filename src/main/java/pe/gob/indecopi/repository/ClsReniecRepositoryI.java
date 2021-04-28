package pe.gob.indecopi.repository;

import pe.gob.indecopi.bean.ClsFilterReniecBean;
import pe.gob.indecopi.util.ClsResultDAO;

public interface ClsReniecRepositoryI {

	public ClsResultDAO doConsultaReniec(ClsFilterReniecBean objReniec);

}
