package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import pe.gob.indecopi.bean.ClsArancelesBean;
import pe.gob.indecopi.bean.ClsClaseBean;
import pe.gob.indecopi.bean.ClsConfiguracionBean;
import pe.gob.indecopi.bean.ClsCostoClaseBean;
import pe.gob.indecopi.bean.ClsFiltroClaseBean;
import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.bean.ClsGeneroBean;
import pe.gob.indecopi.bean.ClsNizaBean;
import pe.gob.indecopi.bean.ClsPaisesBean;
import pe.gob.indecopi.bean.ClsParametrosBean;
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
public class ClsParametroRepository implements Serializable, ClsParametroRepositoryI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789470206482678434L;
	private  Logger logger = Logger.getLogger(ClsParametroRepository.class);
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
	public ClsResultDAO doParametro() {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_GENERAL)
									.withProcedureName(ClsConstantes.SP_GET_PARAMETROS)
									.declareParameters(
									new  SqlOutParameter("POUT_CUR_ARANCELES", OracleTypes.CURSOR ,
									new RowMapper<ClsArancelesBean>() {

										@Override
										public ClsArancelesBean mapRow(ResultSet rs, int rowNum)
												throws SQLException {
											ClsArancelesBean objRespuesta=new ClsArancelesBean();

											objRespuesta.setVcNroArancel(rs.getString("VC_NOMBRE_CAMPO"));

											return objRespuesta;
										}
									}),
									
									new  SqlOutParameter("POUT_VC__RUTA_FILE", OracleTypes.VARCHAR ,"VARCHAR2"),
									
									new  SqlOutParameter("POUT_CUR_COSTO_P_S", OracleTypes.CURSOR,
											new RowMapper<ClsCostoClaseBean>() {

												@Override
												public ClsCostoClaseBean mapRow(ResultSet rs, int rowNum)
														throws SQLException {
													ClsCostoClaseBean objRespuesta=new ClsCostoClaseBean();
													objRespuesta.setNuCodInterno(rs.getInt("NU_CODIGO_INTERNO"));
													objRespuesta.setNuCostoArancel(rs.getBigDecimal("VC_DESCRIPCION"));
													
													return objRespuesta;
												}
											}),
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
	
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute();
			
			
			//response
			List<ClsCostoClaseBean> lstCosto=new ArrayList<ClsCostoClaseBean>();
			lstCosto=(List<ClsCostoClaseBean>)out.get("POUT_CUR_COSTO_P_S");
			
			ClsParametrosBean objParametro=new ClsParametrosBean();
			objParametro.setLstAranceles((List<ClsArancelesBean>)out.get("POUT_CUR_ARANCELES"));
			objParametro.setVcRutaTemporal((""+out.get("POUT_VC_RUTA_FILE")).split(",")[0]);
			objParametro.setVcRutaFinal((""+out.get("POUT_VC_RUTA_FILE")).split(",")[1]);
			objParametro.setNuCostoPClase(lstCosto.get(0).getNuCostoArancel());
			objParametro.setNuCostoSClase(lstCosto.get(1).getNuCostoArancel());	
			objParametro.setNuCantClases(0);

			objResultDAO.put("POUT_PARAMETRO", 		objParametro);
			
			
			objResultDAO.put("POUT_NU_ERROR", 			out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", 			out.get("POUT_VC_ERROR"));
								    		
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ClsException(e+"", new Throwable("ERROR"));
		}
		
		return objResultDAO;
	}
	
	
	
}
