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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sso.app.repository.OrdenRepository;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
    
    /**
     * Genera un enlace para compartir un reporte de Orden por WhatsApp
     * @param ordenId ID de la orden para la que se generará el reporte
     * @param phoneNumber Número de teléfono (opcional) para pre-rellenar en WhatsApp
     * @param message Mensaje personalizado (opcional) para incluir
     * @return URL para compartir el PDF por WhatsApp
     */
    @GetMapping("/compartir-whatsapp/{ordenId}")
    public ResponseEntity<?> compartirReportePorWhatsApp(
            @PathVariable Long ordenId,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false, defaultValue = "Reporte de Orden de Trabajo") String message) {
        
        try {
            // Generar el URL del PDF para descarga
            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            String pdfUrl = baseUrl + "/api/reportes/orden-recepcion/" + ordenId;
            
            // Formatear el número de teléfono si está presente (quitar el + si existe)
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                if (phoneNumber.startsWith("+")) {
                    phoneNumber = phoneNumber.substring(1);
                }
            }
            
            // Crear enlaces para compartir
            Map<String, String> enlaces = new HashMap<>();
            
            // 1. Enlace de descarga directa del PDF
            enlaces.put("pdfUrl", pdfUrl);
            
            // 2. Enlace para compartir por WhatsApp sin número específico
            String textoCompartir = message + "\n" + pdfUrl;
            String whatsappLink = "https://wa.me/?text=" + encodeValue(textoCompartir);
            enlaces.put("whatsappGeneralLink", whatsappLink);
            
            // 3. Enlace para compartir por WhatsApp a un número específico (si se proporcionó)
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                String whatsappNumberLink = "https://wa.me/" + phoneNumber + "?text=" + encodeValue(textoCompartir);
                enlaces.put("whatsappNumberLink", whatsappNumberLink);
            }
            
            return ResponseEntity.ok(enlaces);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al generar enlaces: " + e.getMessage());
        }
    }
    
    /**
     * Método auxiliar para codificar valores para URL
     */
    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }
}
