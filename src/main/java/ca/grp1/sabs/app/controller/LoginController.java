package ca.grp1.sabs.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// =========================
// LOGIN PAGE (for rendering the login.html template)
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This will render the login.html template
    }
}
