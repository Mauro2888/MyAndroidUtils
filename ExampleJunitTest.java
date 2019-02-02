package com.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;

import javax.validation.constraints.AssertTrue;
import java.io.File;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CamelBasicRouteTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    Environment environment;

    String file = "test.xml";
    String fileName = Exchange.FILE_NAME;
    @Test
    public void moveFile() throws InterruptedException {
        producerTemplate.sendBodyAndHeader(environment.getProperty("fromRoute"),file, Exchange.FILE_NAME);

        Thread.sleep(1000);

        File file = new File(environment.getProperty("toRoute")+ fileName);
        asserTrue(file.exists());
        
    }
}

