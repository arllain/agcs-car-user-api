package com.arllain.agcscaruserapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arllain
 *
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "<html>\n" + "<header><title>API RESTful para Sistema de Usuários de Carros</title></header>\n" + "<body>\n"
                + "<br> You can find the source code in  <a href=\"https://github.com/arllain/agcs-car-user-api/\">https://github.com/arllain/agcs-car-user-api</a>\n"
                + "</body>\n" + "</html>";
    }

}