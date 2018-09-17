//if files are overwrited try 
// to("file:xxx?fileExist=Append"); 
// example - from(src).split().method(splitbean which returns an custom Iterator).streaming().to(file:...?fileExist=Append); 
public Iterator<Message> splitMessage(Exchange exchange) { 
 BufferedReader bufferReader = exchange.getIn().getBody(BufferedReader.class); 
 List<Message> messages = new ArrayList<Message>(); 
        String line = null; 
        int count = 0; 
        int fileNameCount = 0; 
        StringBuffer sb = new StringBuffer(); 
        try { 
            while (null != (line = bufferReader.readLine())) { 
                sb.append(line); 
                count++; 

                if (count == 5) { 
                    messages.add(createNewOutput(sb, "Sample"+fileNameCount+".xml")); 
                    count = 0; 
                    sb = new StringBuffer(); 
                    fileNameCount++; 
                } 
            } 

            bufferReader.close(); 
        } catch(Exception ex) { 
            ex.printStackTrace(); 
        } 

        return messages.iterator(); 
    } 

    private Message createNewOutput(StringBuffer sb, String fileName) throws EOFException{ 
        Message message = new DefaultMessage(); 
        message.setBody(sb.toString()); 
        message.setHeader(Exchange.FILE_NAME, fileName); 
        return message; 
    } 
