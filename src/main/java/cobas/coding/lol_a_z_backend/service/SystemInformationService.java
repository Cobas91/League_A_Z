package cobas.coding.lol_a_z_backend.service;

import cobas.coding.lol_a_z_backend.model.SystemInformation;
import cobas.coding.lol_a_z_backend.repository.SystemInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemInformationService {
	@Autowired
	private SystemInformationRepository systemInformationRepository;

	public SystemInformation addInformation(SystemInformation systemInformation){
		return systemInformationRepository.save(systemInformation);
	}

	public SystemInformation getLatestInformation() {
		if (systemInformationRepository.findTopByOrderByCreateDateDesc() == null) {
			return SystemInformation.builder().champVersion("0").build();
		} else {
			return systemInformationRepository.findTopByOrderByCreateDateDesc();
		}
	}
}
