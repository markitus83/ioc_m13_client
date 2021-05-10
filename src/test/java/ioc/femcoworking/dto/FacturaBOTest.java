
package ioc.femcoworking.dto;

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

/**
 * Tests per validar el comportament de les crides HTTP al servidor 
 * @author Marc Ginovart Vega
 */
public class FacturaBOTest {
    MockWebServer server = new MockWebServer();
    
    public FacturaBOTest() {
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
    public void testCrearFacturaAmbCodiAccesNoValid() throws IOException {
        HttpUrl baseUrl = server.url("/facturaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdReserva = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Codi d'accés no vàlid >> "+mockCodiAcces+"\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idReserva", mockIdReserva);
        
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
    public void testCrearFacturaAmbCodiAccesValid() throws IOException {
        HttpUrl baseUrl = server.url("/facturaoficina");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdReserva = UUID.randomUUID().toString();
        String mockIdFactura = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody("{\"message\":\"Factura donada d'alta amb l'identificador "+mockIdFactura+"\"}")                
        );
        
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        JSONObject jsonBody = new JSONObject()
            .put("codiAcces", mockCodiAcces)
            .put("idReserva", mockIdReserva);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(200, response.code());
        assertEquals("Factura donada d'alta amb l'identificador "+mockIdFactura, message);
    }
    
    @Test
    public void testLlistarFacturesAmbCodiAccesNoValid() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        HttpUrl baseUrl = server.url("/factures/"+mockCodiAcces);
        
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
    public void testLlistarFacturesAmbCodiAccesValid() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockLlistaReserves = "[{" +
                "\"idFactura\": \"fbe572f3-7627-427f-a1fa-ca5b53d5b22a\","+
                "\"idReserva\": {"+
                    "\"idReserva\": \"fdfdb7c2-a23e-4733-911a-23a54f22700c\","+
                    "\"dataIniciReserva\": \"2021-05-23\","+
                    "\"dataFiReserva\": \"2021-05-25\","+
                    "\"idOficina\": {"+
                        "\"idOficina\": \"4a82fa77-2e14-4843-8cf1-c2855c1fae3b\","+
                        "\"nom\": \"ofi2qweqwe\","+
                        "\"tipus\": \"OFICINA_PRIVADA\","+
                        "\"capacitat\": 121212,"+
                        "\"preu\": 3234234.0,"+
                        "\"serveis\": \"asdfjasdhf asdfias fashbf\","+
                        "\"habilitada\": true,"+
                        "\"provincia\": \"countyaadsasd\","+
                        "\"poblacio\": \"cityadasdasd\","+
                        "\"direccio\": \"streetsdadasd\","+
                        "\"dataCreacio\": \"2021-04-16T15:16:12.971+00:00\","+
                        "\"dataUltimaEdicio\": null,"+
                        "\"eliminat\": false"+
                    "},"+
                    "\"idUsuari\": {"+
                        "\"idUsuari\": \"64dd40f9-9e47-4b1c-bfc7-ce9949a95481\","+
                        "\"email\": \"tea3@email.com\","+
                        "\"contrasenya\": \"$2a$10$B/i3/EzMUxZsnIrCBkw5t.gXxupKXQaMZNlvTEyEKkro7CDQit34m\","+
                        "\"rol\": \"CLIENT\","+
                        "\"nom\": \"tea3-user\","+
                        "\"cifEmpresa\": \"tea3-cif\","+
                        "\"direccio\": \"tea3-direccio\","+
                        "\"poblacio\": \"tea3-poblacio\","+
                        "\"provincia\": \"tea3-provincia\","+
                        "\"dataCreacio\": \"2021-04-17T09:57:41.837+00:00\","+
                        "\"ultimAcces\": \"2021-05-07T12:26:00.849+00:00\","+
                        "\"deshabilitat\": false"+
                    "}"+
                "},"+
                "\"idUsuari\": {"+
                    "\"idUsuari\": \"64dd40f9-9e47-4b1c-bfc7-ce9949a95481\","+
                    "\"email\": \"tea3@email.com\","+
                    "\"contrasenya\": \"$2a$10$B/i3/EzMUxZsnIrCBkw5t.gXxupKXQaMZNlvTEyEKkro7CDQit34m\","+
                    "\"rol\": \"CLIENT\","+
                    "\"nom\": \"tea3-user\","+
                    "\"cifEmpresa\": \"tea3-cif\","+
                    "\"direccio\": \"tea3-direccio\","+
                    "\"poblacio\": \"tea3-poblacio\","+
                    "\"provincia\": \"tea3-provincia\","+
                    "\"dataCreacio\": \"2021-04-17T09:57:41.837+00:00\","+
                    "\"ultimAcces\": \"2021-05-07T12:26:00.849+00:00\","+
                    "\"deshabilitat\": false"+
                "},"+
                "\"nomUsuariReserva\": \"tea3-user\","+
                "\"nomOficina\": \"ofi2qweqwe\","+
                "\"tipusOficina\": \"OFICINA_PRIVADA\","+
                "\"preuOficina\": 3234234.0,"+
                "\"serveisOficina\": null,"+
                "\"data_inici_reserva\": \"2021-05-23T22:00:00.000+00:00\","+
                "\"data_fi_reserva\": \"2021-05-25T22:00:00.000+00:00\","+
                "\"subTotal\": 6468468.0,"+
                "\"impostos\": 1358378.28,"+
                "\"total\": 7826846.28,"+
                "\"dataCreacio\": \"2021-05-03T21:59:50.654+00:00\""+
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
    
    @Test
    public void testEliminarFacturaAmbCodiAccesNoValid() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdFactura = UUID.randomUUID().toString();
        HttpUrl baseUrl = server.url("/esborrarfactura/"+mockCodiAcces+"/"+mockIdFactura);
        
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
    public void testEliminarFacturaAmbCodiAccesValidSenseSerAdministrador() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdFactura = UUID.randomUUID().toString();
        HttpUrl baseUrl = server.url("/esborrarfactura/"+mockCodiAcces+"/"+mockIdFactura);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(405)
                .setBody("{\"message\":\"Aquesta funcionalitat requereix el rol d'administrador\"}")                
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
        
        assertEquals(405, response.code());
        assertEquals("Aquesta funcionalitat requereix el rol d'administrador", message);
    }
    
    @Test
    public void testEliminarFacturaAmbCodiAccesValidSentAdministrador() throws IOException {
        String mockCodiAcces = UUID.randomUUID().toString();
        String mockIdFactura = UUID.randomUUID().toString();
        HttpUrl baseUrl = server.url("/esborrarfactura/"+mockCodiAcces+"/"+mockIdFactura);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody("{\"message\":\"Factura eliminada\"}")
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
        
        assertEquals(200, response.code());
        assertEquals("Factura eliminada", message);
    }
}