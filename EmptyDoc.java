 private Processor blankFile = new Processor() {

              @Override
              public void process(Exchange exchange) throws Exception {
                     boolean amountFlag = true;
                     String strMessage = exchange.getIn().getBody(String.class);
                     DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                     InputSource src = new InputSource();
                     src.setCharacterStream(new StringReader(strMessage));	
                     Document doc = builder.parse(src);	
                     String XPATH_EXPRESSION = "//*[local-name()='TAG']/path/path";
                     XPath xPath = XPathFactory.newInstance().newXPath();
                     if(!strMessage.contains("lineItem"))	
                     {	
                            String settleAmount = xPath.evaluate(XPATH_EXPRESSION,doc).toString();       	
                            amountFlag = Float.parseFloat(settleAmount)!=0;	
                     }	
                  
                     exchange.getIn().setHeader("flagValue", String.valueOf(amountFlag));	
              }	
       };
