/*
Project: Cookbook Forum
        * Assignment: 2
        * Author(s): Le Duc Thinh
        * Student Number: 101110291
        * Date: Dec 5th 2021
        * Description: This file is the PDF exporting service interface.
*/
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
