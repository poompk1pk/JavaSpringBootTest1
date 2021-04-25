package com.training.platform.controllers;
import com.training.platform.entities.User;
import com.training.platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


@RestController
@RequestMapping("/api")
public class DemoController {

    @RequestMapping(value = "bad")
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String badUrl() {
        return "Bad request";
    }
    @RequestMapping(value = "bad2")
    public String badUrl2(HttpServletResponse respone){
        respone.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "Bad request by HttpServletRespone";
    }

    @RequestMapping(value = "bad3")
    public String badUrl2(@RequestParam int code, HttpServletResponse respone){
        if(code == 400) {
            respone.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad request by HttpServletRespone";
        } else {
            respone.setStatus(200);
            return "OK by HttpServletRespone";
        }


    }
    /*

CONTINUE 100
SWITCHING_PROTOCOLS 101
PROCESSING 102
CHECKPOINT 103
OK 200
CREATED 201
ACCEPTED 202
NON_AUTHORITATIVE_INFORMATION 203
NO_CONTENT 204
RESET_CONTENT 205
PARTIAL_CONTENT 206
MULTI_STATUS 207
ALREADY_REPORTED 208
IM_USED 226
MULTIPLE_CHOICES 300
MOVED_PERMANENTLY 301
FOUND 302
MOVED_TEMPORARILY 302
SEE_OTHER 303
NOT_MODIFIED 304
USE_PROXY 305
TEMPORARY_REDIRECT 307
PERMANENT_REDIRECT 308
BAD_REQUEST 400
UNAUTHORIZED 401
PAYMENT_REQUIRED 402
FORBIDDEN 403
NOT_FOUND 404
METHOD_NOT_ALLOWED 405
NOT_ACCEPTABLE 406
PROXY_AUTHENTICATION_REQUIRED 407
REQUEST_TIMEOUT 408
CONFLICT 409
GONE 410
LENGTH_REQUIRED 411
PRECONDITION_FAILED 412
PAYLOAD_TOO_LARGE 413
REQUEST_ENTITY_TOO_LARGE 413
URI_TOO_LONG 414
REQUEST_URI_TOO_LONG 414
UNSUPPORTED_MEDIA_TYPE 415
REQUESTED_RANGE_NOT_SATISFIABLE 416
EXPECTATION_FAILED 417
I_AM_A_TEAPOT 418
INSUFFICIENT_SPACE_ON_RESOURCE 419
METHOD_FAILURE 420
DESTINATION_LOCKED 421
UNPROCESSABLE_ENTITY 422
LOCKED 423
FAILED_DEPENDENCY 424
TOO_EARLY 425
UPGRADE_REQUIRED 426
PRECONDITION_REQUIRED 428
TOO_MANY_REQUESTS 429
REQUEST_HEADER_FIELDS_TOO_LARGE 431
UNAVAILABLE_FOR_LEGAL_REASONS 451
INTERNAL_SERVER_ERROR 500
NOT_IMPLEMENTED 501
BAD_GATEWAY 502
SERVICE_UNAVAILABLE 503
GATEWAY_TIMEOUT 504
HTTP_VERSION_NOT_SUPPORTED 505
VARIANT_ALSO_NEGOTIATES 506
INSUFFICIENT_STORAGE 507
LOOP_DETECTED 508
BANDWIDTH_LIMIT_EXCEEDED 509
NOT_EXTENDED 510
NETWORK_AUTHENTICATION_REQUIRED 511
*/
    @RequestMapping(value = "response")
    public String responeCode(@RequestParam int code, HttpServletResponse response){

        response.setStatus(code);

        return code+ " "+HttpStatus.valueOf(code).name();
    }



    @PostMapping(value = "")
    public String create(@RequestBody Map<String,Object> inputs) {
        System.out.println("########### POST Param ###########");
        System.out.println(inputs);

        return "Method POST, Function : create => INSERT data to DB";
    }

    @GetMapping(value = "/{id}")
    public String showWithPath(@PathVariable String id) {
        return "Method Get, Function : show, ID : "+ id +" => SHOW data by id on page with path";
    }

    @PatchMapping(value = "/{id}")
    public String update(@PathVariable String id, @RequestParam Map<String,Object> inputs) {
        System.out.println("########### PATCH Param ###########");
        System.out.println(inputs);

        return "Method PATCH, Function : update => ID : " + id + "UPDATE data to DB";
    }

    @DeleteMapping(value = "/{id}")
    public String destroy(@PathVariable String id)  {
        return "Method DELETE, Function : delete, ID : " + id + " => DELETE data to DB";
    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "restfindall")
    public List<User> findAllObject() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        return users;
    }

    @RequestMapping(value = "user")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        return users;
    }

    @GetMapping(value = "find/{id}")
    public User findOneUserById(@PathVariable int id) {
        Optional<User> users = userRepository.findById(id);
        System.out.println(users);
        return users.get();
    }
    @GetMapping(value = "find")
    public User findOneUserByIdQuery(@RequestParam int id) {
        Optional<User> users = userRepository.findById(id);
        System.out.println(users);
        return users.get();
    }
    @GetMapping(value = "findinfo") // demo/findinfo?city=nakornpathom&active=1&age=18
    public List<User> findUserByIdQuery(@RequestParam String city, @RequestParam int active, @RequestParam int age) {
        List<User> users = userRepository.findByCityAndActiveAndAge(city, active, age);
        System.out.println(users);
        return users;
    }

    @GetMapping(value = "findagein")
    public List<User> findAgeIn() {
        List<Integer> ages = new ArrayList<Integer>(Arrays.asList(18, 19,22));
        List<User> users = userRepository.findByAgeIn(ages);
        System.out.println(users);
        return users;
    }

    @GetMapping(value = "/findquery")
    public List<User> findQuery() {
        return userRepository.findAllByQuery();
    }
    // Example for findAllByParamsQuery
    @GetMapping(value = "/findparamsquery") // findparamsquery?active=1&city=nakornpathom
    public List<User> paramsQuery(@RequestParam int active, @RequestParam String city) {
        return userRepository.findAllByParamsQuery(active, city);
    }


    // Example for findAllByJpqlQuery
    @GetMapping(value = "/findalljpqlquery")
    public List<User> jpqlquery1() {
        return userRepository.findAllByJpqlQuery();
    }

    // Example for findAllByJpqlParamsQuery
    @GetMapping(value = "/findallbyjpqlparamsquery")
    public List<User> jpqlparamsquery2() {
        return userRepository.findAllByJpqlParamsQuery(0, "bangkok");
    }


}

