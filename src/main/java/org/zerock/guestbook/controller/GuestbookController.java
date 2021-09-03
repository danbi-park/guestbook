package org.zerock.guestbook.controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.entity.QGuestbook;
import org.zerock.guestbook.service.GuestbookService;


@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor //url로 요청될 때
public class GuestbookController {

    private final GuestbookService service;

    @GetMapping({"/",""})
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    //void, 요청된 url과 똑같은 경로의 resource로 매칭
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list.......");
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
    }


    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("registerPost...");
        Long gno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno);
        redirectAttributes.addFlashAttribute("noti","등록");
        return "redirect:/guestbook/list";
    }


    @GetMapping({"/read","/modify"})
    public void read(Long gno, Model model,
                     @ModelAttribute("requestDTO") PageRequestDTO requestDTO) {
        GuestbookDTO dto = service.read(gno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto, RedirectAttributes redirectAttributes,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO){
        service.modify(dto); //save되서 덮어씀
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("gno", dto.getGno()); //글번호도 넘겨줌, flash 안씀

        return "redirect:/guestbook/read";
    }

    @PostMapping("/remove")                             //only one time
    public String remove(Long gno, RedirectAttributes redirectAttributes) {
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg",gno);
        redirectAttributes.addFlashAttribute("noti","삭제");
        return "redirect:/guestbook/list";
    }



}


