package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_adjuntos")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"solicitudCredito", "tipoDocumento"})
public class DocumentoAdjunto {

    @Id
    @Column(name = "id_documento", length = 20)
    private String idDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudCredito solicitudCredito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "ruta_archivo", length = 150, nullable = false)
    private String rutaArchivo;

    @Column(name = "fecha_cargado", nullable = false)
    private LocalDateTime fechaCargado;

    public DocumentoAdjunto(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    @PrePersist
    public void prePersist() {
        if (fechaCargado == null) {
            fechaCargado = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DocumentoAdjunto that = (DocumentoAdjunto) obj;
        return idDocumento.equals(that.idDocumento);
    }

    @Override
    public int hashCode() {
        return idDocumento.hashCode();
    }
} 