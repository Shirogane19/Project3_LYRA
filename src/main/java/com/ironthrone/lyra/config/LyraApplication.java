package com.ironthrone.lyra.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ironthrone.lyra.security.Reloj;


/** Clase inicial del proyecto Lyra.
 * 
 * @author jeanpaul
 * @version 1.0.0 Build Feb, 13, 2016
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.ironthrone.lyra"})
@EnableAutoConfiguration

@EnableJpaRepositories("com.ironthrone.lyra.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.ironthrone.lyra.ejb")

public class LyraApplication 
{
	/**
	 * Metodo que inicializa la apliaccion.
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(LyraApplication.class, args);
        Reloj reloj = new Reloj();
        
        try {
			reloj.Start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	@Bean	
	public FilterRegistrationBean filterRegistrationBean() {
		
		List<String> urls = new ArrayList<String>();
		urls.add("/*");
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		PassthroughFilter passFilter = new PassthroughFilter();
		registrationBean.setFilter(passFilter);
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
	
	public FilterRegistrationBean filterRegistrationBean2() {
		
		List<String> urls = new ArrayList<String>();
		urls.add("/rest/protected/*");
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		WSFilter wsFilter = new WSFilter();
		registrationBean.setFilter(wsFilter);
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
	
	
}
