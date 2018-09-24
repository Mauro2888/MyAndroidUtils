static void xmlBuilder() throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element root = document.createElement("developers");
        document.appendChild(root);


        Element firstname = document.createElement("nome");
        firstname.appendChild(document.createTextNode("Mauro"));
        root.appendChild(firstname);

        //creo valore
       // Attr attr = document.createAttribute("id");
       // attr.setValue("1");
        //firstname.setAttributeNode(attr);
        
        //creo XML
        TransformerFactory transformerFactoryXML = TransformerFactory.newInstance();
        Transformer transformer = transformerFactoryXML.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult result = new StreamResult(new File("C:\\file.xml"));
        transformer.transform(domSource,result);


        //Leggo

        File file = new File("C:\\file.xml");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String read;
            while((read = reader.readLine())!= null){
                System.out.println(read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
