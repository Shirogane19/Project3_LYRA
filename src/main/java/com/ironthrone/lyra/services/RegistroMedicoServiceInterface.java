package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.RegistroMedicoRequest;
import com.ironthrone.lyra.ejb.RegistrosMedico;
import com.ironthrone.lyra.pojo.RegistroMedicoPOJO;

/**
 * Interfaz de servicio de Registro Medico
 * @author jeanpaul
 *
 */

public interface RegistroMedicoServiceInterface {
	
	List<RegistroMedicoPOJO> getAll(RegistroMedicoRequest rmr);
	List<RegistrosMedico> getRegistrosByAlumno (int idAlumno);
	Boolean saveRegistro (RegistroMedicoRequest rmr);
	Boolean deleteRegistro (RegistroMedicoRequest rmr);
}
