package com.meals.mealsapp.web;

import com.meals.mealsapp.entity.User;
import com.meals.mealsapp.service.AuthService;
import com.meals.mealsapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String getOfferForm(Model model, HttpServletRequest request) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("user") @Valid User user, final BindingResult binding, RedirectAttributes redirectAttributes) {
        if (binding.hasErrors()) {
            return "register";
        }
        try {
            user.setRoles("USER");
            userService.addUser(user);
        } catch (Exception ex) {
            log.error("Error registering user", ex);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "user", binding);
            return "redirect:register";
        }

        return "redirect:login";
    }

    @PostMapping("/admin/register")
    public String createNewUser(@ModelAttribute("user") @Valid User user, final BindingResult binding, RedirectAttributes redirectAttributes) {
        if (binding.hasErrors()) {
            return "register";
        }
        try {
            userService.addUser(user);
        } catch (Exception ex) {
            log.error("Error registering user", ex);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "user", binding);
            return "redirect:register";
        }

        return "meals";
    }

    @GetMapping("/admin/register")
    public String adminRegisterPage(Model model, HttpServletRequest request) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "admin-register";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @ModelAttribute("redirectUrl") String redirectUrl,
                        RedirectAttributes redirectAttributes, HttpSession session) {
        User loggedUser = authService.login(username, password);

        if (loggedUser == null) {
            String errors = "Invalid username or password.";
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:login";
        } else {
            session.setAttribute("user", loggedUser);
            if (redirectUrl != null && redirectUrl.length() > 0) {
                return "redirect:" + redirectUrl;
            } else {
                return "redirect:/";
            }
        }
    }

    @GetMapping("/error-handler")
    public String getErrorHandler() {
        return "error-handler";
    }
}
