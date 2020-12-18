import java.util.Scanner;


    public class Inventario {
//Aqui encontramos el inventario de objetos junto con sus opciones.
        public static int cosas(boolean pistola, boolean linterna, boolean llavePequeña, boolean palanca, boolean fotoLavabo,
                                boolean llaveEscalera, boolean pastillaCordura,
                                boolean llavesCoche, boolean finDelJuego, String eleccionEntrada, int pilasLinterna,
                                int cordura) {
            Scanner sc = new Scanner(System.in);
            System.out.println("pilas: " + pilasLinterna);
            System.out.println("cordura: " + cordura);
            if (linterna == true) {
                System.out.println("linterna");
            }
            if (llavesCoche == true) {
                System.out.println("llave coche");
            }
            if (llaveEscalera == true) {
                System.out.println("llave escalera");
            }
            if (llavePequeña == true) {
                System.out.println("llave pequeña");
            }
            if (pistola == true) {
                System.out.println("pistola");
            }
            if (palanca == true) {
                System.out.println("palanca");
            }
            if (fotoLavabo == true) {
                char leerCarta;
                System.out.println("¿Quieres leer el reverso de la carta?");
                System.out.println("SI[s]    NO[n]");
                leerCarta = sc.nextLine().charAt(0);
                switch (leerCarta) {
                    case 's':
                        System.out.println("Joshua Howarthson\n" +
                                "16 de Diciembre de 2002");
                        System.out.println(".............");
                        System.out.println("No puede ser...\n" +
                                ".....\n" +
                                "Estos son los...¿abuelos de Josh?");
                        break;
                    case 'n':
                        System.out.println("Mejor la guardo");
                        break;
                }
            }
            //Siempre que tengamos las pastillas se nos lanzará la opción de utilizarla.
            if (pastillaCordura == true) {
                char usarPastillaCordura;
                System.out.println("Pastillas de Cordura");
                System.out.println("¿usar?");
                System.out.println("[si]  [no]");
                usarPastillaCordura = sc.nextLine().charAt(0);
                switch (usarPastillaCordura) {
                    case 's':
                        if (cordura < 80) {
                            pilasLinterna--;
                            cordura += 100;
                            System.out.println("Recuperaste la cordura al máximo");
                            pastillaCordura = false;
                        } else {
                            System.out.println("En realidad no creo que lo necesite ahora");
                        }
                        break;
                    case 'n':
                        System.out.println("Sí, creo debería dejarlo para cuando lo necesite");
                        break;
                }

            }


            //El método retornará la cantidad de cordura si se utiliza la pastilla.
            return (cordura);

        }
}
