package cajeroAutomatico;

import java.util.ArrayList;
import java.util.Scanner;

class Cajero {

    //Variables de instancia
    private ArrayList<Cliente> clientes;
    private Scanner entrada;

    //Constructor
    Cajero() {
        clientes = new ArrayList<Cliente>();
        entrada = new Scanner(System.in);
        System.out.println("\nCajero disponible.\n");
    }

    //Métodos
    void registrarCliente(Cliente nuevoCliente) {
        //Se verifica que el cliente no exista
        for (int i = 0; i < clientes.size(); i++) {
            if (nuevoCliente.getCedula().equals(clientes.get(i).getCedula())) {
                System.out.println(
                "\nYa existe un cliente con este número de cédula.\n");
                return;
            }
        }
        clientes.add(nuevoCliente);
        System.out.println("\nSe registró un cliente con éxito.\n");
    }

    void consignar(String cedula, double montoConsignacion) {
        int i;
        try {
            i = getIndice(cedula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        double nuevoSaldo = clientes.get(i).getSaldo() + montoConsignacion;
        System.out.print(
        "\n¡ATENCIÓN!\n"+
        "Se va a consignar $"+montoConsignacion+" al cliente:\n"+
        "    "+clientes.get(i).getNombre()+".\n\n"+
        "¿Desea continuar? S/N ");
        String respuesta = entrada.next().toLowerCase();
        if (respuesta.equals("s")) {
            clientes.get(i).setSaldo(nuevoSaldo);
            System.out.println(
            "\nLa consignación ha sido realizada con éxito.\n");
        } else if (respuesta.equals("n")) {
            System.out.println("\nNo se realizó la consignación.\n");
            return;
        } else {
            System.out.println(
            "\nDebe ingresar S o N.\n"+
            "No se realizó la consignación.\n");
            return;
        }
    }

    void transferir(String cedulaOut, String cedulaIn,
    double montoTransferencia) {
        int iOut, iIn;
        double nuevoSaldoOut, nuevoSaldoIn;

        try {//Se busca el índice del cliente que envía
            iOut = getIndice(cedulaOut);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (clientes.get(iOut).getSaldo() < montoTransferencia) {
            System.out.println(
            "\n"+
            "¡ATENCIÓN!\n"+
            "Saldo insuficiente. No se puede realizar la transferencia.\n");
            return;
        }
        nuevoSaldoOut = clientes.get(iOut).getSaldo() - montoTransferencia;

        try {//Se busca el índice del cliente que recibe
            iIn = getIndice(cedulaIn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        nuevoSaldoIn = clientes.get(iIn).getSaldo() + montoTransferencia;

        System.out.print(
        "\n¡ATENCIÓN!\n"+
        "Se va a transferir $"+montoTransferencia+" del cliente:\n"+
        "    "+clientes.get(iOut).getNombre()+"\n"+
        "al cliente:\n"+
        "    "+clientes.get(iIn).getNombre()+"\n\n"+
        "¿Desea continuar? S/N ");
        String respuesta = entrada.next().toLowerCase();
        if (respuesta.equals("s")) {
            clientes.get(iOut).setSaldo(nuevoSaldoOut);
            clientes.get(iIn).setSaldo(nuevoSaldoIn);
            System.out.println("\nTransferencia realizada con éxito.\n");
        } else if (respuesta.equals("n")) {
            System.out.println("\nNo se realizó la transferencia.\n");
            return;
        } else {
            System.out.println(
            "\nDebe ingresar S o N.\n"+
            "No se realizó la transferencia.\n");
            return;
        }
    }

    void mostrarSaldoCliente(String cedula) {
        int i;
        try {
            i = getIndice(cedula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(
        "\n"+
        "    Cliente: "+clientes.get(i).getNombre()+"\n"+
        "    Saldo: $"+clientes.get(i).getSaldo()+
        "\n");
    }

    void mostrarClientes() {
        String formato = "| %1$20s | %2$40s | %3$20s |\n";
        String s = String.format(formato, "Cédula", "Nombre", "$ Saldo");
        System.out.println(s);
        for (int i = 0; i < clientes.size(); i++) {
            s = String.format(
            formato,
            clientes.get(i).getCedula(),
            clientes.get(i).getNombre(),
            clientes.get(i).getSaldo());
            System.out.println(s);
        }
    }

    void eliminarCliente(String cedula) {
        int i;
        try {
            i = getIndice(cedula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print(
        "\n¡ATENCIÓN!\n"+
        "El cliente seleccionado para eliminar es: "+
        "    "+clientes.get(i).getNombre()+"\n"+
        "\n¿Desea continuar? S/N ");
        String respuesta = entrada.next().toLowerCase();
        if (respuesta.equals("s")) {
            clientes.remove(i);
            System.out.println("\nUn cliente ha sido eliminado con éxito.\n");
        } else if (respuesta.equals("n")) {
            System.out.println("\nNo se eliminó ningún cliente.\n");
            return;
        } else {
            System.out.println(
            "\nDebe ingresar S o N.\n"+
            "No se eliminó ningún cliente.\n");
            return;
        }
    }

    private int getIndice(String cedula) throws Exception {
        for (int i = 0; i < clientes.size(); i++) {
            if (cedula.equals(clientes.get(i).getCedula())) {
                return i;
            }
        }
        throw new Exception("\nLa cédula "+cedula+" no está registrada.\n");
    }
}
