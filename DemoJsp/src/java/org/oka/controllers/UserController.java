package org.oka.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.oka.bll.UserService;
import org.oka.model.Userst;
import org.oka.validators.Validator;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
@MultipartConfig
public class UserController extends HttpServlet {

    @Inject
    UserService userService;
    Userst user = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean showEditOrNew = false;

        String action = request.getParameter("action");
        String userName = request.getParameter("userName");

        if ("Save".equalsIgnoreCase(action)) {

            String passWord = request.getParameter("passWord");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String[] roles = request.getParameterValues("roles");

            user = new Userst();
            user.setUserName(userName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassWord(passWord);
            user.setEmail(email);
            userService.setUserRoles(roles, user);

            Validator validator = new Validator();
            boolean validationErrorFlag
                    = validator.validateKullanici(
                            userName, firstName, lastName,
                            passWord, email, request);

            if (validationErrorFlag == true) {
                showEditOrNew = true;
                request.setAttribute("user", user);
                request.setAttribute("validationErrorFlag", validationErrorFlag);
            } else {

                Userst checkedUser = userService.getUser(user.getUserName());
                if (checkedUser != null) {
                    if (checkedUser.getUserName().equalsIgnoreCase(user.getUserName())) {
                        uploadFile(request, userName);
                        userService.editUser(user);
                        user = null;
                    }
                } else {
                    uploadFile(request, userName);
                    userService.addUser(user);
                    user = null;
                }
            }

        } else if ("Add".equalsIgnoreCase(action)) {
            user = null;
            showEditOrNew = true;

        } else if ("Edit".equalsIgnoreCase(action)) {
            showEditOrNew = true;
            user = userService.getUser(userName);
            request.setAttribute("user", user);
            List<String> userRoles = userService.getUserRoles(user);
            request.setAttribute("userRoles", userRoles);

        } else if ("Delete".equalsIgnoreCase(action)) {
            userService.deleteUser(userName);
            user = null;
        }

        // pagin Start
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int noOfRecords = userService.getAllUsers().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        int firstR = (page - 1) * recordsPerPage;
        int lastR = firstR + recordsPerPage;
        //paging end
        
        request.setAttribute(
                "noOfPages", noOfPages);
        request.setAttribute(
                "currentPage", page);
        request.setAttribute(
                "allUsers", userService.getUsersByRange(firstR, lastR));
        request.setAttribute(
                "allRoles", userService.getAllRoles());
        request.setAttribute(
                "showEditOrNew", showEditOrNew);
        request.getRequestDispatcher(
                "/admin/users.jsp").forward(request, response);
    }

    void uploadFile(HttpServletRequest request, String userName) {
        try {

            Part p1 = request.getPart("file");

            if (p1.getSize() > 0) {

                InputStream is = p1.getInputStream();
                String filename = userName;
                filename = "img/" + filename + ".png";
                // get filename to use on the server
                String outputfile = this.getServletContext().getRealPath(filename);  // get path on the server
                FileOutputStream os = new FileOutputStream(outputfile);

                // write bytes taken from uploaded file to target file
                int ch = is.read();
                while (ch != -1) {
                    os.write(ch);
                    ch = is.read();
                }
                os.close();

            }
        } catch (Exception ex) {
            //out.println("Exception -->" + ex.getMessage());
        } finally {
            //out.close();
        }
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
