package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct // 스프링빈이 다 올라오면, 실행
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            Book book3 = createBook("JPA3 BOOK", 30000, 100);
            em.persist(book3);

            Book book4 = createBook("JPA4 BOOK", 50000, 100);
            em.persist(book4);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            OrderItem orderItem3 = OrderItem.createOrderItem(book3, 30000, 3);
            OrderItem orderItem4 = OrderItem.createOrderItem(book4, 40000, 4);
            Order order = Order.createOrder(member, createDelivery(member),
                    orderItem1, orderItem2, orderItem3, orderItem4);
            em.persist(order);
        }
        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);
            Book book1 = createBook("SPRING1 BOOK", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("SPRING2 BOOK", 20000, 200);
            em.persist(book2);
            Book book3 = createBook("SPRING3 BOOK", 30000, 300);
            em.persist(book3);
            Book book4 = createBook("SPRING4 BOOK", 40000, 400);
            em.persist(book4);
            Delivery delivery = createDelivery(member);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            OrderItem orderItem3 = OrderItem.createOrderItem(book2, 30000, 3);
            OrderItem orderItem4 = OrderItem.createOrderItem(book2, 40000, 4);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2,orderItem3,orderItem4);

            em.persist(order);
        }

            private Member createMember(String name, String city, String street,
                    String zipcode) {
                Member member = new Member();
                member.setName(name);
                member.setAddress(new Address(city, street, zipcode));
                return member;
            }
            private Book createBook(String name, int price, int stockQuantity) {
                Book book = new Book();
                book.setName(name);
                book.setPrice(price);
                book.setStockQuantity(stockQuantity);
                return book;
            }
            private Delivery createDelivery(Member member) {
                Delivery delivery = new Delivery();
                delivery.setAddress(member.getAddress());
                return delivery;
            }
        }
    }

