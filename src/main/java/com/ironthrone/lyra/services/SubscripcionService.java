package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.SubscripcionRequest;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Subscripcion;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.InstitucionPOJO;
import com.ironthrone.lyra.pojo.SubscripcionPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.InstitucionRepository;
import com.ironthrone.lyra.repositories.SubscripcionRepository;
import com.ironthrone.lyra.repositories.UsuarioRepository;
import com.ironthrone.lyra.security.RavenMail;


/**
 * Clase de tipo servicio, manejo de subscripciones y interacción con los repositorios correspondientes
 * @author Randall
 *
 */

@Service
public class SubscripcionService implements SubscripcionServiceInterface{
	
	@Autowired private SubscripcionRepository subscripcionRepository;
	@Autowired private InstitucionRepository institucionRepository;
	@Autowired private UsuarioRepository usersRepository;
	@Autowired private RavenMail raven;	
	//int tiempoRepeticion = 86400000; //Representa 24 horas
	
	private int primeraNotificacion = 30; //Cantidad de dias para la notificacion de vencimiento de la subscripcion
	private int segundaNotificacion = 7;
	private int terceraNotificacion = 0;
	private int vencido = -1;
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param users representa una lista de subscripciones tipo ejb
	 * @return Lista de Subscripcion POJO.
	 */
	private List<SubscripcionPOJO> generateSubscripcionDtos(List<Subscripcion> subscripcion){
		
		List<SubscripcionPOJO> uiSubscripciones = new ArrayList<SubscripcionPOJO>();
		
		subscripcion.stream().forEach(s -> {
			SubscripcionPOJO dto = new SubscripcionPOJO();
			BeanUtils.copyProperties(s,dto);
			dto.setActiveSub(s.getIsActiveSub());
			dto.setInstitucion(generateInstitucionDto(s));
			uiSubscripciones.add(dto);
		});	
		
		return uiSubscripciones;
	};
	
	/**
	 * Retorna una lista de Subscripcion.
	 * @return lista de Subscripcion POJO
	 */
	@Override
	@Transactional
	public List<SubscripcionPOJO> getAll() {
		List<Subscripcion> subscripciones = subscripcionRepository.findAll();
		return generateSubscripcionDtos(subscripciones);
	}

	/**
	 * Guarda los datos de una Subscripcion.
	 * @param SubscripcionRequest de la capa frontend.
	 * @return booleano, true = success, false = error.
	 */
	@Override
	@Transactional
	public Boolean saveSubscripcion(SubscripcionRequest subscripcionRequest) {
		
		Subscripcion newSubscripcion = new Subscripcion();
		Subscripcion nsubscripcionT = null;
		BeanUtils.copyProperties(subscripcionRequest.getSubscripcion(), newSubscripcion);
		
		//------------Institucion-------------/
		Institucion institucion = new Institucion();
		Institucion ninstitucionT = null;
		BeanUtils.copyProperties(subscripcionRequest.getSubscripcion().getInstitucion(), institucion);
		
		List<Usuario> us = new ArrayList<Usuario>(); 
		institucion.setUsuarios(us);
		
		List<Subscripcion> sub = new ArrayList<Subscripcion>(); 
		institucion.setSubscripcions(sub);
		
		ninstitucionT = institucionRepository.save(institucion);
		
		//------------Usuarios-------------/
		List<Usuario> oldUsuarios = new ArrayList<Usuario>();
		
		oldUsuarios = ninstitucionT.getUsuarios();//obtiene la lista de usuarios de la institución
		
		List<Usuario> usuariosFromRequest =  getUsers(subscripcionRequest.getSubscripcion().getInstitucion().getUsuarios());//obtiene los usuariosEJB del request
		
		oldUsuarios.stream().forEach(u ->{//Del arreglo viejo de usuarios se le añade los nuevos
			usuariosFromRequest.add(u);	
		});
		
		setInstitutionsToUsers(usuariosFromRequest, ninstitucionT);//Agrega la institución a cada usuario
		
		institucion.setUsuarios(usuariosFromRequest);
		
		ninstitucionT.addSubscripcion(newSubscripcion);
		
		newSubscripcion.setInstitucion(ninstitucionT);
		//------------------------------------/
		
		if(subscripcionRequest.getSubscripcion().getIdSubscripcion() <= -1){		
			
			newSubscripcion.setIsActiveSub(true);
			newSubscripcion.setFechaInicio(getCurrentDate());
			newSubscripcion.setFechaFin(plusOneYear());
			nsubscripcionT = subscripcionRepository.save(newSubscripcion);
			
		}else{		
			Subscripcion oldSubscripcion = findById(newSubscripcion.getIdSubscripcion());
			
			BeanUtils.copyProperties(newSubscripcion, oldSubscripcion);
			oldSubscripcion.setIsActiveSub(subscripcionRequest.getSubscripcion().isActiveSub());
			
			nsubscripcionT = subscripcionRepository.save(oldSubscripcion);	
		}

		return (nsubscripcionT == null) ? false : true;
	}

