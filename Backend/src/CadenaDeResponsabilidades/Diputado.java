package CadenaDeResponsabilidades;

import java.sql.SQLOutput;

public class Diputado extends EmpleadoGob {

    private final int tipoDeAcceso = 1;



    public void leerDocumento(Documento documento){
        System.out.println("Este documento lo esta leyendo un Diputado y dice: " + documento.getContenido());
    }

    @Override
    public void procesarMensaje(Documento documento) {
        if (documento.isEstaAutorizado()) {
            if(documento.getLecturas().get("Presidente") == Boolean.TRUE && documento.getLecturas().get("Ministro") == Boolean.TRUE && documento.getLecturas().get("Diputado") == Boolean.TRUE){
                System.out.println("Fue leído por todos!");
            }else {
                if (documento.getTipo() == tipoDeAcceso && documento.getLecturas().get("Diputado")==null) {
                    this.leerDocumento(documento);
                    documento.addLectura("Diputado", Boolean.TRUE);
                }
                if (getSiguienteEmpleadoGob() != null) {
                    getSiguienteEmpleadoGob().procesarMensaje(documento);
                }
            }

        } else {
            System.out.println("Este mensaje no ha sido procesado, falta su autorización!");
        }
    }
}