package br.com.empresa.exemploAutenticaco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.mifmif.common.regex.Generex;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {
    	 Auth auth = new Auth();
    	 String token = auth.geraToken();
    	 System.out.println("Token = "+token);
    	 
    	 String payload;
    	 payload = "{\r\n"
    	 		+ "  \"calendario\": {\r\n"
    	 		+ "    \"expiracao\": 3600\r\n"
    	 		+ "  },\r\n"
    	 		+ "  \"devedor\": {\r\n"
    	 		+ "    \"cpf\": \"12345678909\",\r\n"
    	 		+ "    \"nome\": \"Francisco da Silva\"\r\n"
    	 		+ "  },\r\n"
    	 		+ "  \"valor\": {\r\n"
    	 		+ "    \"original\": \"123.45\"\r\n"
    	 		+ "  },\r\n"
    	 		+ "  \"chave\": \"71cdf9ba-c695-4e3c-b010-abb521a3f1be\",\r\n"
    	 		+ "  \"solicitacaoPagador\": \"Cobrança dos serviços prestados.\"\r\n"
    	 		+ "}";
    	 
    	 //cobrança
    	 try {
    		String txid;
    		Generex generex = new Generex("[a-zA-Z0-9]{26,35}");
    		txid = generex.random();
    		URL url = new URL ("https://api-pix-h.gerencianet.com.br/v2/cob/"+txid); //Para ambiente de Desenvolvimento              
	        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("PUT");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "Bearer "+ token);


	        OutputStream os = conn.getOutputStream();
	        os.write(payload.getBytes());
	        os.flush();   

	        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(reader);

	        String response;
	        System.out.println("Enviando "+payload);
	        while ((response = br.readLine()) != null) {
	          System.out.println("Recebido "+response);
	        }
	        conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
}
