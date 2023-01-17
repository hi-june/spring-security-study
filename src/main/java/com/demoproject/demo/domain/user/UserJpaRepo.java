package com.demoproject.demo.domain.user;

import com.demoproject.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DB에서 User를 CRUD(생성, 조회, 수정, 삭제) 하기 위해 메서드를 만들어야 한다.
 *
 * 원래라면 UserRepository를 @Repository를 달아서 클래스를 만들고
 * 사용되는 메서드(findById, findByName, findAll, save 등)을 인터페이스에 선언하고
 * 이를 implements하여 오버라이드로 구현해 사용하는 전략이 일반적이다.
 *
 * 하지만 JpaRepository 라는 Spring Data Jpa를 사용하면 일반적으로 통용되는 DAO에 대한 CRUD 메서드를 전부 구현해놨기 때문에 상속해서 사용하면 된다.
 */
@Repository
public interface UserJpaRepo extends JpaRepository<User, Long> {    // JapRepository<Entitiy class 명, primary key TYPE>
    // Entity의 필드값으로 조회 메서드를 추가하고 싶다면
    // <반환타입> findBy{변수명} (<타입> 인자명) 을 선언만 해주면 알아서 JPA가 구현해 준다.
    User findByName(String name);
    User findByEmail(String email);
}
