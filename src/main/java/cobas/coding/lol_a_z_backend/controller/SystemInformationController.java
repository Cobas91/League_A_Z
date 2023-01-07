package cobas.coding.lol_a_z_backend.controller;

import cobas.coding.lol_a_z_backend.model.SystemInformation;
import cobas.coding.lol_a_z_backend.service.SystemInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/system") public class SystemInformationController {

	@Autowired private SystemInformationService systemInformationService;

	@PostMapping public SystemInformation add(@RequestBody SystemInformation systemInformation) {
		return systemInformationService.addInformation(systemInformation);
	}

	@GetMapping public SystemInformation getLatest() {
		return systemInformationService.getLatestInformation();
	}

}
