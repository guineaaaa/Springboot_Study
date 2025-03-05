package com.apple.shopExample;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    /** @Autowired - Lombok 없이 등록 newItemRepository()하나 뽑아서 변수에 넣음
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
     */

    @GetMapping("/list")

    String list(Model model){
        List<Item> result=itemRepository.findAll(); //itemobject 형태로 모든행가져오기
        System.out.println(result.get(0)); // item 클래스의 Object
        System.out.println(result.get(0).price); //첫번째 데이터의 price
        System.out.println(result.get(0).title); //첫번째 데이터의 title

        model.addAttribute("name","비싼 바지");
        return "list.html";
    }
}
