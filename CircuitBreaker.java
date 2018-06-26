//Processor

public class MyExceptionClass implements Processor{
    Logger Logger = LoggerFactory.getLogger(MyExceptionClass.class);
    @Override
    public void process(Exchange exchange) throws Exception {   
        throw new MyException("Other Exceptions");
    }
}

//Custom Exception
public class MyException extends Exception{

    public MyException() {
    super();
    }

    public MyException(String message){
        super("My Exception : "+ message);
    }
}

// Dopo 2 tentativi entra in Open State, dopo 5 secondi riprova

from("direct:start")
	.loadBalance()
	.circuitBreaker(2, 5000L, MyException.class).	
to("endpoint");
