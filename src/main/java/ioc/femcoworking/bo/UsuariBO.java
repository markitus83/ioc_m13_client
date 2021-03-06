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
import resources.TrustSelfSignedCertificatesOkHttp;

/**
 * Classe que representa el BusinessObject d'Usuari
 * 
 * @author Marc Ginovart Vega
 */
public class UsuariBO {
    // url del servidor en local sense xifrat de dades
    //private static final String URL_SERVIDOR = "http://localhost:8080";
    // url del servidor en local amb xifrat de dades
    //private static final String URL_SERVIDOR = "https://localhost:8443";
    // url del servidor instal·lat en AWS
    private static final String URL_SERVIDOR = "https://52.72.245.187:8443";
    private static final OkHttpClient httpClient = new TrustSelfSignedCertificatesOkHttp().getOkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private CodiAccesVO codiAcces;
        
    /**
     * Login d'un usuari
     * 
     * Petició POST al servidor
     * 
     * @param dadesAcces
     * @return Response
     * @throws IOException 
     */
    public Response login(DadesAccesVO dadesAcces) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/login")
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        //Response response = httpClient.newCall(request).execute();
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Logout d'un usuari
     * 
     * Petició GET al servidor
     * 
     * @param codiAcces
     * @return Response
     * @throws IOException 
     */
    public Response logout(CodiAccesVO codiAcces) throws IOException {
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/logout/" + codiAcces.getCodiAcces())
            .header("Content-Type","application/json; charset=utf-8")
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Canviar contrasenya d'un usuari
     * 
     * Petició PUT al servidor
     * 
     * @param novaContrasenya
     * @param codiAcces
     * @return Response
     * @throws IOException 
     */
    public Response canviarContrasenya(String novaContrasenya, CodiAccesVO codiAcces) throws IOException {
        JSONObject jsonBody = new JSONObject().put("contrasenya", novaContrasenya);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/canviarContrasenya/" + codiAcces.getCodiAcces())
            .header("Content-Type","application/json; charset=utf-8")
            .put(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Obtenir un llistat dels usuaris registrats en el sistema
     * 
     * Petició GET al servidor
     * 
     * @param codiAcces
     * @return Response
     * @throws IOException 
     */
    public Response llistatUsuaris(CodiAccesVO codiAcces) throws IOException {
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/usuaris/" + codiAcces.getCodiAcces())
            .header("Content-Type","application/json; charset=utf-8")
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Habilitar/deshabilitar un usuari segons el seu ID
     * 
     * Petició DELETE al servidor
     * 
     * @param codiAcces
     * @param idUsuari
     * @return Response
     * @throws IOException 
     */
    public Response canviarEstatUsuari(CodiAccesVO codiAcces, String idUsuari) throws IOException {
        JSONObject jsonBody = new JSONObject().put("idUsuari", idUsuari);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/baixa/" + codiAcces.getCodiAcces())
            .header("Content-Type","application/json; charset=utf-8")
            .delete(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response; 
    }
    
    /**
     * Registrar nou usuari en el sistema
     * 
     * @param nouUsuari
     * @return Response
     * @throws IOException 
     */
    public Response registrarNouUsuari(UsuariVO nouUsuari) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(nouUsuari);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/registre")
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response;
    }
    
    /**
     * Editar informació d'un usuari
     * 
     * Petició PUT al servidor
     * 
     * @param codiAcces
     * @param idUsuari
     * @return Response
     * @throws IOException 
     */
    public Response editarUsuari(
        CodiAccesVO codiAcces, 
        PeticioEdicioUsuariVO editarUsuari
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(editarUsuari);
        
        Request request = new Request.Builder()
            .url(URL_SERVIDOR + "/editarusuari/" + codiAcces.getCodiAcces())
            .header("Content-Type","application/json; charset=utf-8")
            .put(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        return response; 
    }
}
