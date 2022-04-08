package com.yog.service.headerimple;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.yog.controllers.service.HeaderProcess;
import com.yog.controllers.service.apiintegrations.ApiServicesImpl;

@Component
public class HeaderProcessImple implements HeaderProcess {

	
	public Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApiServicesImpl apiServicesImpl;
	
	@Value("${charging}")
	private String chargingurl;
	
	@Value("${production}")
	private String redirectulr;
	

	public String procesVCZA(HttpServletRequest request)
	{
		
		
		String zacrToken = request.getHeader("x-vcza-acr");
		
		String redirectURL = "http://vodatest.yogmania.com/operatorredirect/testvideos/";
		
		String URLRe =redirectulr+"&token="
				+ zacrToken + "&package-id=#P&client-txn-id=visiontrek1234&partner-redirect-url=" + redirectURL;

		String testxml = "<er-request id=\"120054\" client-application-id=\"DCB_YOLAMEDIA\" purchase_locale=\"en_ZA\"\r\n"
				+ "language_locale=\"en_ZA\">\r\n" + " <payload>\r\n" + " <get-service-offers>\r\n"
				+ " <charging-id\r\n" + "type=\"msisdn\">" + zacrToken + "</charging-id>\r\n"
				+ " <service-ids>vc-yolamedia-bnr-yogmania-01</service-ids>\r\n" + " </get-service-offers>\r\n"
				+ " </payload>\r\n" + "</er-request>\r\n" + "";
		
		HttpURLConnection urlConnection = null;
		
		try {
			log.info("{}","Json " + testxml);
			
			URL u = new URL(chargingurl);
			urlConnection = (HttpURLConnection) u.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "text/xml");
			urlConnection.setRequestProperty("Authorization", "Basic ZGNiX3ltX2JucjpiZzZWZmZUd1BQR1pKVDNtRnllcEFTN2tkYWFjMk4=");
			urlConnection.connect();
			
			OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
			wr.write(testxml);
			wr.flush();
			StringBuffer response = null;
			log.info("{}","Reponse Code  :: " + urlConnection.getResponseCode());

			 BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			 String line = null; 
			 response = new StringBuffer();
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
				log.info("{}",response);
				
				String packageid="";
				String expdate="",purchasedate="";
				JSONObject js = XML.toJSONObject(response.toString());
				System.out.println(js);
				String resp = js.get("er-response").toString();
				String paylod = new JSONObject(resp).get("payload").toString();
				String pri = new JSONObject(paylod).get("get-service-offers-response").toString();
				String service = new JSONObject(pri).get("service").toString();
				if(service.contains("subscription"))
				{
					
					String subscription=new JSONObject(service).get("subscription").toString();
					   JSONObject subjs = new JSONObject(subscription);
					   packageid= subjs.get("package-id").toString();
					   expdate=subjs.get("expiry-date").toString();
						purchasedate= subjs.get("purchase-date").toString();
						String status=subjs.get("status").toString();
						
						if(status.equalsIgnoreCase("1"))
							URLRe ="http://test.yogmania.com/index.jsp";
						else
						{
							URLRe =URLRe.replace("#P", packageid);
						}
					
				}else {
				String price = new JSONObject(service).get("pricepoint").toString();
				packageid = new JSONObject(price).get("id").toString();
				System.out.println(packageid);
				String rate = new JSONObject(price).get("rate").toString();
				JSONObject rb = new JSONObject(rate);
				URLRe =URLRe.replace("#P", packageid);
				}
				
			
			
		} catch (Exception e) {
			System.out.println("Error in Call Cent Api   :::: "+e.getMessage());
			return "redirect:"+URLRe;

		} 
		
		log.info(" Redirect URl {}",URLRe);

		return "redirect:"+URLRe;
		
	}
	

	@Override
	public String checkHeaderVcAcrToken(HttpServletRequest request) {

		String vacrToken = request.getHeader("x-vc-acr");
//		return apiServicesImpl.getUserMobileNumber(vacrToken);
		return vacrToken;

	}

	
	@Override
	public Map<String, String> checkCookies(HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();
		String ani = "";
		String token = "";

		if (cookies != null) {

			String cookieName = "";
			try {
				cookieName = cookies[1].getName();
				System.out.println("cookieName::" + cookieName);
			} catch (Exception e) {
				System.out.println("No data in cookies");

				return Map.of("status", "0", "url", "redirecttologin", "message", "data is not available");
			}
			for (int i = 0; i < cookies.length; i++) {

				if (cookieName.equalsIgnoreCase("ani")) {
					ani = cookies[1].getValue();
					token = cookies[0].getValue();
					System.out.print("ani : " + ani);
					System.out.print("token: " + token);
				}
				if (cookieName.equalsIgnoreCase("token")) {
					token = cookies[1].getValue();
					ani = cookies[0].getValue();
					System.out.print("ani : " + ani);
					System.out.print("token: " + token);
				}

			}
			if (cookieName.equalsIgnoreCase("ani") || cookieName.equalsIgnoreCase("token")) {
				return Map.of("status", "1", "ani", ani, "message", "cookiesAvailable", "token", token);
			}

		} else {
			return Map.of("status", "0", "url", "redirecttologin", "message", "data is not available");
		}
		return null;
	}

}
