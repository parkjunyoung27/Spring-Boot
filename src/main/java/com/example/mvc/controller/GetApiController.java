package com.example.mvc.controller;

import com.example.mvc.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping( path= "/hello")
    public String getHEllo(){
        return "get Hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET) //http:/localhost:9090/api/get/hi
    public String hi(){
        return "hi";
    }

    //**중요** //주소에 {name}과 어노테이션 name 이름이 같아야 한다.
    @GetMapping("/path-variable/{name}") //http://localhost:8080/api/get/path-variable/{name}
    public String pathVariable(@PathVariable String name){
   // public String pathVariable(@PathVariable(name = "name") String pathName){ //다른이름으로 저장하고 싶을 때 사용
        System.out.println("PathVariable : " + name);
        return name;
    }

//search?q=%EC%9D%B8%ED%85%94%EB%A6%AC%EC%A0%9C%EC%9D%B4&oq=%EC%9D%B8%ED%85%94%EB%A6%AC%EC%A0%9C%EC%9D%B4
// &aqs=chrome..69i57j0i512l9.1066j0j15
// &sourceid=chrome
// &ie=UTF-8
// 쿼리 파라미터 = ? 부터 시작하는 검색하는 주소
//처음에는 물음표가 들어가야 한다.

   //http://localhost:9090/api/get/quert-param?user=steve&email=steve@gamil.com@age=30
    //물음표 키 벨류 값
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String > queryParam){
        //RequestParam *중요*
        //디버깅하는법 **중요**
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = " + entry.getValue() +"\n");
        });

        return sb.toString();
    }

    @GetMapping("query-param02")
    public String queryParam02( // 변수 세개 전부 들어가야됨, //세가지 유형 틀리게 넣으면 오류남
                                // 400대 에러는 클라이언트 실수
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);
        return name + " "+email+ " "+ age;
    }

    //현업에서 가장 많이쓰는 dto로 받는 방식 //**추천**
    @GetMapping("query-param03")
    public String queryParam03( UserRequest userRequest){
        //물음표 뒤에 주소들은 스프링부트에서 판단해 해당객체에서 변수와 이름 매칭
        //UserRequest{name='steve', email='steve@gmail.com', age=10}
        //http://localhost:8080/api/get/query-param03?name=steve&email=steve@gmail.com&age=10&address=서울시
        //dto에 있는 변수들만 맵핑됨

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }


}











