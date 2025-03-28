package com.apple.shopExample.item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{
    // Page<Item> findPageBy(Pageable page);
    // 성능 향상을 위해 Slice 사용, 전체 행 갯수를 세지 않음
    Page<Item> findPageBy(Pageable page);
}

