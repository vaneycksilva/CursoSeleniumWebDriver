package br.pb.vaneyck.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// cria uma data dinâmica a partir do dia atual
public class DataUtils {

	public static Date obterDataComDiferencaDias(int dias) {
		
		// pega a data atual
		Calendar calendar = Calendar.getInstance();
		
		// adiciona os dias na data atual
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		
		// retorna uma instancia de Date
		return calendar.getTime();
	}
	
	public static String obterDataFormatada(Date data) {
		
		DateFormat dataFormatada = new SimpleDateFormat("dd/MM/YYYY");
		return dataFormatada.format(data);
	}
}
