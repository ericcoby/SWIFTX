
    // src/main/java/com/xenderclone/xender_clone/TestController.java

package com.xenderclone.xender_clone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String home() {
        return "App is running!";
    }
}

