package ioc.femcoworking.bo;

import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.femcoworking.vo.CodiAccesVO;
import ioc.femcoworking.vo.DadesAccesVO;
import ioc.femcoworking.vo.PeticioEdicioUsuariVO;
import ioc.femcoworking.vo.UsuariVO;
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
     * Eliminar reserva
     * 
     * Petició DELETE al servidor
     * 
     * @param codiAcces
     * @param idUsuari
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
