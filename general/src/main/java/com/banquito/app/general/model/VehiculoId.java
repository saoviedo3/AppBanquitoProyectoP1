package com.banquito.app.general.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VehiculoId implements Serializable {

    @Column(name = "IdVehiculo", nullable = false)
    private Long IdVehiculo;
    @Column(name = "IdConsesionario", nullable = false)
    private Long IdConsesionario;
    @Column(name = "IdIdentificadorVehiculo", nullable = false)
    private Long IdIdentificadorVehiculo;

    public VehiculoId() {
    }

    public VehiculoId(Long idVehiculo, Long idConsesionario, Long idIdentificadorVehiculo) {
        IdVehiculo = idVehiculo;
        IdConsesionario = idConsesionario;
        IdIdentificadorVehiculo = idIdentificadorVehiculo;
    }

    public Long getIdVehiculo() {
        return IdVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        IdVehiculo = idVehiculo;
    }

    public Long getIdConsesionario() {
        return IdConsesionario;
    }

    public void setIdConsesionario(Long idConsesionario) {
        IdConsesionario = idConsesionario;
    }

    public Long getIdIdentificadorVehiculo() {
        return IdIdentificadorVehiculo;
    }

    public void setIdIdentificadorVehiculo(Long idIdentificadorVehiculo) {
        IdIdentificadorVehiculo = idIdentificadorVehiculo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IdVehiculo == null) ? 0 : IdVehiculo.hashCode());
        result = prime * result + ((IdConsesionario == null) ? 0 : IdConsesionario.hashCode());
        result = prime * result + ((IdIdentificadorVehiculo == null) ? 0 : IdIdentificadorVehiculo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VehiculoId other = (VehiculoId) obj;
        if (IdVehiculo == null) {
            if (other.IdVehiculo != null)
                return false;
        } else if (!IdVehiculo.equals(other.IdVehiculo))
            return false;
        if (IdConsesionario == null) {
            if (other.IdConsesionario != null)
                return false;
        } else if (!IdConsesionario.equals(other.IdConsesionario))
            return false;
        if (IdIdentificadorVehiculo == null) {
            if (other.IdIdentificadorVehiculo != null)
                return false;
        } else if (!IdIdentificadorVehiculo.equals(other.IdIdentificadorVehiculo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "VehiculoId [IdVehiculo=" + IdVehiculo + ", IdConsesionario=" + IdConsesionario
                + ", IdIdentificadorVehiculo=" + IdIdentificadorVehiculo + "]";
    }

}
