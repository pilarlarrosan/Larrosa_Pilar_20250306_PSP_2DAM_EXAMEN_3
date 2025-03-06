package Larrosa_Pilar_20250306_PSP_2DAM_EXAMEN_3;

public class Buzon {

    // Atributos de la clase.
    private char a_Turno = MayusMinus.ID_MAYUS;
    private String a_Mayus = "";
    private String a_Minus = "";
    private int a_Contador = 0;
    private int a_Vuelta = 1;
    private boolean a_Finalizar = false;

    public Buzon() {
    }

    // Métodos de la clase.
    public synchronized void esperar() {

        try {
            this.wait();
        } catch (InterruptedException p_Excepcion) {
            System.out.println("ERROR: No se puede ejecutar el wait() en el buzón.");
        }

    }   // esperar()

    public synchronized void notificar() {

        this.notify();

    }   // notificar()

    public synchronized char getTurno() {
        return a_Turno;
    }

    public synchronized void setTurno(char p_Turno) {
        a_Turno = p_Turno;
    }

    public synchronized String getMayus() {
        return a_Mayus;
    }

    public synchronized void setMayus(String p_Mayus) {
        a_Mayus = p_Mayus;
    }

    public synchronized String getMinus() {
        return a_Minus;
    }

    public synchronized void setMinus(String p_Minus) {
        a_Minus = p_Minus;
    }

    public synchronized int getContador() {
        return a_Contador;
    }

    public synchronized void incContador() {

        a_Contador++;

        // Si todavía no se han procesado todas las letras de la palabra.
        if (a_Contador < MayusMinus.TEXTO_A_PROCESAR.length()) {

            // Comprueba si la palabra es un espacio en blanco para, en ese caso, saltársela.
            if (MayusMinus.TEXTO_A_PROCESAR.charAt(a_Contador) == ' ') {
                a_Contador++;
            }

        } else if (a_Contador > MayusMinus.TEXTO_A_PROCESAR.length()){

            // Ya se han procesado todas las letras de la palabra.

            // Si aún está en la primera vuelta (pasando de minúsculas a mayúsculas).
            if (a_Vuelta == 1) {

                // Cambia los valores para poder realizar la segunda vuelta.
                a_Contador = 1;
                a_Vuelta = 2;

            } else {

                // Ya se han completado ambas vueltas del programa.

                // Si el programa se debe ejecutar una sola vez.
                if (!MayusMinus.EJECUCION_INFINITA) {

                    // Indica a los procesos que deben finalizar mediante la variable de control.
                    a_Finalizar =  true;

                } else {

                    // Cambia los valores para permitir la ejecución infinita.
                    a_Contador = 0;
                    a_Vuelta = 1;

                }

            }

        }

    }   // incContador()

    public synchronized int getVuelta() {
        return a_Vuelta;
    }

    public synchronized void setVuelta(int p_Vuelta) {
        a_Vuelta = p_Vuelta;
    }

    public synchronized boolean isFinalizar() {
        return a_Finalizar;
    }

    public synchronized void setFinalizar(boolean a_Finalizar) {
        a_Finalizar = true;
    }

}   // Buzon