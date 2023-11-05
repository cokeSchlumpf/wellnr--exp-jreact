package com.wellnr.jreact.jreact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping(path = "/", produces = "text/html;charset=UTF-8")
    public String index() {
        var html = new SampleApp();
        return html.render(new SampleState()).toHtml();
    }

}
