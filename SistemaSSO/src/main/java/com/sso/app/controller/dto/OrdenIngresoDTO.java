package com.sso.app.controller.dto;

import lombok.Data;

@Data
public class OrdenIngresoDTO {
    private String comentario;
    private String remitoTransporte;
    private String etapaActual;

    private ClienteDTO cliente;
    private EquipoDTO equipo;

    @Data
    public static class ClienteDTO {
        private String razonSocial;
        private String nombreContacto;
        private String telefono;
        private String mail;
    }

    @Data
    public static class EquipoDTO {
        private String numSerieEquipo;
        private TipoEquipoDTO tipoEquipo;

        @Data
        public static class TipoEquipoDTO {
            private String tipo;
            private String modelo;
            private String marca;
        }
    }
}
