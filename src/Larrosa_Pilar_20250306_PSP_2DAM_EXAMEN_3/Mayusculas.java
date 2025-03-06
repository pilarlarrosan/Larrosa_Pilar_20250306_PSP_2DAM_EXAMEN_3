package Larrosa_Pilar_20250306_PSP_2DAM_EXAMEN_3;

public class Mayusculas implements Runnable {

    // Atributos de la clase.
    private Buzon a_Buzon = null;
    private char a_IdObjeto = ' ';

    // Constructor que asigna los valores pasados por parámetro a los atributos de la clase.
    public Mayusculas(Buzon p_Buzon, char p_IdObjeto) {
        a_Buzon = p_Buzon;
        a_IdObjeto = p_IdObjeto;
    }

    // Sobrecarga de run() por usar la interfaz Runnable. Es aquí donde se realiza el trabajo de la tarea.
    @Override
    public void run() {

        String l_TextoMayus = "";

        // Mientras no se haya dado al orden de finalizar.
        while (!a_Buzon.isFinalizar()) {

            // Si no es su turno, espera permiso de acceso al Buzon.
            if (a_Buzon.getTurno() != a_IdObjeto) a_Buzon.esperar();

            // Si no se ha dado la orden de finalizar mientras estaba esperando.
            if (!a_Buzon.isFinalizar()) {

                // Si es la primera vuelta del programa (de minúsculas a mayúsculas).
                if (a_Buzon.getVuelta() == 1) {

                    // El texto en mayúsculas es una subcadena desde la primera letra del texto hasta donde indique el contador.
                    l_TextoMayus = MayusMinus.TEXTO_A_PROCESAR.substring(0, a_Buzon.getContador()).toUpperCase();

                } else {    // Si es la segunda vuelta del programa (de mayúsculas a minúsculas).

                    // El texto en mayúsculas es una subcadena desde donde indique el contador hasta el final.
                    l_TextoMayus = MayusMinus.TEXTO_A_PROCESAR.substring(a_Buzon.getContador()).toUpperCase();

                }

                // Deja el texto en mayúsculas en el Buzon.
                a_Buzon.setMayus(l_TextoMayus);

                // Cambia el turno a minúsculas.
                a_Buzon.setTurno(MayusMinus.ID_MINUS);

            }

            // Libera el permiso de acceso al Buzon.
            a_Buzon.notificar();

        }   // while()

    }   // run()

}   // Mayusculas