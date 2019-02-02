package com.example.springboot.demospringboot.web;

import com.example.springboot.demospringboot.model.PwdReset;
import com.example.springboot.demospringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PwdWebController {
    
    @Autowired
    private CustomerRepository customerRepository;

    
    @GetMapping("/page/public/pwd-result")
    public String getResetPwdResult(Model model) {
        return "reset-pwd-result";
    }
    
    
    @GetMapping("/page/public/pwd")
    public String getResetPwd(Model model) {
        model.addAttribute("pwdreset", new PwdReset());
        return "reset-pwd";
    }
    


    
    @PostMapping("/page/public/pwd")
    public RedirectView postResetPwd(RedirectAttributes attributes, @ModelAttribute PwdReset pwdReset) {
        attributes.addAttribute("success", "success");
        return new RedirectView("pwd-result");
    }
    
    
    
    
    
    /* @GetMapping("/redirectWithRedirectView")
    public RedirectView redirectWithUsingRedirectView(
      RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("redirectedUrl");
    }*/
    
    
        
    /*@PostMapping("/page/public/pwd")
    public ModelAndView postResetPwd(ModelMap model, RedirectAttributes attributes,  @ModelAttribute PwdReset pwdReset) {
        //model.addAttribute("pwdreset", pwdReset);
        //attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        //attributes.addAttribute("success", "success");
        //attributes.addAttribute("pwdreset", pwdReset);
        model.addAttribute("success", "success");
        attributes.addAttribute("success", "success");
        //model.addAttribute("pwdreset", pwdReset);
        return new ModelAndView("reset-pwd-result", model);
    }*/
    

}