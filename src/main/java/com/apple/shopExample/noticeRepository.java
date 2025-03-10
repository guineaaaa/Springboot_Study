package com.apple.shopExample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface noticeRepository extends JpaRepository<Item, Long> {
}
