
package com.duoc.depurandomiproyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepurandoMiProyecto {

public static void main(String[] args) {

        // Inicializacion de variable scanner
        Scanner scanner = new Scanner(System.in);

        // Declaracion de variables
        boolean bienvenida = false;
        int opcionBienvenida, opcionEvento, opcionEntrada;
        String compraEntrada;
        EntradaReservada entradaReservada;


        // Inicializacion de variables
        int precioJueves = 10000;
        int precioViernes = 12000;
        int precioSabado = 15000;

        // Inicializacion de listas
        String[] listaOpciones = {RESERVAR, COMPRAR, MODIFICAR, IMPRIMIR, SALIR};
        String[] listaEventos = {EVENTO_A, EVENTO_B, EVENTO_C};
        List<EntradaReservada> listaReservada = new ArrayList<>();
        List<EntradaReservada> listaReservadaFinal = new ArrayList<>();
        List<EntradaReservada> listaCompra = new ArrayList<>();
        /*List<Entrada>*/
        String[] listaA = {"1", "2", "3", "4", "5"};
        String[] listaB = {"1", "2", "3", "4", "5"};
        String[] listaC = {"1", "2", "3", "4", "5"};

        do {
            // Comienzo del programa
            System.out.println(BIENVENIDA);
            System.out.println(SALTO_DE_LINEA);
            System.out.println(MENU);
            // Ciclo for de bienvenida
            mostrarOpciones(listaOpciones);
            // Ingreso OpcionBienvenida
            System.out.println(CONTINUAR);
            opcionBienvenida = scanner.nextInt();
            try {
                if (opcionBienvenida <= 5 && opcionBienvenida >= 1) {
                    switch (opcionBienvenida) {
                        case 1:
                            // Reservar entradas - Venta
                            try {
                                mostrarTotalEntradas(listaEventos, precioJueves, precioViernes, precioSabado);
                                System.out.println(INGRESE_CODIGO);
                                opcionEvento = scanner.nextInt();
                                if (opcionEvento > 3) {
                                    bienvenida = validaOpcion();
                                } else {
                                    mostrarListaEntradas(opcionEvento, listaEventos, listaA, listaB, listaC);
                                    System.out.println(INGRESE_ENTRADA);
                                    opcionEntrada = scanner.nextInt();
                                    if (opcionEntrada > 5 || opcionEntrada < 1) {
                                        bienvenida = validaOpcion();
                                    } else {
                                        if (opcionEvento == 1) {
                                            entradaReservada = seleccionarEntrada(opcionEntrada, "A", listaA);
                                            listaReservadaFinal = agregaReservada(entradaReservada, listaReservada);
                                        } else if (opcionEvento == 2) {
                                            entradaReservada = seleccionarEntrada(opcionEntrada, "B", listaB);
                                            listaReservadaFinal = agregaReservada(entradaReservada, listaReservada);
                                        } else {
                                            entradaReservada = seleccionarEntrada(opcionEntrada, "C", listaB);
                                            listaReservadaFinal = agregaReservada(entradaReservada, listaReservada);
                                        }
                                        if (validaLista(listaReservadaFinal)) {
                                            System.out.println("Usted tiene " + listaReservadaFinal.size() + ENTRADAS_RESERVADAS);
                                            System.out.println(FINALIZAR);
                                            compraEntrada = scanner.next().toUpperCase();
                                            if (deseaContinuar(compraEntrada)) {
                                                listaCompra = comprarEntrada(listaA, listaB, listaC, listaReservadaFinal);
                                                iniciarTimer();
                                            }
                                            bienvenida = true;
                                        } else {
                                            System.out.println(DESEA_CONTINUAR);
                                            compraEntrada = scanner.next().toUpperCase();
                                            bienvenida = deseaContinuar(compraEntrada);
                                        }
                                    }
                                }
                            } catch (RuntimeException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 2:
                            try {
                                if (validaLista(listaReservadaFinal)) {
                                    System.out.println("Usted tiene " + listaReservadaFinal.size() + ENTRADAS_RESERVADAS);
                                    System.out.println(FINALIZAR);
                                    compraEntrada = scanner.next().toUpperCase();
                                    if (deseaContinuar(compraEntrada)) {
                                        listaCompra = comprarEntrada(listaA, listaB, listaC, listaReservadaFinal);
                                        iniciarTimer();
                                    }
                                    bienvenida = true;
                                } else {
                                    System.out.println("No tiene" + ENTRADAS_RESERVADAS);
                                    System.out.println(SALTO_DE_LINEA);
                                    bienvenida = true;
                                }
                            } catch (RuntimeException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 3:
                            try {
                                System.out.println("No hay indicaciones específicas en el documento");
                                System.out.println("para la modificacion de entradas");
                                System.out.println(DESEA_CONTINUAR);
                                compraEntrada = scanner.next().toUpperCase();
                                bienvenida = deseaContinuar(compraEntrada);
                            } catch (RuntimeException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 4:
                            try {
                                bienvenida = mostrarBoleta(listaCompra, precioJueves, precioViernes, precioSabado);
                            } catch (RuntimeException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 5:
                            bienvenida = false;
                            break;
                    }
                } else {
                    bienvenida = validaOpcion();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } while (bienvenida);

        scanner.close();
        System.out.println("*** Gracias por visitarnos ***");
    }

    public static void mostrarOpciones(String[] lista) {
        for (int i = 0; i < lista.length; i++) {
            System.out.println("- " + (i + 1) + ": " + lista[i]);
        }
    }

    public static boolean validaOpcion() {
        System.out.println("Opción incorrecta , vuelva a intentarlo");
        System.out.println(SALTO_DE_LINEA);
        return true;
    }

    public static void mostrarTotalEntradas(String[] lista, int jueves, int viernes, int sabado) {
        System.out.println(DISPONIBLES);
        for (int i = 0; i < lista.length; i++) {
            System.out.println(SALTO_DE_LINEA);
            System.out.println("* Obra " + lista[i] + ": Codigo [" + (i + 1) + "]");
            if (i == 0) {
                System.out.println(PRECIO + jueves);
            } else if (i == 1) {
                System.out.println(PRECIO + viernes);
            } else {
                System.out.println(PRECIO + sabado);
            }
        }
    }

    public static void mostrarListaEntradas(int opcion, String[] eventos, String[] vip, String[] platea, String[] general) {
        System.out.println("Entradas disponibles para :");
        switch (opcion) {
            case 1:
                System.out.println(eventos[0]);
                mostrarEntradas(vip);
                break;
            case 2:
                System.out.println(eventos[1]);
                mostrarEntradas(platea);
                break;
            case 3:
                System.out.println(eventos[2]);
                mostrarEntradas(general);
                break;
        }
    }

    public static void mostrarEntradas(String[] lista) {
        for (String item : lista) {
            System.out.print(" [" + item + "]");
        }
        System.out.println(SALTO_DE_LINEA);
    }

    public static EntradaReservada seleccionarEntrada(int entrada, String evento, String[] lista) {
        EntradaReservada entradaReservada = new EntradaReservada();
        entradaReservada.setEvento(evento);
        boolean reservada;
        if (!lista[entrada - 1].equals("X")) {
            lista[entrada - 1] = "X";
            System.out.println("Entrada " + entrada + " reservada");
            reservada = true;
            entradaReservada.setUbicacionReservada(String.valueOf(entrada));
        } else {
            System.out.println("Esa entrada ya fue reservada.");
            reservada = false;
            entradaReservada.setUbicacionReservada("");
        }
        entradaReservada.setReservada(reservada);
        return entradaReservada;
    }

    public static List<EntradaReservada> comprarEntrada(String[] listaA, String[] listaB, String[] listaC, List<EntradaReservada> listaReservada) {
        List<EntradaReservada> listaCompradas = new ArrayList<>();
        List<EntradaReservada> listaEliminar = new ArrayList<>();
        //BREAKPOINT INCLUIDO : validar ciclo for
        for (EntradaReservada entradaReservada : listaReservada) {
            //BREAKPOINT INCLUIDO : validar si entra al if
            if (entradaReservada.reservada) {
                String ubicacionStr = entradaReservada.ubicacionReservada;
                int posicion = Integer.parseInt(ubicacionStr) - 1;
                String vendida = "[Vendida]";
                //BREAKPOINT INCLUIDO : validar valor del switch
                switch (entradaReservada.evento) {
                    case "A":
                        if (posicion >= 0 && posicion < listaA.length) {
                            //BREAKPOINT INCLUIDO : validar si actualiza campo
                            listaA[posicion] = vendida;
                        }
                        break;
                    case "B":
                        if (posicion >= 0 && posicion < listaB.length) {
                            //BREAKPOINT INCLUIDO : validar si actualiza campo
                            listaB[posicion] = vendida;
                        }
                        break;
                    case "C":
                        if (posicion >= 0 && posicion < listaC.length) {
                            //BREAKPOINT INCLUIDO : validar si actualiza campo
                            listaC[posicion] = vendida;
                        }
                        break;
                }
                listaCompradas.add(entradaReservada);
                listaEliminar.add(entradaReservada);
            }
        }
        //BREAKPOINT INCLUIDO : validar removeall lista
        listaReservada.removeAll(listaEliminar);
        //BREAKPOINT INCLUIDO : validar return
        return listaCompradas;
    }

    public static void iniciarTimer() {
        System.out.println("Realizando compra ...");
        try {
            Thread.sleep(1000); // Espera 20 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Se ha realizado la compra exitosamente");
        System.out.println("* " + INGRESE4 + " *");
        System.out.println(SALTO_DE_LINEA);

    }

    public static boolean deseaContinuar(String opcion) {
        return opcion.equals("S");
    }

    public static boolean mostrarBoleta(List<EntradaReservada> lista, int precioA, int precioB, int precioC) {
        System.out.println("* Boleta de compra *");
        System.out.println("Cantidad de entradas Compradas : " + lista.size());
        int contadorA = 0;
        int contadorB = 0;
        int contadorC = 0;
        //BREAKPOINT INCLUIDO : validar ciclo for
        for (EntradaReservada entradaReservada : lista) {
            //BREAKPOINT INCLUIDO : validar si entra al evento A
            if (entradaReservada.getEvento().equals("A")) {
                System.out.println("Obra : " + EVENTO_A);
                System.out.println("Ubicacion Comprada : " + entradaReservada.getUbicacionReservada());
                System.out.println("Precio Unitario : " + precioA);
                //BREAKPOINT INCLUIDO : validar contador
                contadorA++;
                //BREAKPOINT INCLUIDO : validar si entra al evento B
            } else if (entradaReservada.getEvento().equals("B")) {
                System.out.println("Obra : " + EVENTO_B);
                System.out.println("Ubicacion Reservada : " + entradaReservada.getUbicacionReservada());
                System.out.println("Precio Unitario : " + precioB);
                contadorB++;
                //BREAKPOINT INCLUIDO : validar si entra al evento C
            } else {
                System.out.println("Obra : " + EVENTO_C);
                System.out.println("Ubicacion Reservada : " + entradaReservada.getUbicacionReservada());
                System.out.println("Precio Unitario : $ " + precioC);
                contadorC++;
                //BREAKPOINT INCLUIDO : validar contador
            }
            System.out.println("-------------------------------");
        }
        //BREAKPOINT INCLUIDO : validar total pagado
        int total = ((precioA * contadorA) + (precioB * contadorB) + (precioC * contadorC));
        System.out.println("Total Pagado : $ " + total);
        System.out.println(SALTO_DE_LINEA);
        return false;
    }

    public static List<EntradaReservada> agregaReservada(EntradaReservada entrada, List<EntradaReservada> lista) {
        if (entrada.isReservada()) {
            lista.add(entrada);
        }
        return lista;
    }

    public static boolean validaLista(List<EntradaReservada> lista) {
        for (EntradaReservada entrada : lista) {
            if (entrada.isReservada()) {
                return true;
            }
        }
        return false;
    }

    // Constantes
    public static final String COMPRAR = "Comprar Entrada";
    public static final String RESERVAR = "Reservar Entrada";
    public static final String MODIFICAR = "Modificar Entrada";
    public static final String IMPRIMIR = "Imprimir Boleta";
    public static final String FINALIZAR = "¿ Desea finalizar su compra [S]/[N]?";
    public static final String SALIR = "Salir";
    public static final String SALTO_DE_LINEA = " ";
    public static final String BIENVENIDA = "*** Bienvenido a Teatro del Moro ***";
    public static final String MENU = "Menu de Venta :";
    public static final String PRECIO = "Precio : $ ";
    public static final String CONTINUAR = "Para continuar ingrese una opcion: ";
    public static final String DISPONIBLES = "Entradas Disponibles : ";
    public static final String INGRESE_CODIGO = "Ingrese el codigo del evento para el que desea reservar :";
    public static final String INGRESE_ENTRADA = "Ingrese el numero de la entrada que desea reservar :";
    public static final String DESEA_CONTINUAR = "¿ Desea realizar otra operación ? [S]/[N]";
    public static final String ENTRADAS_RESERVADAS = " entradas reservadas";
    public static final String INGRESE4 = "Ingrese opción 4 para imprimir Boleta";
    public static final String EVENTO_A = "Jhonny Cien Pesos El Musical";
    public static final String EVENTO_B = "El loco y la triste";
    public static final String EVENTO_C = "Tengo Miedo Torero";

    public static class EntradaReservada {
        private String evento;
        private String ubicacionReservada;
        private boolean reservada;

        public EntradaReservada() {
        }

        ;

        public EntradaReservada(String evento, String ubicacionReservada, boolean reservada) {
            this.evento = evento;
            this.ubicacionReservada = ubicacionReservada;
            this.reservada = reservada;
        }

        public String getEvento() {
            return evento;
        }

        public void setEvento(String evento) {
            this.evento = evento;
        }

        public String getUbicacionReservada() {
            return ubicacionReservada;
        }

        public void setUbicacionReservada(String ubicacionReservada) {
            this.ubicacionReservada = ubicacionReservada;
        }

        public boolean isReservada() {
            return this.reservada;
        }

        public void setReservada(boolean reservada) {
            this.reservada = reservada;
        }
    }
}
