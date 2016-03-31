package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.RegistroMedicoRequest;
import com.ironthrone.lyra.ejb.Alumno;
import com.ironthrone.lyra.ejb.RegistrosMedico;
import com.ironthrone.lyra.pojo.RegistroMedicoPOJO;
import com.ironthrone.lyra.repositories.AlumnoRepository;
import com.ironthrone.lyra.repositories.RegistroMedicoRepository;

@Service
public class RegistroMedicoService implements RegistroMedicoServiceInterface {

	@Autowired RegistroMedicoRepository registroRepository;
	@Autowired AlumnoRepository alumnoRepository;

	/**
	 * Genera una lista de Objetos POJO dada una lista de objetos ejb
	 * @param registros medicos de tipo ejb
	 * @return lista de registros pojo
	 */
	private List<RegistroMedicoPOJO> generateRegistroDTO(List<RegistrosMedico> registros){
		
		List<RegistroMedicoPOJO> uiRecord = new ArrayList<RegistroMedicoPOJO>();
		
		registros.stream().forEach(r ->{
			
			RegistroMedicoPOJO dto = new RegistroMedicoPOJO();
			
			dto.setIdRegistro(r.getIdRegistros_Medicos());
			dto.setNombreRegistro(r.getNombreRegistro());
			dto.setDescripcion(r.getDescripcion());
			dto.setAlumno(null);
			
			uiRecord.add(dto);
			
		});
		
		return uiRecord;
	}
	
	/**
	 * Retorna todos los registros del historial medico de un alumno
	 * @param rmr registro medico request
	 * @return lista de registros pojo
	 */
	@Override
	@Transactional
	public List<RegistroMedicoPOJO> getAll(RegistroMedicoRequest rmr) {
		
		int idAlumno = rmr.getRegistro().getIdAlumno();
		List<RegistrosMedico> registros = getRegistrosByAlumno(idAlumno);
		
		return generateRegistroDTO(registros);
	}

	/**
	 * Retorna los registros de un alumno
	 * @param idAlumno del alumno al cual se desea consultar
	 * @return la lista de registros
	 */
	@Override
	public List<RegistrosMedico> getRegistrosByAlumno(int idAlumno) {
		
		return registroRepository.findByAlumno(getAlumno(idAlumno));
	}
	
	/**
	 * Consigue un alumno dado su ID unico.
	 * @param idAlumno del estudiante
	 * @return
	 */
	@Transactional
	public Alumno getAlumno (int idAlumno){
		
		Alumno alumno = alumnoRepository.findOne(idAlumno);
		return alumno;
	}

	/**
	 * Salva un nuevo registro, o bien modifica uno existente con los datos traidos 
	 * de la capa IU.
	 * @param rmr registro medico request
	 * @return true si es exitoso, false si fallo.
	 */
	@Override
	public Boolean saveRegistro(RegistroMedicoRequest rmr) {
		
		RegistrosMedico nreg = null;
		RegistrosMedico newRegistro = new RegistrosMedico();
		boolean isNew = false;
		
		if(rmr.getRegistro().getIdRegistro() <= -1){
			isNew = true;
			newRegistro = assignProperties(rmr.getRegistro(), newRegistro, isNew);	
			nreg = registroRepository.save(newRegistro);
			
		}else{
			isNew = false;
			RegistrosMedico oldRegister = registroRepository.findOne(rmr.getRegistro().getIdRegistro());
			oldRegister = assignProperties(rmr.getRegistro(), oldRegister, isNew);
			nreg = registroRepository.save(oldRegister);
					
		}
		
		return (nreg == null) ? false : true;
	}

	/**
	 * Reemplaza la funcion del copyBeanUtils, copia las propiedades de un objeto a otro.
	 * @param pojo objeto registro pojo traido de la capa UI.
	 * @param ejb  objeto registro ejb el cual se salvara en la base de datos.
	 * @param isNew variable que determina si se esta copiando a un objeto nuevo o uno existente.
	 * @return
	 */
	private RegistrosMedico assignProperties(RegistroMedicoPOJO pojo, RegistrosMedico ejb, boolean isNew) {
		
		if(isNew){
			ejb.setIdRegistros_Medicos(-1);
			ejb.setAlumno(getAlumno(pojo.getIdAlumno()));
			ejb.setNombreRegistro(pojo.getNombreRegistro());
			ejb.setDescripcion(pojo.getDescripcion());
		}else{
			ejb.setIdRegistros_Medicos(pojo.getIdRegistro());		
			ejb.setNombreRegistro(pojo.getNombreRegistro());
			ejb.setDescripcion(pojo.getDescripcion());			
			
		}
	
		return ejb;
	}

	/**
	 * Metodo que elimina un registro medico del historial de un alumno
	 * @param rmr registro medico request
	 * @return true si no se borro, false si fue exitoso.
	 */
	@Override
	public Boolean deleteRegistro(RegistroMedicoRequest rmr) {
		
		RegistrosMedico nreg = null;
		
		return (nreg == null) ? false : true;
	}

}
