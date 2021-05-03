package ioc.femcoworking.dto;

import ioc.femcoworking.bo.FacturaBO;
import ioc.femcoworking.bo.ReservaBO;
import ioc.femcoworking.bo.UsuariBO;
import ioc.femcoworking.vo.CodiAccesVO;
import ioc.femcoworking.vo.DadesAccesVO;
import ioc.femcoworking.vo.PeticioEdicioUsuariVO;
import ioc.femcoworking.vo.UsuariVO;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.Response;
import org.json.JSONObject;
import resources.Rol;


/**
 * Classe per gestionar totes les funcionalitat BusinessObject de factures
 * 
 * @author Marc Ginovart Vega
 */
public class DTOFactura {
    private CodiAccesVO codiAcces;  

    public DTOFactura() {
    }

    /**
     * Obtenir llistat de factures
     * 
     * @param codi
     * @return JSONObject
     * @throws IOException 
     */
    public JSONObject llistatFactures(String codi) throws IOException {
        FacturaBO factura = new FacturaBO();
        
        Response response = factura.llistatFactures(codi);
        
        String responseBody = response.body().string();
                
        HashMap<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("code", response.code());
        
        if (200 != response.code()) {
            JSONObject errorMessage = new JSONObject(responseBody);
            jsonResponse.put("message", errorMessage.get("message"));
        } else {            
            jsonResponse.put("message", responseBody);
        }
        
        return new JSONObject(jsonResponse);
    }
    
    /**
     * Crear la factura d'una reserva
     * 
     * @param codiAcces
     * @param idReserva
     * @return
     * @throws IOException 
     */
    public JSONObject crearFactura(
        String codiAcces,
        String idReserva
    ) throws IOException {
        FacturaBO factura = new FacturaBO();
        
        Response response = factura.crearFactura(codiAcces, idReserva);
        
        String responseBody = response.body().string();
                
        HashMap<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("code", response.code());
        
        if (200 != response.code()) {
            JSONObject errorMessage = new JSONObject(responseBody);
            jsonResponse.put("message", errorMessage.get("message"));
        } else {            
            jsonResponse.put("message", responseBody);
        }
        
        return new JSONObject(jsonResponse);
    }
    
    /**
     * Eliminar factura
     * 
     * @param codiAcces
     * @param idFactura
     * @return JSONObject
     * @throws IOException 
     */
    public JSONObject eliminarFactura(String codiAcces, String idFactura) throws IOException {
        FacturaBO factura = new FacturaBO();
        
        Response response = factura.eliminarFactura(codiAcces, idFactura);
        
        String responseBody = response.body().string();
        
        HashMap<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("code", response.code());
        
        if (200 != response.code()) {
            JSONObject errorMessage = new JSONObject(responseBody);
            jsonResponse.put("message", errorMessage.get("message"));
        } else {            
            jsonResponse.put("message", responseBody);
        }
        
        return new JSONObject(jsonResponse);
    }
}
