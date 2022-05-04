package site.metacoding.blogv1.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM User WHERE username = :username AND password= :password", nativeQuery = true)
    User mLogin(@Param("username") String username, @Param("password") String password);

    // 중복검사 쿼리문
    @Query(value = "SELECT * FROM User WHERE username =:username", nativeQuery = true)
    User mUsernameSameCheck(@Param("username") String username);
}
