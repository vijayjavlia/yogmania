package com.yog.controllers.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface HeaderProcess {
    

     String checkHeaderVcAcrToken(HttpServletRequest request);
     Map<String ,String> checkCookies(HttpServletRequest request);
     
     default boolean getHeaderToken(HttpServletRequest request,String tokenname)
     {
    	 System.out.println(tokenname);
    	 return (request.getHeader(tokenname) == null ? false : true);
     }

     
}
