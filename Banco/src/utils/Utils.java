package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// Classe utilitária para formatação de valores numéricos
public class Utils {

    // Criação de um formato específico para valores monetários
    static NumberFormat formantandoValores = new DecimalFormat("R$ #,##0.00");
    // Formato específico para datas no padrão brasileiro "dd/MM/yyyy"
    static SimpleDateFormat formatandoData = new SimpleDateFormat("dd/MM/yyyy");

    // Metodo estático que converte um valor Double para String, formatado como moeda brasileira
    public static String doubleToString(Double valor) {
        return formantandoValores.format(valor);  // Formata o valor no padrão "R$ #.##0,00"
    }
    // Metodo estático que converte um objeto Date para String, formatado como "dd/MM/yyyy"
    public static String dateToString(Date data) {
        return Utils.formatandoData.format(data);
    }


}