package org.springbootbackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test")
public class TestController {

//    @Autowired
//    private HttpServletRequest context;

    @GetMapping("/public")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('GUEST') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "Guest Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
//        if (context.getCookies()!=null) {
//            iterteAndPrintCookies();
//        }

        return "Admin Board.";
    }

//    private void iterateAndPrintCookies() {
//        for (int i = 0; i < context.getCookies().length; i++) {
//            System.out.println("==" + context.getCookies()[i].getValue());
//        }
//    }
}
