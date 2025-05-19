package com.sso.app.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sso.app.entity.Orden;
import com.sso.app.entity.Recepcion;
import com.sso.app.entity.ItemRecepcion;
import com.sso.app.entity.Cliente;
import com.sso.app.entity.Equipo;
import com.sso.app.entity.TipoEquipo;
import com.sso.app.entity.Imagen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;

@Service
@RequiredArgsConstructor
public class PDFGeneratorService {

    // Colores
    private static final BaseColor COLOR_PRINCIPAL = new BaseColor(49, 89, 150); // Azul corporativo
    private static final BaseColor COLOR_SECUNDARIO = new BaseColor(240, 240, 240); // Gris claro
    private static final BaseColor COLOR_TERCIARIO = new BaseColor(220, 231, 242); // Azul muy claro
    private static final BaseColor COLOR_OK = new BaseColor(0, 150, 0); // Verde
    private static final BaseColor COLOR_NO_OK = new BaseColor(200, 0, 0); // Rojo
    
    // Fuentes
    private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, COLOR_PRINCIPAL);
    private static final Font SUBTITLE_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, COLOR_PRINCIPAL);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
    private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.WHITE);
    private static final Font FOOTER_FONT = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
    private static final Font STATUS_OK_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, COLOR_OK);
    private static final Font STATUS_NO_OK_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, COLOR_NO_OK);
    
    /**
     * Genera un reporte en PDF con la información de una Orden y su Recepción asociada
     * @param orden La orden a incluir en el reporte
     * @return Bytes del PDF generado
     */
    public byte[] generarReporteOrdenRecepcion(Orden orden) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        Document document = new Document(PageSize.A4, 36, 36, 72, 36); // Márgenes
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        
        // Agregar clase personalizada para encabezado y pie de página
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(orden);
        writer.setPageEvent(event);
        
        document.open();
        
        // Metadata del documento
        document.addTitle("Reporte de Orden " + orden.getNumeroOT());
        document.addSubject("Información de la orden y recepción");
        document.addKeywords("SSO, Orden, Recepción, Reporte");
        document.addCreator("Sistema SSO");
        
        // Agregar título del documento
        addTitlePage(document, orden);
        
        // Información de la orden
        addOrdenInformation(document, orden);
        
        // Información de la recepción si existe
        if (orden.getRecepcion() != null) {
            addRecepcionInformation(document, orden.getRecepcion());
        }
        
        // Información del cliente si existe
        if (orden.getCliente() != null) {
            addClienteInformation(document, orden.getCliente());
        }
        
        // Información del equipo si existe
        if (orden.getEquipo() != null) {
            addEquipoInformation(document, orden.getEquipo());
        }
        
        // Información de inspecciones si existen
        if (orden.getInspeccionPcpVh60() != null) {
            addInspeccionInformation(document, "Inspección VH60", orden.getInspeccionPcpVh60());
        }
        
        if (orden.getInspeccionPcpMiniG() != null) {
            addInspeccionInformation(document, "Inspección MiniG", orden.getInspeccionPcpMiniG());
        }
        
        if (orden.getInspeccionPcpDV1() != null) {
            addInspeccionInformation(document, "Inspección DV1", orden.getInspeccionPcpDV1());
        }
        
        if (orden.getInspeccionPcpCougar() != null) {
            addInspeccionInformation(document, "Inspección Cougar", orden.getInspeccionPcpCougar());
        }
        
        // Información de ensayos si existen
        if (orden.getEnsayoVh60() != null) {
            addEnsayoInformation(document, "Ensayo VH60", orden.getEnsayoVh60());
        }
        
        if (orden.getEnsayoMiniG() != null) {
            addEnsayoInformation(document, "Ensayo MiniG", orden.getEnsayoMiniG());
        }
        
        if (orden.getEnsayoDv1() != null) {
            addEnsayoInformation(document, "Ensayo DV1", orden.getEnsayoDv1());
        }
        
        if (orden.getEnsayoCougar() != null) {
            addEnsayoInformation(document, "Ensayo Cougar", orden.getEnsayoCougar());
        }
        
        document.close();
        
        return outputStream.toByteArray();
    }
    
    /**
     * Clase interna que maneja los encabezados y pies de página del documento PDF
     */
    private class HeaderFooterPageEvent extends PdfPageEventHelper {
        private Orden orden;
        private PdfTemplate totalTemplate;
        
        public HeaderFooterPageEvent(Orden orden) {
            this.orden = orden;
        }
        
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            totalTemplate = writer.getDirectContent().createTemplate(30, 16);
        }
        
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            
            // Rectángulo de color para el encabezado
            Rectangle rect = new Rectangle(document.left(), document.top() + 30, document.right(), document.top() + 70);
            rect.setBackgroundColor(COLOR_PRINCIPAL);
            cb.rectangle(rect);
            
            try {
                // Agregar el logo
                Image logo = Image.getInstance(new ClassPathResource("static/images/logo.png").getURL());
                float logoWidth = 60; // Ancho deseado del logo
                float aspectRatio = logo.getWidth() / logo.getHeight();
                float logoHeight = logoWidth / aspectRatio;
                logo.scaleToFit(logoWidth, logoHeight);
                
                // Posicionar el logo en el encabezado (esquina izquierda)
                logo.setAbsolutePosition(document.left() + 10, document.top() + 35);
                writer.getDirectContent().addImage(logo);
                
                // Título del documento en el encabezado (desplazado por el logo)
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                        new Phrase("REPORTE DE ORDEN", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE)),
                        document.left() + 80, document.top() + 45, 0);
            } catch (IOException | DocumentException e) {
                // Si ocurre algún error al cargar el logo, mostrar solo el título
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                        new Phrase("SISTEMA SSO - REPORTE DE ORDEN", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE)),
                        document.left() + 20, document.top() + 45, 0);
            }
            
            // Número de OT en el encabezado
            String numeroOT = orden.getNumeroOT() != null ? orden.getNumeroOT() : "N/A";
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
                    new Phrase("OT: " + numeroOT, new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE)),
                    document.right() - 20, document.top() + 45, 0);
        }
        
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            String text = "Página " + writer.getPageNumber() + " de ";
            
            // Rectángulo de color para el pie de página
            Rectangle rect = new Rectangle(document.left(), document.bottom() - 20, document.right(), document.bottom());
            rect.setBackgroundColor(COLOR_PRINCIPAL);
            cb.rectangle(rect);
            
            // Fecha en el pie de página
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                    new Phrase(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), FOOTER_FONT),
                    document.left() + 20, document.bottom() - 10, 0);
            
            // Número de página
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
                    new Phrase(text, FOOTER_FONT),
                    document.right() - 30, document.bottom() - 10, 0);
            // Usar el template para mostrar el número total de páginas
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
                    new Phrase(String.valueOf(writer.getPageNumber()), FOOTER_FONT),
                    document.right() - 10, document.bottom() - 10, 0);
        }
        
        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            // Añadir el número total de páginas (se podría implementar si fuera necesario)
            // ColumnText.showTextAligned(totalTemplate, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
        }
    }
    
    private void addTitlePage(Document document, Orden orden) throws DocumentException {
        // Espacio para dejar margen desde el encabezado
        document.add(Chunk.NEWLINE);
        
        // Título centrado con colores corporativos
        Paragraph title = new Paragraph("Reporte Detallado de Orden", TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        
        // Subtítulo con número de orden
        Paragraph subtitle = new Paragraph("Orden de Trabajo: " + 
                (orden.getNumeroOT() != null ? orden.getNumeroOT() : "N/A"), SUBTITLE_FONT);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        subtitle.setSpacingBefore(10);
        document.add(subtitle);
        
        // Línea decorativa
        PdfPTable lineTable = new PdfPTable(1);
        lineTable.setWidthPercentage(50);
        lineTable.setSpacingBefore(15);
        lineTable.setSpacingAfter(15);
        
        PdfPCell lineCell = new PdfPCell();
        lineCell.setFixedHeight(3f);
        lineCell.setBackgroundColor(COLOR_PRINCIPAL);
        lineCell.setBorder(Rectangle.NO_BORDER);
        lineTable.addCell(lineCell);
        
        document.add(lineTable);
    }
    
    private void addOrdenInformation(Document document, Orden orden) throws DocumentException {
        // Sección de información de la orden con estilo
        Paragraph ordenTitle = new Paragraph("Información de la Orden", SUBTITLE_FONT);
        ordenTitle.setAlignment(Element.ALIGN_LEFT);
        ordenTitle.setSpacingBefore(10);
        document.add(ordenTitle);
        
        // Crear un fondo para la tabla con bordes redondeados
        PdfPTable backgroundTable = new PdfPTable(1);
        backgroundTable.setWidthPercentage(100);
        
        PdfPCell backgroundCell = new PdfPCell();
        backgroundCell.setPadding(10);
        backgroundCell.setBorderColor(COLOR_PRINCIPAL);
        backgroundCell.setBorderWidth(1f);
        backgroundCell.setBackgroundColor(COLOR_SECUNDARIO);
        
        // Tabla con los detalles de la orden
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        
        // Configurar anchos de columna
        float[] columnWidths = {1f, 3f};
        table.setWidths(columnWidths);
        
        // Agregar encabezados y datos con estilo
        addStyledTableRow(table, "ID:", orden.getId() != null ? orden.getId().toString() : "N/A");
        addStyledTableRow(table, "Número OT:", orden.getNumeroOT() != null ? orden.getNumeroOT() : "N/A");
        addStyledTableRow(table, "Fecha:", orden.getFecha() != null ? 
                orden.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : "N/A");
        
        // Añadir la tabla de datos al fondo
        backgroundCell.addElement(table);
        backgroundTable.addCell(backgroundCell);
        document.add(backgroundTable);
        document.add(Chunk.NEWLINE);
        
        // Mostrar información del cliente
        Cliente cliente = orden.getCliente();
        if (cliente != null) {
            // Sección específica para el cliente
            addClienteInformation(document, cliente);
        } else {
            // Mensaje de cliente no especificado
            Paragraph noCliente = new Paragraph("No hay información de cliente asociada", NORMAL_FONT);
            noCliente.setAlignment(Element.ALIGN_CENTER);
            noCliente.setSpacingBefore(5);
            noCliente.setSpacingAfter(5);
            document.add(noCliente);
        }
        
        // Mostrar información del equipo
        Equipo equipo = orden.getEquipo();
        if (equipo != null) {
            // Sección específica para el equipo
            addEquipoInformation(document, equipo);
        } else {
            // Mensaje de equipo no especificado
            Paragraph noEquipo = new Paragraph("No hay información de equipo asociada", NORMAL_FONT);
            noEquipo.setAlignment(Element.ALIGN_CENTER);
            noEquipo.setSpacingBefore(5);
            noEquipo.setSpacingAfter(5);
            document.add(noEquipo);
        }
        
        // Información adicional de la orden
        Paragraph adicionalTitle = new Paragraph("Información Adicional", SUBTITLE_FONT);
        adicionalTitle.setAlignment(Element.ALIGN_LEFT);
        adicionalTitle.setSpacingBefore(10);
        document.add(adicionalTitle);
        
        // Tabla para información adicional
        PdfPTable backgroundTableAdicional = new PdfPTable(1);
        backgroundTableAdicional.setWidthPercentage(100);
        
        PdfPCell backgroundCellAdicional = new PdfPCell();
        backgroundCellAdicional.setPadding(10);
        backgroundCellAdicional.setBorderColor(COLOR_PRINCIPAL);
        backgroundCellAdicional.setBorderWidth(1f);
        backgroundCellAdicional.setBackgroundColor(COLOR_SECUNDARIO);
        
        PdfPTable tableAdicional = new PdfPTable(2);
        tableAdicional.setWidthPercentage(100);
        tableAdicional.setWidths(columnWidths);
        
        addStyledTableRow(tableAdicional, "Etapa Actual:", orden.getEtapaActual() != null ? orden.getEtapaActual() : "N/A");
        addStyledTableRow(tableAdicional, "Comentario:", orden.getComentario() != null ? orden.getComentario() : "N/A");
        addStyledTableRow(tableAdicional, "Remito Transporte:", orden.getRemitoTransporte() != null ? orden.getRemitoTransporte() : "N/A");
        
        backgroundCellAdicional.addElement(tableAdicional);
        backgroundTableAdicional.addCell(backgroundCellAdicional);
        document.add(backgroundTableAdicional);
        document.add(Chunk.NEWLINE);
    }
    
    private void addRecepcionInformation(Document document, Recepcion recepcion) throws DocumentException {
        // Título de la sección con estilo
        Paragraph recepcionTitle = new Paragraph("Información de la Recepción", SUBTITLE_FONT);
        recepcionTitle.setAlignment(Element.ALIGN_LEFT);
        recepcionTitle.setSpacingBefore(10);
        document.add(recepcionTitle);
        
        // Crear un fondo para la tabla de comentarios con bordes de color
        PdfPTable comentarioTable = new PdfPTable(1);
        comentarioTable.setWidthPercentage(100);
        
        PdfPCell comentarioCell = new PdfPCell();
        comentarioCell.setPadding(10);
        comentarioCell.setBorderColor(COLOR_PRINCIPAL);
        comentarioCell.setBorderWidth(1f);
        comentarioCell.setBackgroundColor(COLOR_SECUNDARIO);
        
        // Comentario general de recepción
        Paragraph comentario = new Paragraph();
        comentario.add(new Chunk("COMENTARIO: ", BOLD_FONT));
        comentario.add(new Chunk(recepcion.getComentario() != null ? recepcion.getComentario() : "N/A", NORMAL_FONT));
        
        comentarioCell.addElement(comentario);
        comentarioTable.addCell(comentarioCell);
        document.add(comentarioTable);
        document.add(Chunk.NEWLINE);
        
        // Detalles de los items de recepción si existe
        if (recepcion.getItemRecepcion() != null) {
            addItemRecepcionDetails(document, recepcion.getItemRecepcion());
        }
        
        // Información sobre las imágenes
        if (recepcion.getImagenes() != null && !recepcion.getImagenes().isEmpty()) {
            // Filtrar solo las imágenes marcadas como publicar=true
            List<Imagen> imagenesToShow = recepcion.getImagenes().stream()
                .filter(Imagen::isPublicar)
                .collect(Collectors.toList());
            
            // Solo mostrar sección de imágenes si hay imágenes para mostrar
            if (!imagenesToShow.isEmpty()) {
                Paragraph imagenesTitle = new Paragraph("Imágenes Adjuntas", SUBTITLE_FONT);
                imagenesTitle.setAlignment(Element.ALIGN_LEFT);
                imagenesTitle.setSpacingBefore(10);
                document.add(imagenesTitle);
                
                PdfPTable imagenesTable = new PdfPTable(2); // 2 columnas para mostrar imágenes en par
                imagenesTable.setWidthPercentage(100);
                imagenesTable.setSpacingBefore(10);
                
                // Quitar bordes externos de la tabla
                imagenesTable.getDefaultCell().setBorderWidth(0);
                
                // Párrafo con información de cantidad de imágenes
                Paragraph imagenesInfo = new Paragraph("Cantidad de imágenes: " + imagenesToShow.size(), BOLD_FONT);
                PdfPCell infoCell = new PdfPCell(imagenesInfo);
                infoCell.setColspan(2);
                infoCell.setPadding(10);
                infoCell.setBorderColor(COLOR_PRINCIPAL);
                infoCell.setBorderWidth(1f);
                infoCell.setBackgroundColor(COLOR_SECUNDARIO);
                imagenesTable.addCell(infoCell);
                
                // Agregar cada imagen al PDF
                for (Imagen imagen : imagenesToShow) {
                    try {
                        // Crear la celda para la imagen
                        PdfPCell imagenCell = new PdfPCell();
                        imagenCell.setPadding(5);
                        // Quitar bordes de las celdas individuales
                        imagenCell.setBorderWidth(0);
                        
                        // Intentar cargar la imagen desde la URL
                        Image img = null;
                        try {
                            // Intentar cargar la imagen desde la URL
                            if (imagen.getUrl() != null && !imagen.getUrl().isEmpty()) {
                                // Validar si la URL es local o remota
                                if (imagen.getUrl().startsWith("http")) {
                                    // URL remota
                                    img = Image.getInstance(java.net.URI.create(imagen.getUrl()).toURL());
                                } else {
                                    // Path local - ajustar según cómo se almacenan las imágenes
                                    img = Image.getInstance(imagen.getUrl());
                                }
                                
                                // Escalar la imagen
                                float maxWidth = 250f; // Ancho máximo
                                float maxHeight = 200f; // Alto máximo
                                
                                if (img.getWidth() > maxWidth || img.getHeight() > maxHeight) {
                                    img.scaleToFit(maxWidth, maxHeight);
                                }
                                
                                // Centrar la imagen en la celda
                                img.setAlignment(Element.ALIGN_CENTER);
                                imagenCell.addElement(img);
                                
                                // Agregar descripción si existe
                                if (imagen.getDescripcion() != null && !imagen.getDescripcion().isEmpty()) {
                                    Paragraph descripcion = new Paragraph(imagen.getDescripcion(), NORMAL_FONT);
                                    descripcion.setAlignment(Element.ALIGN_CENTER);
                                    imagenCell.addElement(descripcion);
                                }
                            } else {
                                // Si no hay URL, mostrar mensaje
                                Paragraph noImage = new Paragraph("[Imagen no disponible]", NORMAL_FONT);
                                noImage.setAlignment(Element.ALIGN_CENTER);
                                imagenCell.addElement(noImage);
                            }
                        } catch (Exception e) {
                            // En caso de error al cargar la imagen
                            Paragraph errorMsg = new Paragraph("[Error al cargar imagen: " + e.getMessage() + "]", NORMAL_FONT);
                            errorMsg.setAlignment(Element.ALIGN_CENTER);
                            imagenCell.addElement(errorMsg);
                        }
                        
                        // Agregar la celda a la tabla
                        imagenesTable.addCell(imagenCell);
                    } catch (Exception e) {
                        // Manejar errores generales
                        PdfPCell errorCell = new PdfPCell(new Paragraph("Error procesando imagen: " + e.getMessage(), NORMAL_FONT));
                        errorCell.setColspan(2);
                        imagenesTable.addCell(errorCell);
                    }
                }
                
                // Si hay un número impar de imágenes, agregar una celda vacía para completar la última fila
                if (imagenesTable.getRows().size() % 2 != 0) {
                    PdfPCell emptyCell = new PdfPCell();
                    emptyCell.setBorderWidth(0); // Sin bordes para la celda vacía
                    imagenesTable.addCell(emptyCell);
                }
                
                document.add(imagenesTable);
            }
        }
    }
    
    private void addItemRecepcionDetails(Document document, ItemRecepcion item) throws DocumentException {
        // Título para los componentes
        Paragraph componentesTitle = new Paragraph("Componentes Inspeccionados", SUBTITLE_FONT);
        componentesTitle.setAlignment(Element.ALIGN_LEFT);
        componentesTitle.setSpacingBefore(10);
        componentesTitle.setSpacingAfter(5);
        document.add(componentesTitle);
        
        // Tabla para los items de recepción con mejor formato
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);
        
        // Configurar anchos de columna
        float[] columnWidths = {2f, 1f, 3f};
        table.setWidths(columnWidths);
        
        // Encabezados de la tabla con estilo
        PdfPCell headerCell1 = new PdfPCell(new Phrase("COMPONENTE", HEADER_FONT));
        headerCell1.setBackgroundColor(COLOR_PRINCIPAL);
        headerCell1.setPadding(5);
        headerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        PdfPCell headerCell2 = new PdfPCell(new Phrase("ESTADO", HEADER_FONT));
        headerCell2.setBackgroundColor(COLOR_PRINCIPAL);
        headerCell2.setPadding(5);
        headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        PdfPCell headerCell3 = new PdfPCell(new Phrase("OBSERVACIONES", HEADER_FONT));
        headerCell3.setBackgroundColor(COLOR_PRINCIPAL);
        headerCell3.setPadding(5);
        headerCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(headerCell1);
        table.addCell(headerCell2);
        table.addCell(headerCell3);
        
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
        // Celda para el nombre del componente
        PdfPCell componentCell = new PdfPCell(new Phrase(componentName, NORMAL_FONT));
        componentCell.setPadding(5);
        
        // Celda para el estado (OK/NO OK)
        PdfPCell estadoCell = new PdfPCell();
        estadoCell.setPadding(5);
        estadoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Phrase estadoPhrase;
        if (estado) {
            estadoPhrase = new Phrase("OK", STATUS_OK_FONT);
        } else {
            estadoPhrase = new Phrase("NO OK", STATUS_NO_OK_FONT);
        }
        estadoCell.addElement(estadoPhrase);
        
        // Celda para observaciones y requerimientos
        PdfPCell obsCell = new PdfPCell();
        obsCell.setPadding(5);
        
        Paragraph obsContent = new Paragraph();
        if (requerimiento != null && !requerimiento.isEmpty()) {
            obsContent.add(new Chunk("Requerimiento: ", BOLD_FONT));
            obsContent.add(new Chunk(requerimiento, NORMAL_FONT));
            obsContent.add(Chunk.NEWLINE);
        }
        
        if (observacion != null && !observacion.isEmpty()) {
            obsContent.add(new Chunk("Observación: ", BOLD_FONT));
            obsContent.add(new Chunk(observacion, NORMAL_FONT));
        }
        
        obsCell.addElement(obsContent);
        
        table.addCell(componentCell);
        table.addCell(estadoCell);
        table.addCell(obsCell);
    }
    
    /**
     * Añade información de inspección al PDF
     */
    private void addInspeccionInformation(Document document, String title, Object inspeccion) throws DocumentException {
        Paragraph inspeccionTitle = new Paragraph(title, SUBTITLE_FONT);
        inspeccionTitle.setAlignment(Element.ALIGN_LEFT);
        inspeccionTitle.setSpacingBefore(10);
        document.add(inspeccionTitle);
        
        // Crear un fondo para la tabla con bordes de color
        PdfPTable backgroundTable = new PdfPTable(1);
        backgroundTable.setWidthPercentage(100);
        
        PdfPCell backgroundCell = new PdfPCell();
        backgroundCell.setPadding(10);
        backgroundCell.setBorderColor(COLOR_PRINCIPAL);
        backgroundCell.setBorderWidth(1f);
        backgroundCell.setBackgroundColor(COLOR_SECUNDARIO);
        
        // Tabla con los detalles de la inspección
        PdfPTable inspeccionTable = new PdfPTable(2);
        inspeccionTable.setWidthPercentage(100);
        
        // Configurar anchos de columna
        try {
            float[] columnWidths = {1f, 3f};
            inspeccionTable.setWidths(columnWidths);
            
            // Agregar método para extraer dinámicamente propiedades del objeto
            java.lang.reflect.Method[] methods = inspeccion.getClass().getMethods();
            for (java.lang.reflect.Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    try {
                        String propertyName = methodName.substring(3);
                        // Primera letra en minúscula para mostrarlo más amigable
                        propertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
                        
                        Object value = method.invoke(inspeccion);
                        if (value != null) {
                            // Si es una entidad, mostrar solo su ID
                            if (value instanceof Orden || value instanceof Recepcion) {
                                // Skip relaciones inversas para evitar recursión
                                continue;
                            }
                            
                            // Si es una colección de imágenes, procesarlas
                            if (methodName.equals("getImagenes") && value instanceof java.util.Collection) {
                                // Las imágenes se manejan por separado, saltamos
                                continue;
                            }
                            
                            String valueStr = value.toString();
                            // Para valores booleanos, convertir a Sí/No
                            if (value instanceof Boolean) {
                                valueStr = ((Boolean)value) ? "Sí" : "No";
                            }
                            
                            addStyledTableRow(inspeccionTable, propertyName + ":", valueStr);
                        }
                    } catch (Exception e) {
                        // Ignorar errores al acceder a propiedades
                        continue;
                    }
                }
            }
            
            // Mostrar imágenes asociadas si las hubiera
            try {
                java.lang.reflect.Method getImagenes = inspeccion.getClass().getMethod("getImagenes");
                Object imagenesObj = getImagenes.invoke(inspeccion);
                if (imagenesObj != null && imagenesObj instanceof java.util.Collection) {
                    @SuppressWarnings("unchecked")
                    java.util.Collection<Imagen> imagenes = (java.util.Collection<Imagen>) imagenesObj;
                    if (!imagenes.isEmpty()) {
                        // Filtrar solo las imágenes marcadas como publicar=true
                        List<Imagen> imagenesToShow = imagenes.stream()
                            .filter(Imagen::isPublicar)
                            .collect(Collectors.toList());
                        
                        if (!imagenesToShow.isEmpty()) {
                            // Mostrar imágenes
                            PdfPCell imagesTitle = new PdfPCell(new Phrase("IMÁGENES", HEADER_FONT));
                            imagesTitle.setBackgroundColor(COLOR_PRINCIPAL);
                            imagesTitle.setColspan(2);
                            imagesTitle.setPadding(5);
                            imagesTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
                            inspeccionTable.addCell(imagesTitle);
                            
                            for (Imagen imagen : imagenesToShow) {
                                if (imagen.getUrl() != null && !imagen.getUrl().isEmpty()) {
                                    try {
                                        // Crear celda para la imagen
                                        PdfPCell imageCell = new PdfPCell();
                                        imageCell.setColspan(2);
                                        imageCell.setBorderWidth(0); // Sin bordes
                                        imageCell.setPadding(5);
                                        
                                        // Cargar la imagen
                                        Image img;
                                        if (imagen.getUrl().startsWith("http")) {
                                            img = Image.getInstance(java.net.URI.create(imagen.getUrl()).toURL());
                                        } else {
                                            img = Image.getInstance(imagen.getUrl());
                                        }
                                        
                                        // Escalar la imagen
                                        float maxWidth = 300f;
                                        float maxHeight = 200f;
                                        if (img.getWidth() > maxWidth || img.getHeight() > maxHeight) {
                                            img.scaleToFit(maxWidth, maxHeight);
                                        }
                                        
                                        img.setAlignment(Element.ALIGN_CENTER);
                                        imageCell.addElement(img);
                                        
                                        // Agregar descripción si existe
                                        if (imagen.getDescripcion() != null && !imagen.getDescripcion().isEmpty()) {
                                            Paragraph desc = new Paragraph(imagen.getDescripcion(), NORMAL_FONT);
                                            desc.setAlignment(Element.ALIGN_CENTER);
                                            imageCell.addElement(desc);
                                        }
                                        
                                        inspeccionTable.addCell(imageCell);
                                    } catch (Exception e) {
                                        // Ignorar errores al cargar imágenes
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // No hay método getImagenes o error al acceder
            }
            
        } catch (DocumentException e) {
            // Error al configurar la tabla
        }
        
        backgroundCell.addElement(inspeccionTable);
        backgroundTable.addCell(backgroundCell);
        document.add(backgroundTable);
        document.add(Chunk.NEWLINE);
    }
    
    /**
     * Añade información de ensayo al PDF
     */
    private void addEnsayoInformation(Document document, String title, Object ensayo) throws DocumentException {
        // Utilizamos el mismo método que para inspecciones, ya que la estructura es similar
        addInspeccionInformation(document, title, ensayo);
    }
    
    /**
     * Añade una sección con la información detallada del cliente
     * Agrega una sección con la información detallada del cliente
     */
    private void addClienteInformation(Document document, Cliente cliente) throws DocumentException {
        // Título de la sección con estilo
        Paragraph clienteTitle = new Paragraph("Información del Cliente", SUBTITLE_FONT);
        clienteTitle.setAlignment(Element.ALIGN_LEFT);
        clienteTitle.setSpacingBefore(10);
        document.add(clienteTitle);
        
        // Crear un fondo para la tabla con bordes de color
        PdfPTable backgroundTable = new PdfPTable(1);
        backgroundTable.setWidthPercentage(100);
        
        PdfPCell backgroundCell = new PdfPCell();
        backgroundCell.setPadding(10);
        backgroundCell.setBorderColor(COLOR_PRINCIPAL);
        backgroundCell.setBorderWidth(1f);
        backgroundCell.setBackgroundColor(COLOR_SECUNDARIO);
        
        // Tabla con los detalles del cliente
        PdfPTable clienteTable = new PdfPTable(2);
        clienteTable.setWidthPercentage(100);
        
        // Configurar anchos de columna
        float[] columnWidths = {1f, 3f};
        clienteTable.setWidths(columnWidths);
        
        // Agregar datos del cliente con estilo
        addStyledTableRow(clienteTable, "ID:", cliente.getId() != null ? cliente.getId().toString() : "N/A");
        addStyledTableRow(clienteTable, "CUIT:", cliente.getCuit() != null ? cliente.getCuit() : "N/A");
        addStyledTableRow(clienteTable, "Razón Social:", cliente.getRazonSocial() != null ? cliente.getRazonSocial() : "N/A");
        addStyledTableRow(clienteTable, "Nombre Fantasía:", cliente.getNombreFantasia() != null ? cliente.getNombreFantasia() : "N/A");
        addStyledTableRow(clienteTable, "Área:", cliente.getArea() != null ? cliente.getArea() : "N/A");
        addStyledTableRow(clienteTable, "Contacto:", cliente.getNombreContacto() != null ? cliente.getNombreContacto() : "N/A");
        addStyledTableRow(clienteTable, "Teléfono:", cliente.getTelefono() != null ? cliente.getTelefono() : "N/A");
        addStyledTableRow(clienteTable, "Email:", cliente.getMail() != null ? cliente.getMail() : "N/A");
        
        // Añadir la tabla al fondo estilizado
        backgroundCell.addElement(clienteTable);
        backgroundTable.addCell(backgroundCell);
        document.add(backgroundTable);
        document.add(Chunk.NEWLINE);
    }
    
    /**
     * Agrega una sección con la información detallada del equipo
     */
    private void addEquipoInformation(Document document, Equipo equipo) throws DocumentException {
        // Título de la sección con estilo
        Paragraph equipoTitle = new Paragraph("Información del Equipo", SUBTITLE_FONT);
        equipoTitle.setAlignment(Element.ALIGN_LEFT);
        equipoTitle.setSpacingBefore(10);
        document.add(equipoTitle);
        
        // Crear un fondo para la tabla con bordes de color
        PdfPTable backgroundTable = new PdfPTable(1);
        backgroundTable.setWidthPercentage(100);
        
        PdfPCell backgroundCell = new PdfPCell();
        backgroundCell.setPadding(10);
        backgroundCell.setBorderColor(COLOR_PRINCIPAL);
        backgroundCell.setBorderWidth(1f);
        backgroundCell.setBackgroundColor(COLOR_SECUNDARIO);
        
        // Tabla con los detalles del equipo
        PdfPTable equipoTable = new PdfPTable(2);
        equipoTable.setWidthPercentage(100);
        
        // Configurar anchos de columna
        float[] columnWidths = {1f, 3f};
        equipoTable.setWidths(columnWidths);
        
        // Agregar datos del equipo con estilo
        addStyledTableRow(equipoTable, "ID:", equipo.getId() != null ? equipo.getId().toString() : "N/A");
        addStyledTableRow(equipoTable, "Número de Serie:", equipo.getNumSerieEquipo() != null ? equipo.getNumSerieEquipo() : "N/A");
        addStyledTableRow(equipoTable, "Marca:", equipo.getMarca() != null ? equipo.getMarca() : "N/A");
        
        // Tipo de equipo
        TipoEquipo tipoEquipo = equipo.getTipoEquipo();
        if (tipoEquipo != null) {
            // Título para la subcategoria
            PdfPCell tipoCell = new PdfPCell(new Phrase("TIPO DE EQUIPO", HEADER_FONT));
            tipoCell.setBackgroundColor(COLOR_PRINCIPAL);
            tipoCell.setColspan(2);
            tipoCell.setPadding(5);
            tipoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            equipoTable.addCell(tipoCell);
            
            addStyledTableRow(equipoTable, "Tipo:", tipoEquipo.getTipo() != null ? tipoEquipo.getTipo() : "N/A");
            addStyledTableRow(equipoTable, "Marca (Tipo):", tipoEquipo.getMarca() != null ? tipoEquipo.getMarca() : "N/A");
            addStyledTableRow(equipoTable, "Modelo:", tipoEquipo.getModelo() != null ? tipoEquipo.getModelo() : "N/A");
        } else {
            addStyledTableRow(equipoTable, "Tipo:", "No especificado");
        }
        
        // Añadir la tabla al fondo estilizado
        backgroundCell.addElement(equipoTable);
        backgroundTable.addCell(backgroundCell);
        document.add(backgroundTable);
        document.add(Chunk.NEWLINE);
    }
    
    /**
     * Añade una fila estilizada a la tabla con colores alternados
     */
    private void addStyledTableRow(PdfPTable table, String label, String value) {
        // Verificar si es fila par o impar para alternar colores
        boolean isEvenRow = (table.getRows().size() % 2 == 0);
        BaseColor backgroundColor = isEvenRow ? COLOR_TERCIARIO : BaseColor.WHITE;
        
        // Celda para la etiqueta
        PdfPCell labelCell = new PdfPCell(new Phrase(label, BOLD_FONT));
        labelCell.setBackgroundColor(backgroundColor);
        labelCell.setPadding(5);
        labelCell.setBorderColor(BaseColor.WHITE);
        labelCell.setBorderWidth(1f);
        
        // Celda para el valor
        PdfPCell valueCell = new PdfPCell(new Phrase(value, NORMAL_FONT));
        valueCell.setBackgroundColor(backgroundColor);
        valueCell.setPadding(5);
        valueCell.setBorderColor(BaseColor.WHITE);
        valueCell.setBorderWidth(1f);
        
        table.addCell(labelCell);
        table.addCell(valueCell);
    }
    
    /**
     * Método original para compatibilidad con código existente
     */
    // Este método se mantiene para compatibilidad con posible código futuro
    private void addTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setPadding(5);
        
        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setPadding(5);
        
        table.addCell(labelCell);
        table.addCell(valueCell);
    }
}
