package com.shopweb.servlet.client;

import com.shopweb.beans.User;
import com.shopweb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmailVerificationServlet", value = "/verify-email")
public class EmailVerificationServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/verifyEmailView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("verificationCode");
        User pendingUser = (User) request.getSession().getAttribute("pendingUser");

        if (pendingUser != null && pendingUser.getVerificationCode().equals(code)) {
            pendingUser.setVerified(true);
            userService.update(pendingUser);
            request.getSession().removeAttribute("pendingUser");
            response.sendRedirect(request.getContextPath() + "/signin");
        } else {
            request.setAttribute("error", "Mã xác thực không đúng!");
            request.getRequestDispatcher("/WEB-INF/views/verifyEmailView.jsp").forward(request, response);
        }
    }
}
