public boolean permResutl(Context context, String[] arrayPerm){
    int req = ContextCompat.checkSelfPermission(context,arrayPerm[0]);
    return req == PackageManager.PERMISSION_GRANTED;
}

public void getBytesFromFile(InputStream input, OutputStream output) throws IOException {
    byte[] bufferDati = new byte[1024];
    int readData;
    while ((readData = input.read(bufferDati))!= -1){
        output.write(bufferDati,0,readData);
    }if (output!= null){
        output.close();
    }
}
