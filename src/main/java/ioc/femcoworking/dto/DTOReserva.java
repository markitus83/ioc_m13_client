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
     * Canviar estat d'usuari
     * 
     * @param codi
     * @param idUsuari
     * @return JSONObject
     * @throws IOException 
     */
    public JSONObject canviarEstatUsuari(String codi, String idUsuari) throws IOException {
        UsuariBO usuari = new UsuariBO();
        codiAcces = new CodiAccesVO(codi);
        
        Response response = usuari.canviarEstatUsuari(codiAcces, idUsuari);
        
        String responseBody = response.body().string();
        
        HashMap<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("code", response.code());
        
        if (400 == response.code()) {
            JSONObject errorMessage = new JSONObject(responseBody);
            jsonResponse.put("message", errorMessage.get("message"));
        } else {            
            jsonResponse.put("message", responseBody);
        }
        
        return new JSONObject(jsonResponse);
    }
    
    /**
     * Registrar nou usuari
     * 
     * @param nom
     * @param contrasenya
     * @param email
     * @param rol
     * @param cif
     * @param direccio
     * @param poblacio
     * @param provincia
     * @param deshabilitat
     * @return
     * @throws IOException 
     */
    public JSONObject registrarNouUsuari(
        String nom,
        String contrasenya,
        String email,
        Rol rol,
        String cif,
        String direccio,
        String poblacio,
        String provincia,
        Boolean deshabilitat
    ) throws IOException {
        UsuariBO usuari = new UsuariBO();        
        UsuariVO nouUsuari = new UsuariVO(
            email, 
            contrasenya, 
            rol, 
            nom, 
            cif, 
            direccio, 
            poblacio, 
            provincia, 
            deshabilitat
        );
    
        Response response = usuari.registrarNouUsuari(nouUsuari);
        
        String responseBody = response.body().string();
        
        HashMap<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("code", response.code());
        
        if (400 == response.code()) {
            JSONObject errorMessage = new JSONObject(responseBody);
            jsonResponse.put("message", errorMessage.get("message"));
        } else {            
            jsonResponse.put("message", responseBody);
        }
        
        return new JSONObject(jsonResponse);
    }
    
    public JSONObject editarUsuari(
        String codi, 
        String nom,
        String cif,
        String direccio,
        String poblacio,
        String provincia
    ) throws IOException {
        UsuariBO usuari = new UsuariBO();
        codiAcces = new CodiAccesVO(codi);
        
        PeticioEdicioUsuariVO editarUsuari = new PeticioEdicioUsuariVO(
            nom, 
    cif, 
            direccio, 
            poblacio, 
            provincia
        );
        
        Response response = usuari.editarUsuari(codiAcces, editarUsuari);
        
        String responseBody = response.body().string();
        
        HashMap<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("code", response.code());
        
        if (400 == response.code()) {
            JSONObject errorMessage = new JSONObject(responseBody);
            jsonResponse.put("message", errorMessage.get("message"));
        } else {            
            jsonResponse.put("message", responseBody);
        }
        
        return new JSONObject(jsonResponse);
    }
}
