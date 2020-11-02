package cajeroAutomatico;

import java.util.Scanner;

class Main {
    private static Scanner entrada;

    public static void main(String[] args) {

        //Clientes precargados
        Cliente cliente1 = new Cliente("1", "Carlos Andrés Pérez Granados", 1500000);
        Cliente cliente2 = new Cliente("2", "María Alejandra Alzate Bustamante", 2500000);
        Cliente cliente3 = new Cliente("3", "Astrid Gómez Roldán", 2000000);

        //Se instancia un objeto de la clase Cajero
        Cajero cajero = new Cajero();

        //Se ingresan los clientes precargados al cajero
        System.out.println("Cargando clientes...\n");
        cajero.registrarCliente(cliente1);
        cajero.registrarCliente(cliente2);
        cajero.registrarCliente(cliente3);

        entrada = new Scanner(System.in);
        int opcion;
        desplegarMenu();
        boolean entrar = true;
        while (entrar) {
            System.out.print(">>> ");
            opcion = entrada.nextInt();

            switch (opcion) {

            case 1:
                System.out.println("---- Registro de cliente nuevo ----\n");
                System.out.print("Ingrese el nombre del nuevo cliente: ");
                String nombre = entrada.nextLine();
                System.out.print("Ingrese la cédula del nuevo cliente: ");
                String cedula = entrada.nextLine();
                System.out.print("Ingrese el saldo inicial del nuevo cliente: $");
                double saldo = entrada.nextDouble();
                Cliente nuevoCliente = new Cliente(cedula, nombre, saldo);
                cajero.registrarCliente(nuevoCliente);
                break;

            case 2:
                System.out.println("---- Consignación a un cliente ----\n");
                System.out.print("Ingrese la cédula del cliente: ");
                cedula = entrada.nextLine();
                System.out.print("Ingrese el monto a consignar: $");
                double montoConsignacion = entrada.nextDouble();
                cajero.consignar(cedula, montoConsignacion);
                break;

            case 3:
                System.out.println("---- Transferencia entre clientes ----\n");
                System.out.print("Ingrese la cédula del cliente que envía: ");
                String cedulaOut = entrada.nextLine();
                System.out.print
                ("Ingrese la cédula del cliente que recibe: ");
                String cedulaIn = entrada.nextLine();
                System.out.print("Ingrese el monto a tranferir: $");
                double montoTransferencia = entrada.nextDouble();
                cajero.transferir(cedulaOut, cedulaIn, montoTransferencia);
                break;

            case 4:
                System.out.println("---- Consulta de saldo ----\n");
                System.out.print("Ingrese la cédula del cliente: ");
                cedula = entrada.nextLine();
                cajero.mostrarSaldoCliente(cedula);
                break;

            case 5:
                System.out.println("---- Lista de clientes ----\n");
                cajero.mostrarClientes();
                break;

            case 6:
                System.out.println("---- Eliminar cliente ----\n");
                System.out.print("Ingrese la cédula del cliente que desea eliminar: ");
                cedula = entrada.nextLine();
                cajero.eliminarCliente(cedula);
                break;

            case 7:
                desplegarMenu();
                break;

            default:
                System.out.println("Hasta luego :D");
                entrar = false;
            }
        }
    }

    static void desplegarMenu() {
        System.out.println(
            "    **************************\n"+
            "    *          MENÚ          *\n"+
            "    **************************\n\n"+
            "Ingrese:\n\n"+
            "    1 para registrar un nuevo cliente.\n"+
            "    2 para realizar una consignación.\n"+
            "    3 para realizar una transferencia entre clientes.\n"+
            "    4 para consultar el saldo de un cliente.\n"+
            "    5 para listar todos los clientes.\n"+
            "    6 para eliminar cliente.\n"+
            "    7 para mostrar este menú.\n"+
            "    Cualquier otro número para salir.\n");
    }
}
