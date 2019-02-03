package com.camel.route;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CamelRouteTestMock {

    @Autowired
    CamelContext context;

    @Autowired
    Environment environmentCall;

    @EndpointInject(uri = "mock:file:C:/test/output")
    private MockEndpoint mockEndpoint;

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    public void testMoveFileMock() throws InterruptedException {
        String file = "xmlfile";
        
        mockEndpoint.expectedMessageCount(1);
        mockEndpoint.expectedBodiesReceived(file);
        producerTemplate.sendBody(environmentCall.getProperty("fromRoute"),file);

        mockEndpoint.assertIsSatisfied();
    }
}
