package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;

import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired//스프링의 Bean으로 등록하여 인스턴스 자동 생성
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println("testClass::" + memoRepository.getClass().getName());//interface이지만 스스로 객체 생성, Proxy
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> { //100개의 새로운 행 삽입
            Memo memo = Memo.builder().memoText("Sample..." + i).build();//
            memoRepository.save(memo);
        });
    }
}