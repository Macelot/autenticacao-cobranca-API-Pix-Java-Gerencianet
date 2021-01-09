package br.com.empresa.exemploAutenticaco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.json.JSONObject;

public class Auth {
	
	public final String client_id = "Client_Id_76e7254a937e2e2af069c0403c0adb5a8da341ee";
	public final String client_secret = "Client_Secret_117c06443e20208450e25a4f30038a25901ba8a4";
	public final String basicAuth = Base64.getEncoder().encodeToString(((client_id+':'+client_secret).getBytes()));
	

	public String geraToken() {
		String token="";
		try {
			//Diretório em que seu certificado em formato .p12 deve ser inserido
	        System.setProperty("javax.net.ssl.keyStore", "developer278612.p12"); 
	        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	        
	        URL url = new URL ("https://api-pix-h.gerencianet.com.br/oauth/token"); //Para ambiente de Desenvolvimento              
	        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "Basic "+ basicAuth);
	        conn.setSSLSocketFactory(sslsocketfactory);
	        String input = "{\"grant_type\": \"client_credentials\"}";
	       
	        OutputStream os = conn.getOutputStream();
	        os.write(input.getBytes());
	        os.flush();   

	        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(reader);

	        String response;
	        StringBuilder responseBuilder = new StringBuilder();
	        while ((response = br.readLine()) != null) {
	          System.out.println(response);
	          responseBuilder.append(response);
	        }
	        try {
				JSONObject jsonObject = new JSONObject(responseBuilder.toString());
				token = jsonObject.getString("access_token");
			} catch (Exception e) {
				// TODO: handle exception
			}
	        conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
		return token;
       
	}
}
