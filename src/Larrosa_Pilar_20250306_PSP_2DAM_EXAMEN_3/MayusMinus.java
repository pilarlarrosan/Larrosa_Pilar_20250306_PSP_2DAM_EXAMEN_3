package Larrosa_Pilar_20250306_PSP_2DAM_EXAMEN_3;

public class MayusMinus {

    // Constantes de clase.
    public static final char ID_MAYUS = 'A';
    public static final char ID_MINUS = 'B';
    public static final char ID_IMP = 'I';
    public static final String NOMBRE_HILO_MAYUS = "HILO-MAYUSCULAS";
    public static final String NOMBRE_HILO_MINUS = "HILO-MINUSCULAS";
    public static final String NOMBRE_HILO_IMP = "HILO-IMPRESOR";
    public static final int MS_PAUSA_IMPRIMIR = 1_000;
    public static final String TEXTO_A_PROCESAR = "En un lugar de la Mancha";
    public static final boolean EJECUCION_INFINITA = true;

    // Objeto para realizar el intercambio de información entre las clases.
    private static volatile Buzon a_Buzon = new Buzon();

    public static void main(String[] args) {

        // Crea un objeto Runnable para cada proceso.
        Mayusculas l_Mayusculas = new Mayusculas(a_Buzon, ID_MAYUS);
        Minusculas l_Minusculas = new Minusculas(a_Buzon, ID_MINUS);
        Impresor l_Impresor = new Impresor(a_Buzon, ID_IMP);

        // Crea un hilo para cada objeto Runnable.
        Thread l_HiloMayus = new Thread(l_Mayusculas);
        l_HiloMayus.setName(NOMBRE_HILO_MAYUS);
        Thread l_HiloMinus = new Thread(l_Minusculas);
        l_HiloMinus.setName(NOMBRE_HILO_MINUS);
        Thread l_HiloImp = new Thread(l_Impresor);
        l_HiloImp.setName(NOMBRE_HILO_IMP);

        // Avisa al gestor de que los hilos son elegibles.
        l_HiloMayus.start();
        l_HiloMinus.start();
        l_HiloImp.start();

    }   // main()

}   // MayusMinus