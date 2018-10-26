 public static void listFiles() throws IOException {
        File files = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File[] listFiles = files.listFiles();
        for (File directory : listFiles){
            if (directory.isDirectory()){
                Log.d(TAG,"start zip");
                ZipUtil.pack(new File(directory.getAbsolutePath()), new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "\\images.zip"));
                Log.d(TAG,"end zip");
            }
        }
    }
