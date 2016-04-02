package com.ironthrone.lyra.security;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.ironthrone.lyra.services.SubscripcionServiceInterface;

import java.util.Date;
 
@Component
public class Reloj {
 
	Timer timer = new Timer(); // El timer que se encarga de administrar los tiempo de repeticion
	public int segundos; // manejar el valor del contador
	
 
	// clase interna que representa una tarea, se puede crear varias tareas y asignarle al timer luego
	class MiTarea extends TimerTask {
		@Autowired private SubscripcionServiceInterface SSI;
		
		public void run() {
			segundos++;
			System.out.println(segundos);
			//SSI.revisarVencimientos();
			if(SSI == null){
				System.out.println("null");
			}
			
		}// end run()
	}// end SincronizacionAutomatica
 
	public void Start() throws Exception {
		
		Date horaDespertar = new Date(System.currentTimeMillis());
		
		Calendar c = Calendar.getInstance();
		c.setTime(horaDespertar);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		// Si la hora es posterior a las 8am se programa la alarma para el dia siguiente
		if (c.get(Calendar.HOUR_OF_DAY) >= 22) {
			c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
		}
		
		c.set(Calendar.HOUR_OF_DAY, 12);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		
		horaDespertar = c.getTime();
		System.out.println(horaDespertar);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		// El despertador suena cada 24h (una vez al dia)

		
//		WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext())
//        .getAutowireCapableBeanFactory().autowireBean(this);
		
//		final SubscripcionServiceInterface sii = this.SSI;
		
		//frozen = false;
		// le asignamos una tarea al timer
		timer.schedule(new MiTarea(), 0, 1000);
		
		
	}// end Start

 
}// end Reloj
