package es.exmaster.contasoc.util;

import es.exmaster.contasoc.Contasoc;
import es.exmaster.contasoc.common.Estado;
import es.exmaster.contasoc.database.ContasocDAO;
import es.exmaster.contasoc.persona.Socio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CodePlayground {
    public static void main(String[] args) {
        List<String> socios = new ArrayList<>();
        for (Socio s : ContasocDAO.leerTabla("Socios").stream().map(Parsers::socioParser).toList()) {
            if (s.getEstado() != Estado.INACTIVO) {
                String socio = s.getSocio() + ";" + s.getHuerto() + ";" + s.getPersona().getNombre() + ";"
                        + s.getPersona().getDni() + ";" + s.getPersona().getTelefono() + ";"
                        + s.getPersona().getCorreo() + ";" + Parsers.dateParser(s.getAlta()) + ";"
                        + Parsers.dateParser(s.getEntrega()) + ";" + Parsers.dateParser(s.getBaja()) + ";"
                        + s.getNotas() + ";"
                        + s.getTipo().toString().replace("A_E", "A DE E").replace("O_INVERNADERO", "O + INV") + ";"
                        + s.getEstado();
                socios.add(Arrays.stream(socio.split(";"))
                        .map(x -> x.equals("null") ? "" : x)
                        .collect(Collectors.joining(";")));
            }
        }
        PDFPrinter.printStringToPDF(socios, 12,
                new float[]{30f, 25f, 200f, 80f, 70f, 170f, 55f, 55f, 55f, 120f, 110f, 50f},
                "logohuerto_pdf.png", "Listado de socios", true, new String[]{"S", "H", "Nombre", "DNI",
                        "Teléfono", "Correo", "Alta", "Entrega", "Baja", "Notas", "Tipo", "Estado"},
                true, 8, Contasoc.ESCRITORIO + "/socios.pdf");
        ErrorHandler.pdfCreado();
    }
}