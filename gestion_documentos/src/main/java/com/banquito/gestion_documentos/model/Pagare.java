package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagares")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"solicitudCredito"})
public class Pagare {

    @Id
    @Column(name = "id_pagare", length = 20)
    private String idPagare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudCredito solicitudCredito;

    @Column(name = "numero_cuota", nullable = false)
    private Integer numeroCuota;

    @Column(name = "monto_archivo", precision = 15, scale = 2, nullable = false)
    private BigDecimal montoArchivo;

    @Column(name = "fecha_generado", nullable = false)
    private LocalDateTime fechaGenerado;

    public Pagare(String idPagare) {
        this.idPagare = idPagare;
    }

    @PrePersist
    public void prePersist() {
        if (fechaGenerado == null) {
            fechaGenerado = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pagare pagare = (Pagare) obj;
        return idPagare.equals(pagare.idPagare);
    }

    @Override
    public int hashCode() {
        return idPagare.hashCode();
    }
} 