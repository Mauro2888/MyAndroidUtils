public class CopyFilesJava {

    public static void main(String[] args) throws IOException {
    //noop=true options for copy
    
    
    /**Classe copia file con e senza route camel
        from(file:/data/input?noop=true)
        .to(file:/data/output);
         **/
    
    
        File input = new File("/data/input");
        File output = new File("/data/output");
        
        File[] files = input.listFiles();
        for (File source :files) {
            if (source.isFile()){
                FileOutputStream fileOutputStream = new FileOutputStream(output.getPath() + source.getName());
                FileInputStream fileInputStream = new FileInputStream(source);
                byte[] buffer = new byte[(int) source.length()];
                fileInputStream.read(buffer);

                try {
                    fileOutputStream.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    fileOutputStream.close();
                    fileInputStream.close();
                }

            }
        }

    }
}
