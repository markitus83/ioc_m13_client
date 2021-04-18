
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
    public void testAltaOficinaAmbCodiAccesNoValid() throws IOException {
        HttpUrl baseUrl = server.url("/altaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Codi d'accés no vàlid >> "+mockCodiAcces+"\"}")                
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
            .put("codiAcces", mockCodiAcces)
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
        assertEquals("Codi d'accés no vàlid >> "+mockCodiAcces, message);
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
    
    @Test
    public void testLlistarOficinesAmbCodiAccesNoValid() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/oficines/"+mockCodiAcces);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Codi d'accés no vàlid >> "+mockCodiAcces+"\"}")                
        );
        
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .get()
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("Codi d'accés no vàlid >> "+mockCodiAcces, message);
    }
    
    @Test
    public void testLlistarOficinesAmbCodiAccesValid() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockLlistaOficines = "[{"
            + "\"idOficina\":\"cfaabaec-c15d-482b-9205-eca8aaafa131\","
            + "\"nom\":\"sala1\","
            + "\"tipus\": \"OFICINA_PRIVADA\","
            + "\"capacitat\": 2,"
            + "\"preu\": 11.0,"
            + "\"serveis\": \"wifi, cookies\","
            + "\"habilitada\": \"true\","
            + "\"provincia\": \"neverland\","
            + "\"poblacio\": \"wonderworld\","
            + "\"direccio\": \"sesam street\","
            + "\"eliminat\": \"true\""
        + "}]";
        
        HttpUrl baseUrl = server.url("/oficines/"+mockCodiAcces);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody(mockLlistaOficines)                
        );
        
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .get()
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        assertEquals(200, response.code());
        assertEquals(mockLlistaOficines, response.body().string());
    }
    
    @Test
    public void testBaixaOficinesAmbCodiAccesNoValid() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdOficina = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/baixaoficina/"+mockCodiAcces+"/"+mockIdOficina);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Codi d'accés no vàlid >> "+mockCodiAcces+"\"}")                
        );
        
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .delete()
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("Codi d'accés no vàlid >> "+mockCodiAcces, message);
    }
    
    @Test
    public void testBaixaOficinesSenseIndicarIdOficina() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdOficina = null;
        
        HttpUrl baseUrl = server.url("/baixaoficina/"+mockCodiAcces+"/"+mockIdOficina);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp id oficina és obligatori\"}")                
        );
        
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .delete()
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp id oficina és obligatori", message);
    }
    
    @Test
    public void testBaixaOficinesSenseSerAdministrador() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdOficina = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/baixaoficina/"+mockCodiAcces+"/"+mockIdOficina);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(405)
                .setBody("{\"message\":\"Aquesta funcionalitat requereix el rol d'administrador\"}")               
        );
        
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .delete()
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(405, response.code());
        assertEquals("Aquesta funcionalitat requereix el rol d'administrador", message);
    }
    
    @Test
    public void testBaixaOficinesCorrecte() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdOficina = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/baixaoficina/"+mockCodiAcces+"/"+mockIdOficina);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody("Oficina donada de baixa")
        );
        
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .delete()
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        assertEquals(200, response.code());
        assertEquals("Oficina donada de baixa", response.body().string());
    }
}
