package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsClaseBean;
import pe.gob.indecopi.bean.ClsConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroClaseBean;
import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroValidacionBean;
import pe.gob.indecopi.bean.ClsGeneroBean;
import pe.gob.indecopi.bean.ClsNizaBean;
import pe.gob.indecopi.bean.ClsPaisesBean;
import pe.gob.indecopi.bean.ClsPersoneriaBean;
import pe.gob.indecopi.bean.ClsTipoDocumentoBean;
import pe.gob.indecopi.bean.ClsTipoPresentacionBean;
import pe.gob.indecopi.bean.ClsTipoPrioridadBean;
import pe.gob.indecopi.bean.ClsTipoSolicitud;
import pe.gob.indecopi.exception.ClsException;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Repository
@Transactional
public class ClsGeneralRepository implements Serializable, ClsGeneralRepositoryI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789470206482678434L;
	private  Logger logger = Logger.getLogger(ClsGeneralRepository.class);
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//DataSouser incio multiple
	  @Autowired
	  private DataSource dataSource;
	  
	  @PostConstruct
	  private void init()
	  {
	    this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	  }
	//DataSouser fin multiple
	
	@Autowired
	private ClsResultDAO objResultDAO;
	
	@Override
	public ClsResultDAO doLstGeneral() {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_GENERAL)
									.withProcedureName(ClsConstantes.SP_LST_GENERAL)
									.declareParameters(
									new  SqlOutParameter("POUT_CUR_TIPO_SOLIC", OracleTypes.CURSOR ,
									new RowMapper<ClsTipoSolicitud>() {

										@Override
										public ClsTipoSolicitud mapRow(ResultSet rs, int rowNum)
												throws SQLException {
											ClsTipoSolicitud objRespuesta=new ClsTipoSolicitud();
											objRespuesta.setNuIdTipoSolicitud(rs.getInt("NU_ID_TIPO_SOLICITUD"));
											objRespuesta.setVcDesTipoSolicitud(rs.getString("VC_TIPO_SOL_SIS"));
											objRespuesta.setVcUrlIcono(rs.getString("VC_URL_ICONO"));
											objRespuesta.setVcTooltip(rs.getString("VC_TOOLTIP"));
											
											objRespuesta.setBlSeleccionado(rs.getInt("CH_FLAG_SELEC")==1?true:false);
											
											return objRespuesta;
										}
									}),
									new  SqlOutParameter("POUT_CUR_TIPO_SIGNO", OracleTypes.CURSOR ,
											new RowMapper<ClsTipoPresentacionBean>() {

												@Override
												public ClsTipoPresentacionBean mapRow(ResultSet rs, int rowNum)
														throws SQLException {
													ClsTipoPresentacionBean objRespuesta=new ClsTipoPresentacionBean();
													objRespuesta.setNuIdTipoPresentacion(rs.getInt("NU_ID_TIPO_PRESENTACION"));
													objRespuesta.setNuIdTipoSolicitud(rs.getInt("NU_ID_TIPO_SOLICITUD"));
													objRespuesta.setVcDesPresentacion(rs.getString("VC_TIPO_PRESENTACION"));
													objRespuesta.setVcUrlIcono(rs.getString("VC_URL_ICONO"));
													objRespuesta.setVcTooltip(rs.getString("VC_TOOLTIP"));
													
													objRespuesta.setBlSeleccionado(rs.getInt("CH_FLAG_SELEC")==1?true:false);
													objRespuesta.setBlPalFrase(rs.getInt("CH_FLAG_PALFRASE")==1?true:false);
													objRespuesta.setBlDenominacion(rs.getInt("CH_FLAG_DENOMINACI")==1?true:false);
													objRespuesta.setBlAdjuntarFigura(rs.getInt("CH_FLAG_ADJ_FIG")==1?true:false);
													objRespuesta.setBlReinvindicaColores(rs.getInt("CH_FLAG_REI_COLOR")==1?true:false);
													
													return objRespuesta;
												}
											}),
									
									new  SqlOutParameter("POUT_CUR_GENERO", OracleTypes.CURSOR ,
											new RowMapper<ClsGeneroBean>() {

												@Override
												public ClsGeneroBean mapRow(ResultSet rs, int rowNum)
														throws SQLException {
													ClsGeneroBean objRespuesta=new ClsGeneroBean();
													objRespuesta.setNuIdGenero(rs.getInt("NU_ID_GENERO"));
													objRespuesta.setVcDescripcion(rs.getString("VC_DESCRIPCION"));
													
													return objRespuesta;
												}
											}),
									new  SqlOutParameter("POUT_CUR_TIPO_PRIORIDAD", OracleTypes.CURSOR ,
											new RowMapper<ClsTipoPrioridadBean>() {

												@Override
												public ClsTipoPrioridadBean mapRow(ResultSet rs, int rowNum)
														throws SQLException {
													ClsTipoPrioridadBean objRespuesta=new ClsTipoPrioridadBean();
													objRespuesta.setNuIdPrioridad(rs.getInt("NU_ID_TIPO_PRIORIDAD"));
													objRespuesta.setVcDescripcion(rs.getString("VC_DESCRIPCION"));
													
													return objRespuesta;
												}
											}),
									new  SqlOutParameter("POUT_CUR_TIPO_DOCUMENTO", OracleTypes.CURSOR ,
											new RowMapper<ClsTipoDocumentoBean>() {

												@Override
												public ClsTipoDocumentoBean mapRow(ResultSet rs, int rowNum)
														throws SQLException {
													ClsTipoDocumentoBean objRespuesta=new ClsTipoDocumentoBean();
													objRespuesta.setNuIdTipoDocumento(rs.getInt("NU_ID_TIPO_DOCUMENTO"));
													objRespuesta.setVcDescripcion(rs.getString("VC_DESCRIPCION"));
													objRespuesta.setNuIdTipoOrigen(rs.getInt("NU_ID_TIPO_ORIGEN"));
													
													return objRespuesta;
												}
											}),
									new  SqlOutParameter("POUT_CUR_PAISES", OracleTypes.CURSOR ,
											new RowMapper<ClsPaisesBean>() {

												@Override
												public ClsPaisesBean mapRow(ResultSet rs, int rowNum)
														throws SQLException {
													ClsPaisesBean objRespuesta=new ClsPaisesBean();
													objRespuesta.setNuIdUbigeoPais(rs.getInt("nu_ubigeo"));
													objRespuesta.setVcPais(rs.getString("vc_denominacion"));
													
													return objRespuesta;
												}
											}),
									new  SqlOutParameter("POUT_CUR_PERSONERIA", OracleTypes.CURSOR ,
											new RowMapper<ClsPersoneriaBean>() {

												@Override
												public ClsPersoneriaBean mapRow(ResultSet rs, int rowNum)
														throws SQLException {
													ClsPersoneriaBean objRespuesta=new ClsPersoneriaBean();
													objRespuesta.setNuIdPersoneria(rs.getInt("NU_CODIGO_INTERNO"));;
													objRespuesta.setVcDescripcion(rs.getString("VC_NOMBRE_CAMPO"));;
													
													return objRespuesta;
												}
											}),
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
	
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute();
			
			//response
			objResultDAO.put("POUT_CUR_TIPO_SOLIC", 	out.get("POUT_CUR_TIPO_SOLIC"));
			objResultDAO.put("POUT_CUR_TIPO_SIGNO", 	out.get("POUT_CUR_TIPO_SIGNO"));
			objResultDAO.put("POUT_CUR_GENERO", 		out.get("POUT_CUR_GENERO"));
			objResultDAO.put("POUT_CUR_TIPO_PRIORIDAD", out.get("POUT_CUR_TIPO_PRIORIDAD"));
			objResultDAO.put("POUT_CUR_TIPO_DOCUMENTO", out.get("POUT_CUR_TIPO_DOCUMENTO"));
			objResultDAO.put("POUT_CUR_PAISES", 		out.get("POUT_CUR_PAISES"));
			objResultDAO.put("POUT_CUR_PERSONERIA", 		out.get("POUT_CUR_PERSONERIA"));
			
			
			
			objResultDAO.put("POUT_NU_ERROR", 			out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", 			out.get("POUT_VC_ERROR"));
								    		
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ClsException(e+"", new Throwable("ERROR1"));
		}
		
		return objResultDAO;
	}
	
	@Override
	public ClsResultDAO doLstClase(ClsFiltroClaseBean objFiltroClase) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_GENERAL)
									.withProcedureName(ClsConstantes.SP_LST_CLASE)
									.declareParameters(
									new  SqlOutParameter("POUT_CUR_CLASE", OracleTypes.CURSOR ,
									new RowMapper<ClsNizaBean>() {

										@Override
										public ClsNizaBean mapRow(ResultSet rs, int rowNum)
												throws SQLException {
											ClsNizaBean objRespuesta=new ClsNizaBean();
											objRespuesta.setNuIdClase(rs.getInt("NU_ID_CLASE"));
											objRespuesta.setVcProductosServicios(rs.getString("VC_CLASE"));
											objRespuesta.setVcSugerencia(rs.getString("VC_SUGENERENCIA"));
											
											return objRespuesta;
										}
									}),
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();

	        inParamMap.put("PIN_VC_PARA_BUSQ", objFiltroClase.getVcParaBusq());

	        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(in);
			
			//response
			objResultDAO.put("POUT_CUR_CLASE", out.get("POUT_CUR_CLASE"));
			objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));
								    		
			
		}catch(Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		
		return objResultDAO;
	}
	
	@Override
	public ClsResultDAO doValidaExpediente(ClsFiltroValidacionBean objFiltro) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_REG_MARCA)
									.withProcedureName(ClsConstantes.SP_GET_VALIDA_EXPEDIENTE)
									.declareParameters(
									new  SqlOutParameter("POUT_NU_VALOR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();

	        inParamMap.put("PIN_VC_NRO_EXPEDIENTE", objFiltro.getVcParametro());

	        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(in);
			
			//response
			objResultDAO.put("POUT_NU_VALOR", out.get("POUT_NU_VALOR"));
			objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));
								    		
			
		}catch(Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		
		return objResultDAO;
	}
	
	@Override
	public ClsResultDAO doValidaCertificado(ClsFiltroValidacionBean objFiltro) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_REG_MARCA)
									.withProcedureName(ClsConstantes.SP_GET_VALIDA_CERTIFICADO)
									.declareParameters(
									new  SqlOutParameter("POUT_NU_VALOR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();

	        inParamMap.put("PIN_VC_NRO_CERTIFICADO", objFiltro.getVcParametro());

	        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(in);
			
			//response
			objResultDAO.put("POUT_NU_VALOR", out.get("POUT_NU_VALOR"));
			objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));
								    		
			
		}catch(Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		
		return objResultDAO;
	}
	
}
