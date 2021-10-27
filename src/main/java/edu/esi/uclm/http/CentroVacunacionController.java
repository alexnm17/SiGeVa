package edu.esi.uclm.http;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.esi.uclm.dao.CentroVacunacionDao;
import edu.esi.uclm.model.CentroVacunacion;


@RestController
public class CentroVacunacionController {
	
	@Autowired
	private CentroVacunacionDao centroDao;
	
	@PostMapping("/addCentro")
	public void add(HttpServletRequest request,@RequestBody CentroVacunacion centro) {
		try {
			centroDao.save(centro);
		} catch(Exception e) {	
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}

}
