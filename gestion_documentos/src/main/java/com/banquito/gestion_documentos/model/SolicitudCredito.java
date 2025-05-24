package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "solicitudes_creditos")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"clienteProspecto", "vehiculo", "vendedor"})
public class SolicitudCredito {

    @Id
    @Column(name = "id_solicitud", length = 20)
    private String idSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente_prospecto", nullable = false)
    private ClienteProspecto clienteProspecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vendedor", nullable = false)
    private Vendedor vendedor;

    @Column(name = "numero_solicitud", length = 20, nullable = false, unique = true)
    private String numeroSolicitud;

    @Column(name = "monto_solicitado", precision = 12, scale = 2, nullable = false)
    private BigDecimal montoSolicitado;

    @Column(name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @Column(name = "entrada", precision = 12, scale = 2, nullable = false)
    private BigDecimal entrada;

    @Column(name = "score_interno", precision = 6, scale = 2)
    private BigDecimal scoreInterno;

    @Column(name = "score_externo", precision = 6, scale = 2)
    private BigDecimal scoreExterno;

    @Column(name = "relacion_cuota_ingreso", precision = 5, scale = 2)
    private BigDecimal relacionCuotaIngreso;

    @Column(name = "tasa_anual", precision = 5, scale = 2)
    private BigDecimal tasaAnual;

    @Column(name = "cuota_mensual", precision = 8, scale = 2)
    private BigDecimal cuotaMensual;

    @Column(name = "total_pagar", precision = 12, scale = 2)
    private BigDecimal totalPagar;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "fecha_generado", nullable = false)
    private LocalDateTime fechaGenerado;

    @OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistorialEstado> historialEstados;

    @OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ObservacionAnalista> observaciones;

    @OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DocumentoAdjunto> documentosAdjuntos;

    @OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pagare> pagares;

    @OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contrato> contratos;

    public SolicitudCredito(String idSolicitud) {
        this.idSolicitud = idSolicitud;
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
        SolicitudCredito that = (SolicitudCredito) obj;
        return idSolicitud.equals(that.idSolicitud);
    }

    @Override
    public int hashCode() {
        return idSolicitud.hashCode();
    }
} 