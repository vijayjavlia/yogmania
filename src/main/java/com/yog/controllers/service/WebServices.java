package com.yog.controllers.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yog.modals.Category;
import com.yog.modals.Subscription;
import com.yog.modals.Videos;

public interface WebServices {

	String checkUserTokens(HttpServletRequest request);

	String processUserRequest(HttpServletRequest request);

	Subscription checkUserSubscription(String ani, String serviceName);

	List<Category> getContent();

	Videos getByVid(String id);
}
