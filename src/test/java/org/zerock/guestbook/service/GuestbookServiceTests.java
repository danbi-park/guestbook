package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookServiceTests {
    @Autowired
    GuestbookService guestbookService;

//    @Test
//    void register() {
//        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
//                .title("title on GuestbookService")
//                .content("content on GuestbookService")
//                .writer("writer on GuestbookService").build();
//        System.out.println("gno::>>>"+guestbookService.register(guestbookDTO));
//    }

//    @Test
//    void testList() {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                        .page(1).size(10).build();
//        PageResultDTO<GuestbookDTO, Guestbook> requestDTO =
//                guestbookService.getList(pageRequestDTO);
//        for(GuestbookDTO dto: requestDTO.getDtoList()){
//            System.out.println(dto);
//        }
//    }

//    @Test
//    public void testPageNumberList(){
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
//        PageResultDTO<GuestbookDTO, Guestbook> resultDTO =
//                guestbookService.getList(pageRequestDTO);
//        System.out.println("prev ::" + resultDTO.isPrev()); //boolean type get -> is
//        System.out.println("next ::" + resultDTO.isNext());
//        System.out.println("total ::" + resultDTO.getTotalPage());
//        System.out.println("------------------------------------");
//        for (GuestbookDTO dto : resultDTO.getDtoList()){
//            System.out.println(dto);
//        }
//        System.out.println("------------------------------------");
//        resultDTO.getPageList().forEach(i ->System.out.println(i));
//    }

    @Test
    public void testSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc") // 검색조건 t,c,w,tc,tcw
                .keyword("한글")
                .build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = guestbookService.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("---------------------------------------");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        System.out.println("=======================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }


}