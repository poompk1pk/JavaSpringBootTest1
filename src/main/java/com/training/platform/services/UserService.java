package com.training.platform.services;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.training.platform.entities.User;
import org.springframework.data.domain.PageRequest;

public interface UserService {

    List<User> findAll();
    Map<String,String> getCities();
    Page<User> findAll(PageRequest pageRequest);

    Optional<User> findById(Integer id);

    Page<User> findAllByLimit(Integer start, Integer limit, String field);

    List<User> findByCityAndActiveAndAge(String city, Integer active, Integer age);

    List<User> findByAgeIn(String ages);
    List<User> findByAgeIn(List<Integer> ages);
    List<User> findAllByQuery();

    List<User> findAllByParamsQuery(Integer active, String city);

    List<User> findAllByJpqlQuery();
    User save(Map<String,String> inputs) throws Exception;
    List<User> findAllByJpqlParamsQuery(Integer active, String city);

    boolean isEmailAlreadyInUse(String email);

    User update(Optional<User> user, Map<String,String> inputs) throws Exception;

    void deleteById(Integer id) throws Exception;

}
