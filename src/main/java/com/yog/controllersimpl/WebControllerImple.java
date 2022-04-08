package com.yog.controllersimpl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yog.services.webserviceImpl.WebServiceImple;

@Controller
public class WebControllerImple {

	@Autowired
	private WebServiceImple service;

	private HttpServletRequest request;

	@Autowired
	public WebControllerImple(HttpServletRequest request) {
		this.request = request;
	}
	public Logger log=LoggerFactory.getLogger(getClass());
	
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("message","Hello ThymeLeaf");
		return "index";
		
	}
	
	

	@GetMapping("/test")
	public String getRedirectUser() {

		Enumeration<String> params = request.getHeaderNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
		log.info("Parameter Name - {}" + paramName + ", Value - {}" + request.getHeader(paramName));			
		}
		return service.processUserRequest(request);
	}

//	@GetMapping("/redirect")
//	public String porcessSubscriptionRequest(@RequestParam(name = "ani", required = false) String ani,
//			@RequestParam(name = "token", required = false) String token,
//			@RequestParam(name = "extid", required = false, defaultValue = "0") String extid) {
//
//		return "redirect:http://test.yogmania.com";
//	}

	@GetMapping("/callback")
	public String callBack(@RequestBody String callback) {
		
		log.info("Callback :::::{} " +callback);
	
		return callback;		
	}

	@GetMapping("/operatorredirect/{extid}/")
	public String responseRedirect(@PathVariable( name="extid" ,required = false) String extid,@RequestParam(name="result",required = false) String result,
			@RequestParam( name="status-code",required=false) String status,@RequestParam( name="client-txn_id" ,required=false) String txid,@RequestParam( name="result-description",required=false) String desc,
			@RequestParam(name ="gws_rd" ,required=false) String rd) {

		log.info("Query String{}",request.getQueryString());
		
		log.info("{} ",extid + " :::: result" + result+"--status--" +status +"--txid--"+ txid+"--desc--"+ desc +"--rd--"+rd +"");
		if(result.equalsIgnoreCase("Accepted")){
			
		    return "redirect:http://test.yogmania.com/index.jsp";
			}
		else if(result.equalsIgnoreCase("Declined")) {
			
			return  "redirect:http://test.yogmania.com/index.jsp";
		}
		return  "redirect:http://test.yogmania.com/index.jsp";
	}
	
	@GetMapping("/check")
	public String  checkApi(Model content)
	{
		content.addAttribute("content",service.getContent());
		return "index";
	}
	
	@GetMapping("/play")
	public String  videoPlay(Model video,@RequestParam(name="videoid" ,required = false) String videoid )
	{
		if(videoid.equalsIgnoreCase("") || videoid == null) return "redirect:/check";
		
		    video.addAttribute("video",service.getByVid(videoid));	
		
		return "play";
	}

}
