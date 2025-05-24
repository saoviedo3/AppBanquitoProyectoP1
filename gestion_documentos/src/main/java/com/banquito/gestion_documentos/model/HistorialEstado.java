package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estados")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"solicitudCredito"})
public class HistorialEstado {

    @Id
    @Column(name = "id_historial", length = 20)
    private String idHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudCredito solicitudCredito;

    @Column(name = "estado", length = 12, nullable = false)
    private String estado;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "usuario", length = 50, nullable = false)
    private String usuario;

    @Column(name = "motivo", length = 120)
    private String motivo;

    public HistorialEstado(String idHistorial) {
        this.idHistorial = idHistorial;
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
        HistorialEstado that = (HistorialEstado) obj;
        return idHistorial.equals(that.idHistorial);
    }

    @Override
    public int hashCode() {
        return idHistorial.hashCode();
    }
} 