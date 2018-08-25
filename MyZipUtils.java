 
 
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
    
    
