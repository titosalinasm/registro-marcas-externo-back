package pe.gob.indecopi.repository;

import pe.gob.indecopi.bean.ClsExpedienteBean;
import pe.gob.indecopi.bean.ClsSolicitudBean;
import pe.gob.indecopi.util.ClsResultDAO;

public interface ClsRegistroRepositoryI {
	
	public ClsResultDAO doRegistro(ClsSolicitudBean objSolicitud);
	public ClsResultDAO inExpediente(ClsExpedienteBean objExpediente, ClsSolicitudBean objSolicitud);
	public ClsResultDAO doMigraSSE(ClsExpedienteBean objExpediente, ClsSolicitudBean objSolicitud);

}
