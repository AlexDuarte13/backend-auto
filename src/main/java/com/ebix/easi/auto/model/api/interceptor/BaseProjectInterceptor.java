package com.ebix.easi.auto.model.api.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

@Component
public class BaseProjectInterceptor implements HandlerInterceptor{

	@Autowired
	RestTemplate restTemplate;
	
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

    	try {

        
	        HttpHeaders headers = new HttpHeaders();

	        
	        System.out.println(request.getHeader("token"));
	        System.out.println(request.getAttribute("token"));
	        

	        HttpEntity<String> entity = null;
	        if (request.getHeader("token") == null || "".equalsIgnoreCase(request.getHeader("token"))) {
	        	
		        final Map<String, String> pathVariables = (Map<String, String>) request
                        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	        	
		        String token = pathVariables.get("TOKEN");
		        
		        headers.set("token", token);
		        entity = new HttpEntity<>(headers);
			}else {
		        headers.set("token", request.getHeader("token"));
		       entity = new HttpEntity<>(headers);
			}
	        
	        
	                
	        ResponseEntity<Boolean> responseAuth = restTemplate.exchange(
	        		"http://localhost:9094/authorization/check", HttpMethod.GET, entity, Boolean.class);
	        
	        if(HttpStatus.OK.equals(responseAuth.getStatusCode())) {
	        	return true;
	
	        }else {
	            response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        	return false;
			}
        
		
		} catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        	return false;
		}
    	

    }

}
