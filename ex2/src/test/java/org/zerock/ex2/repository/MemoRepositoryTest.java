package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;

import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        System.out.println("testClass::"+memoRepository.getClass().getName());
    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i -> { //100개의 새로운 행들이 들어감
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

}