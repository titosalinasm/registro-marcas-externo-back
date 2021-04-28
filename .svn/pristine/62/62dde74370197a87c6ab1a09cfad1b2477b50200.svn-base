package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsDatosDetTerminosBean;
import pe.gob.indecopi.bean.ClsFiltroTerminosBean;
import pe.gob.indecopi.bean.ClsFiltroVerificarTermsBean;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.util.ClsResultDAO;

@Repository
@Transactional
public class ClsTerminosRepository implements ClsTerminosRepositoryI, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8684022410822082742L;

	private  Logger logger = Logger.getLogger(ClsTerminosRepository.class);

	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	//DataSouser incio multiple
	  @Autowired
	  @Qualifier("dataSourceSEL")
	  private DataSource dataSourceSEL;
	  
	  @PostConstruct
	  private void init()
	  {
	    this.jdbcTemplate = new JdbcTemplate(this.dataSourceSEL);
	  }
	//DataSouser fin multiple
	
	@Autowired
	private ClsResultDAO objResultDAO;
	
	@Override
	public ClsResultDAO doConsultaTerminos(ClsFiltroTerminosBean objFiltroTerminos) {
		logger.info("doConsultaTerminos() - Procedimiento => "+objFiltroTerminos.getNuIdProcedimiento());
		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_SEL)
									.withCatalogName(ClsConstantes.PKG_SEL_TUPA)
									.withProcedureName(ClsConstantes.SP_LST_REQUISITO_VIGENTE)
									.declareParameters(
									new  SqlOutParameter("POUT_CUR_RESULTADO", OracleTypes.CURSOR ,
									new RowMapper<ClsDatosDetTerminosBean>() {

										@Override
										public ClsDatosDetTerminosBean mapRow(ResultSet rs, int rowNum)
												throws SQLException {
											ClsDatosDetTerminosBean resDetTerminos=new ClsDatosDetTerminosBean();
											resDetTerminos.setNuItem(rs.getBigDecimal("VC_NRO_ORDEN"));
											resDetTerminos.setVcDescTermino(rs.getString("VC_DESC_REQUISITO"));
											return resDetTerminos;
										}
									}),
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
			//param
			SqlParameterSource inParamMap = new MapSqlParameterSource()
											    .addValue("PIN_NU_ID_PROCEDIMIENTO", objFiltroTerminos.getNuIdProcedimiento())
											    ;
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
			
			//response
			objResultDAO.put("pOUT_CUR_TERMINOS", out.get("POUT_CUR_RESULTADO"));
			objResultDAO.put("pOUT_NU_CODIGO_ERROR", out.get("POUT_NU_ERROR"));
			objResultDAO.put("pOUT_VC_MENSAJE_ERROR", out.get("POUT_VC_ERROR"));
											    		
			
		}catch(Exception e) {
			logger.info(e);
		}
		
		return objResultDAO;
	}
	
	
    @Override
    public String doVerificarTerminos(ClsFiltroVerificarTermsBean objVerificarTerms) {
        String flagAceptado = "0";
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
							            		.withCatalogName(ClsConstantes.PKG_SEL_TUPA)
							            		.withProcedureName(ClsConstantes.SP_GET_ACEPTA_CONSIDERACIONES);

            Map<String, Object> inParamMap = new HashMap<String, Object>();

            inParamMap.put("PIN_NU_ID_PROCEDIMIENTO", objVerificarTerms.getNuIdProcedimiento());
            inParamMap.put("PIN_NU_ID_USUARIO_SEL", objVerificarTerms.getNuIdUsuarioSel());

            SqlParameterSource in = new MapSqlParameterSource(inParamMap);
            Map<String, Object> out = simpleJdbcCall.execute(in);

            BigDecimal idError = (BigDecimal) out.get("POUT_NU_ERROR");
            flagAceptado = ((String) out.get("POUT_VC_FLAG_ACEPTADO"));

        } catch (Exception e) {
        	logger.info(e);
        }

        return flagAceptado;
    }
	
    
    @Override
    public String saveAceptacionConsideracion(ClsFiltroTerminosBean objFiltroTerminos) {
        String flagAceptado = "0";
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
            		.withCatalogName(ClsConstantes.PKG_SEL_TUPA)
            		.withProcedureName(ClsConstantes.SP_DO_ACEPTA_CONSIDERACIONES);

            Map<String, Object> inParamMap = new HashMap<String, Object>();

            inParamMap.put("PIN_NU_ID_PROCEDIMIENTO", objFiltroTerminos.getNuIdProcedimiento());
            inParamMap.put("PIN_NU_ID_USUARIO_SEL", objFiltroTerminos.getNuIdUsuarioSel());

            SqlParameterSource in = new MapSqlParameterSource(inParamMap);
            Map<String, Object> out = simpleJdbcCall.execute(in);

            BigDecimal idError = (BigDecimal) out.get("POUT_NU_ERROR");

            flagAceptado = ((String) out.get("POUT_VC_FLAG_ACEPTADO"));

        } catch (Exception e) {
        }

        return flagAceptado;
    }
	

}
