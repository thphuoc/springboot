package com.heimdall.forwarder;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RequestForwarder {
//    @PostMapping("/**")
//    public ModelAndView redirectPostToPost(HttpServletRequest request) {
//        System.out.println("QueryString: "+request.getQueryString());
//        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
//        return new ModelAndView("forward:/http://5a51bfb150dffb001256e08f.mockapi.io/testing/login");
//    }
}
