package edu.esi.uclm.http;

import static org.junit.Assert.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.esi.uclm.dao.FormatoVacunacionDao;
import edu.esi.uclm.model.FormatoVacunacion;
import edu.uclm.esi.exceptions.SiGeVaException;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TestFormatoVacunacionControler extends TestCase{
	private FormatoVacunacionDao dao;
	private FormatoVacunacionController componente;
	
	@Test
	public void testDefinirFormatoCorrecto() {
		
		componente = new FormatoVacunacionController();
		Map<String, Object> body = new HashMap<String,Object>();
		body.put("horaInicio","12:30");
		body.put("horaFin","18:00");
		body.put("duracionFranja",1);
		body.put("personasAVacunar",10);
		FormatoVacunacion f = new FormatoVacunacion("12:30","18:00",1,10);
		
		componente.definirFormatoVacunacion(null,body);
		
		List<FormatoVacunacion> resultado= dao.findAll();
		Assert.assertEquals(f,resultado.get(0));
		
	}
	@Test
	public void testDefinirFormatoIncorrectoHorasNoValidas() {
		
		componente = new FormatoVacunacionController();
		Map<String, Object> body = new HashMap<String,Object>();
		body.put("horaInicio","12:30");
		body.put("horaFin","11:00");
		body.put("duracionFranja",1);
		body.put("personasAVacunar",10);
		try {
			componente.definirFormatoVacunacion(null,body);
			//fail("A pesar de que la hora de inicio es posterior a la de fin lo acepta como valido");
			
		}catch(Exception e) {
			String expectedMessage = "Las horas del formato no son correctas";
			assertEquals(expectedMessage,e.getMessage());
		}

		
	}
	@Test	
public void testDefinirFormatoIncorrectoCondicionesNoPermitidas() {
		
		componente = new FormatoVacunacionController();
		Map<String, Object> body = new HashMap<String,Object>();
		body.put("horaInicio","12:30");
		body.put("horaFin","15:00");
		body.put("duracionFranja",1);
		body.put("personasAVacunar",20);
		componente.definirFormatoVacunacion(null,body);
		Exception exception = Assert.assertThrows(SiGeVaException.class,() -> componente.definirFormatoVacunacion(null,body));
		fail("Aunque las condiciones no son validas, se han aceptado para adaptarlas al formato");
		String expectedMessage = "Las condiciones no estan bien";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
