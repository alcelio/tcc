package com.tc.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Alcélio Gomes Classe Utilitaria para WebServices
 */
public final class WsUtil {

	private static final int TIME_OUT = 20000;

	public static String formatarParaHora(String hraDouble) {

		hraDouble = hraDouble.replace(".", ":");

		// Caso 8
		if (hraDouble.length() == 1) {
			final StringBuilder sb = new StringBuilder();
			sb.append("0");
			sb.append(hraDouble);
			sb.append(":00");

			hraDouble = sb.toString();
		}

		// Caso 23
		if (hraDouble.length() == 2)
			hraDouble = hraDouble + ":00";

		// Caso 8:3
		if (hraDouble.length() == 3) {
			final StringBuilder sb = new StringBuilder();
			sb.append("0");
			sb.append(hraDouble);
			sb.append("0");

			hraDouble = sb.toString();
		}

		// Caso 23:5
		if (hraDouble.length() == 4)
			hraDouble = hraDouble + "0";

		return hraDouble;
	}

	public static String sendGet(String url) throws Exception {

		final URL obj = new URL(url);
		final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setConnectTimeout(TIME_OUT);
		con.setReadTimeout(TIME_OUT);
		con.setAllowUserInteraction(false);
		con.setDoOutput(true);

		con.setRequestMethod("GET");

		final BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		final StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	public static String sendPost(String url, String params) throws Exception {

		final URL obj = new URL(url);
		final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");

		final String urlParameters = params;

		// Send post request
		con.setDoOutput(true);
		final DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		final int responseCode = con.getResponseCode();

		System.out.println(responseCode);
		final BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		final StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return (response.toString());
	}
}
