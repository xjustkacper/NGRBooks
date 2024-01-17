package com.ngr.ngrbooks.user.profile.pdf;


import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.ngr.ngrbooks.books.Book;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.*;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

@RequiredArgsConstructor
public class PdfExporter {

    private final List<Book> books;


    private void writeTableHeader(PdfPTable table) {
        Font font = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font.setColor(Color.WHITE);

        PdfPCell cell;

        cell = new PdfPCell(new Paragraph("Autor", font));
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Tytul", font));
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Gatunek", font));
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Rok wydania", font));
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        table.addCell(cell);
    }


    private void writeTableData(PdfPTable table) {
        for (Book book : books) {
            table.addCell(normalizeText(String.valueOf(book.getAuthor())));
            table.addCell(normalizeText(String.valueOf(book.getTitle())));
            table.addCell(normalizeText(String.valueOf(book.getGenre())));
            table.addCell(String.valueOf(book.getReleaseDate()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Ulubione ksiazki", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2f, 4f, 3f, 2f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }

    private String normalizeText(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }



}
