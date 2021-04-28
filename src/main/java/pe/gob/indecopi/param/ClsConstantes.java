package pe.gob.indecopi.param;

import java.io.Serializable;

public class ClsConstantes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//SCHEMAS
	public static final String SCHEMA_USR_MARCAS="USR_MARCAS";
	public static final String SCHEMA_USR_SEL="USR_SEL";
	public static final String SCHEMA_USR_NIZA="USR_NIZA";
	
	//USR_NIZA
	public static final String PKG_BUSQUEDAS_NIZA = "PKG_BUSQUEDAS_NIZA";
	public static final String SP_RELACION_TITULOS_NIZA_NAME="SP_RELACION_TITULOS_NIZA_NAME";
	
	//USR_MARCAS
    public static final String PKG_GENERAL = "PKG_GENERAL";
    public static final String SP_GET_CONFIG_FORM = "SP_GET_CONFIG_FORM";
    public static final String SP_LST_GENERAL="SP_LST_GENERAL";
    public static final String SP_LST_CLASE="SP_LST_CLASE";
    public static final String SP_GET_PARAMETROS="SP_GET_PARAMETROS";
    public static final String SP_GET_VALIDA_EXPEDIENTE="SP_GET_VALIDA_EXPEDIENTE";
    public static final String SP_GET_VALIDA_CERTIFICADO="SP_GET_VALIDA_CERTIFICADO";
    
    
    public static final String PKG_REG_MARCA="PKG_REG_MARCA";
    public static final String SP_DO_REG_MARCAS="SP_DO_REG_MARCAS";
    public static final String SP_I_EXPEDIENTE="SP_I_EXPEDIENTE";
    public static final String SP_DO_MIGRA_MAR_SSE="SP_DO_MIGRA_MAR_SSE";
    
    public static final String PKG_UBIGEO = "PKG_UBIGEO";
    public static final String SP_LST_UBIGEO = "SP_LST_UBIGEO";
    public static final String SP_GET_DATOS_UBIGEO = "SP_GET_DATOS_UBIGEO";
    
    
	
	//USR_SEL
	//PKG_SEL_TUPA
    public static final String PKG_SEL_TUPA = "PKG_SEL_TUPA";
    public static final String SP_LST_PROCEDIMIENTO_VIGENTE = "SP_LST_PROCEDIMIENTO_VIGENTE";
    public static final String SP_LST_REQUISITO_VIGENTE = "SP_LST_REQUISITO_VIGENTE";
    public static final String SP_LST_ARANCEL_PROCEDIMIENTO = "SP_LST_ARANCEL_PROCEDIMIENTO";
    public static final String SP_LST_NOTA_PROCEDIMIENTO = "SP_LST_NOTA_PROCEDIMIENTO";
    public static final String SP_DO_ACEPTA_CONSIDERACIONES = "SP_DO_ACEPTA_CONSIDERACIONES";
    public static final String SP_GET_ACEPTA_CONSIDERACIONES = "SP_GET_ACEPTA_CONSIDERACIONES";
    public static final String PKG_SEL_AVISO = "PKG_SEL_AVISO";
    public static final String SP_LST_AVISO_VIGENTE = "SP_LST_AVISO_VIGENTE";
	
	//PKG_SEL_WS_PAGOS
	public static final String PKG_SEL_MARCAS="PKG_SEL_MARCAS";
	public static final String SP_DO_COMPRUEBA_PAGO="SP_DO_COMPRUEBA_PAGO";
	public static final String SP_GET_VALIDA_PAGO_MAR="SP_GET_VALIDA_PAGO_MAR";
	public static final String SP_GET_USUARIO="SP_GET_USUARIO";
	public static final String SP_DO_EXPEDIENTE="SP_DO_EXPEDIENTE";
	public static final String SP_I_TRAMITE_MARCAS="SP_I_TRAMITE_MARCAS";
	
	
	
	//constantes DSD
	private static final String vcUsuarioEsquema="USR_MARCAS";
	private static final Integer nuTipoConsulta=2;
	public  static final String vcArea="DSD";
	
	
	
	
	
	

}
