package com.minkeun.start.springboot.web;

import com.minkeun.start.springboot.config.auth.LoginUser;
import com.minkeun.start.springboot.config.auth.dto.SessionUser;
import com.minkeun.start.springboot.service.PostsService;
import com.minkeun.start.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.HttpSessionRequiredException;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    //private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { //Model=서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음.

        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
