
package ioc.femcoworking.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ReservaBOTest {
    MockWebServer server = new MockWebServer();
    
    public ReservaBOTest() {
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
    public void testAltaReservaAmbCodiAccesNoValid() throws IOException {
        HttpUrl baseUrl = server.url("/reservaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockCodiOficina = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Codi d'accés no vàlid >> "+mockCodiAcces+"\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idOficina", mockCodiOficina)
            .put("dataIniciReserva", "2021-02-21")
            .put("dataFiReserva", "2021-02-24");
        
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
    public void testAltaReservaAmbCodiAccesValidSenseIndicarReserva() throws IOException {
        HttpUrl baseUrl = server.url("/reservaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockCodiOficina = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Reserva no informada\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idOficina", mockCodiOficina)
            .put("dataIniciReserva", "2021-02-21")
            .put("dataFiReserva", "2021-02-24");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("Reserva no informada", message);
    }
    
    @Test
    public void testAltaReservaAmbCodiAccesValidSenseIndicarDataInici() throws IOException {
        HttpUrl baseUrl = server.url("/reservaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockCodiOficina = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp data_inici_reserva és obligatori\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idOficina", mockCodiOficina)
            .put("dataIniciReserva", "null")
            .put("dataFiReserva", "2021-02-24");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp data_inici_reserva és obligatori", message);
    }
    
    @Test
    public void testAltaReservaAmbCodiAccesValidSenseIndicarDataFi() throws IOException {
        HttpUrl baseUrl = server.url("/reservaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockCodiOficina = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp data_fi_reseva és obligatori\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idOficina", mockCodiOficina)
            .put("dataIniciReserva", "2021-02-21")
            .put("dataFiReserva", "null");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp data_fi_reseva és obligatori", message);
    }
    
    @Test
    public void testAltaReservaAmbCodiAccesValidAmbDataFiAnteriorDataInici() throws IOException {
        HttpUrl baseUrl = server.url("/reservaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockCodiOficina = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp data_fi_reseva és anterior a data_inici_reserva\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idOficina", mockCodiOficina)
            .put("dataIniciReserva", "2021-02-21")
            .put("dataFiReserva", "2021-02-15");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp data_fi_reseva és anterior a data_inici_reserva", message);
    }
    
    @Test
    public void testAltaReservaAmbCodiAccesValidSenseIndicarOficina() throws IOException {
        HttpUrl baseUrl = server.url("/reservaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockCodiOficina = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Oficina no informada\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idOficina", "null")
            .put("dataIniciReserva", "2021-02-21")
            .put("dataFiReserva", "2021-02-25");
        
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
    public void testAltaReservaAmbCodiAccesValidSenseIndicarUsuari() throws IOException {
        HttpUrl baseUrl = server.url("/reservaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockCodiOficina = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Usuari no informada\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idOficina", mockCodiOficina)
            .put("dataIniciReserva", "2021-02-21")
            .put("dataFiReserva", "2021-02-25");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("Usuari no informada", message);
    }
    
    @Test
    public void testLlistarReservesAmbCodiAccesNoValid() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        HttpUrl baseUrl = server.url("/reserves/"+mockCodiAcces);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Codi d'accés no vàlid >> "+mockCodiAcces+"\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
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
    public void testLlistarReservesAmbCodiAccesValid() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockLlistaReserves = "[{" +
                "\"idReserva\": \"2b292429-3a9d-498b-bf1f-b21c71220ecd\"," +
                "\"dataIniciReserva\": \"2021-05-21\"," +
                "\"dataFiReserva\": \"2021-07-23\"," +
                "\"idOficina\": {" +
                "\"idOficina\": \"8097aa34-6f68-4eea-b731-79abb3ae925a\"," +
                "\"nom\": \"testofi\"," +
                "\"tipus\": \"ESCRIPTORI_DEDICAT\"," +
                "\"capacitat\": 14," +
                "\"preu\": 21.0," +
                "\"serveis\": \"wifi, acces wc, cafetra\"," +
                "\"habilitada\": true," +
                "\"provincia\": \"county\"," +
                "\"poblacio\": \"city\"," +
                "\"direccio\": \"street\"," +
                "\"dataCreacio\": \"2021-04-15T09:22:51.699+00:00\"," +
                "\"dataUltimaEdicio\": \"2021-04-16T14:39:11.762+00:00\"," +
                "\"eliminat\": false }" +
                "\"idUsuari\": {" +
                "\"idUsuari\": \"2ec13407-d5c5-4fcc-b618-08515b4a7f20\"," +
                "\"email\": \"user1@email.com\"," +
                "\"contrasenya\": \"$2a$10$TPXdTIiEw/Ixjt1ggY1rBOVKO7aPunATGDk/f0ryCIUzO4kCA07CO\"," +
                "\"rol\": \"CLIENT\"," +
                "\"nom\": \"User1\"," +
                "\"cifEmpresa\": \"unCifUser1\"," +
                "\"direccio\": \"unaDireccioUser1\"," +
                "\"poblacio\": \"unaPoblacioUser1\"," +
                "\"provincia\": \"unaProvinciaUser1\"," +
                "\"dataCreacio\": \"2021-03-21T17:01:44.112+00:00\"," +
                "\"ultimAcces\": \"2021-03-23T22:23:59.981+00:00\"," +
                "\"deshabilitat\": false }" +
            "}]";
        
        HttpUrl baseUrl = server.url("/reserves/"+mockCodiAcces);      
        
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody(mockLlistaReserves)                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .get()
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        assertEquals(200, response.code());
        assertEquals(mockLlistaReserves, response.body().string());
    }
    
}