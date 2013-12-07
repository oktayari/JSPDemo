package org.oka.controllers;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.oka.bll.LoginService;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Inject LoginService loginService;

    RequestDispatcher dispatcher = null;
    String action = "";
    String urlx = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        action = request.getParameter("action");
        urlx = "index.jsp";
               
        if ("Login".equalsIgnoreCase(action)) {
          if (!loginService.authenticate(request)) {
               urlx = "/ortak/loginError.jsp";
           } 
       } else if ("Logout".equalsIgnoreCase(action)) {
            loginService.logout(request);
        } else if ("ShowInfo".equalsIgnoreCase(action)) {
            loginService.showInfo(request);
            urlx = "/ortak/userInfo.jsp";
        } else if ("Register".equalsIgnoreCase(action)) {
            urlx =loginService.register(request);
        } 

        dispatcher = request.getRequestDispatcher(urlx);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
