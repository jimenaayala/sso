package com.sso.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio para integración con WhatsApp Business API
 * Este servicio permite enviar mensajes y documentos a través de WhatsApp
 */
@Service
@RequiredArgsConstructor
public class WhatsAppService {

    private final RestTemplate restTemplate;
    
    @Value("${whatsapp.api.url:https://graph.facebook.com}")
    private String whatsappApiUrl;
    
    @Value("${whatsapp.api.version:v18.0}")
    private String apiVersion;
    
    @Value("${whatsapp.api.phone.number.id:your_phone_number_id}")
    private String phoneNumberId;
    
    @Value("${whatsapp.api.access.token:your_access_token}")
    private String accessToken;
    
    @Value("${whatsapp.business.account.id:your_business_account_id}")
    private String businessAccountId;
    
    /**
     * Envía un documento PDF a través de WhatsApp Business API
     * @param phoneNumber Número de teléfono del destinatario (formato internacional: 549XXXXXXXX sin el +)
     * @param documentBytes Bytes del documento PDF a enviar
     * @param documentName Nombre del documento
     * @param message Mensaje descriptivo que acompaña al documento
     * @return true si el envío fue exitoso, false en caso contrario
     */
    public boolean enviarDocumentoPDF(String phoneNumber, byte[] documentBytes, String documentName, String message) {
        try {
            // Formatear el número de teléfono (asegurar que tenga el formato correcto)
            if (!phoneNumber.startsWith("54")) {
                phoneNumber = "54" + phoneNumber.replaceAll("[^0-9]", "");
            }
            
            // Primero necesitamos subir el archivo como adjunto multimedia
            String mediaId = subirAdjuntoMultimedia(documentBytes, "application/pdf");
            
            if (mediaId == null) {
                System.err.println("Error al subir el documento como adjunto multimedia");
                return false;
            }
            
            // Ahora enviamos el mensaje con el documento adjunto
            String endpoint = String.format("%s/%s/%s/messages", whatsappApiUrl, apiVersion, phoneNumberId);
            
            Map<String, Object> document = new HashMap<>();
            document.put("id", mediaId);
            document.put("caption", message);
            document.put("filename", documentName);
            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("messaging_product", "whatsapp");
            requestBody.put("recipient_type", "individual");
            requestBody.put("to", phoneNumber);
            requestBody.put("type", "document");
            requestBody.put("document", document);
            
            // Configurar cabeceras de la solicitud
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            
            // Crear la entidad HTTP
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            
            // Realizar la solicitud POST a la API de WhatsApp
            ResponseEntity<String> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            
            // Verificar si la solicitud fue exitosa
            return response.getStatusCode().is2xxSuccessful();
            
        } catch (Exception e) {
            // Registrar el error y devolver false
            System.err.println("Error al enviar documento por WhatsApp: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Sube un archivo como adjunto multimedia a la API de WhatsApp
     * @param fileBytes Bytes del archivo a subir
     * @param mimeType Tipo MIME del archivo
     * @return ID del archivo multimedia o null si falla
     */
    private String subirAdjuntoMultimedia(byte[] fileBytes, String mimeType) {
        try {
            String endpoint = String.format("%s/%s/%s/media", whatsappApiUrl, apiVersion, businessAccountId);
            
            // Preparamos los datos multipart para el archivo
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            
            // Agregamos el archivo como ByteArrayResource
            ByteArrayResource fileResource = new ByteArrayResource(fileBytes) {
                @Override
                public String getFilename() {
                    return "document.pdf";
                }
            };
            
            body.add("file", fileResource);
            body.add("messaging_product", "whatsapp");
            body.add("type", mimeType);
            
            // Configuramos los headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Bearer " + accessToken);
            
            // Creamos la entidad HTTP
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            
            // Enviamos la solicitud
            ResponseEntity<Object> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    requestEntity,
                    Object.class
            );
            
            // Extraemos el ID del archivo multimedia de la respuesta
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                if (response.getBody() instanceof Map) {
                    Map<?, ?> responseMap = (Map<?, ?>) response.getBody();
                    Object idObj = responseMap.get("id");
                    if (idObj != null) {
                        return idObj.toString();
                    }
                }
            }
            
            return null;
            
        } catch (Exception e) {
            System.err.println("Error al subir archivo multimedia: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Envía un mensaje de texto simple a través de WhatsApp
     * @param phoneNumber Número de teléfono del destinatario (formato internacional: 549XXXXXXXX)
     * @param message Mensaje de texto a enviar
     * @return true si el envío fue exitoso, false en caso contrario
     */
    public boolean enviarMensaje(String phoneNumber, String message) {
        try {
            // Formatear el número de teléfono (asegurar que tenga el formato correcto)
            if (!phoneNumber.startsWith("54")) {
                phoneNumber = "54" + phoneNumber.replaceAll("[^0-9]", "");
            }
            
            // Endpoint para enviar mensajes
            String endpoint = String.format("%s/%s/%s/messages", whatsappApiUrl, apiVersion, phoneNumberId);
            
            // Crear el cuerpo de la solicitud en formato JSON
            Map<String, Object> textContent = new HashMap<>();
            textContent.put("preview_url", false);
            textContent.put("body", message);
            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("messaging_product", "whatsapp");
            requestBody.put("recipient_type", "individual");
            requestBody.put("to", phoneNumber);
            requestBody.put("type", "text");
            requestBody.put("text", textContent);
            
            // Configurar cabeceras
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            
            return response.getStatusCode().is2xxSuccessful();
            
        } catch (Exception e) {
            System.err.println("Error al enviar mensaje por WhatsApp: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
