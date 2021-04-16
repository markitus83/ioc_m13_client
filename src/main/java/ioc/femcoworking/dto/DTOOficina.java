
package ioc.femcoworking.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.femcoworking.bo.OficinaBO;
import ioc.femcoworking.vo.CodiAccesVO;
import ioc.femcoworking.vo.OficinaVO;
import ioc.femcoworking.vo.PeticioAltaOficinaVO;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.Response;
import org.json.JSONObject;
import resources.Categoria;

/**
 * Classe per gesitonar totes les funcionalitat BusinessObject d'Oficines
 * 
 * @author Marc Ginovart Vega
 */
public class DTOOficina {
    private CodiAccesVO codiAcces;

    public DTOOficina() {
    }
    
    /**
     * Registrar nova oficina
     * 
     * @param codiAcces
     * @param nom
     * @param categoria
     * @param capacitat
     * @param preu
     * @param serveis
     * @param provincia
     * @param poblacio
     * @param direccio
     * @param deshabilitat
     * @param eliminat
     * @return
     * @throws JsonProcessingException 
     */
    public JSONObject altaOficina(
        String codiAcces,
        String nom,
        Categoria categoria,
        Integer capacitat,
        Double preu,
        String serveis,
        String provincia,
        String poblacio,
        String direccio,
        Boolean deshabilitat,
        Boolean eliminat
    ) throws JsonProcessingException, IOException {
        OficinaBO oficina = new OficinaBO();
        OficinaVO novaOficina = new OficinaVO(
            nom, 
            categoria, 
            capacitat, 
            preu, 
            serveis, 
            provincia, 
            poblacio, 
            direccio, 
            deshabilitat, 
            eliminat
        );
        
        PeticioAltaOficinaVO peticioAltaOficina = new PeticioAltaOficinaVO();
        peticioAltaOficina.setCodiAcces(codiAcces);
        peticioAltaOficina.setOficina(novaOficina);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(peticioAltaOficina);
        
        System.out.println("requestbody >> " + requestBody);
        
        Response response = oficina.altaNovaOficina(peticioAltaOficina);
        
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
    
    public JSONObject llistatOficines(String codiAcces) throws IOException {
        OficinaBO oficina = new OficinaBO();
        
        Response response = oficina.llistatOficines(codiAcces);
        
        String responseBody = response.body().string();
                
        HashMap<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("code", response.code());
        
        if (405 == response.code()) {
            JSONObject errorMessage = new JSONObject(responseBody);
            jsonResponse.put("message", errorMessage.get("message"));
        } else {            
            jsonResponse.put("message", responseBody);
        }
        
        return new JSONObject(jsonResponse);
    }
    
    public JSONObject baixaOficina(String codiAcces, String idOficina) throws IOException {
        OficinaBO oficina = new OficinaBO();
        
        Response response = oficina.baixaOficina(codiAcces, idOficina);
        
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
