package com.ry.filter;
/**
 * 
 * @author Runhang
 *
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractFilter implements Filter{
	
	private static Logger log = LoggerFactory.getLogger("AbstractFilter");
	
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("init..");
	}

	public abstract void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException;

	public void destroy() {
		log.debug("destroy");
	}

}
