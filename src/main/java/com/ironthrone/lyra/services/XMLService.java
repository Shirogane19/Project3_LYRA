package com.ironthrone.lyra.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.AlumnoRequest;
import com.ironthrone.lyra.contracts.GradoRequest;
import com.ironthrone.lyra.contracts.MateriaRequest;
import com.ironthrone.lyra.contracts.UsuarioRequest;
import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.GradoPOJO;
import com.ironthrone.lyra.pojo.InstitucionPOJO;
import com.ironthrone.lyra.pojo.MateriaPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;


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
	@Autowired private GradoServiceInterface   gradeService;	
	@Autowired private MateriaServiceInterface materiaService;
	
	/** Esta variable representa la institucion a la cual se le esta realizando la transaccion **/
	
			   private int intitution;
			   
	/**Estas variables representan el resultado de cada tipo de insercion en el servico
	  * Si una falla todo el proceso es abortado para evitar datos huerfanos **/
	  
			   private Boolean userResult;
			   private Boolean alumResult;
			   private Boolean gradResult;
			   private Boolean mateResult;
			   
			   
	@Override
	public Boolean bulkUpload(int idInstitucion, String file) {
		
		/**Resultado final de todo el proceso**/
		
		Boolean resultado = false;
		
		/** Lista de todos los objetos a insertar **/
		
		List<UsuarioPOJO>  users   = new ArrayList<UsuarioPOJO>();
		List<GradoPOJO>    grados  = new ArrayList<GradoPOJO>();
		List<MateriaPOJO>  materias  = new ArrayList<MateriaPOJO>();
		List<AlumnoPOJO>   students  = new ArrayList<AlumnoPOJO>();
		
		/** Primero consegimos la institucion a la cual se le van a agregar los datos **/
		intitution = idInstitucion;
		
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
		grados = poiService.getGrados(grados);
		materias = poiService.getMaterias(materias);
		students = poiService.getAlumnos(students);
		
//		if(userResult && alumResult && gradResult && mateResult){
//			resultado = true;
//		}
		
		
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
			u.setIdInstitucion(intitution);
			
			ur.setUsuario(u);
			userResult = usersService.saveUser(ur);
			if(!userResult){
				break;
			}
		};

		return userResult;
	}

	/**
	 * Metodo que crea un request de grado por cada grado
	 * en la lista de grados que venia en el excel y llama
	 * al metodo de saveGrado en GradoService.
	 * @param users Lista de gradosPOJO
	 * @return true si fue exitoso, false si fallo.
	 */
	
	@Override
	@Transactional
	public Boolean insertGrados(List<GradoPOJO> grados) {
		
		/**Creo el request de grado **/
		GradoRequest gr = new GradoRequest();

		Iterator<GradoPOJO> iteratorList = grados.stream().iterator();
		/**Itero sobre la lista de grados, guardando cada uno **/

		while (iteratorList.hasNext()) {
			GradoPOJO g = iteratorList.next();
			g.setIdInstitucion(intitution);
			
			gr.setGrado(g);
			gradResult = gradeService.saveGrado(gr);
			if(!gradResult){
				break;
			}
		};

		return gradResult;
	}

	/**
	 * Metodo que crea un request de materia por cada materia
	 * en la lista de materias que venia en el excel y llama
	 * al metodo de saveMateria en MateriaService.
	 * @param users Lista de materiasPOJO
	 * @return true si fue exitoso, false si fallo.
	 */
	
	@Override
	@Transactional
	public Boolean insertMaterias(List<MateriaPOJO> materias) {
		
		/**Creo el request de materia **/
		MateriaRequest mr = new MateriaRequest();

		Iterator<MateriaPOJO> iteratorList = materias.stream().iterator();
		/**Itero sobre la lista de materias, guardando cada una **/

		while (iteratorList.hasNext()) {
			MateriaPOJO m = iteratorList.next();
			m.setIdInstitucion(intitution);
			
			mr.setMateria(m);
			mateResult = materiaService.saveMateria(mr);
			if(!mateResult){
				break;
			}
		};

		return mateResult;
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
		ins.setIdInstitucion(intitution);
		
		Iterator<AlumnoPOJO> iteratorList = students.stream().iterator();
		/**Itero sobre la lista de alumnos, guardando cada uno **/
		
		while (iteratorList.hasNext()) {
			AlumnoPOJO a = iteratorList.next();
			a.setInstitucion(ins);
			
			ar.setAlumno(a);
			alumResult = studentService.saveAlumno(ar);
			if(!alumResult){
				break;
			}
		};	

		return alumResult;
		
	}



}
