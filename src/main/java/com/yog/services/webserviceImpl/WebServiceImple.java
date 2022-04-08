package com.yog.services.webserviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yog.controllers.service.HeaderProcess;
import com.yog.controllers.service.WebServices;
import com.yog.modals.Category;
import com.yog.modals.Subscription;
import com.yog.modals.Videos;
import com.yog.repositorys.CategoryRepo;
import com.yog.repositorys.VideosRepo;
import com.yog.service.headerimple.HeaderProcessImple;

@Service
public class WebServiceImple implements WebServices {

	private final Logger LOG = LoggerFactory.getLogger(WebServiceImple.class);

	@Autowired
	private HeaderProcessImple headerProcessImple;

	@Autowired
	private HeaderProcess headerProcess;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private VideosRepo vrepo;

	@Override
	public String processUserRequest(HttpServletRequest request) {

		return headerProcess.getHeaderToken(request, "x-vcza-acr") == true ? checkUserTokens(request) : "info";

	}	

	public String checkUserTokens(HttpServletRequest request) {

		String VCZAToken = headerProcessImple.procesVCZA(request);

		LOG.info("{}", VCZAToken);

		return VCZAToken;

	}

	public String processUserSubscriptionAndTokensResponse(String ani) {
		String result = "";
		String status = "";
		Subscription sub = checkUserSubscription(ani, "Yogmania");
		if (status.equals("0"))
			result = "charging pending";
		if (status.equals("1"))
			result = "onportal";
		if (status.equals("404"))
			result = "need login";
		return result;
	}

	@Override
	public Subscription checkUserSubscription(String ani, String serviceName) {

//		Subscription sub = subRepo.findByAniAndServicename(ani, serviceName).orElse(new Subscription("404"));

		return null;

	}
	@Override
	public List<Category>  getContent()
	{
		
	 List<Category> allcatContent = categoryRepo.findAll();
	 System.out.println(allcatContent);		
      return allcatContent;
	}
	
	@Override
	public Videos getByVid(String id) {
		
		Videos video = vrepo.findByVideoId(id);
		LOG.info(video.toString());
		return video;
	}
	
	
	
	
	
	

}
