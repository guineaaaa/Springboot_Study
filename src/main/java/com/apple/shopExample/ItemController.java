package com.apple.shopExample;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model){
        List<Item> result=itemRepository.findAll(); //itemobject 형태로 모든행가져오기
        model.addAttribute("items",result);

        /* just object 출력이여서 불편하다
        var a=new Item();
        System.out.println(a.toString());
        System.out.println(a);
        */
        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title,String price){
        System.out.println(title);
        System.out.println(price);

        Item item=new Item(title, price);
        itemRepository.save(item);
        return "redirect:/list";
    }
}
