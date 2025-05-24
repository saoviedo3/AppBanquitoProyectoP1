package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_documentos")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TipoDocumento {

    @Id
    @Column(name = "id_tipo_documento", length = 20)
    private String idTipoDocumento;

    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 150)
    private String descripcion;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    public TipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TipoDocumento that = (TipoDocumento) obj;
        return idTipoDocumento.equals(that.idTipoDocumento);
    }

    @Override
    public int hashCode() {
        return idTipoDocumento.hashCode();
    }
} 