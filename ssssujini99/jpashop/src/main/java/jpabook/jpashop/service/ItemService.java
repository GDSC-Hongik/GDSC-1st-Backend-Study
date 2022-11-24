package jpabook.jpashop.service;


import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); // findItem -> 영속성 상태의 객체 (같은 엔티티를 조회)
        findItem.setPrice(price); // 데이터 수정
        findItem.setName(name); // 데이터 수정
        findItem.setStockQuantity(stockQuantity); // 데이터 수정

        // 방법1: 변경 감지 기능 사용
        //// 영속성 컨텍스트에서 엔티티를 다시 조회한 후에 데이터를 수정하는 방법
                // 트랜젝션 안에서 엔티티를 다시 조회, 변경할 값 선택
                // -> 트랜젝션 커밋 시점에 변경 감지(Dirty Checking)가 동작해서 데이터베이스에 UPDATE SQL 실행
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
