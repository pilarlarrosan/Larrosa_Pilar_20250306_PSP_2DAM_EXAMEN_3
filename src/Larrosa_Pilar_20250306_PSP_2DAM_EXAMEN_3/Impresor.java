package Larrosa_Pilar_20250306_PSP_2DAM_EXAMEN_3;

public class Impresor implements Runnable {

    // Atributos de la clase.
    private Buzon a_Buzon = null;
    private char a_IdObjeto = ' ';

    // Constructor que asigna los valores pasados por parámetro a los atributos de la clase.
    public Impresor(Buzon p_Buzon, char p_IdObjeto) {
        a_Buzon = p_Buzon;
        a_IdObjeto = p_IdObjeto;
    }

    // Sobrecarga de run() por usar la interfaz Runnable. Es aquí donde se realiza el trabajo de la tarea.
    @Override
    public void run() {

        // Mientras no se haya dado al orden de finalizar.
        while (!a_Buzon.isFinalizar()) {

            // Si no es su turno, espera permiso de acceso al Buzon.
            if (a_Buzon.getTurno() != a_IdObjeto) a_Buzon.esperar();

            // Si no se ha dado la orden de finalizar mientras estaba esperando.
            if (!a_Buzon.isFinalizar()) {

                // Realiza una pausa para generar la cadencia indicada.
                try {
                    Thread.sleep(MayusMinus.MS_PAUSA_IMPRIMIR);
                } catch (InterruptedException p_Excepcion) {
                    System.out.println("ERROR: Ha fallado el sleep() del impresor.");
                }

                // Si es la primera vuelta del programa (de minúsculas a mayúsculas).
                if (a_Buzon.getVuelta() == 1) {

                    // Imprime primero las mayúsculas y luego las minúsculas.
                    System.out.println(a_Buzon.getMayus() + a_Buzon.getMinus());

                } else {    // Si es la segunda vuelta del programa (de mayúsculas a minúsculas).

                    // Imprime primero las minúsculas y luego las mayúsculas.
                    System.out.println(a_Buzon.getMinus() + a_Buzon.getMayus());

                }

                // Incrementa el contador.
                a_Buzon.incContador();

                // Cambia el turno a mayúsculas.
                a_Buzon.setTurno(MayusMinus.ID_MAYUS);

            }

            // Libera el permiso de acceso al Buzon.
            a_Buzon.notificar();

        }   // while()

    }   // run()

}   // Impresor