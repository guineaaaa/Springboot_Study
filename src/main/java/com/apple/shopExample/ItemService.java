package com.apple.shopExample;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 아이템 저장
    public void saveItem(String title,String price){
        int parsedPrice;
        try{
            parsedPrice = Integer.parseInt(price);
        }catch(NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가격은 숫자로 입력하세요");
        }

        if(parsedPrice < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가격은 음수가 될 수 없습니다");
        }

        Item item=new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    // 전체 아이템 리스트 조회
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
    
    // 개별 아이템 상세 조회
    public Item getItemById(Long id){
        return itemRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 ID의 아이템을 찾을 수 없습니다"));
    }

    // 아이템 수정
    public void editItem(Long id, String title, String price){
        Item item=new Item();

        if(title.length()>1000){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "1000자 이상의 title은 불가능합니다");
        }
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
}
