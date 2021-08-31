package org.zerock.guestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.repository.GuestbookRepository;

import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository guestbookRepository;
    //의존성 주입이 필요한 필드를 final로 선언하여 불변하게 사용, 순환 참조 방지

    //setter(@AutoWired)를 사용할 경우 생성자보다 생성 시점이 불분명하여 생성자 사용
    //하지만 생성자를 통하여 스프링빈을 생성할 때 순환참조 발생, 이를 막기 위해 final 선언
    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO.............");
        log.info(dto);
        Guestbook entity = dtoToEntity(dto);
        log.info(entity);
        guestbookRepository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO dto) {
        //화면에 페이지 처리와 필요한 값 생성
        Pageable pageable = dto.getPageable(Sort.by("gno").descending());
        //JPA처리 결과인 Page<Entity> 객체 생성
        Page<Guestbook> result = guestbookRepository.findAll(pageable);
        //JPA로 부터 처리된 결과에 Entity를 DTO로 변형하는 처리 부분
        Function<Guestbook, GuestbookDTO> fn = entity-> entityToDto(entity);
        //위에서 만든 두가지를 PageResultDTO에 넣으면 fn에 정의된대로 변환해서 결과 return
        return new PageResultDTO<>(result,fn);
    }
}
