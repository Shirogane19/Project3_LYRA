package com.ironthrone.lyra.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.AlumnoRequest;
import com.ironthrone.lyra.contracts.UsuarioRequest;
import com.ironthrone.lyra.ejb.Alumno;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Periodo;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.InstitucionPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.AlumnoRepository;
import com.ironthrone.lyra.repositories.PeriodoRepository;
import com.ironthrone.lyra.repositories.UsuarioRepository;


/**
 * Servicio que lee un archivo excel, separa los objetos y los inserta en la base de datos.
 * @author jeanpaul
 *
 */
@Service
public class XMLService implements XMLServiceInterface{

	@Autowired private POIServiceInterface poiService;
	
	//@Autowired private InstitucionServiceInterface instituteService;
	
	@Autowired private UsuarioServiceInterface usersService;
	@Autowired private AlumnoServiceInterface  studentService;
	@Autowired private PeriodoRepository periodoRepository;
	@Autowired private UsuarioRepository userRepository;
	@Autowired private AlumnoRepository studentRepository;
	
	/** Esta variable representa la institucion a la cual se le esta realizando la transaccion **/
	
			   private int institution;
			   
	/**Representa los usuarios en el sistema actual **/
			   
			   private static List<Usuario> actualUsers = new ArrayList<Usuario>();
	
	/**Representa los alumnos en el sistema actual **/
			   
			   private static List<Alumno> actualStudents = new ArrayList<Alumno>();
			   
	/**Estas variables representan el resultado de cada tipo de insercion en el servico
	  * Si una falla todo el proceso es abortado para evitar datos huerfanos **/
	  
			   private Boolean userResult;
			   private Boolean alumResult;

			   
			   
	@Override
	public Boolean bulkUpload(int idInstitucion, String file) {
		
		/**Resultado final de todo el proceso**/
		
		Boolean resultado = false;
		
		/** Lista de todos los objetos a insertar **/
		
		List<UsuarioPOJO>  users   = new ArrayList<UsuarioPOJO>();
		List<AlumnoPOJO>   students  = new ArrayList<AlumnoPOJO>();
		
		/** Primero consegimos la institucion a la cual se le van a agregar los datos **/
		institution = idInstitucion;
		
	//	System.out.println("El ID de la Insitucion es: " + idInstitucion);
		
		/**Instancio el File de XSLS que voy a usar en el servicio de Apache poi **/
		

			try {
				poiService.setEnviroment(file);
			} catch (IllegalStateException e) {
				
				e.printStackTrace();
				} catch (InvalidFormatException e) {
					
					e.printStackTrace();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
		
		
		users = poiService.getUsers(users);
		students = poiService.getAlumnos(students);
		
		if(users != null && students != null){
			setPeriodo();
			
			Institucion ints = new Institucion();
			ints.setIdInstitucion(institution);
			
			actualUsers    = userRepository.findByInstitucionsIn(ints);
			actualStudents = studentRepository.findByInstitucion(ints);
			userResult = insertUsers(users);
			alumResult = insertAlumnos(students);		
		}
		
		if(userResult && alumResult){
			resultado = true;
		}
		
		
		return resultado;
	}

	/**
	 * Metodo que crea un request de usuario por cada usuario
	 * en la lista de usuarios que venia en el excel y llama
	 * al metodo de saveUser en UsuarioService.
	 * @param users Lista de usuariosPOJO
	 * @return true si fue exitoso, false si fallo.
	 */
	
	@Override
	@Transactional
	public Boolean insertUsers(List<UsuarioPOJO> users) {
			
		/**Creo el request de usuario **/
		UsuarioRequest ur = new UsuarioRequest();
		
		Iterator<UsuarioPOJO> iteratorList = users.stream().iterator();
		/**Itero sobre la lista de usuarios, guardando cada uno **/
		
		while (iteratorList.hasNext()) {
			UsuarioPOJO u = iteratorList.next();
			u.setIdInstitucion(institution);			
		
			boolean emailExists = isExistingEmail(u.getEmail());
			
			if(emailExists){
				int id = userRepository.getUserIdbyEmail(u.getEmail());
				u.setIdUsuario(id);	

			}
			
			u.setActiveUs(true);
			ur.setUsuario(u);
			userResult = usersService.saveUser(ur);						
				
			if(!userResult){
				break;
			}
		};

		return userResult;
	}

	/**Metodo que busca en los usuarios actuales un correo que concuerde con alguno
	 * dado en el excel, de ser asi retorna true para que el usuarios sea modificado
	 * @param email
	 * @return true si existe, false si no.
	 */
	
    private static boolean isExistingEmail(String email) {
    	
        for (Usuario user: actualUsers) {
            // Checks if the user email is equal to the email parameter
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

	/**
	 * Metodo que crea un request de alumno por cada alumnos
	 * en la lista de alumnos que venia en el excel y llama
	 * al metodo de saveAlumnos en AlumnosService.
	 * @param users Lista de alumnosPOJO
	 * @return true si fue exitoso, false si fallo.
	 */
	
	@Override
	@Transactional
	public Boolean insertAlumnos(List<AlumnoPOJO> students) {

		/**Creo el request de alumno **/
		AlumnoRequest ar = new AlumnoRequest();
		
		InstitucionPOJO ins = new InstitucionPOJO();
		ins.setIdInstitucion(institution);
		
		Iterator<AlumnoPOJO> iteratorList = students.stream().iterator();
		/**Itero sobre la lista de alumnos, guardando cada uno **/
		
		while (iteratorList.hasNext()) {
			AlumnoPOJO a = iteratorList.next();
			a.setInstitucion(ins);

			boolean cedulaExists = isExistingCedula(a.getCedula());
			
			if(cedulaExists){
				Alumno alumno = studentRepository.findByCedula(a.getCedula());
				int id = alumno.getIdAlumno();
				a.setIdAlumno(id);	
			}
			
			a.setActiveAl(true);
			ar.setAlumno(a);
			alumResult = studentService.saveAlumno(ar);
			
			if(!alumResult){
				break;
			}
		};	

		return alumResult;
		
	}
	
	/**Metodo que busca en los alumnos actuales una cedula que concuerde con alguno
	 * dado en el excel, de ser asi retorna true para que el alunmno sea modificado
	 * @param email
	 * @return true si existe, false si no.
	 */
	
    private static boolean isExistingCedula(String cedula) {
    	
        for (Alumno a: actualStudents) {
            // Checks if the user's cedula is equal to the email parameter
            if (a.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }
    
	/**
	 * Al iniciar cada nueva carga se crea un nuevo periodo. El ultimo periodo activo es puesto en inactivo.
	 */
	@Transactional
	public void setPeriodo(){
		
		Periodo p = periodoRepository.findByIsActivePrTrue();
		p.setIsActivePr(false);
		
		periodoRepository.save(p);
		
		Periodo newPeriodo = new Periodo();
		newPeriodo.setIsActivePr(true);
		newPeriodo.setYear(getYear());
		
		periodoRepository.save(newPeriodo);

	}

	/** 
	 * Retorna un año en formato string
	 * @return año actual.
	 */
	public String getYear(){
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		
		return yearInString;
	}


}
