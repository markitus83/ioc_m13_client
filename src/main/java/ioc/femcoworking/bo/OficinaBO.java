
package ioc.femcoworking.bo;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.femcoworking.vo.OficinaVisualitzacioVO;
import ioc.femcoworking.vo.PeticioAltaOficinaVO;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import resources.TrustSelfSignedCertificatesOkHttp;

/**
 * Classe que representa el BusinessObject d'Oficina
 * 
 * @author Marc Ginovart Vega
 */
public class OficinaBO {
    private static final String URL_SERVIDOR = "https://localhost:8443";
    private static final OkHttpClient httpClient = new TrustSelfSignedCertificatesOkHttp().getOkHttpClient();
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
    public Response altaNovaOficina(PeticioAltaOficinaVO peticioAltaOficina) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(peticioAltaOficina);
        
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
    public Response llistatOficines(String codiAcces) throws IOException {
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/oficines/" + codiAcces)
            .header("Content-Type","application/json; charset=utf-8")
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Obtenir un llistat de les oficines disponibles per a reserva
     * 
     * Petició POST al servidor
     * 
     * @param codiAcces
     * @param dataInici
     * @param dataFi
     * @return Response
     * @throws IOException 
     */
    public Response llistatOficinesDisponibles(
        String codiAcces, 
        String dataInici, 
        String dataFi
    ) throws IOException {
        JSONObject jsonBody = new JSONObject()
            .put("dataInici", dataInici)
            .put("dataFi", dataFi);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/oficinesdisponibles/" + codiAcces)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Editar informació d'oficina
     * 
     * Petició PUT al servidor
     * 
     * @param codiAcces
     * @param edicioOficina
     * @return Response
     * @throws IOException 
     */
    public Response editarOficina(String codiAcces, OficinaVisualitzacioVO edicioOficina) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(edicioOficina);
                
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/editaroficina/" + codiAcces)
            .header("Content-Type","application/json; charset=utf-8")
            .put(RequestBody.create(requestBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Eliminar oficina (sense esborrar registre, softdelete)
     * 
     * Petició DELETE al servidor
     * 
     * @param codiAcces
     * @param idOficina
     * @return Response
     * @throws IOException 
     */
    public Response baixaOficina(String codiAcces, String idOficina) throws IOException {
                
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/baixaoficina/" + codiAcces + "/" + idOficina)
            .header("Content-Type","application/json; charset=utf-8")
            .delete()
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
}
