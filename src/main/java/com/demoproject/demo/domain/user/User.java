package com.demoproject.demo.domain.user;

import com.demoproject.demo.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * DB에서 관리해줄 User 엔티티를 만들고, 해당 엔티티를 담을 Table과 규칙을 정의
 */
@Builder    // 디자인 패턴 중 하나인 빌더패턴방식으로 객체를 생성할 수 있게해준다.
@Entity // @Entity가 붙은 클래스는 JPA가 관리하는 클래스가 되고, 해당 클래스를 엔티티라고 한다. JPA를 이용하여 테이블과 클래스를 매핑할 시 반드시 필요함
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor  // 인자가 있는, 필드의 모든 값을 인자로 갖는 생성자를 만들어줌
@Table(name = "user")   // 엔티티를 테이블과 매핑하기 위해 테이블을 지정해주는 것
public class User extends BaseTimeEntity {
    @Id // primary key임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 값의 생성을 db에 위임
    private Long userId;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;
}
