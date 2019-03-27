//Write
IOUtils.write(message, fileOutputStream, "UTF-8");

//Read
FileInputStream fisTargetFile = new FileInputStream(outFile);

String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8");
