
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
public class UsuariBOTest {
    MockWebServer server = new MockWebServer();
    
    public UsuariBOTest() {
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
    
    /**
     * Test per validar l'acció de fer login sense indicar cap dada
     * @throws IOException 
     */
    @Test
    public void testLoginSensePosarDades() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/login/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp email és obligatori\"}")                
        );
        
        // Simulació de la petició
        DadesAccesVO dadesAcces = new DadesAccesVO("", "");
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp email és obligatori", message);
    }
    
    /**
     * Test per validar l'acció de fer login només indicant l'email de l'usuari
     * @throws IOException 
     */
    @Test
    public void testLoginNomesUsuari() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/login/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp contrasenya és obligatori\"}")                
        );
        
        // Simulació de la petició
        DadesAccesVO dadesAcces = new DadesAccesVO("admin@email.com", "");
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp contrasenya és obligatori", message);
    }
    
    /**
     * Test per validar l'acció de fer login només indicant la contrasenya
     * @throws IOException 
     */
    @Test
    public void testLoginNomesContrasenya() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/login/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp usuari és obligatori\"}")                
        );
        
        // Simulació de la petició
        DadesAccesVO dadesAcces = new DadesAccesVO("", "1234");
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp usuari és obligatori", message);
    }
    
    /**
     * Test per validar l'acció de fer login només indicant la contrasenya
     * @throws IOException 
     */
    @Test
    public void testLoginUsuariCorrecteContrasenyaIncorrecta() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/login/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"La contrasenya no és vàlida\"}")                
        );
        
        // Simulació de la petició
        DadesAccesVO dadesAcces = new DadesAccesVO("admin@email.com", "9876");
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("La contrasenya no és vàlida", message);
    }
    
    /**
     * Test per validar l'acció de fer login només indicant la contrasenya
     * @throws IOException 
     */
    @Test
    public void testLoginUsuariInexistent() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/login/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"L'usuari no existeix\"}")                
        );
        
        // Simulació de la petició
        DadesAccesVO dadesAcces = new DadesAccesVO("fakeUser@email.com", "1234");
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("L'usuari no existeix", message);
    }
    
    /**
     * Test per validar l'acció de fer login només indicant la contrasenya
     * @throws IOException 
     */
    @Test
    public void testLoginUsuariDeshabilitat() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/login/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"L'usuari està deshabilitat\"}")                
        );
        
        // Simulació de la petició
        DadesAccesVO dadesAcces = new DadesAccesVO("admin@email.com", "1234");
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("L'usuari està deshabilitat", message);
    }
    
    /**
     * Test per validar l'acció de fer login només indicant la contrasenya
     * @throws IOException 
     */
    @Test
    public void testLoginCorrecte() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/login/");
        
        String mockCodiAcces = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody(mockCodiAcces)                
        );
        
        // Simulació de la petició
        DadesAccesVO dadesAcces = new DadesAccesVO("admin@email.com", "1234");
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(200, response.code());
        assertNotNull(mockCodiAcces);
    }
    
    /**
     * Test per validar l'acció de fer login només indicant la contrasenya
     * @throws IOException 
     */
    @Test
    public void testLogoutUsuari() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAcces = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/logout/"+mockCodiAcces);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody("Sessió finalitzada")                
        );
        
        // Simulació de la petició
        DadesAccesVO dadesAcces = new DadesAccesVO("admin@email.com", "1234");
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(dadesAcces);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(200, response.code());
        assertEquals("Sessió finalitzada", response.body().string());
    }
    
    /**
     * Test per validar l'acció de canviar contrasenya deixant el camp buit
     * @throws IOException 
     */
    @Test
    public void testCanviContransenyaBuida() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAccess = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/canviarContrasenya/"+mockCodiAccess);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("El camp contrasenya és obligatori")                
        );
        
        // Simulació de la petició
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        JSONObject jsonBody = new JSONObject().put("contrasenya", "");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .put(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(400, response.code());
        assertEquals("El camp contrasenya és obligatori", response.body().string());
    }
    
    /**
     * Test per validar l'acció de canviar contrasenya per l'indicada al input
     * @throws IOException 
     */
    @Test
    public void testCanviContransenyaCorrecte() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAccess = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/canviarContrasenya/"+mockCodiAccess);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody("Contrasenya modificada")                
        );
        
        // Simulació de la petició
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        JSONObject jsonBody = new JSONObject().put("contrasenya", "1234");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .put(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(200, response.code());
        assertEquals("Contrasenya modificada", response.body().string());
    }
    
    /**
     * Test per validar l'acció de llistar usuaris demanada per usuari que NO
     * és administrador
     * @throws IOException 
     */
    @Test
    public void testLlistarUsuarisNoSentAdministrador() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAccess = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/usuaris/"+mockCodiAccess);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(405)
                .setBody("Aquest usuari no té permís d'administrador")                
        );
        
        // Simulació de la petició  
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(405, response.code());
        assertEquals("Aquest usuari no té permís d'administrador", response.body().string());
    }
    
    /**
     * Test per validar l'acció de llistar usuaris demanada per usuari que SI
     * és administrador
     * @throws IOException 
     */
    @Test
    public void testLlistarUsuarisSentAdministrador() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAccess = UUID.randomUUID().toString();
        String mockLlistarUsuaris = "[{"
            + "\"idUsuari\":\"cfaabaec-c15d-482b-9205-eca8aaafa131\","
            + "\"email\":\"admin@email.com\","
            + "\"contrasenya\":\"$2a$10$2IyyCi3JNKQw1olyCbSACumMIq/Pxv2hvJ.JpKfchn/ou8UYnxBHm\","
            + "\"rol\":\"ADMINISTRADOR\","
            + "\"nom\":\"Administrador\","
            + "\"cifEmpresa\":\"unCif\","
            + "\"direccio\":\"unaDireccio\","
            + "\"poblacio\":\"unaPoblacio\","
            + "\"provincia\":\"unaProvincia\","
            + "\"dataCreacio\":\"2021-03-14T18:22:32.328+00:00\","
            + "\"ultimAcces\":\"2021-03-21T15:40:20.799+00:00\","
            + "\"deshabilitat\":false"
        + "}]";
        
        HttpUrl baseUrl = server.url("/usuaris/"+mockCodiAccess);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody(mockLlistarUsuaris)                
        );
        
        // Simulació de la petició  
        OkHttpClient httpClient = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(200, response.code());
        assertEquals(mockLlistarUsuaris, response.body().string());
    }
    
    /**
     * Test per canviar l'estat d'un usuari sense indicar el idUsuari
     * @throws IOException 
     */
    @Test
    public void testDeshabilitatUsuariSenseIdUsuari() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAccess = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/baixa/"+mockCodiAccess);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("El camp id usuari és obligatori")                
        );
        
        // Simulació de la petició
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        JSONObject jsonBody = new JSONObject().put("idUsuari", "");
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .delete(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(400, response.code());
        assertEquals("El camp id usuari és obligatori", response.body().string());
    }
    
    /**
     * Test per canviar l'estat d'un usuari NO sent administrador
     * 
     * @throws IOException 
     */
    @Test
    public void testDeshabilitatUsuariSenseSerAdministrador() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAccess = UUID.randomUUID().toString();
        String mockIdUsuari = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/baixa/"+mockCodiAccess);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(405)
                .setBody("Aquest usuari no té permís d'administrador")                
        );
        
        // Simulació de la petició
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        JSONObject jsonBody = new JSONObject().put("idUsuari", mockIdUsuari);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .delete(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(405, response.code());
        assertEquals("Aquest usuari no té permís d'administrador", response.body().string());
    }
    
    /**
     * Test per canviar l'estat d'un usuari SI sent administrador
     * 
     * @throws IOException 
     */
    @Test
    public void testDeshabilitatUsuariSenseSentAdministrador() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAccess = UUID.randomUUID().toString();
        String mockIdUsuari = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/baixa/"+mockCodiAccess);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody("Usuari deshabilitat")                
        );
        
        // Simulació de la petició
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        JSONObject jsonBody = new JSONObject().put("idUsuari", mockIdUsuari);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .delete(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(200, response.code());
        assertEquals("Usuari deshabilitat", response.body().string());
    }
    
    /**
     * Test per canviar l'estat d'un usuari sense indicar el idUsuari sent
     * administrador
     * 
     * @throws IOException 
     */
    @Test
    public void testDeshabilitatUsuariAmbIdUsuariErroni() throws IOException {
        // Prepara el Mock i la response esperada
        String mockCodiAccess = UUID.randomUUID().toString();
        String mockIdUsuari = UUID.randomUUID().toString();
        
        HttpUrl baseUrl = server.url("/baixa/"+mockCodiAccess);
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(404)
                .setBody("No s'ha trobat cap usuari amb l'identificador "+mockIdUsuari)                
        );
        
        // Simulació de la petició
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        JSONObject jsonBody = new JSONObject().put("idUsuari", mockIdUsuari);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .delete(RequestBody.create(jsonBody.toString(), JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(404, response.code());
        assertEquals("No s'ha trobat cap usuari amb l'identificador "+mockIdUsuari, response.body().string());
    }
    
    /**
     * Test per registrar nou usuari però sense indicar email
     * 
     * @throws IOException 
     */
    @Test
    public void testRegistreNouUsuariSenseEmail() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/registre/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp email és obligatori\"}")                
        );
        
        // Simulació de la petició
        String nom = "usuariNom";
        String contrasenya = "2468";
        String email = "";
        Rol rol = Rol.CLIENT;
        String cif = "G95688156";
        String direccio = "Mock street, 3";
        String poblacio = "Mock població";
        String provincia = "Mock provincia";
        Boolean deshabilitat = Boolean.FALSE;
        
        UsuariVO nouUsuari = new UsuariVO(
            email, 
            contrasenya, 
            rol, 
            nom, 
            cif, 
            direccio, 
            poblacio, 
            provincia, 
            deshabilitat
        );
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(nouUsuari);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp email és obligatori", message);
    }
    
    /**
     * Test per registrar nou usuari però sense indicar contrsenya
     * 
     * @throws IOException 
     */
    @Test
    public void testRegistreNouUsuariSenseContrasenya() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/registre/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp contrasenya és obligatori\"}")                
        );
        
        // Simulació de la petició
        String nom = "usuariNom";
        String contrasenya = "";
        String email = "usuarinou@email.com";
        Rol rol = Rol.CLIENT;
        String cif = "G95688156";
        String direccio = "Mock street, 3";
        String poblacio = "Mock població";
        String provincia = "Mock provincia";
        Boolean deshabilitat = Boolean.FALSE;
        
        UsuariVO nouUsuari = new UsuariVO(
            email, 
            contrasenya, 
            rol, 
            nom, 
            cif, 
            direccio, 
            poblacio, 
            provincia, 
            deshabilitat
        );
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(nouUsuari);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp contrasenya és obligatori", message);
    }
    
    /**
     * Test per registrar nou usuari però sense indicar rol
     * 
     * @throws IOException 
     */
    @Test
    public void testRegistreNouUsuariSenseRol() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/registre/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"El camp rol és obligatori\"}")                
        );
        
        // Simulació de la petició
        String nom = "usuariNom";
        String contrasenya = "2345";
        String email = "usuarinou@email.com";
        Rol rol = null;
        String cif = "G95688156";
        String direccio = "Mock street, 3";
        String poblacio = "Mock població";
        String provincia = "Mock provincia";
        Boolean deshabilitat = Boolean.FALSE;
        
        UsuariVO nouUsuari = new UsuariVO(
            email, 
            contrasenya, 
            rol, 
            nom, 
            cif, 
            direccio, 
            poblacio, 
            provincia, 
            deshabilitat
        );
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(nouUsuari);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("El camp rol és obligatori", message);
    }
    
    /**
     * Test per registrar nou usuari però sense indicar rol
     * 
     * @throws IOException 
     */
    @Test
    public void testRegistreNouUsuariEmailJaExistent() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/registre/");
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(400)
                .setBody("{\"message\":\"Ja existeix un usuari actiu amb aquest email\"}")                
        );
        
        // Simulació de la petició
        String nom = "usuariNom";
        String contrasenya = "2345";
        String email = "usuarinou@email.com";
        Rol rol = Rol.CLIENT;
        String cif = "G95688156";
        String direccio = "Mock street, 3";
        String poblacio = "Mock població";
        String provincia = "Mock provincia";
        Boolean deshabilitat = Boolean.FALSE;
        
        UsuariVO nouUsuari = new UsuariVO(
            email, 
            contrasenya, 
            rol, 
            nom, 
            cif, 
            direccio, 
            poblacio, 
            provincia, 
            deshabilitat
        );
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(nouUsuari);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        JSONObject responseMessage = new JSONObject(response.body().string());
        String message = responseMessage.getString("message");
        
        assertEquals(400, response.code());
        assertEquals("Ja existeix un usuari actiu amb aquest email", message);
    }
    
    /**
     * Test per registrar nou usuari però sense indicar rol
     * 
     * @throws IOException 
     */
    @Test
    public void testRegistreNouUsuariCorrecte() throws IOException {
        // Prepara el Mock i la response esperada
        HttpUrl baseUrl = server.url("/registre/");
        String mockIdUsuari = UUID.randomUUID().toString();
        
        server.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setBody("Usuari registrat correctament amb el id "+mockIdUsuari)                
        );
        
        // Simulació de la petició
        String nom = "usuariNom";
        String contrasenya = "2345";
        String email = "usuarinou@email.com";
        Rol rol = Rol.CLIENT;
        String cif = "G95688156";
        String direccio = "Mock street, 3";
        String poblacio = "Mock població";
        String provincia = "Mock provincia";
        Boolean deshabilitat = Boolean.FALSE;
        
        UsuariVO nouUsuari = new UsuariVO(
            email, 
            contrasenya, 
            rol, 
            nom, 
            cif, 
            direccio, 
            poblacio, 
            provincia, 
            deshabilitat
        );
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(nouUsuari);
        
        Request request = new Request.Builder()
            .url(baseUrl)
            .header("Content-Type","application/json; charset=utf-8")
            .post(RequestBody.create(requestBody, JSON))
            .build();
        
        Response response = httpClient.newCall(request).execute();
        
        // Tractament de la resposta
        assertEquals(200, response.code());
        assertEquals("Usuari registrat correctament amb el id "+mockIdUsuari, response.body().string());
    }
}
