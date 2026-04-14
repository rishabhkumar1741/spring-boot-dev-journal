package com.example.Week1Introduction.Week_1_.Introduction.post;

import com.example.Week1Introduction.Week_1_.Introduction.api.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class postController {

    @GetMapping
    public ApiResponse alllpost(){
        return ApiResponse.success("data","All Post");
    }

}
