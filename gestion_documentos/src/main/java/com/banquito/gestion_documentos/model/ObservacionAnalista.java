package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "observacion_analistas")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"solicitudCredito"})
public class ObservacionAnalista {

    @Id
    @Column(name = "id_observacion_analista", length = 20)
    private String idObservacionAnalista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudCredito solicitudCredito;

    @Column(name = "usuario", length = 50, nullable = false)
    private String usuario;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "razon_intervencion", length = 500, nullable = false)
    private String razonIntervencion;

    public ObservacionAnalista(String idObservacionAnalista) {
        this.idObservacionAnalista = idObservacionAnalista;
    }

    @PrePersist
    public void prePersist() {
        if (fechaHora == null) {
            fechaHora = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ObservacionAnalista that = (ObservacionAnalista) obj;
        return idObservacionAnalista.equals(that.idObservacionAnalista);
    }

    @Override
    public int hashCode() {
        return idObservacionAnalista.hashCode();
    }
} 