package com.apple.shopExample;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @GetMapping("/list")
    String list(Model model){
        model.addAttribute("name","홍길동");
        return "list.html";
    }
}
