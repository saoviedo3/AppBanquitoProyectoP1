package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendedores")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"concesionario"})
public class Vendedor {

    @Id
    @Column(name = "id_vendedor", length = 20)
    private String idVendedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concesionario", nullable = false)
    private Concesionario concesionario;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "telefono", length = 20, nullable = false)
    private String telefono;

    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    public Vendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vendedor vendedor = (Vendedor) obj;
        return idVendedor.equals(vendedor.idVendedor);
    }

    @Override
    public int hashCode() {
        return idVendedor.hashCode();
    }
} 