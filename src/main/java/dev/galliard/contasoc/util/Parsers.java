package dev.galliard.contasoc.util;

import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.common.TipoSocio;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Parsers {
    public static String dateParser(Date date) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = date.toLocalDate();
        return localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
    }

    public static String dashDateParser(String date) {
        String res = null;
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoNuevo = new SimpleDateFormat("d MMM yyyy", new Locale("es", "ES"));
        try {
            if (!date.isEmpty()) {
                res = formatoNuevo.format(formatoOriginal.parse(date));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static String dashDateParserReversed(String date) {
        String res = null;
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("d MMM yyyy", new Locale("es", "ES"));
        SimpleDateFormat formatoNuevo = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (!date.isEmpty()) {
                if (date.isEmpty()) {
                    res = null;
                } else {
                    res = formatoNuevo.format(formatoOriginal.parse(date));
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return res;
    }


    public static String decimalSymbolParser(String cantidad) {
        if (cantidad.contains(",")) {
            cantidad.replace(",", ".");
        } else if (cantidad.contains("'")) {
            cantidad.replace("'", ".");
        }
        return cantidad;
    }

    public static String tipoHortelanoParser(String tipo) {
        if (tipo.equals("LISTA DE ESPERA")) {
            tipo = "LISTA_ESPERA";
        } else if (tipo.equals("HORTELANO + INV")) {
            tipo = "HORTELANO_INVERNADERO";
        }
        return tipo;
    }

    public static Ingresos ingresoParser(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] t = s.split(";");
        Integer socio = Integer.valueOf(t[0].trim());
        Date fecha = Date.valueOf(LocalDate.parse(t[1], formatter));
        String concepto = t[2];
        Double cantidad = Double.valueOf(t[3].trim());
        TipoPago tipo = TipoPago.valueOf(t[4]);
        return new Ingresos(socio, fecha, concepto, cantidad, tipo);
    }

    public static Socios socioParser(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] t = s.split(";");
        Integer socio = Integer.valueOf(t[0].trim());
        Integer huerto = Integer.valueOf(t[1].trim());
        String nombre = t[2];
        String dni = t[3];
        Integer telefono = t[4].equals("null") ? null : Integer.valueOf(t[4].trim());
        String correo = t[5];
        Date alta = Date.valueOf(LocalDate.parse(t[6], formatter));
        Date entrega = t[7].equals("null") ? null : Date.valueOf(LocalDate.parse(t[7], formatter));
        Date baja = t[8].equals("null") ? null : Date.valueOf(LocalDate.parse(t[8], formatter));
        String notas = t[9];
        TipoSocio tipo = TipoSocio.valueOf(t[10]);
        Estado estado = Estado.valueOf(t[11]);

        Socios res = new Socios(socio, huerto, nombre, dni, telefono, correo,
                alta, entrega, baja, notas, tipo, estado);

        return res;
    }

    public static Gastos pagoParser(String s) {
        String[] t = s.split(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date fecha = Date.valueOf(LocalDate.parse(t[0], formatter));
        String proveedor = t[1];
        String concepto = t[2];
        Double cantidad = Double.parseDouble(t[3].trim());
        String factura = t[4];
        TipoPago tipo = TipoPago.valueOf(t[5]);
        return new Gastos(fecha, proveedor, concepto, cantidad, factura, tipo);
    }
}
