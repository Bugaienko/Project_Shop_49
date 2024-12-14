package ait.cohort49.shop.repository;

import ait.cohort49.shop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Bugaenko
 * {@code @date} 13.12.2024
 */


/*
findBy - для поиска / объекты, списки, потоки, Optional
countBy - для подсчета / long
deleteBy - для удаления / void, long
existsBy - для проверки существования / boolean

+ использовать имена полей наших сущностей
findByLastname - ищет сущности по фамилии
findByAgeGreaterThan - ищет все сущности, у которых возраст больше указанного

And
Or
Between
LessThan, GreaterThan
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
