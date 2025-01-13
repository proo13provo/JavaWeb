package com.shopweb.servlet.client;

import com.shopweb.beans.User;
import com.shopweb.service.UserService;
import com.shopweb.utils.EmailUtils;
import com.shopweb.utils.HashingUtils;
import com.shopweb.utils.Protector;
import com.shopweb.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import jakarta.mail.MessagingException;
import java.sql.SQLException;


@WebServlet(name = "SignupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/signupView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lưu các parameter (tên-giá trị) vào map values
            Map<String, String> values = new HashMap<>();
            values.put("username", request.getParameter("username"));
            values.put("password", request.getParameter("password"));
            values.put("fullname", request.getParameter("fullname"));
            values.put("email", request.getParameter("email"));
            values.put("phoneNumber", request.getParameter("phoneNumber"));
            values.put("gender", request.getParameter("gender"));
            values.put("address", request.getParameter("address"));
            values.put("policy", request.getParameter("policy"));

            // Kiểm tra các parameter, lưu các vi phạm (nếu có) vào map violations
            Map<String, List<String>> violations = new HashMap<>();
            Optional<User> userFromServer = Protector.of(() -> userService.getByUsername(values.get("username")))
                    .get(Optional::empty);
            violations.put("usernameViolations", Validator.of(values.get("username"))
                    .isNotNullAndEmpty()
                    .isNotBlankAtBothEnds()
                    .isAtMostOfLength(25)
                    .isNotExistent(userFromServer.isPresent(), "Tên đăng nhập")
                    .toList());
            violations.put("passwordViolations", Validator.of(values.get("password"))
                    .isNotNullAndEmpty()
                    .isNotBlankAtBothEnds()
                    .isAtMostOfLength(32)
                    .toList());
            violations.put("fullnameViolations", Validator.of(values.get("fullname"))
                    .isNotNullAndEmpty()
                    .isNotBlankAtBothEnds()
                    .toList());
            violations.put("emailViolations", Validator.of(values.get("email"))
                    .isNotNullAndEmpty()
                    .isNotBlankAtBothEnds()
                    .hasPattern("^[^@]+@[^@]+\\.[^@]+$", "email")
                    .toList());
            violations.put("phoneNumberViolations", Validator.of(values.get("phoneNumber"))
                    .isNotNullAndEmpty()
                    .isNotBlankAtBothEnds()
                    .hasPattern("^\\d{10,11}$", "số điện thoại")
                    .toList());
            violations.put("genderViolations", Validator.of(values.get("gender"))
                    .isNotNull()
                    .toList());
            violations.put("addressViolations", Validator.of(values.get("address"))
                    .isNotNullAndEmpty()
                    .isNotBlankAtBothEnds()
                    .toList());
            violations.put("policyViolations", Validator.of(values.get("policy"))
                    .isNotNull()
                    .toList());

            // Tính tổng các vi phạm sau kiểm tra (nếu có)
            int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
            String successMessage = "Đã đăng ký thành công!";
            String errorMessage = "Đã có lỗi truy vấn!";

            // Khi không có vi phạm trong kiểm tra các parameter
            if (sumOfViolations == 0) {
                String verificationCode = generateVerificationCode();
                
                User user = new User(
                        0L,
                        values.get("username"),
                        HashingUtils.hash(values.get("password")),
                        values.get("fullname"),
                        values.get("email"),
                        values.get("phoneNumber"),
                        Protector.of(() -> Integer.parseInt(values.get("gender"))).get(0),
                        values.get("address"),
                        "CUSTOMER"
                );
                user.setVerified(false);
                user.setVerificationCode(verificationCode);

                try {
                    userService.insert(user);
                    EmailUtils.sendVerificationEmail(user.getEmail(), verificationCode);
                    request.getSession().setAttribute("pendingUser", user);
                    response.sendRedirect(request.getContextPath() + "/verify-email");
                    return;
                } catch (MessagingException e) {
                    // Xử lý lỗi gửi email
                    request.setAttribute("values", values);
                    request.setAttribute("errorMessage", "Không thể gửi email xác thực. Vui lòng thử lại sau.");
                    e.printStackTrace();
                } catch (Exception e) {
                    // Xử lý các lỗi khác
                    request.setAttribute("values", values);
                    String errorDetail = e.getMessage(); // Lấy thông tin chi tiết về lỗi
                    System.out.println("Lỗi chi tiết: " + errorDetail); // In ra console để debug
                    
                    // Thông báo lỗi cụ thể hơn cho người dùng
                    if (e instanceof SQLException) {
                        request.setAttribute("errorMessage", "Lỗi database: Có thể username hoặc email đã tồn tại");
                    } else if (e instanceof NullPointerException) {
                        request.setAttribute("errorMessage", "Lỗi dữ liệu: Thiếu thông tin bắt buộc");
                    } else {
                        request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình đăng ký. Vui lòng thử lại. (" + errorDetail + ")");
                    }
                    e.printStackTrace(); // In stack trace để debug
                }
            }

            request.setAttribute("values", values);
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("/WEB-INF/views/signupView.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}

