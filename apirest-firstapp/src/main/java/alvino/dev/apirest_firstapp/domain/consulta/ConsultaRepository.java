package alvino.dev.apirest_firstapp.domain.consulta;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime fecha);

    boolean existsByPacienteIdAndFechaBettween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}
