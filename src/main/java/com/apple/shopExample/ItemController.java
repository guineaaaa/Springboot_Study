package com.apple.shopExample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    // 리스트 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<Item> items = itemService.getAllItems();
        // just List<Item>을 리턴하면 템플릿 엔진이 아닌 JSON으로 반환됨
        model.addAttribute("items", items);
        // model.addAtribute를 통해 items데이터를 HTML으로 넘겨주기
        // 즉, HTML 파일에서 items 데이터를 사용할 수 있게 되는 것이다.
        return "list.html";
    }

    // 쓰기 페이지
    @GetMapping("/write")
    public String write() {
        return "write.html";
    }

    // 아이템 추가
    @PostMapping("/add")
    public String addPost(@RequestParam String title, @RequestParam String price) {
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    // 상세 페이지 조회
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("data", item);

        return "detail.html";
    }

    // 테스트용 데이터 출력
    @PostMapping("/test")
    public String add(@RequestParam Map<String, String> formData) {
        System.out.println(formData);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model) {
        Optional<Item> result = itemRepository.findById(id);

        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    public String editItem( String title, String price, Long id) {
        itemService.editItem(id,title,price);

        return "redirect:/list";
    }


}
