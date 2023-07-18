package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import lombok.Lombok;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args
		);
	}

	@Bean
	Hibernate5JakartaModule hibernate5Module() {
		Hibernate5JakartaModule hibernate5JakartaModule = new Hibernate5JakartaModule();

		// 이 방법은 좋치 않다. 성능상 이슈가 있음, 필요없는 부분도 지연로딩을 강제로 하기 때문에..
		// 즉, 엔티티를 외부로 호출했을 경우 문제가됨
		hibernate5JakartaModule.configure(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING,
				true);

		return hibernate5JakartaModule; // 위 설정 없다면, 프록시를 null로 처리
	}

}
