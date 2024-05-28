package com.example.BloggingServers.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**
 * SimpleCorsFilter is a filter responsible for handling Cross-Origin Resource Sharing (CORS) in the application.
 * It allows requests from specified origins (e.g., frontend applications) to access resources on the server.
 * This filter is applied to all incoming requests and sets appropriate CORS headers to enable cross-origin requests.
 *
 * CORS (Cross-Origin Resource Sharing) is a security feature implemented by web browsers that restricts
 * web pages from making requests to a different domain than the one that served the original page.
 * CORS headers are used to inform the browser whether it's allowed to make requests to a specific origin.
 *
 * This filter sets the following CORS headers:
 * - Access-Control-Allow-Origin: Specifies which origins are allowed to access the resources. It is set to the
 *   value of the "origin" header in the incoming request.
 * - Access-Control-Allow-Methods: Specifies the HTTP methods (e.g., POST, PUT, GET, DELETE) that are allowed
 *   when accessing the resources.
 * - Access-Control-Max-Age: Specifies how long the results of a preflight request can be cached.
 * - Access-Control-Allow-Headers: Specifies which HTTP headers can be used when making the actual request.
 *
 * Additionally, this filter handles preflight requests (OPTIONS) by setting the response status to 200 OK
 * if the request method is OPTIONS, indicating that the actual request can proceed.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class SimpleCorsFilter implements Filter {

    /**
     * The URL of the client application (frontend) that is allowed to make cross-origin requests.
     */
    @Value("${app.client.url}")
    private String clientAppUrl = "";

    /**
     * Default constructor for the SimpleCorsFilter class.
     */
    public SimpleCorsFilter(){
    }

    /**
     * Initializes the filter.
     *
     * @param filterConfig a FilterConfig object containing the filter's configuration and initialization parameters
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Filters the request and response objects to handle CORS.
     *
     * @param servletRequest  the ServletRequest object containing the client's request
     * @param servletResponse the ServletResponse object for sending the response to the client
     * @param filterChain     the FilterChain object for invoking the next filter in the chain
     * @throws IOException      if an I/O error occurs during the filtering process
     * @throws ServletException if an error occurs while processing the request
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Inside doFilter Method");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Retrieve the origin header from the request
        String originHeader = request.getHeader("origin");

        // Set CORS headers in the response
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");

        // Handle preflight requests (OPTIONS method)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Pass the request and response to the next filter in the chain
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Destroys the filter.
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}








/*In this class we are allowing all the apis call from our frontend application*/
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Slf4j
//public class SimpleCorsFilter implements Filter {
//
//    @Value("${app.client.url}")
//    private String clientAppUrl = "";
//
//    public SimpleCorsFilter(){
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.info("Inside doFilter Method");
//
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        Map<String,String> map = new HashMap<>();
//        String originHeader = request.getHeader("origin");
//        response.setHeader("Access-Control-Allow-Origin",originHeader);
//        response.setHeader("Access-Control-Allow-Methods","POST,PUT,GET,DELETE,OPTIONS");
//        response.setHeader("Access-Control-Max-Age","3600");
//        response.setHeader("Access-Control-Allow-Headers","*");
//
//        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
//            response.setStatus(HttpServletResponse.SC_OK);
//        }else{
//            filterChain.doFilter(request,response);
//        }
//
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
