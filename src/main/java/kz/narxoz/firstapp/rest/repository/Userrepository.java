package kz.narxoz.firstapp.rest.repository;

import kz.narxoz.firstapp.rest.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Userrepository extends JpaRepository<User, Long> {
    //JPA Methots
    List<User> findByNameAndSurname(String name, String surname);
    List<User> findByEmailContainingOrderBySurname(String email);
    List<User> findTop2ByNameStartsWith(String name);
    List<User> findBySurnameStartsWith(String name);
    List<User> findByIdOrderById(Long id);



//    Native Query

    @Query(value="select * from users order by surname asc", nativeQuery = true)
    List<User> findUserByCustomQuery();

    @Query(value="select * from users where id > :qid", nativeQuery = true)
    List<User> findGreaterId(long qid);

    @Query(value="select * from users order by id desc limit 2", nativeQuery = true)
    List<User> findLastInsertedId() ;

    //6-task
    @Query(value = "select * from users order by name desc", nativeQuery = true)
    List<User> findUsersOrderByNameDesc();

    //7-task
    List<User> findByEmailNotContaining(String email);

    //8-task
    @Query(value = "select * from users where name=surname", nativeQuery = true)
    List<User> findByNameEqualsSurname();

    //9-task
    @Query(value = "select * from users where email like '%narxoz.kz' or email like '%gmail.com' or email like '%gmail.com'", nativeQuery = true)
    List<User> findByEmailContains();

    //10-task
    @Query(value = "select distinct +on (name) * from users", nativeQuery = true)
    List<User> findDistinctByName();
}


