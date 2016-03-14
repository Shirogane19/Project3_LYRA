package com.ironthrone.lyra.services;

import javax.servlet.http.HttpSession;

import com.ironthrone.lyra.contracts.LoginRequest;
import com.ironthrone.lyra.contracts.LoginResponse;

/**
 * Declara los servicios de login.
 * @author jeanpaul
 *
 */

public interface LoginServiceInterface {

	public void checkUser(LoginRequest lr, LoginResponse response, HttpSession currentSession);

}
