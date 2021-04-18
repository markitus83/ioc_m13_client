
package ioc.femcoworking.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import static ioc.femcoworking.bo.OficinaBO.JSON;
import ioc.femcoworking.vo.DadesAccesVO;
import ioc.femcoworking.vo.UsuariVO;
import java.io.IOException;
import java.util.UUID;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import resources.Rol;

/**
 * Tests per validar el comportament de les crides HTTP al servidor 
 * @author Marc Ginovart Vega
 */
public class OficinaBOTest {
    MockWebServer server = new MockWebServer();
    
    public OficinaBOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        server.start();
    }
    
    @After
    public void tearDown() throws IOException {
        server.shutdown(); 
    }
    
    @Test
    public void testAltaOficinaSenseSerAdministrador() throws IOException {
        HttpUrl baseUrl = server.url("/altaoficina");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(405)
                .setBody("{\"message\":\"Aquesta funcionalitat requereix el rol d'administrador\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonOficina = new JSONObject()
            .put("idOficina", "null")
            .put("nom", "null")
            .put("tipus", "null")
            .put("capacitat", "null")
            .put("preu", "null")
            .put("serveis", "null")
            .put("habilitada", "null")
            .put("provincia", "null")
            .put("poblacio", "null")
            .put("direccio", "null")
            .put("dataCreacio", "null")
            .put("dataUltimaEdicio", "null")
            .put("eliminat", "null");
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", UUID.randomUUID().toString())
            .put("oficina", jsonOficina);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(405, response.code());
        assertEquals("Aquesta funcionalitat requereix el rol d'administrador", message);
    }
    
    @Test
    public void testAltaOficinaComAdministradorSenseIndicarOficina() throws IOException {
        HttpUrl baseUrl = server.url("/altaoficina");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Oficina no informada\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
                
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", UUID.randomUUID().toString())
            .put("oficina", "null");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("Oficina no informada", message);
    }
    
    @Test
    public void testAltaOficinaComAdministradorSenseIndicarNomOficina() throws IOException {        
        HttpUrl baseUrl = server.url("/altaoficina");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp nom és obligatori\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
                
        JSONObject jsonOficina = new JSONObject()
            .put("idOficina", "null")
            .put("nom", "null")
            .put("tipus", "null")
            .put("capacitat", "null")
            .put("preu", "null")
            .put("serveis", "null")
            .put("habilitada", "null")
            .put("provincia", "null")
            .put("poblacio", "null")
            .put("direccio", "null")
            .put("dataCreacio", "null")
            .put("dataUltimaEdicio", "null")
            .put("eliminat", "null");
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", UUID.randomUUID().toString())
            .put("oficina", jsonOficina);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp nom és obligatori", message);
    }
    
    @Test
    public void testAltaOficinaComAdministradorSenseIndicarTipusOficina() throws IOException {
        HttpUrl baseUrl = server.url("/altaoficina");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp tipus és obligatori\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
                
        JSONObject jsonOficina = new JSONObject()
            .put("idOficina", "null")
            .put("nom", "null")
            .put("tipus", "null")
            .put("capacitat", "null")
            .put("preu", "null")
            .put("serveis", "null")
            .put("habilitada", "null")
            .put("provincia", "null")
            .put("poblacio", "null")
            .put("direccio", "null")
            .put("dataCreacio", "null")
            .put("dataUltimaEdicio", "null")
            .put("eliminat", "null");
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", UUID.randomUUID().toString())
            .put("oficina", jsonOficina);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp tipus és obligatori", message);
    }
    
    @Test
    public void testAltaOficinaComAdministradorSenseIndicarPreuOficina() throws IOException {
        HttpUrl baseUrl = server.url("/altaoficina");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp preu és obligatori i no pot ser igual o inferior a 0\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
                
        JSONObject jsonOficina = new JSONObject()
            .put("idOficina", "null")
            .put("nom", "null")
            .put("tipus", "null")
            .put("capacitat", "null")
            .put("preu", "null")
            .put("serveis", "null")
            .put("habilitada", "null")
            .put("provincia", "null")
            .put("poblacio", "null")
            .put("direccio", "null")
            .put("dataCreacio", "null")
            .put("dataUltimaEdicio", "null")
            .put("eliminat", "null");
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", UUID.randomUUID().toString())
            .put("oficina", jsonOficina);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp preu és obligatori i no pot ser igual o inferior a 0", message);
    }
    
    @Test
    public void testAltaOficinaComAdministradorCorrecte() throws IOException {
        HttpUrl baseUrl = server.url("/altaoficina");
        
        String mockIdOficina = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody("Oficina donada d'alta amb l'identificador "+ mockIdOficina)                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
                
        JSONObject jsonOficina = new JSONObject()
            .put("idOficina", "null")
            .put("nom", "NomOficina")
            .put("tipus", "OFICINA_PRIVADA")
            .put("capacitat", "2")
            .put("preu", "10.0")
            .put("serveis", "wifi, bar")
            .put("habilitada", "true")
            .put("provincia", "Barcelona")
            .put("poblacio", "Sitges")
            .put("direccio", "Carrer Espalter, 23")
            .put("dataCreacio", "null")
            .put("dataUltimaEdicio", "null")
            .put("eliminat", "false");
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", UUID.randomUUID().toString())
            .put("oficina", jsonOficina);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        assertEquals(200, response.code());
        assertEquals("Oficina donada d'alta amb l'identificador " + mockIdOficina, response.body().string());        
    }
}