	/**
	 * Retorna un objeto SubscripcionPOJO.
	 * @param idSubscripcionPOJO, identificador único de la Subscripcion.
	 * @return Subscripcion de tipo SubscripcionPOJO.
	 */
	@Override
	@Transactional
	public SubscripcionPOJO getSubscripcionById(int idSubscripcion) {
		
		Subscripcion subscripcion =  subscripcionRepository.findOne(idSubscripcion);
		SubscripcionPOJO dto = new SubscripcionPOJO();
		BeanUtils.copyProperties(subscripcion,dto);
	
		return dto;
	}

	/**
	 * Retorna una única subscripcion por ID.
	 * @param idSubscripcion, identificador único de la subscripción.
	 * @return Subscripción de tipo SubscripcionEJB.
	 */
	@Override
	@Transactional
	public Subscripcion findById(int idSubscripcion) {
		return subscripcionRepository.findOne(idSubscripcion);
	}
	
	/**
	 * Consigue la fecha de hoy
	 * @return Date
	 */
	public Date getCurrentDate(){
	   Date date = new Date();
	   return date;
	}
	
	/**
	 * Consigue una fecha de hoy a un año
	 * @return Date
	 */
	public Date plusOneYear(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 1);
	    return calendar.getTime();
	}
	
	/**
	 * Busca una lista de usuario ejb segun la lista de UsuarioPojo recibido
	 * @param List<UsuarioPOJO>
	 * @return Lista de usuarios de tipo EJB
	 */
	public List<Usuario> getUsers(List<UsuarioPOJO> users){
		List<Usuario> oldUsuarios= new ArrayList<Usuario>();
		users.stream().forEach(u ->{
			Usuario oldUser = usersRepository.findOne(u.getIdUsuario());
			oldUsuarios.add(oldUser);
		});
		return oldUsuarios;
	}
	
	/**
	 * Agrega a los usuarios una institución
	 * @param List<Usuario>
	 * @param Institucion
	 */
	public void setInstitutionsToUsers(List<Usuario> users, Institucion i){
		users.stream().forEach(u ->{
			u.getInstitucions().add(i);
		});
	}
	
	/**
	 * Genera una institucion de tipo POJO
	 * @param Subscripcion tipo EJB
	 * @return InstitucionPOJO
	 */
	private InstitucionPOJO generateInstitucionDto(Subscripcion s) {
		
		Institucion institucion =  institucionRepository.findOne(s.getInstitucion().getIdInstitucion());
		InstitucionPOJO dto = new InstitucionPOJO();
		BeanUtils.copyProperties(institucion,dto);
		dto.setHasSuscripcion(institucion.getHasSuscripcion());
		dto.setAlumnos(null);
		dto.setBitacoras(null);
		dto.setGrados(null);
		dto.setMaterias(null);
		dto.setSubscripcions(null);
		dto.setUsuarios(generateUserDto(institucion));
		
		return dto;
	}
	
	/**
	 * Retorna una lista de Usuarios POJO de una institución
	 * @param Institucion recibe un objeto Institución
	 * @return List<UsuarioPOJO> Lista de usuario de tipo POJO
	 */
	private List<UsuarioPOJO> generateUserDto(Institucion i) {
		
		List<UsuarioPOJO> users = new ArrayList<UsuarioPOJO>();
		
		i.getUsuarios().stream().forEach(u -> {
			UsuarioPOJO user = new UsuarioPOJO();
			BeanUtils.copyProperties(u, user);	
			user.setPassword("secret");
			user.setActiveUs(u.getIsActiveUs());
			u.getRols().stream().forEach(r -> {
				if(r.getIdRol() == 1){
					ArrayList<String> idRols = new ArrayList<String>();
					idRols.add("1");
					user.setIdRoles(idRols);
				}
			});
			user.setRols(null);
			user.setInstitucion(null);
			
			users.add(user);
		});	

		return users;
	};
	
	/**
	 * Retorna una lista de Subscripciones activas.
	 * @return List<SubscripcionPOJO> lista de Subscripcion POJO
	 */
	@Override
	@Transactional
	public List<SubscripcionPOJO> getAllByActive() {
		List<Subscripcion> subscripciones = subscripcionRepository.findByisActiveSubTrue();
		return generateSubscripcionDtos(subscripciones);
	}
	
	/**
	 * Compara la fecha actual con la fecha de vencimiento de las subscripciones activas
	 */
	@Override
	@Transactional
	@Scheduled(fixedDelay = 86400000)
	public void revisarVencimientos(){
		
		System.out.println("Revisando subscripciones " + getCurrentDate());
		
		List<SubscripcionPOJO> listSubs =  getAllByActive();
		
		listSubs.stream().forEach( s -> {
			long diferencia = getCurrentDate().getTime() - s.getFechaFin().getTime();
			System.out.println(s.getIdSubscripcion() + "->" + Math.floor(diferencia / (1000 * 60 * 60 * 24)));
			
			if(Math.floor(diferencia / (1000 * 60 * 60 * 24)) == primeraNotificacion){
				System.out.println("Notificar");
				enviarNotificacion(s, primeraNotificacion);
			}
			if(Math.floor(diferencia / (1000 * 60 * 60 * 24)) == segundaNotificacion){
				System.out.println("Notificar 2");
				enviarNotificacion(s, segundaNotificacion);
			}
			if(Math.floor(diferencia / (1000 * 60 * 60 * 24)) == terceraNotificacion){
				System.out.println("Notificar 3");
				enviarNotificacion(s, terceraNotificacion);
			}
			if(Math.floor(diferencia / (1000 * 60 * 60 * 24)) == vencido){
				System.out.println("vencido");
				enviarNotificacion(s, vencido);
				subscripcionAVencer(s);
			}
		});
		
	}
	
	/**
	 * Envía la notificación de vencimiento de la subscripción
	 * @param SubscripcionPOJO objecto subscripcionPOJO
	 * @param int numero de días
	 */
	private void enviarNotificacion (SubscripcionPOJO s, int dias){
		
		s.getInstitucion().getUsuarios().stream().forEach( u -> {
			if(u.getIdRoles() != null){
				System.out.println(u.getNombre());
				raven.subscriptionExpirationNotice(u.getEmail(), u.getNombre(), u.getApellido(), dias, s.getInstitucion().getNombreInstitucion());
			};
		});

	}
	
	/**
	 * Cambia el estado de actividad de la subscripción a inactivo o vencido
	 * @param SubscripcionPOJO
	 */
	private void subscripcionAVencer(SubscripcionPOJO s){
		
		Subscripcion oldSubscripcion = findById(s.getIdSubscripcion());
		oldSubscripcion.setIsActiveSub(false);
		
		subscripcionRepository.save(oldSubscripcion);	
	}
	
	/**
	 * Renovar la subscripción, crea una nueva con los datos de la subcripcion anterior
	 * @param SubscripcionRequest
	 * @return SubscripcionPOJO
	 */
	@Override
	@Transactional
	public SubscripcionPOJO renovarSubscripcion(SubscripcionRequest subscripcionRequest){
		
		Subscripcion subsc = new Subscripcion();
		Subscripcion savedSP = null;
		Subscripcion oldSubs = subscripcionRepository.findOne(subscripcionRequest.getSubscripcion().getIdSubscripcion());
		oldSubs.setIsActiveSub(false);
		
		Date d = plusOneYearFromOldDate(oldSubs.getFechaFin());
		
		Institucion institucion = institucionRepository.findOne(subscripcionRequest.getSubscripcion().getInstitucion().getIdInstitucion());
		
		subsc.setFechaInicio(getCurrentDate());
		subsc.setFechaFin(d);
		subsc.setIsActiveSub(true);
		subsc.setInstitucion(institucion);
		
		savedSP = subscripcionRepository.save(subsc);
		
		SubscripcionPOJO sp = new SubscripcionPOJO();
		BeanUtils.copyProperties(savedSP, sp);
		sp.setActiveSub(savedSP.getIsActiveSub());
		sp.setFechaFinString(DateFormatUtils.format(savedSP.getFechaFin(), "yyyy-MM-dd"));
		
		return sp;
	}
	
	/**
	 * Suma la fecha recibida, un año mas
	 * @param Date
	 * @return Date
	 */
	private Date plusOneYearFromOldDate(Date d){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.YEAR, 1);
	    return calendar.getTime();
	}
	
	
}
