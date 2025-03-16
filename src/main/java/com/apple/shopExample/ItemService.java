package com.apple.shopExample;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title,String price){
        if(Integer.parseInt(price)<0){
            // throw new RuntimeException("음수 불가");
            // 순수한 Exception()은 문법상 try, catch를 강제하기 때문에 일부로 에러 처리발생 시킬때는 RuntimeException()을 주로 사용
            // unchecked Excpetion
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"404에러남");
        }
        Item item=new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
}
