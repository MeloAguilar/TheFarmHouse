import java.util.Scanner;

public class TheFarmhouse extends Inventario {
    public static void main(String[] args) {
        String eleccionEntrada;
        boolean pistola = false, linterna = false, llaveFinal = false, palanca = false, fotoLavabo = false,
                llaveEscalera = false, pastillaCordura = false, llavesCoche = false, finDelJuego = false;
        int habitacion = 0;
        Scanner sc = new Scanner(System.in);
        int pilasLinterna = 20, cordura = 100;
        //En este juego responderas por teclado con las opciones expuestas en los corchetes.
        //Hay varios requisitos. Para poder llegar a la planta de arriba tendrás que encontrar 3 objetos.
        //La linterna, las llaves del coche y la llave de las escaleras.
        //Para terminar el juego deberás encontrar como abrir la puerta de la izquierda.
        //Se necesita la llave Final y la palanca para entrar en la puerta final.
        //No hay un único final. Cuando abras la puerta final existen 6 finales malos y uno no tan malo.
        Conversaciones.nombreDelJuego();
        Conversaciones.pintarIntroduccion();
        String inicio;
        Conversaciones.pintarInicio();
        inicio = sc.nextLine().toLowerCase();
        while (!finDelJuego) {//Mientras que no se active el booleano de fin de juego no saldrá del bucle principal
            if (inicio.equals("si") || inicio.equals("no")) {//Aquí hacemos criba de cadenas para evitar errores
                switch (inicio) {//En este switch se decide si se inicia o no el juego
                    case "no":
                        Conversaciones.pintarNoInicial();
                        finDelJuego = true;
                        break;
                    case "si":
                        //Si entra en el si, el juego comenzará a partir de esta linea
                        Conversaciones.pintarSiInicial();
                        while (pilasLinterna > 0 && cordura > 0 && !finDelJuego) {
                            //Gracias a este while evitaremos repetir una y otra vez el metodo pintarSiInicial
                            System.out.println("¿Dónde quieres ir? \n" +
                                    "[salon]  [garaje]  [escaleras]\n" +
                                    "Inventario [cosas]");
                            eleccionEntrada = sc.nextLine().toLowerCase();
                            //Aquí encontraremos el inventario
                            if (!finDelJuego && eleccionEntrada.equals("cosas")) {
                                Inventario.cosas(pistola, linterna, llaveFinal, palanca
                                        , fotoLavabo, llaveEscalera, pastillaCordura,
                                        llavesCoche, finDelJuego, eleccionEntrada, pilasLinterna, cordura);
                                //Gracias a la opcion de salida del juego podrás salir siempre que quieras, a modo de "start"
                                Conversaciones.opcionSalirDelJuego();
                                String seguirJugando;
                                seguirJugando = sc.nextLine();
                                //Si elegimos seguir, la partida continuara
                                if (seguirJugando.equals("seguir")) {
                                    System.out.println("...Sigamos...");
                                    //Si elegimos salir el juego terminará
                                } else if (seguirJugando.equals("salir")) {
                                    Conversaciones.pintarNoInicial();
                                    finDelJuego = true;
                                    //Si escribimos cualquier otra cosa la partida continuará, pero no te preocupes que siempre
                                    //podras entrar de nuevo al menú de pausa escribiendo "cosas" en cualquiera de los dos main(Entrada y descansillo)
                                } else {
                                    System.out.println("");
                                    System.out.println("Elección indeterminada, vulelves al juego");
                                    System.out.println("");
                                }
                                //En esta linea se vuelve a hacer criba de cadenas para que no de error y le añadimos
                                //que el booleano de fin de juego no puede ser verdad, ya que sino saldrá del bucle
                            } else if (!finDelJuego && (eleccionEntrada.equals("salon") || eleccionEntrada.equals("garaje") || eleccionEntrada.equals("escaleras"))) {
                                switch (eleccionEntrada) {
                                    //Aqui tendremos que buscar entre tres zonas
                                    //Se aconseja empear por el salon.
                                    case "salon":
                                        String eleccionesSalon;
                                        Conversaciones.pintarEntradaSalon();
                                        pilasLinterna--;
                                        boolean token = false;
                                        //Creacion del token que se activará cuando se escoja la eleccion salir o el jugador muera.
                                        while (!token) {
                                            //Aqui tenemos la entrada al salon, con tres lugares donde buscar
                                            System.out.println("¿A qué quieres acercarte?");
                                            System.out.println("[cuadros]  [mesa]  [dibujos] [salir]");
                                            eleccionesSalon = sc.nextLine().toLowerCase();
                                            //Realizamos otra criba de cadenas para evitar errores
                                            if (eleccionesSalon.equals("cuadros") || eleccionesSalon.equals("mesa") || eleccionesSalon.equals("dibujos")) {
                                                //Se crea el switch que dara lugar a la eleccion entre los tres lugares del salon
                                                switch (eleccionesSalon) {
                                                    case "cuadros"://Aqui tendremos la caja fuerte, que es el lugar principal del salon
                                                        Conversaciones.pintarsalonCuadros();
                                                        String abrirCajaFuerte;
                                                        abrirCajaFuerte = sc.nextLine().toLowerCase();
                                                        //Para que no se cree un bucle indeseado o salgan errores se realiza un if para cada caso
                                                        if (abrirCajaFuerte.equals("probar")) {
                                                            //If para el caso en el que quieres probar
                                                            Conversaciones.pintarCuadrosProbar();
                                                            String numeroSecreto = "9585", contrasenya;
                                                            contrasenya = sc.nextLine();
                                                            //De nuevo if para que si el numero introducido no es correcto
                                                            //o es otro tipo de caracter, no salga del bucle
                                                            if (contrasenya.equals(numeroSecreto)) {
                                                                Conversaciones.pintarCuadrosProbarAcierto();
                                                                linterna = true;
                                                                pilasLinterna += 1;
                                                                pilasLinterna--;
                                                                token = true;//El token se activará siempre que se quiera salir de la estancia
                                                            } else {
                                                                //Si no se acierta nos lanzará un mensaje que nos devolverá al salon
                                                                System.out.println("Parece que no se abre, seguiré buscando por aquí");
                                                            }
                                                            //Tambien nos llevará de vuelta al salon si nuestra eleccion es "pasar"
                                                        } else if (abrirCajaFuerte.equals("pasar")) {
                                                            System.out.println("seguiré buscando");

                                                        } else {
                                                            //Para validar cualquier entrada de texto he realizado un else que recoja cualquier otra cadena
                                                            System.out.println("no creo que valga con eso");
                                                        }
                                                        break;
                                                    case "mesa":
                                                        //Aqui encontraremos solo informacion y pilas, bastante necesarias ya que equivalen a tus movimientos
                                                        Conversaciones.pintarSalonMesa();
                                                        pilasLinterna--;
                                                        System.out.println("Encuentras dos pilas en un cajon de la mesa");
                                                        pilasLinterna += 2;
                                                        break;
                                                    case "dibujos":
                                                        //Aqui tambien encontraremos informacion para resolver el acertijo de los cuadros
                                                        Conversaciones.pintarSalonDibujos();
                                                        pilasLinterna--;
                                                        break;
                                                }
                                                //Aqui se realiza la opcion para salir del salon manualmente
                                            } else if (eleccionesSalon.equals("salir")) {

                                                token = true;
                                                break;
                                            }

                                        }

                                        break;
                                    case "garaje":
                                        //Si entramos al garaje con todos los items que se encuentran dentro en nuestro poder
                                        //el programa nos expulsará de nuevo a la entrada con un mensaje de texto explicativo
                                        if (llavesCoche && llaveEscalera) {
                                            System.out.println("Hace demasiado frío aquí... mejor será seguir buscando.");
                                            //Ya que eleccionEntrada queda fuera de este bucle, para evitar que se repita cuando se imprime por pantalla
                                            //lo he realizado tambien en algunas zonas
                                            System.out.println("¿Dónde quieres ir? \n" +
                                                    "[salon]  [garaje]  [escaleras]\n" +
                                                    "Inventario [cosas]");
                                            eleccionEntrada = sc.nextLine().toLowerCase();
                                            //Si no tenemos las llaves del coche ni la llave de la escalera entraremos en el siguiente bucle
                                        } else {

                                            String eleccionGaraje1, eleccionGaraje2;
                                            Conversaciones.pintarEntradaGaraje();
                                            boolean tokenGaraje = false;
                                            //Lo que nos hará salir, ya sea con la propia opcion o con la perdida de cordura o pilas, será este token del garaje
                                            while (!tokenGaraje && cordura > 0 && pilasLinterna > 0) {
                                                pilasLinterna--;
                                                eleccionGaraje1 = sc.nextLine().toLowerCase();
                                                //Este mientras cerrará las posibilidades a las opciones no validas de entrar en el bucle
                                                while (eleccionGaraje1.equals("coche") || eleccionGaraje1.equals("taquillas")) {
                                                    switch (eleccionGaraje1) {
                                                        case "coche":
                                                            if (!llavesCoche) {
                                                                //Si te acercas al coche sin sus llaves solo saltará un cuadro de dialogo
                                                                Conversaciones.pintarNoLlavesCoche();
                                                                Conversaciones.printDondeGaraje();
                                                            } else if (llavesCoche) {
                                                                //Si tienes las llaves podrás encontrar las llaves de la escalera y un easter egg
                                                                tokenGaraje = false;
                                                                String libreta;
                                                                Conversaciones.pintarGararjeCoche();
                                                                llaveEscalera = true;
                                                                System.out.println("¿Quieres abrir la libreta?");
                                                                System.out.println("[si]  [no]");
                                                                libreta = sc.nextLine().toLowerCase();
                                                                switch (libreta) {//Opcion de abrir la libreta que contiene un easter egg
                                                                    case "si":
                                                                        Conversaciones.pintarCocheLibretaSi();
                                                                        Conversaciones.printDondeGaraje();
                                                                        break;
                                                                    case "no":
                                                                        System.out.println("(tú)No tengo tiempo para leer esto...");
                                                                        Conversaciones.printDondeGaraje();
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case "taquillas":
                                                            //Habiendo elegido las taquillas deberas elegir entre la derecha y la izquierda
                                                            Conversaciones.pintarGarajeTaquillas();
                                                            eleccionGaraje2 = sc.nextLine().toLowerCase();
                                                            if (eleccionGaraje2.equals("izq") || eleccionGaraje2.equals("der")) {
                                                                switch (eleccionGaraje2) {
                                                                    case "izq":
                                                                        if (!linterna) {
                                                                            //Si no tienes linterna tu cordura llegará a 0 aqui y se terminará el juego
                                                                            Conversaciones.pintarTaquillaIzquierda();
                                                                            cordura -= 100;
                                                                            System.out.println("Busca la linterna... Te será de ayuda en tu proximo viaje.");
                                                                            pilasLinterna--;
                                                                        } else if (linterna) {
                                                                            //Si tienes la linterna solo perderás vida,pero encontrarás algo interesante(easter egg)
                                                                            Conversaciones.pintarTaquillaIzquierda();
                                                                            cordura -= 25;
                                                                            pilasLinterna--;
                                                                            System.out.println("cordura-->" + cordura);
                                                                            Conversaciones.printDondeGaraje();

                                                                        }
                                                                        break;
                                                                    case "der":
                                                                        if (!linterna) {
                                                                            //Sin la linterna sufriras daño al coger las llaves del coche
                                                                            Conversaciones.pintarTaquillaDerechaNoLinterna();
                                                                            cordura -= 70;
                                                                            pilasLinterna++;
                                                                            llavesCoche = true;
                                                                            System.out.println("cordura-->" + cordura);
                                                                            pilasLinterna--;
                                                                            Conversaciones.printDondeGaraje();
                                                                        } else if (linterna) {
                                                                            //Con la linterna solo se abrirá un dialogo que te indicará que tienes la linterna
                                                                            Conversaciones.pintarTaquillaDerecha();
                                                                            llavesCoche = true;
                                                                            pilasLinterna--;
                                                                            Conversaciones.printDondeGaraje();
                                                                        }
                                                                        break;
                                                                }

                                                            }

                                                            break;
                                                    }


                                                    break;
                                                }
                                                //Si la opcion en el garaje es salir el token se activará y volveremos a la entrada
                                                if (eleccionGaraje1.equals("salir")) {
                                                    tokenGaraje = true;
                                                }
                                            }
                                        }

                                        break;
                                    case "escaleras":
                                        //Hasta que no consigamos la llave de la escalera no se podrá subir
                                        if (!llaveEscalera) {
                                            System.out.println("La puerta está cerrada");
                                        } else {
                                            //Para que no se vuelva a repetir la intro he realizado un while que la excluye del bucle
                                            Conversaciones.pintarEntradaEscaleras();
                                            while (!finDelJuego && ((cordura > 0) && (pilasLinterna > 0))) {
                                                Conversaciones.pintarOpcionesDescansillo();
                                                String descansillo;
                                                descansillo = sc.nextLine().toLowerCase();
                                                if (descansillo.equals("der1") || descansillo.equals("der2")
                                                        || descansillo.equals("delante") || descansillo.equals("izq") || descansillo.equals("cosas")) {
                                                    if (!finDelJuego && descansillo.equals("cosas")) {
                                                        Inventario.cosas(pistola, linterna, llaveFinal, palanca
                                                                , fotoLavabo, llaveEscalera, pastillaCordura,
                                                                llavesCoche, finDelJuego, descansillo, pilasLinterna, cordura);
                                                        Conversaciones.opcionSalirDelJuego();
                                                        String seguirJugando;
                                                        seguirJugando = sc.nextLine();
                                                        if (seguirJugando.equals("seguir")) {
                                                            System.out.println("...Sigamos...");
                                                        } else if (seguirJugando.equals("salir")) {
                                                            Conversaciones.pintarNoInicial();
                                                            token = true;
                                                            finDelJuego = true;
                                                        } else {
                                                            System.out.println("");
                                                            System.out.println("Elección indeterminada, vulelves al juego");
                                                            System.out.println("");
                                                        }
                                                        if (!finDelJuego) {

                                                        }
                                                    }

                                                    if (!finDelJuego && pastillaCordura && descansillo.equals("der1")) {
                                                        cordura = 100;
                                                        System.out.println("Vuelves al descansillo");

                                                    }

                                                    if (cordura <= 0 || pilasLinterna <= 0) {
                                                        finDelJuego = true;
                                                    }

                                                    boolean tokenDescansillo = false;
                                                    switch (descansillo) {
                                                        case "der1":
                                                            if (pastillaCordura) {
                                                                System.out.println("No creo que haya nada más aquí...");
                                                                tokenDescansillo = true;
                                                            } else {
                                                                char hab1;
                                                                Conversaciones.pintarHabDerechaUno();
                                                                hab1 = sc.next().charAt(0);
                                                                switch (hab1) {
                                                                    case 's':
                                                                        pilasLinterna--;
                                                                        pastillaCordura = true;
                                                                        if (linterna) {
                                                                            Conversaciones.pintarCogerPildoras();
                                                                            pilasLinterna += 5;
                                                                            System.out.println("Vuelves al descansillo");
                                                                            sc.nextLine();
                                                                        } else {
                                                                            cordura -= 50;
                                                                            Conversaciones.pintarHabitacionUnoPastillasMal();
                                                                            pilasLinterna += 5;
                                                                            System.out.println("Vuelves al descansillo");

                                                                        }
                                                                        break;
                                                                    case 'n':
                                                                        System.out.println("Es... Es mejor no arriesgarse...");

                                                                        break;

                                                                }
                                                            }
                                                            break;
                                                        case "der2":
                                                            String camas;
                                                            Conversaciones.pintarHabitacionDosInicio();
                                                            boolean tokenCamas = false;
                                                            while (!tokenCamas) {
                                                                tokenCamas = false;
                                                                System.out.println("¿Quieres acercarte y buscar en las camas?");
                                                                System.out.println("[si]  [no]");
                                                                camas = sc.nextLine().toLowerCase();
                                                                if (camas.equals("si") || camas.equals("no"))
                                                                    switch (camas) {
                                                                        case "si":
                                                                            pilasLinterna--;
                                                                            String literas;
                                                                            Conversaciones.pintarOpcionesLiteras();
                                                                            literas = sc.nextLine().toLowerCase();
                                                                            if (literas.equals("izq") || literas.equals("der")) {
                                                                                switch (literas) {
                                                                                    case "izq":
                                                                                        Conversaciones.pintarLiterasIzquierdas();
                                                                                        pilasLinterna--;
                                                                                        pistola = true;
                                                                                        break;
                                                                                    case "der":
                                                                                        Conversaciones.pintarLiterasDerechas();
                                                                                        String cogerLibro;
                                                                                        System.out.println("Quieres ");
                                                                                        System.out.println("[si]  [no]");
                                                                                        cogerLibro = sc.nextLine().toLowerCase();
                                                                                        //Crear if de coger el libro
                                                                                        //Crear if de no coger el libro
                                                                                        //El libro se debe de poder soltar
                                                                                        if (cogerLibro.equals("no")) {
                                                                                            System.out.println("(tú)-No debería atreverme hasta estar completamente seguro");
                                                                                        } else if (cogerLibro.equals("si")) {
                                                                                            System.out.println("Introduces la mano en el mecanismo y comienzas a girar las pequeñas ruedas dentadas... ¿Cuál es el código?");
                                                                                            String numeroLibro = "793", intentoLibro;
                                                                                            intentoLibro = sc.nextLine();
                                                                                            if (intentoLibro.equals(numeroLibro)) {
                                                                                                Conversaciones.pintarAciertoLibro();
                                                                                                pilasLinterna--;
                                                                                                llaveFinal = true;
                                                                                                tokenCamas = true;
                                                                                            } else {
                                                                                                Conversaciones.pintarFalloLibro();
                                                                                                cordura -= 40;
                                                                                                System.out.println("cordura-->" + cordura);
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("(tú)-No se...");
                                                                                        }
                                                                                        break;
                                                                                    case "salir":
                                                                                        Conversaciones.pintarSalirCamas();
                                                                                        tokenCamas = true;
                                                                                        break;
                                                                                }
                                                                            }


                                                                            break;
                                                                        case "no":
                                                                            System.out.println("(tú)-No creo que pueda pasar nada bueno si me acerco más.");
                                                                            tokenCamas = true;

                                                                            break;

                                                                    }
                                                            }

                                                            break;
                                                        case "delante":
                                                            //Aqui entramos en el cuarto de baño.
                                                            Conversaciones.pintarEscalerasDelanteInicio();
                                                            if (!finDelJuego && cordura > 0 && pilasLinterna > 0) {
                                                                pilasLinterna--;
                                                                String acertijoFinal;
                                                                boolean tokenDelante = false;
                                                                //Mientras que el token no aparezca se mantendrá en el bucle.
                                                                while (!tokenDelante) {
                                                                    if ((pilasLinterna > 0 && cordura > 0)) {
                                                                        Conversaciones.pintarOpcionesBanyo();
                                                                        acertijoFinal = sc.nextLine().toLowerCase();
                                                                        if (acertijoFinal.equals("bañera") || acertijoFinal.equals("lavabo") ||
                                                                                acertijoFinal.equals("espejo")) {
                                                                            //Desde aqui se crea las opciones para ir a los tres lugares del cuarto de baño.
                                                                            switch (acertijoFinal) {
                                                                                case "bañera":
                                                                                    if(!palanca) {
                                                                                        pilasLinterna--;
                                                                                        String inspeccionBanyera;
                                                                                        Conversaciones.pintarOpcionesBanyera();
                                                                                        inspeccionBanyera = sc.nextLine().toLowerCase();
                                                                                        //Aqui no encontraremos nada, es solamente una trampa
                                                                                        if (inspeccionBanyera.equals("rapido")) {
                                                                                            boolean tokenBanyera = false;
                                                                                            Conversaciones.pintarDelanteBanyeraRapido();
                                                                                            pilasLinterna--;
                                                                                            cordura -= 70;
                                                                                            System.out.println("Cordura = "+cordura);
                                                                                            //Para salir del juego ponemos un if que devuelva el booleano del fin del juego si se acaban las pilas o la cordura
                                                                                            if (cordura <= 0 || pilasLinterna <= 0) {
                                                                                                finDelJuego = true;
                                                                                                tokenDelante = true;

                                                                                            }
                                                                                        } else if (inspeccionBanyera.equals("inspeccion")) {
                                                                                            //Si elegimos esta opción encontraremos la palanca, necesaria para abrir la puerta final
                                                                                            System.out.println("Mirando junto a la bañera encuentras una palanca oxidada");
                                                                                            palanca = true;
                                                                                            if (cordura <= 0 || pilasLinterna <= 0) {
                                                                                                finDelJuego = true;
                                                                                            }

                                                                                        } else {
                                                                                            System.out.println("....");
                                                                                        }
                                                                                    }else{
                                                                                        System.out.println("No quiero acercarme más ahí...");
                                                                                    }
                                                                                    break;
                                                                                case "lavabo":
                                                                                    //Aqui encontraremos un easter egg que se trata de una foto
                                                                                    if (finDelJuego) {
                                                                                        tokenDelante = true;

                                                                                    } else {
                                                                                        pilasLinterna--;
                                                                                        String inspeccionLavabo;
                                                                                        Conversaciones.pintarAcercarteLavabo();
                                                                                        inspeccionLavabo = sc.nextLine();
                                                                                        if (inspeccionLavabo.equals("tirar")) {
                                                                                            Conversaciones.pintarLavaboTirar();
                                                                                            pilasLinterna--;
                                                                                            if (cordura <= 0 || pilasLinterna <= 0) {
                                                                                                finDelJuego = true;
                                                                                                tokenDelante = true;
                                                                                            }
                                                                                        } else if (inspeccionLavabo.equals("pasar")) {
                                                                                            Conversaciones.pintarInspeccionLavaboPasar();
                                                                                            pilasLinterna--;
                                                                                            if (cordura <= 0 || pilasLinterna <= 0) {
                                                                                                finDelJuego = true;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case "espejo":
                                                                                    //Aqui encontramos el sobre del hospital que tambien contiene un easter egg
                                                                                    pilasLinterna--;
                                                                                    String sobreHospital;
                                                                                    String inspeccionEspejo;
                                                                                    Conversaciones.pintarSobreDEntroEspejo();
                                                                                    sobreHospital = sc.nextLine();
                                                                                    if (sobreHospital.equals("si")) {
                                                                                        Conversaciones.pintarAbrirSobre();
                                                                                    } else if (sobreHospital.equals("no")) {
                                                                                        System.out.println("En realidad no necesito saber más sobre esto...");
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        } else if (acertijoFinal.equals("salir")) {
                                                                            System.out.println("Quizá sea mejor buscar en otra zona...");
                                                                            tokenDelante = true;
                                                                           } else {
                                                                            tokenDelante = true;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                                break;
                                                                case "izq":
                                                                    System.out.println("Desde fuera escuchas voces... No parece que sean gritos, sino más bien");
                                                                    System.out.println("...¿risas?...");
                                                                    if (!llaveFinal || !palanca) {
                                                                        System.out.println("Podría quitar los tablones pero la puerta también está cerrada con llave");
                                                                    //Si tienes la palanca y la llave final podrás entrar en la ultima habitacion.
                                                                    } else if (palanca && llaveFinal) {
                                                                        boolean tokenFinDelJuego = false;
                                                                        String abrirPuertaFinal;
                                                                        Conversaciones.pintarAbrirPuertaFinal();
                                                                        abrirPuertaFinal = sc.nextLine().toLowerCase();
                                                                        if (abrirPuertaFinal.equals("si")) {
                                                                            //Si decides que quieres hacerlo comenzará el fin del juego.
                                                                            while (!tokenFinDelJuego) {
                                                                                if (cordura <= 0 || pilasLinterna <= 0) {
                                                                                    tokenFinDelJuego = true;
                                                                                }
                                                                                pilasLinterna--;
                                                                                String josh;
                                                                                Conversaciones.pintarEntradaPuertaFinal();
                                                                                pilasLinterna--;
                                                                                Conversaciones.pintarOpcionesPuertaFinal();
                                                                                josh = sc.nextLine();
                                                                                if (josh.equals("apuntar")) {
                                                                                    //Esta es la opción principal.
                                                                                    if (pistola) {
                                                                                        String disparoFinal;
                                                                                        System.out.println("¿Apuntar a Josh, al techo para asustarlo o al ente?");
                                                                                        System.out.println("[josh]  [techo]  [ente]");
                                                                                        disparoFinal = sc.nextLine().toLowerCase();
                                                                                        if (disparoFinal.equals("josh") || disparoFinal.equals("techo") || disparoFinal.equals("ente")) {
                                                                                            switch (disparoFinal) {
                                                                                                case "josh":
                                                                                                    //Esta es la opción principal, donde apuntamos a Josh.
                                                                                                    String desorientadoDescansillo;
                                                                                                    Conversaciones.pintarOpcionesJosh();
                                                                                                    desorientadoDescansillo = sc.nextLine();
                                                                                                    //Aquí tendremos varias opciones para seguir adelante.
                                                                                                    if (desorientadoDescansillo.equals("delante")) {
                                                                                                        //Esta opción hace que el juego termine..Mal.
                                                                                                        Conversaciones.pintarDesorientadoEnDescansilloDelante();
                                                                                                        tokenFinDelJuego = true;
                                                                                                        finDelJuego = true;
                                                                                                    } else if (desorientadoDescansillo.equals("izq")) {
                                                                                                        //Esta opcion tambien hace que el juego termine mal.
                                                                                                        Conversaciones.pintarDesorientadoEnDescansilloIzquierda();
                                                                                                        tokenFinDelJuego = true;
                                                                                                        finDelJuego = true;
                                                                                                    } else if (desorientadoDescansillo.equals("der")) {
                                                                                                        //Esta es la opción buena en este camino.
                                                                                                        String eleccionFinal1;
                                                                                                        Conversaciones.pintarDesorientadoEnPasillo();
                                                                                                        eleccionFinal1 = sc.nextLine().toLowerCase();
                                                                                                        switch (eleccionFinal1) {
                                                                                                            //Tendremos de nuevo dos opciones, para las de antes teniamos que conocernos la casa.
                                                                                                            //Para esta opcion solo tendremos que utilizar la intuicion.
                                                                                                            case "rodar":
                                                                                                                Conversaciones.pintarDesorientadoEnPasilloRodar();
                                                                                                                tokenFinDelJuego = true;
                                                                                                                finDelJuego = true;
                                                                                                                break;
                                                                                                            case "saltar":
                                                                                                                //Esta es la opcion correcta para terminar bien el juego.
                                                                                                                String muerteTablones;
                                                                                                                Conversaciones.pintarDesorientadoEnPasilloSaltar();
                                                                                                                muerteTablones = sc.nextLine();
                                                                                                                switch (muerteTablones) {
                                                                                                                    case "correr":
                                                                                                                        //Con esta opción saldremos con el final bueno de la historia.
                                                                                                                        Conversaciones.joshSaltarCorrer();
                                                                                                                        tokenFinDelJuego = true;
                                                                                                                        finDelJuego = true;
                                                                                                                        break;
                                                                                                                    case "cerrar":
                                                                                                                        //Si elegimos esta opcion se terminará el juego justo antes de finalizar.
                                                                                                                        Conversaciones.joshSaltarCerrar();
                                                                                                                        cordura = 0;
                                                                                                                        tokenFinDelJuego = true;
                                                                                                                        finDelJuego = true;
                                                                                                                        break;
                                                                                                                }
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case "techo":
                                                                                                    //Esta es una mala opcion.
                                                                                                    String disparoTecho;
                                                                                                    Conversaciones.pintarDisparoTecho();
                                                                                                    disparoTecho = sc.nextLine();
                                                                                                    //Si elegimos esta opcion el juego terminará.
                                                                                                    if (disparoTecho.equals("correr")) {
                                                                                                        Conversaciones.pintarDisparoTechoCorrer();
                                                                                                        tokenFinDelJuego = true;
                                                                                                        finDelJuego = true;
                                                                                                        //Con esta opción tambien terminará el juego.
                                                                                                    } else if (disparoTecho.equals("esconderse")) {
                                                                                                        Conversaciones.pintarDisparoTechoEsconderse();
                                                                                                        tokenFinDelJuego = true;
                                                                                                        finDelJuego = true;
                                                                                                    }
                                                                                                    break;
                                                                                                case "ente":
                                                                                                    //Con esta opción podremos encontrar la decision acertada para el mejor final del juego.
                                                                                                    String disparoEnte;
                                                                                                    Conversaciones.pintarDisparoEnte();
                                                                                                    disparoEnte = sc.nextLine().toLowerCase();
                                                                                                    if (disparoEnte.equals("saltar") || disparoEnte.equals("correr")) {
                                                                                                        switch (disparoEnte) {
                                                                                                            case "saltar":
                                                                                                                //Al saltar por la ventana termina el juego.
                                                                                                                Conversaciones.pintarDisparoEnteSaltar();
                                                                                                                finDelJuego = true;
                                                                                                                tokenFinDelJuego = true;
                                                                                                                break;
                                                                                                            case "correr":
                                                                                                                //Al esconderte saldrán dos opciones mas.
                                                                                                                String eleccionCorrerDisparoEnte;
                                                                                                                Conversaciones.pintarDisparoEnteEsconderse();
                                                                                                                eleccionCorrerDisparoEnte = sc.nextLine();
                                                                                                                //Al elegir la opcion para esquivar llegaremos al final predefinido como "bueno".
                                                                                                                if (eleccionCorrerDisparoEnte.equals("esquivar")) {
                                                                                                                    String esquivarEnteDisparoEnte;
                                                                                                                    Conversaciones.pintarDisparoEnteEsquivar();
                                                                                                                    esquivarEnteDisparoEnte = sc.nextLine();
                                                                                                                    switch (esquivarEnteDisparoEnte) {
                                                                                                                        case "correr":
                                                                                                                            Conversaciones.pintarEsquivarEnteCorrer();
                                                                                                                            finDelJuego = true;
                                                                                                                            tokenFinDelJuego = true;
                                                                                                                            break;
                                                                                                                        case "cerrar":
                                                                                                                            Conversaciones.pintarEsquivarEnteCerrar();
                                                                                                                            System.out.println("FIN");
                                                                                                                            tokenFinDelJuego = true;
                                                                                                                            finDelJuego = true;
                                                                                                                            break;
                                                                                                                    }
                                                                                                                } else if (eleccionCorrerDisparoEnte.equals("enfrentar")) {
                                                                                                                    //Un poco de risa nunca viene mal y por supuesto enfrentar al ente hace que el juego termine.
                                                                                                                    Conversaciones.pintarEnfrentarEnte();
                                                                                                                    tokenFinDelJuego = true;
                                                                                                                    cordura = 0;
                                                                                                                    finDelJuego = true;
                                                                                                                }
                                                                                                                break;
                                                                                                        }

                                                                                                    }else{
                                                                                                        System.out.println("(tú)-No sé...");
                                                                                                    }
                                                                                                    break;

                                                                                            }
                                                                                        }

                                                                                    } else {
                                                                                        //Si no tienes pistola y eliges apuntar en las opciones de la habitacion izquierda acabarás mal.
                                                                                        Conversaciones.noTengoPistola();
                                                                                        finDelJuego = true;
                                                                                        cordura = 0;
                                                                                        tokenFinDelJuego = true;
                                                                                    }
                                                                                    //Aqui encontramos el último final malo.
                                                                                } else if (josh.equals("irse")) {
                                                                                    Conversaciones.puertaFinalirse();
                                                                                    tokenFinDelJuego = true;
                                                                                    finDelJuego = true;
                                                                                }

                                                                            }

                                                                        } else if (abrirPuertaFinal.equals("no")) {
                                                                            //Aqui se encuentra la opcion en la que decimos que no a entrar en la habitacion de la izquierda.
                                                                            System.out.println("(tú)-Mejor que siga buscando");
                                                                            tokenFinDelJuego = false;
                                                                        }

                                                                    }


                                                                    break;
                                                            }
                                                    } else{
                                                        System.out.println("(tú)-No sé...");


                                                    }

                                                }
                                            }
                                            break;
                                        }
                                }
                            }
                        //Aqui se encuentran los dos casos en los que morimos, ya sea por pérdida de cordura o por que se acaben la pilas.
                            if (pilasLinterna <= 0) {
                                Conversaciones.muertePorPilas();
                                finDelJuego = true;
                            } else if (cordura <= 0) {
                                Conversaciones.muertePorCordura();
                                finDelJuego = true;
                            }
                            break;//break del si inicial.
                        }
                } else{
                //Si en el inicio ponemos algo que no sea si o no nos saldrá este mensaje.
                    Conversaciones.noEntiendoInicial();
                    inicio = sc.nextLine();
                }
            }
        }
    //RUTA PRINCIPAL
//En primer lugar hay que ir al salon, elegir los cuadros y poner el codigo 9585 para conseguir la linterna
//Después, debemos ir al garaje, seleccionar las taquillas, luego elegir la taquilla derecha y conseguiremos la llave del coche
//A continuacion nos dirigimos al coche y abrimos la libreta para coger la llave de la escalera
//Cuando estemos en la 2 planta, podemos entrar en la habitacion der1 para conseguir un bote de pastillas que nos recuperara cordura en el caso de que hayamos perdido algo.
//Luego debemos entrar a la habitacion der2 y buscar en la litera izquierda para conseguir la pistola y la litera derecha para encontrar el libro que con el codigo 793 se abrira y nos dara la llave de la habitacion derecha
//Para conseguir el ultimo objeto debemos ir la habitacion de delante e inspeccionar la bañera (no darle a la opcion rapido), asi, conseguiremos una palanca para quitar los tablones de la habitacion derecha
//Cuando entremos en la habitacion derecha debemos elegir la opción disparar y seguidamente disparariamos a josh
//Luego te da a elegir unas opciones de direccion en la cual debemos elegir la opcion derecha
//Seguidamente debemos elegir la opciones saltar los escalones y automaticamente elegir la opcion correr para salir de la casa sano y salvo
    }
