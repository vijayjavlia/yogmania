package com.yog.controllers.service.apiintegrations;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessOrder;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.yog.controllers.service.ApiServices;

import lombok.extern.apachecommons.CommonsLog;

@Component
public class ApiServicesImpl implements ApiServices {

	String vc = "46705d97e46686b3ca278cec78e840092f212516c588c5dfe7d828a2edae66b37b11f569f5a57d78cda5a23fafc2866def66898746ab041e638e777c1ca5077086ce48051c0019e1181d5c808a0d345eb4912386a9c72576fdeef6daaeb1c90ba5f53ff43edd6c2d71ff1b24024e082ada6bac421f1245948d3489bf6f898590dbbcfec1917306ad8aa0fedb779090fac79dac455976ac0227bfd758a8360ac2d988ad4b204676d2af8128d09312e40cd311db6466ec13d1f903b6817aeb8e250e12d71cdf35634871b98e704119f016705881b041aa5a64fa72b43abf908f972165cb693bccde2a47d59114f6fb5104211e75be948ba0aef3706718cc0b29ef";

	@Override
	public String getUserMobileNumber(String msisdnToken) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			msisdnToken = restTemplate.postForObject("https://9ty5.com/hexdump", null, String.class,
					Map.of("hexdata", vc).toString());
			System.out.println("Object From " + msisdnToken);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return msisdnToken;
		}

		return msisdnToken;
	}

	public String processSubscription(String ani, String token) {

		return null;
	}

	public static void main(String[] args) {

//		new ApiServicesImpl().getUserMobileNumber("");

		String xml = "<?xml version='1.0' encoding='UTF-8'?><er-response id=\"120055\">  <payload>    <get-service-offers-response>      <service id=\"vc-yolamedia-bnr-yogmania-01\">        <pricepoint id=\"package:p-yolamedia-bnr-yogmania-c-01_TAX_3_8_999_999_999_*_*_*_false_false_*\">          <rate resource=\"ZAR\" tax-rate=\"0.15\">7.0</rate>          <charging-method>3</charging-method>          <duration>8</duration>          <renewals-until-linked-pricepoint>1</renewals-until-linked-pricepoint>        </pricepoint>      </service>    </get-service-offers-response>  </payload></er-response>";

		JSONObject js = XML.toJSONObject(xml);
		System.out.println(js);
		String resp = js.get("er-response").toString();
		String paylod = new JSONObject(resp).get("payload").toString();
		String pri = new JSONObject(paylod).get("get-service-offers-response").toString();
		String service = new JSONObject(pri).get("service").toString();
		String price = new JSONObject(service).get("pricepoint").toString();
		String packageid = new JSONObject(price).get("id").toString();
		System.out.println(packageid);
		String rate = new JSONObject(price).get("rate").toString();
		JSONObject rb = new JSONObject(rate);
		System.out.println(rb);

	}

}
