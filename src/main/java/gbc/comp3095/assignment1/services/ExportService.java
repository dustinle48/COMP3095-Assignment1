package gbc.comp3095.assignment1.services;

import com.itextpdf.text.DocumentException;
import gbc.comp3095.assignment1.models.Ingredient;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public interface ExportService {
    void CartPDFExporter(Set<Ingredient> cart);
    void exportPdf(HttpServletResponse response) throws DocumentException, IOException;
}
