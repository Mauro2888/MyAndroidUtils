  for (int i = 0; i < 1000 ; i ++){

          String randomNumbers = String.format("%03d", new Random().nextInt(1000));
          String dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime());
          String outPutFile = FILE_NAME + dateFormat + "_" +  randomNumbers + ".xml";
          String strInputFileName = outPutFile.substring(0, outPutFile.lastIndexOf("."));

          Pattern patternNumber = Pattern.compile("[^_]+$",Pattern.MULTILINE);
          Matcher matcher = patternNumber.matcher(strInputFileName);
          if (matcher.find()){
             String outputFileNumbers = matcher.group();

             ArrayList<Integer> arrayList = new ArrayList<>();
             arrayList.add(Integer.valueOf(outputFileNumbers));

              for (int numeri:arrayList) {
                  File file = new File("C:\\data\\" + "\\pari\\" + strInputFileName);
                  if ((numeri % 2) == 0){
                      try {
                          FileWriter fileWriter = new FileWriter(file);
                          fileWriter.write("");
                          fileWriter.flush();

                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }else {
                      File fileDispari = new File("C:\\data\\" + "\\dispari\\" + strInputFileName);
                      try {
                          FileWriter fileWriter = new FileWriter(fileDispari);
                          fileWriter.write("");
                          fileWriter.flush();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
              }
         }

      }
