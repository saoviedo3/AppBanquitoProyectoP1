package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Auditoria {

    @Id
    @Column(name = "id_auditoria", length = 20)
    private String idAuditoria;

    @Column(name = "tabla", length = 40, nullable = false)
    private String tabla;

    @Column(name = "id_registro", nullable = false)
    private Integer idRegistro;

    @Column(name = "accion", length = 6, nullable = false)
    private String accion;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "usuario", length = 50, nullable = false)
    private String usuario;

    public Auditoria(String idAuditoria) {
        this.idAuditoria = idAuditoria;
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
        Auditoria auditoria = (Auditoria) obj;
        return idAuditoria.equals(auditoria.idAuditoria);
    }

    @Override
    public int hashCode() {
        return idAuditoria.hashCode();
    }
} 