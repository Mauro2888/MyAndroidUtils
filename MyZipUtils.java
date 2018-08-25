 
 
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

public static void readAndCopy(InputStream inputStream) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("C:\\testZip\\ZML_zipped.zip"));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        writeZipWithXml(zipEntry.getName(), zipOutputStream);
        zipOutputStream.closeEntry();
        zipOutputStream.close();


    }

public static void writeZipWithXml(String xmlNameFile, ZipOutputStream zipStream)
           throws IOException {

        File aFile = new File(xmlNameFile);
        FileInputStream inputStream = new FileInputStream(aFile);
        ZipEntry zipEntry = new ZipEntry(xmlNameFile);
        zipStream.putNextEntry(zipEntry);

        byte[] buffer = new byte[inputStream.available()];
        int length;
        while ((length = inputStream.read(buffer)) >= 0) {
            zipStream.write(buffer, 0, length);
        }

        zipStream.closeEntry();
        inputStream.close();

}
    
    
