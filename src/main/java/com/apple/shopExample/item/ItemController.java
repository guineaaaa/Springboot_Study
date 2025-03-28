package com.apple.shopExample.item;

import com.apple.shopExample.member.Member;
import com.apple.shopExample.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

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
    public String addPost(@RequestParam String title, @RequestParam String price, Authentication auth) {
        // 로그인한 사용자의 이름을 가져와 Member 조회
        String username=auth.getName();
        Member member=memberRepository.findByUsername(username)
                        .orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없습니다"));

        // item 저장
        itemService.saveItem(title, price,member);
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

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Long id){
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제 완료");
    }

    @GetMapping("/test2")
    String deleteItem(){
        var result=new BCryptPasswordEncoder().encode("문자열");
        System.out.println("해싱결과 "+result);
        /**
         * 1. 같은 문자 해싱하면 항상 같은 결과
         * 2. 원래 문자 추론 불가
         * 3. 비번 해싱 시 salt+hash
         */
        return "redirect:/list";
    }

    @GetMapping("/list/page/{page}")
    public String getListPage(Model model, @PathVariable int page) {

        Page<Item> result=itemRepository.findPageBy(PageRequest.of(page-1,2));

        model.addAttribute("items", result);

        // 페이지 수를 계산
        int totalPages = result.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        return "list.html";
    }
}
