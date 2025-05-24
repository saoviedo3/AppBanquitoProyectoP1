package com.banquito.gestion_documentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "identificadores_vehiculos")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"vehiculo"})
public class IdentificadorVehiculo {

    @Id
    @Column(name = "id_identificador_vehiculo", length = 20)
    private String idIdentificadorVehiculo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "vin", length = 17, nullable = false, unique = true)
    private String vin;

    @Column(name = "numero_motor", length = 20, nullable = false)
    private String numeroMotor;

    @Column(name = "placa", length = 7)
    private String placa;

    public IdentificadorVehiculo(String idIdentificadorVehiculo) {
        this.idIdentificadorVehiculo = idIdentificadorVehiculo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IdentificadorVehiculo that = (IdentificadorVehiculo) obj;
        return idIdentificadorVehiculo.equals(that.idIdentificadorVehiculo);
    }

    @Override
    public int hashCode() {
        return idIdentificadorVehiculo.hashCode();
    }
} 