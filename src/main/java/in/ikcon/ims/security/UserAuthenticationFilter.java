package in.ikcon.ims.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.ikcon.ims.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UserService service;

	private Environment environment;

	private BCryptPasswordEncoder encoder;

	private static final String TOKEN_EXPIRATION_PROPERTY = "token.expiration_time";
	private static final String LOGIN_ATTEMPTS = "loginAttempts";

	public UserAuthenticationFilter(UserService service, Environment environment, AuthenticationManager authManager,
			BCryptPasswordEncoder encoder) {
		log.info(
				"UserAuthenticationFilter() constructor entered with args - UserService,Environment and AuthenticationManager Objects.");
		this.service = service;
		this.environment = environment;
		this.encoder = encoder;
		super.setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.info("Location" + request.getHeader("loginCity"));
		log.info("attemptAuthentication() entered with args - HttpRequest and HttpResponse Objects.");
		// String clientIP = request.getRemoteAddr();
		String forwardedIP = request.getHeader("X-Forwarded-For");
		// String forwardedIP2 = request.getHeader("X-Real-IP");
		log.info("Login request received from " + forwardedIP);
		// String clientName = request.getRemoteHost();
		String deviceType = request.getHeader("User-Agent");
		// Define the regular expression pattern
		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		// Match the pattern against the input string
		Matcher matcher = pattern.matcher(deviceType);
		// Find the substring within brackets
		String orginalDeviceType = "";
		if (matcher.find()) {
			orginalDeviceType = matcher.group(1);
		}

		log.info("attemptAuthentication() excuted succesfully.");
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(StringUtils.EMPTY, StringUtils.EMPTY, new ArrayList<>()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info(
				"UserAuthenticationFilter.successfulAuthentication() entered with args - HttpRequest , HttpResponse , FilterChain & Authentication objects.");
		log.info("successfulAuthentication() executed successfully.");
		log.info("successfulAuthentication() User Authentication sucessfull.");
	}
}
