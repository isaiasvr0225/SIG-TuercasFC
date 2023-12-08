package com.sig_tuercasfc.domain;


//Esta clase hereda de la clase Personas, por lo tanto, todos los atributos de la clase Personas serán heredados por la clase Funcionarios
public class Funcionarios extends Personas{
    private String cargo;

    public Funcionarios() {
    }

    public Funcionarios(int cedula, String nombres, String apellidos, int telefono, String correo, String direccion, double sueldo, String cargo) {
        super(cedula, nombres, apellidos, telefono, correo, direccion, sueldo);
        this.cargo = cargo;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionarios{" +
                "cargo='" + cargo + '\'' +
                ", cedula=" + cedula +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono=" + telefono +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}
