package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contratos")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"solicitudCredito"})
public class Contrato {

    @Id
    @Column(name = "id_contrato", length = 20)
    private String idContrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudCredito solicitudCredito;

    @Column(name = "ruta_archivo", length = 150, nullable = false)
    private String rutaArchivo;

    @Column(name = "fecha_generado", nullable = false)
    private LocalDateTime fechaGenerado;

    @Column(name = "fecha_firma")
    private LocalDateTime fechaFirma;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "condicion_especial", length = 120)
    private String condicionEspecial;

    public Contrato(String idContrato) {
        this.idContrato = idContrato;
    }

    @PrePersist
    public void prePersist() {
        if (fechaGenerado == null) {
            fechaGenerado = LocalDateTime.now();
        }
        if (estado == null) {
            estado = "GENERADO";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contrato contrato = (Contrato) obj;
        return idContrato.equals(contrato.idContrato);
    }

    @Override
    public int hashCode() {
        return idContrato.hashCode();
    }
} 