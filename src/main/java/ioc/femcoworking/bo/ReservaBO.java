package ioc.femcoworking.bo;

import ioc.femcoworking.vo.CodiAccesVO;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/**
 * Classe que representa el BusinessObject de Reserva
 * 
 * @author Marc Ginovart Vega
 */
public class ReservaBO {
    private static final String URL_SERVIDOR = "http://localhost:8080";
    private static final OkHttpClient httpClient = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    private CodiAccesVO codiAcces;
    
    /**
     * Obtenir un llistat de reserves
     * 
     * Petició GET al servidor
     * 
     * @param codiAcces
     * @return Response
     * @throws IOException 
     */
    public Response llistatReserves(String codiAcces) throws IOException {
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/reserves/" + codiAcces)
            .header("Content-Type","application/json; charset=utf-8")
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Crear la reserva d'una oficina per part d'un usuari
     * 
     * @param codiAcces
     * @param idOficina
     * @param dataInici
     * @param dataFi
     * @return
     * @throws IOException 
     */
    public Response reservaOficina(
        String codiAcces,
        String idOficina,
        String dataInici,
        String dataFi
    ) throws IOException {
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", codiAcces)
            .put("idOficina", idOficina)
            .put("dataIniciReserva", dataInici)
            .put("dataFiReserva", dataFi);
        
        System.out.println(jsonBody.toString());
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/reservaoficina")
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Eliminar reserva
     * 
     * Petició DELETE al servidor
     * 
     * @param codiAcces
     * @param idReserva
     * @return Response
     * @throws IOException 
     */
    public Response eliminarReserva(String codiAcces, String idReserva) throws IOException {
        JSONObject jsonBody = new JSONObject().put("idReserva", idReserva);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/esborrarreserva/" + codiAcces)
            .header("Content-Type","application/json; charset=utf-8")
            .delete(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response; 
    }
}
