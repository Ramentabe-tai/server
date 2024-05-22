package com.cocoon.cop.controller;

import com.cocoon.cop.domain.QSteamApi;
import com.cocoon.cop.domain.SteamApi;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

import static com.cocoon.cop.domain.QSteamApi.*;


@SpringBootTest
@Transactional
class WebClientControllerTest {


    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    EntityManager em;



    @Test
    public void test() {
        URI uri = URI.create("ftpsas:/ㅁㄴㅇㅁㅎㅈㄷlocalhost:8080ㄴㅇ");
        String string = uri.toString();
        System.out.println("string = " + string);

    }

    @Test
    public void steamGameDataResetTest() {
        List<SteamApi> result = jpaQueryFactory
                .selectFrom(steamApi)
                .fetch();

        System.out.println("result = " + result);
    }

}