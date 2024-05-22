package com.cocoon.cop.paypay;

import jp.ne.paypay.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PayPayClientTest {

    @Test
    void test1() {
        PayPayClient payPayClient = new PayPayClient();
        try {
            payPayClient.buildClient();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

}