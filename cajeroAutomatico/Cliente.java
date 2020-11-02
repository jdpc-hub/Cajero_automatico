package cajeroAutomatico;

class Cliente {

    //Variables de instancia
    private String cedula;
    private String nombre;
    private double saldo;

    //Constructor
    Cliente(String cedula, String nombre, double saldo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    //Métodos
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
