package pe.gob.indecopi.repository;

import java.sql.Clob;
import java.util.List;

import pe.gob.indecopi.bean.ClsFilterPagoBean;
import pe.gob.indecopi.bean.ClsFiltroProcedimientoBean;
import pe.gob.indecopi.bean.ClsFiltroTramitePagoBean;
import pe.gob.indecopi.bean.ClsParametrosBean;
import pe.gob.indecopi.bean.ClsSolicitudBean;
import pe.gob.indecopi.bean.ClsTramiteSELBean;
import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.util.ClsResultDAO;

public interface ClsTramiteSELRepositoryI {
	public ClsResultDAO doCompruebaPagos(ClsParametrosBean objParametros,ClsSolicitudBean objSolicitud);
	public ClsResultDAO doValidarPago(ClsFilterPagoBean objTramitePago, ClsParametrosBean objParametro);
	public ClsResultDAO doUsuario(ClsUsuarioBean objUsuario);
	public ClsResultDAO doAviso( ClsFiltroProcedimientoBean objProcedimiento);
	public ClsResultDAO doTramite( ClsTramiteSELBean objTramite, 
				ClsSolicitudBean objSolicitud,
				ClsParametrosBean objParametros,
				 String vcUserName
				  );
	
}
