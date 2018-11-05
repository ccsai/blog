package com.chenchuan.admin.test.controller;

import com.chenchuan.admin.test.vo.VisitedVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VisitedController {

    @RequestMapping("/getVisitedSuccessNum")
    public String getVisitedSuccessNum(Model model){
        model.addAttribute("VisitedSuccessNum",VisitedVo.getVisitedSuccess());
        return "/admin/test/visited";
    }
}
