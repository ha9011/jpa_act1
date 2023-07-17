package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        // null 이면 생성, 아니면 업데이트
        if( item.getId() == null ){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        Item item = em.find(Item.class, id);
        return item;
    }

    public List<Item> findAll(){
        String query = "SELECT I FROM Item i";
        return em.createQuery(query, Item.class).getResultList();

    }

}
