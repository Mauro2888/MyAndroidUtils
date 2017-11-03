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

public String SubtractHour(String ora){
        SimpleDateFormat mFormatOraFromRecycler = new SimpleDateFormat("HH.mm");
        Date dateRecy = null;
        Date dateNow = Calendar.getInstance().getTime();
        try {
            dateRecy = mFormatOraFromRecycler.parse(ora);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long subtract = dateRecy.getTime() - dateNow.getTime();
        Date formatAllMillisToHour = new Date(subtract - 2 * 60 * 60 * 1000);//converto 2 ore * 60 *60 * 1000 (millis )
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatAllMillisToHour);
        int ore = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
    
        return ore + " " + minute;
    }

public void checkOnlineState() { ConnectivityManager CManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); NetworkInfo NInfo = CManager.getActiveNetworkInfo(); if (NInfo != null && NInfo.isConnectedOrConnecting()) { if (InetAddress.getByName("www.xy.com").isReachable(timeout)) { // host reachable } else { // host not reachable } } return; }

Public void BitmapToInputStreaam(){
    ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
    bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, bos); 
    byte[] bitmapdata = bos.toByteArray();
    ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);


}
    
    public void recreateActivity() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    
     public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns >= 2 ? noOfColumns : 2;
    }
