package com.apple.shopExample.notice;

import com.apple.shopExample.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface noticeRepository extends JpaRepository<Item, Long> {
}
