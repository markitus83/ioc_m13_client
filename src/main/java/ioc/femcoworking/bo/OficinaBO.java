
package ioc.femcoworking.bo;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.femcoworking.vo.CodiAccesVO;
import ioc.femcoworking.vo.OficinaVO;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/*
ACCIÓ                           TIPUS       DADES ENTRADA               DADES SORTIDA   
alta oficina                    POST        json info oficina           string
llistat oficines                GET         -                           list<oficines>
editar oficina                  PUT         json info oficina           string
deshabilitar oficina            PUT         json info Oficina           string
 */

/**
 * Classe que representa el BusinessObject d'Oficina
 * 
 * @author Marc Ginovart Vega
 */
public class OficinaBO {
    private static final String URL_SERVIDOR = "http://localhost:8080";
    private static final OkHttpClient httpClient = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    /**
     * Registrar nova oficina en el sistema
     * 
     * Petició POST al servidor
     * 
     * @param 
     * @return Response
     * @throws IOException 
     */
    public Response altaNovaOficina(OficinaVO novaOficina) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(novaOficina);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/altaoficina")
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Obtenir un llistat de les oficines registrats en el sistema
     * 
     * Petició GET al servidor
     * 
     * @param codiAcces
     * @return Response
     * @throws IOException 
     */
    public Response llistatOficines(CodiAccesVO codiAcces) throws IOException {
        /*
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/oficines/" + codiAcces.getCodiAcces())
            .header("Content-Type","application/json; charset=utf-8")
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
        */
        Response response = null;
        return response;
    }
    
    
    
    /**
     * Editar informació d'oficina
     * 
     * Petició PUT al servidor
     * 
     * @param codiAcces
     * @param novaInfoOficina
     * @return Response
     * @throws IOException 
     */
    public Response editarOficina(CodiAccesVO codiAcces, String novaInfoOficina) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(novaInfoOficina);
        
        /*
        JSONObject jsonBody = new JSONObject().put("contrasenya", novaContrasenya);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/editarOficina/" + codiAcces.getCodiAcces())
            .header("Content-Type","application/json; charset=utf-8")
            .put(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
        */
        Response response = null;
        return response;
    }
    
    /**
     * Deshabilitar oficina
     * 
     * Petició PUT al servidor
     * 
     * @param codiAcces
     * @param idOficina
     * @return Response
     * @throws IOException 
     */
    public Response deshabilitarOficina(CodiAccesVO codiAcces, String idOficina) throws IOException {
        JSONObject jsonBody = new JSONObject()
            .put("idOficina", idOficina);
        
        /*
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/deshabilitarOficina/" + codiAcces.getCodiAcces())
            .header("Content-Type","application/json; charset=utf-8")
            .put(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
        */
        Response response = null;
        return response;
    }
}
