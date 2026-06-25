package br.edu.infnet.hospital_api.config;

import br.edu.infnet.hospital_api.models.Medico;
import br.edu.infnet.hospital_api.models.Paciente;
import br.edu.infnet.hospital_api.repositories.MedicoRepository;
import br.edu.infnet.hospital_api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void run(String... args) throws Exception {

        if (medicoRepository.count() ==  0 && pacienteRepository.count() == 0) {
            Medico medico1 = new Medico();
            medico1.setNome("Dr. Jonas");
            medico1.setCrm("CRM12345");
            medico1.setEspecialidade("Cardiologia");

            Medico medico2 = new Medico();
            medico2.setNome("Dra. Vanessa");
            medico2.setCrm("CRM54321");
            medico2.setEspecialidade("Ortopedia");

            medicoRepository.save(medico1);
            medicoRepository.save(medico2);

            Paciente paciente1 = new Paciente();
            paciente1.setNome("João Silva");
            paciente1.setCpf("111.111.111-11");
            paciente1.setDataNascimento(LocalDate.of(1970, 9, 23));
            paciente1.setTelefone("11111-1111");

            Paciente paciente2 = new Paciente();
            paciente2.setNome("Maria Oliveira");
            paciente2.setCpf("222.222.222-22");
            paciente2.setDataNascimento(LocalDate.of(1992, 3, 5));
            paciente2.setTelefone("22222-2222");

            pacienteRepository.save(paciente1);
            pacienteRepository.save(paciente2);
        }
    }
}
