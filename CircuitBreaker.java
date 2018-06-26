/*
    	 * After two failures circuit breaker goes into Open State; after five
    	 * seconds will try again. In Open State messages go to DLQ.*/
    	from("direct:saas").
    	log("Sending SAAS HTTP Request").
    	setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.GET)).
		  loadBalance().circuitBreaker(2, 5000L, CircuitBreakerOpenException.class).	
    	to("http4://localhost:8080/event");
