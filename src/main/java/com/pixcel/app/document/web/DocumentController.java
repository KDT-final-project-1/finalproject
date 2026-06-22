package com.pixcel.app.document.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {
	@GetMapping("/documentList")
    public String accordion() {
        return "sample/documentList";
    }

}
