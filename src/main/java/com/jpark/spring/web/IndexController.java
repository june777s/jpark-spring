package com.jpark.spring.web;

import com.jpark.spring.service.posts.PostsService;
import com.jpark.spring.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/") //-> @RequestMapping(value="/", method = RequestMethod.GET) 과 동일
    public String index(Model model)
    {
        model.addAttribute("posts",postsService.findAllDesc());
        //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
        return "index";
        //머스테치 스타터 덕분에 index를 반환할때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정
        // 예를들어서 경로가 src/main/resources/templates/index.mustache 인데
        // 여기서 앞 경로 : src/main/resources/templates/ 와
        //      뒤 확장자 : mustache를 자동으로 지정해준다는 말
        // 그래서 index 반환은 src/main/resources/templates/index.mustache 이것과 동일하게 전환되어
        // ViewResolver가 처리하게 됨
        // ViewResolver는 컨트롤러단에서 사용자가 알려준 View name을 이용하여 실제로 사용하게 될 View 객체를 결정하는 역할을 한다.
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        //model.addAttribute("변수이름", "변수에 넣을 데이터값");
        return "posts-update";
    }


}
