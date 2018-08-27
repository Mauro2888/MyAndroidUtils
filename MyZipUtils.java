 
//with commons 
  ModelDto modelDto = new ModelDto();
  public static void jmsToZip(InputStream inputStream) throws IOException {
        File flowZipped = new File("C:\\zip\\mauro\\out.zip");
        FileUtils.copyToFile(inputStream,flowZipped);
        checkUserName("Mauro");
        connectToSftp(flowZipped,"I3021");
        modelDto.setTmpFile("Mauro");
    }
 
 //copy  zip flow to new zip content, only xml end file 
 public static void copyZipContent() throws IOException {

        ZipFile zipFile = new ZipFile("C:\\testZip\\doc.zip");
        final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("C:\\testZip\\fileCopiato.zip"));
        for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements(); ) {
            ZipEntry entryIn = e.nextElement();
            if (entryIn.getName().contains(".xml")) {
                System.out.println(entryIn.getName());
                ZipEntry zipEntry = new ZipEntry(entryIn.getName());
                zos.putNextEntry(zipEntry);
                InputStream is = zipFile.getInputStream(entryIn);
                byte[] buf = new byte[is.available()];
                int len;
                while ((len = (is.read(buf))) > 0) {
                    zos.write(buf,0,len);
                    zos.write(buf);
                }
            }
            zos.closeEntry();
        }
        zos.close();

    }

/** eseguo
InputStream inputStream = new FileInputStream("C:\\testZip\\doc.zip");
        readAndCopy(inputStream);
*/

 /**
     *  Decode zip flow from jms with .xml extension
     *
     * @param inputStream zip flow from jms
     * @throws IOException file exception
     */

public static void readAndCopy(InputStream inputStream) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("C:\\testZip\\ZML_zipped.zip"));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
       if (zipEntry.getName().contains(".xml")) {
            writeToZipFile(zipEntry.getName(), zipOutputStream);
        }else {
            System.out.println("No Xml file inside zip");
        }
        zipOutputStream.closeEntry();
        zipOutputStream.close();


    }

/**
     * Copy content xml file inside new compressed zip and save it to sftp
     * 
     * @param xmlNameFile xml file extracted from jms zip flow
     * @param zipOutputStream output destination stream 
     * @throws IOException
     */

    public static void writeToZipFile(String xmlNameFile, ZipOutputStream zipOutputStream)
           throws IOException {
//path for file must to be appended to file name 
        File inputXmlFile = new File("C:\\testZip\\" + xmlNameFile);
        FileInputStream inputStream = new FileInputStream(inputXmlFile);
        ZipEntry zipEntry = new ZipEntry(xmlNameFile);
        zipOutputStream.putNextEntry(zipEntry);

        byte[] buffer = new byte[inputStream.available()];
        int length;
        while ((length = inputStream.read(buffer)) >= 0) {
            zipOutputStream.write(buffer, 0, length);
        }

        zipOutputStream.closeEntry();
        inputStream.close();

}
    
    
