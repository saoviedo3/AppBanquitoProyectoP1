package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehiculos")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"concesionario"})
public class Vehiculo {

    @Id
    @Column(name = "id_vehiculo", length = 20)
    private String idVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concesionario", nullable = false)
    private Concesionario concesionario;

    @Column(name = "id_identificador_vehiculo", nullable = false)
    private Integer idIdentificadorVehiculo;

    @Column(name = "marca", length = 40, nullable = false)
    private String marca;

    @Column(name = "modelo", length = 40, nullable = false)
    private String modelo;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "valor", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "color", length = 30, nullable = false)
    private String color;

    @Column(name = "extras", length = 150)
    private String extras;

    public Vehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) obj;
        return idVehiculo.equals(vehiculo.idVehiculo);
    }

    @Override
    public int hashCode() {
        return idVehiculo.hashCode();
    }
} 