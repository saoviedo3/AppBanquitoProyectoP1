package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "concesionarios")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"vendedores", "vehiculos"})
public class Concesionario {

    @Id
    @Column(name = "id_concesionario", length = 20)
    private String idConcesionario;

    @Column(name = "razon_social", length = 80, nullable = false)
    private String razonSocial;

    @Column(name = "direccion", length = 120, nullable = false)
    private String direccion;

    @Column(name = "telefono", length = 20, nullable = false)
    private String telefono;

    @Column(name = "email_contacto", length = 50, nullable = false)
    private String emailContacto;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @OneToMany(mappedBy = "concesionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vendedor> vendedores;

    @OneToMany(mappedBy = "concesionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vehiculo> vehiculos;

    public Concesionario(String idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Concesionario that = (Concesionario) obj;
        return idConcesionario.equals(that.idConcesionario);
    }

    @Override
    public int hashCode() {
        return idConcesionario.hashCode();
    }
} 