package alvino.dev.apirest_firstapp.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String estado;

    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.barrio = direccion.barrio();
        this.codigo_postal = direccion.codigo_postal();
        this.ciudad = direccion.ciudad();
        this.estado = direccion.estado();
    }

    public void actualizarDireccion(DatosDireccion direccion) {
        if(direccion.calle() != null) { this.calle = direccion.calle(); }
        if(direccion.numero() != null) { this.numero = direccion.numero(); }
        if(direccion.complemento() != null) { this.complemento = direccion.complemento(); }
        if(direccion.barrio() != null) { this.barrio = direccion.barrio(); }
        if(direccion.codigo_postal() != null) { this.codigo_postal = direccion.codigo_postal(); }
        if(direccion.ciudad() != null) { this.ciudad = direccion.ciudad(); }
        if(direccion.estado() != null) { this.estado = direccion.estado(); }
    }
}
