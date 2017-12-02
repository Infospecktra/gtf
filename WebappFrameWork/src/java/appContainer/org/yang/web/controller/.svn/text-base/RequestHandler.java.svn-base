package org.yang.web.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestHandler
{
   void init(ServletConfig config, 
             Properties properties) throws ServletException;

   String handleRequest(HttpServletRequest request, 
                        HttpServletResponse response, 
                        HttpServlet servlet) throws ServletException, IOException, Exception;  
}