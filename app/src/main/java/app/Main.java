package app;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class Main {

    public static void main(String[] args) {
        try {
            PDDocument document = new PDDocument();
            PDType0Font font = PDType0Font.load(document, new File("AbhayaLibre-Regular.ttf"));

            // Create a new page
            PDPage page = new PDPage();
            // Add the page to the document
            document.addPage(page);

            // Create a new content stream to write to the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set font and font size for text
            contentStream.setFont(font, 12);

            // Begin the text
            contentStream.beginText();

            // Set the position for the text
            contentStream.newLineAtOffset(50, 750); // Adjust the position as needed

            // Add text to the page
            contentStream.showText("UAB \"Vilgla\"");


            // End the text
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(230, 700);
            contentStream.showText("PVM SĄSKAITA FAKTŪRA");
            contentStream.endText();
            contentStream.beginText();
            contentStream.setFont(font, 10);
            contentStream.newLineAtOffset(150, 650);
            contentStream.showText("Serija      A");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(350, 650);
            contentStream.showText("Nr. temp number");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(255, 600);
            contentStream.showText("date");
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(75, 550);
            contentStream.showText("Prekių (paslaugų) tiekėjas");
            contentStream.endText();

            // Close the content stream
            contentStream.close();

            // Save the document
            document.save(new File("invoice_with_new_page.pdf"));

            // Close the document
            document.close();

            System.out.println("New page created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}