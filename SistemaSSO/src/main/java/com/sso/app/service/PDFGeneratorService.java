package com.sso.app.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sso.app.entity.Orden;
import com.sso.app.entity.Recepcion;
import com.sso.app.entity.ItemRecepcion;
import com.sso.app.entity.Cliente;
import com.sso.app.entity.Equipo;
import com.sso.app.entity.TipoEquipo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PDFGeneratorService {

    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    private static final Font SUBTITLE_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
    private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    
    /**
     * Genera un reporte en PDF con la información de una Orden y su Recepción asociada
     * @param orden La orden a incluir en el reporte
     * @return Bytes del PDF generado
     */
    public byte[] generarReporteOrdenRecepcion(Orden orden) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        
        document.open();
        
        // Agregar título del documento
        addTitlePage(document, orden);
        
        // Información de la orden
        addOrdenInformation(document, orden);
        
        // Información de la recepción si existe
        if (orden.getRecepcion() != null) {
            addRecepcionInformation(document, orden.getRecepcion());
        }
        
        document.close();
        
        return outputStream.toByteArray();
    }
    
    private void addTitlePage(Document document, Orden orden) throws DocumentException {
        Paragraph title = new Paragraph("Reporte de Orden y Recepción", TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        
        document.add(title);
        document.add(Chunk.NEWLINE);
        
        // Fecha de generación del reporte
        Paragraph date = new Paragraph("Fecha de generación: " + 
                java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), NORMAL_FONT);
        date.setAlignment(Element.ALIGN_RIGHT);
        
        document.add(date);
        document.add(Chunk.NEWLINE);
    }
    
    private void addOrdenInformation(Document document, Orden orden) throws DocumentException {
        // Sección de información de la orden
        Paragraph ordenTitle = new Paragraph("Información de la Orden", SUBTITLE_FONT);
        ordenTitle.setAlignment(Element.ALIGN_LEFT);
        document.add(ordenTitle);
        document.add(Chunk.NEWLINE);
        
        // Tabla con los detalles de la orden
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        
        // Configurar anchos de columna
        float[] columnWidths = {1f, 3f};
        table.setWidths(columnWidths);
        
        // Agregar encabezados y datos
        addTableRow(table, "ID:", orden.getId() != null ? orden.getId().toString() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(table, "Número OT:", orden.getNumeroOT() != null ? orden.getNumeroOT() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(table, "Fecha:", orden.getFecha() != null ? 
                orden.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : "N/A", BOLD_FONT, NORMAL_FONT);
        
        // Mostrar información del cliente
        Cliente cliente = orden.getCliente();
        if (cliente != null) {
            document.add(table);
            document.add(Chunk.NEWLINE);
            
            // Sección específica para el cliente
            addClienteInformation(document, cliente);
            
            // Crear una nueva tabla para el resto de la información de la orden
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(columnWidths);
        } else {
            addTableRow(table, "Cliente:", "No especificado", BOLD_FONT, NORMAL_FONT);
        }
        
        // Mostrar información del equipo
        Equipo equipo = orden.getEquipo();
        if (equipo != null) {
            document.add(table);
            document.add(Chunk.NEWLINE);
            
            // Sección específica para el equipo
            addEquipoInformation(document, equipo);
            
            // Crear una nueva tabla para el resto de la información de la orden
            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(columnWidths);
        } else {
            addTableRow(table, "Equipo:", "No especificado", BOLD_FONT, NORMAL_FONT);
        }
        
        addTableRow(table, "Etapa Actual:", orden.getEtapaActual() != null ? orden.getEtapaActual() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(table, "Comentario:", orden.getComentario() != null ? orden.getComentario() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(table, "Remito Transporte:", orden.getRemitoTransporte() != null ? orden.getRemitoTransporte() : "N/A", BOLD_FONT, NORMAL_FONT);
        
        document.add(table);
        document.add(Chunk.NEWLINE);
    }
    
    private void addRecepcionInformation(Document document, Recepcion recepcion) throws DocumentException {
        // Sección de información de la recepción
        Paragraph recepcionTitle = new Paragraph("Información de la Recepción", SUBTITLE_FONT);
        recepcionTitle.setAlignment(Element.ALIGN_LEFT);
        document.add(recepcionTitle);
        document.add(Chunk.NEWLINE);
        
        // Comentario general de recepción
        Paragraph comentario = new Paragraph("Comentario: " + 
                (recepcion.getComentario() != null ? recepcion.getComentario() : "N/A"), NORMAL_FONT);
        document.add(comentario);
        document.add(Chunk.NEWLINE);
        
        // Detalles de los items de recepción si existe
        if (recepcion.getItemRecepcion() != null) {
            addItemRecepcionDetails(document, recepcion.getItemRecepcion());
        }
        
        // Información sobre las imágenes
        if (recepcion.getImagenes() != null && !recepcion.getImagenes().isEmpty()) {
            Paragraph imagenesInfo = new Paragraph("Cantidad de imágenes adjuntas: " + 
                    recepcion.getImagenes().size(), NORMAL_FONT);
            document.add(imagenesInfo);
        }
    }
    
    private void addItemRecepcionDetails(Document document, ItemRecepcion item) throws DocumentException {
        // Tabla para los items de recepción
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        
        // Configurar anchos de columna
        float[] columnWidths = {2f, 1f, 3f};
        table.setWidths(columnWidths);
        
        // Encabezados de la tabla
        PdfPCell cell1 = new PdfPCell(new Phrase("Componente", BOLD_FONT));
        PdfPCell cell2 = new PdfPCell(new Phrase("Estado", BOLD_FONT));
        PdfPCell cell3 = new PdfPCell(new Phrase("Observaciones", BOLD_FONT));
        
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        
        // Agregar las filas con los datos de cada componente
        addComponentRow(table, "Cubre Grampa", item.isCgestado(), 
                item.getCgrequerimiento(), item.getCgobservacion());
        
        addComponentRow(table, "Cubre Polea", item.isEstado(), 
                item.getRequerimiento(), item.getObservacion());
        
        addComponentRow(table, "Cubre Vastago", item.isCvestado(), 
                item.getCvrequerimiento(), item.getCvobservacion());
        
        addComponentRow(table, "Grampa Anti-Eyección", item.isGaestado(), 
                item.getGarequerimiento(), item.getGaobservacion());
        
        addComponentRow(table, "Estructura Chasis", item.isEcestado(), 
                item.getEcrequerimiento(), item.getEcobservacion());
        
        addComponentRow(table, "Linterna Separador", item.isLsestado(), 
                item.getLsrequerimiento(), item.getLsobservacion());
        
        addComponentRow(table, "Mesa de Motor", item.isMmestado(), 
                item.getMmrequerimiento(), item.getMmobservacion());
        
        addComponentRow(table, "Rieles de Motor", item.isRmestado(), 
                item.getRmrequerimiento(), item.getRmobservacion());
        
        addComponentRow(table, "Soporte de Transporte", item.isStestado(), 
                item.getStrequerimiento(), item.getStobservacion());
        
        addComponentRow(table, "Polea Conducida", item.isPcestado(), 
                item.getPcrequerimiento(), item.getPcobservacion());
        
        document.add(table);
    }
    
    private void addComponentRow(PdfPTable table, String componentName, boolean estado, 
            String requerimiento, String observacion) {
        
        // Nombre del componente
        table.addCell(new Phrase(componentName, NORMAL_FONT));
        
        // Estado (OK/No OK)
        String estadoText = estado ? "OK" : "No OK";
        table.addCell(new Phrase(estadoText, NORMAL_FONT));
        
        // Observaciones y requerimientos
        String obsText = "";
        if (requerimiento != null && !requerimiento.isEmpty()) {
            obsText += "Req: " + requerimiento + "\n";
        }
        if (observacion != null && !observacion.isEmpty()) {
            obsText += "Obs: " + observacion;
        }
        
        if (obsText.isEmpty()) {
            obsText = "N/A";
        }
        
        table.addCell(new Phrase(obsText, NORMAL_FONT));
    }
    
    /**
     * Agrega una sección con la información detallada del cliente
     */
    private void addClienteInformation(Document document, Cliente cliente) throws DocumentException {
        Paragraph clienteTitle = new Paragraph("Información del Cliente", SUBTITLE_FONT);
        clienteTitle.setAlignment(Element.ALIGN_LEFT);
        document.add(clienteTitle);
        document.add(Chunk.NEWLINE);
        
        PdfPTable clienteTable = new PdfPTable(2);
        clienteTable.setWidthPercentage(100);
        
        // Configurar anchos de columna
        float[] columnWidths = {1f, 3f};
        clienteTable.setWidths(columnWidths);
        
        // Agregar datos del cliente
        addTableRow(clienteTable, "ID:", cliente.getId() != null ? cliente.getId().toString() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(clienteTable, "CUIT:", cliente.getCuit() != null ? cliente.getCuit() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(clienteTable, "Razón Social:", cliente.getRazonSocial() != null ? cliente.getRazonSocial() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(clienteTable, "Nombre Fantasía:", cliente.getNombreFantasia() != null ? cliente.getNombreFantasia() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(clienteTable, "Área:", cliente.getArea() != null ? cliente.getArea() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(clienteTable, "Contacto:", cliente.getNombreContacto() != null ? cliente.getNombreContacto() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(clienteTable, "Teléfono:", cliente.getTelefono() != null ? cliente.getTelefono() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(clienteTable, "Email:", cliente.getMail() != null ? cliente.getMail() : "N/A", BOLD_FONT, NORMAL_FONT);
        
        document.add(clienteTable);
        document.add(Chunk.NEWLINE);
    }
    
    /**
     * Agrega una sección con la información detallada del equipo
     */
    private void addEquipoInformation(Document document, Equipo equipo) throws DocumentException {
        Paragraph equipoTitle = new Paragraph("Información del Equipo", SUBTITLE_FONT);
        equipoTitle.setAlignment(Element.ALIGN_LEFT);
        document.add(equipoTitle);
        document.add(Chunk.NEWLINE);
        
        PdfPTable equipoTable = new PdfPTable(2);
        equipoTable.setWidthPercentage(100);
        
        // Configurar anchos de columna
        float[] columnWidths = {1f, 3f};
        equipoTable.setWidths(columnWidths);
        
        // Agregar datos del equipo
        addTableRow(equipoTable, "ID:", equipo.getId() != null ? equipo.getId().toString() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(equipoTable, "Número de Serie:", equipo.getNumSerieEquipo() != null ? equipo.getNumSerieEquipo() : "N/A", BOLD_FONT, NORMAL_FONT);
        addTableRow(equipoTable, "Marca:", equipo.getMarca() != null ? equipo.getMarca() : "N/A", BOLD_FONT, NORMAL_FONT);
        
        // Tipo de equipo
        TipoEquipo tipoEquipo = equipo.getTipoEquipo();
        if (tipoEquipo != null) {
            addTableRow(equipoTable, "Tipo:", tipoEquipo.getTipo() != null ? tipoEquipo.getTipo() : "N/A", BOLD_FONT, NORMAL_FONT);
            addTableRow(equipoTable, "Marca (Tipo):", tipoEquipo.getMarca() != null ? tipoEquipo.getMarca() : "N/A", BOLD_FONT, NORMAL_FONT);
            addTableRow(equipoTable, "Modelo:", tipoEquipo.getModelo() != null ? tipoEquipo.getModelo() : "N/A", BOLD_FONT, NORMAL_FONT);
        } else {
            addTableRow(equipoTable, "Tipo:", "No especificado", BOLD_FONT, NORMAL_FONT);
        }
        
        document.add(equipoTable);
        document.add(Chunk.NEWLINE);
    }
    
    private void addTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        table.addCell(new Phrase(label, labelFont));
        table.addCell(new Phrase(value, valueFont));
    }
}
