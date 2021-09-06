package org.zerock.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page, size;
    private String type, keyword;

    public PageRequestDTO(){
        page = 1;
        size = 10;
    }
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page-1, size, sort);
    }


}
