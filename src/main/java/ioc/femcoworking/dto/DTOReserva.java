package ioc.femcoworking.dto;

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
 * Classe per gestionar totes les funcionalitat BusinessObject de reserves
 * 
 * @author Marc Ginovart Vega
 */
public class DTOReserva {
    private CodiAccesVO codiAcces;  

    public DTOReserva() {
    }

    /**
     * Obtenir llistat d'usuaris
     * 
     * @param codi
     * @return JSONObject
     * @throws IOException 
     */
    public JSONObject llistatReserves(String codi) throws IOException {
        ReservaBO reserva = new ReservaBO();
        
        Response response = reserva.llistatReserves(codi);
        
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
     * Eliminar reserva
     * 
     * @param codiAcces
     * @param idUsuari
     * @return JSONObject
     * @throws IOException 
     */
    public JSONObject eliminarReserva(String codiAcces, String idUsuari) throws IOException {
        ReservaBO reserva = new ReservaBO();
        
        Response response = reserva.eliminarReserva(codiAcces, idUsuari);
        
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
