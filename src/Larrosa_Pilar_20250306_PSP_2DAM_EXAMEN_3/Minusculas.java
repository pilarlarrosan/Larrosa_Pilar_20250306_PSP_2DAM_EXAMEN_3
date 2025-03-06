package Larrosa_Pilar_20250306_PSP_2DAM_EXAMEN_3;

public class Minusculas implements Runnable {

    // Atributos de la clase.
    private Buzon a_Buzon = null;
    private char a_IdObjeto = ' ';

    // Constructor que asigna los valores pasados por parámetro a los atributos de la clase.
    public Minusculas(Buzon p_Buzon, char p_IdObjeto) {
        a_Buzon = p_Buzon;
        a_IdObjeto = p_IdObjeto;
    }

    // Sobrecarga de run() por usar la interfaz Runnable. Es aquí donde se realiza el trabajo de la tarea.
    @Override
    public void run() {

        String l_TextoMinus = "";

        // Mientras no se haya dado al orden de finalizar.
        while (!a_Buzon.isFinalizar()) {

            // Si no es su turno, espera permiso de acceso al Buzon.
            if (a_Buzon.getTurno() != a_IdObjeto) a_Buzon.esperar();

            // Si no se ha dado la orden de finalizar mientras estaba esperando.
            if (!a_Buzon.isFinalizar()) {

                // Si es la primera vuelta del programa (de minúsculas a mayúsculas).
                if (a_Buzon.getVuelta() == 1) {

                    // El texto en minúsculas es una subcadena desde donde indique el contador hasta el final.
                    l_TextoMinus = MayusMinus.TEXTO_A_PROCESAR.substring(a_Buzon.getContador()).toLowerCase();

                } else {    // Si es la segunda vuelta del programa (de mayúsculas a minúsculas).

                    // El texto en minúsculas es una subcadena desde la primera letra del texto hasta donde indique el contador.
                    l_TextoMinus = MayusMinus.TEXTO_A_PROCESAR.substring(0, a_Buzon.getContador()).toLowerCase();

                }

                // Deja el texto en minúsculas en el Buzon.
                a_Buzon.setMinus(l_TextoMinus);

                // Cambia el turno a impresor.
                a_Buzon.setTurno(MayusMinus.ID_IMP);

            }

            // Libera el permiso de acceso al Buzon.
            a_Buzon.notificar();

        }   // while()

    }   // run()

}   // Minusculas