PDDocument document = PDDocument.load(new File("test.pdf"));
if (!document.isEncrypted()) {
    PDFTextStripper stripper = new PDFTextStripper();
    String text = stripper.getText(document);
    System.out.println("Text:" + text);
}
document.close();
