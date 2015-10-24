package com.tc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@javax.servlet.annotation.WebFilter("/*")
public class WebFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain arg2) throws IOException, ServletException {

		long tempoInicial = System.currentTimeMillis();

		String uri = null;
		try {
			uri = ((HttpServletRequest) req).getRequestURI();

			if (uri.contains(".jsf")) {
				arg2.doFilter(req, resp);
				return;
			}


			arg2.doFilter(req, resp);
		} finally {
			long tempoFinal = System.currentTimeMillis();

			System.out.println("Tempo da requisicao: " + uri + " - (ms): "
					+ (tempoFinal - tempoInicial));
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
