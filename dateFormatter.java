    static void dataChange() throws ParseException {
        String strDate = "2019-06-06T13:20:24+0000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date originalConvertedDate = dateFormat.parse(strDate);
        SimpleDateFormat newDataFormat = new SimpleDateFormat("ddMMMYYYY");
        String finalDateString = newDataFormat.format(originalConvertedDate);

        String day = StringUtils.substring(finalDateString,0,2);
        String capitalize = StringUtils.capitalize(StringUtils.substring(finalDateString,2,finalDateString.length()));
        String finalString = StringUtils.join(day,capitalize);
        System.out.println(finalString);
    }

    static String month(String dateString){
        String month = null;
        Pattern pattern = Pattern.compile("[a-zA-Z]{3}");
        Matcher matcher = pattern.matcher(dateString);
        while (matcher.find()){
            month = StringUtils.capitalize(matcher.group());
        }
        return month;
    }
