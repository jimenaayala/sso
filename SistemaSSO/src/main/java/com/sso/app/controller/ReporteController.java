package com.sso.app.controller;

import com.itextpdf.text.DocumentException;
import com.sso.app.entity.Orden;
import com.sso.app.service.PDFGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sso.app.repository.OrdenRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportes")
public class ReporteController {

    private final PDFGeneratorService pdfGeneratorService;
    private final OrdenRepository ordenRepository;

    /**
     * Genera un reporte en PDF con la información de una Orden y su Recepción asociada
     * @param ordenId ID de la orden para la que se generará el reporte
     * @return Archivo PDF con el reporte generado
     */
    @GetMapping("/orden-recepcion/{ordenId}")
    public ResponseEntity<byte[]> generarReporteOrdenRecepcion(@PathVariable Long ordenId) {
        try {
            // Buscar la orden por su ID con carga explícita de la recepción y sus items
            Orden orden = ordenRepository.findByIdWithRecepcion(ordenId)
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + ordenId));
            
            // Generar el PDF
            byte[] pdfBytes = pdfGeneratorService.generarReporteOrdenRecepcion(orden);
            
            // Configurar los headers para la descarga del PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "Reporte_Orden_" + ordenId + ".pdf");
            
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            
        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
