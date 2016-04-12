package com.ironthrone.lyra.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.StatsRequest;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Periodo;
import com.ironthrone.lyra.pojo.StatsPOJO;
import com.ironthrone.lyra.repositories.AlumnoRepository;
import com.ironthrone.lyra.repositories.InstitucionRepository;
import com.ironthrone.lyra.repositories.PeriodoRepository;
import com.ironthrone.lyra.repositories.MateriaRepository;
import com.ironthrone.lyra.repositories.UsuarioRepository;

@Service
public class StatsService implements StatsServiceInterface {

	
	@Autowired private PeriodoRepository periodoRepository;
	@Autowired private UsuarioRepository userRepository;
	@Autowired private AlumnoRepository studentRepository;
	@Autowired private InstitucionRepository schoolRepository;
	@Autowired private MateriaRepository materiaRepository;
	
	
	/**
	 * Metodo que inicializa y retorna los stats de una institucion.
	 */
	@Transactional
	@Override
	public StatsPOJO getStatsByInstitution(StatsRequest r) {
		
		StatsPOJO stats = new StatsPOJO();
		Institucion inst = new Institucion();
		inst = schoolRepository.findOne(r.getStats().getIdInstitucion());
		
		stats.setCantAlumnos((int)studentRepository.countByInstitucion(inst));
		stats.setCantUsuarios((int)userRepository.countByInstitucionsIn(inst));
		stats.setPeriodo(periodoRepository.findByIsActivePrTrue().getYear());
		stats.setCantMaterias((int)materiaRepository.countByInstitucion(inst));
		return stats;
		
		
	}
	
	@Override
	@Transactional
	@Scheduled(fixedDelay = 86400000)
	public void startPeriodo(){
		
		System.out.println("Revisando periodo");
		
		Periodo p = periodoRepository.findByIsActivePrTrue();
		String this_year = getYear();
		
		if(p == null){
			
			Periodo newPeriodo = new Periodo();
			newPeriodo.setIsActivePr(true);
			newPeriodo.setYear(getYear());	
			
			periodoRepository.save(newPeriodo);	
			
		}else{
			
			if(!p.getYear().equals(this_year)){
				
				p.setIsActivePr(false);
				periodoRepository.save(p);
				
				Periodo newPeriodo = new Periodo();
				newPeriodo.setIsActivePr(true);
				newPeriodo.setYear(getYear());	
				
				periodoRepository.save(newPeriodo);			
			}
		}
				
	}
	
	/** 
	 * Retorna un año en formato string
	 * @return año actual.
	 */
	private String getYear(){
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		
		return yearInString;
	}
	
	

}
