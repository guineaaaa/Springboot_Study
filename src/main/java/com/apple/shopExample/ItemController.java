package com.apple.shopExample;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // user가 form으로 보낸 데이터 출력
    @PostMapping("/add")
    String addPost(String title,String price){
        System.out.println(title);
        System.out.println(price);

        Item item=new Item(title, price);
        itemRepository.save(item);

        return "redirect:/list";
    }

    @PostMapping("/test")
    String add(@RequestParam Map formData){
        System.out.println(formData);
        HashMap<String, String> test=new HashMap<>();
        test.put("와우","hey");
        test.put("어찌라고","공화국");
        System.out.println(test);
        return "redirect:/list";
    }

    @PostMapping("/hey")
    String addHey(String title, String price){
        Item item=new Item();

        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);

        return "redirect:/list";
    }
    //input 데이터들을 바로 object로 변환하려면 @ModelAttribute
    @PostMapping("/skr")
    String addSkr(@ModelAttribute Item item){
        System.out.println(item);
        itemRepository.save(item);
        return "redirect:/list";
    }
}
