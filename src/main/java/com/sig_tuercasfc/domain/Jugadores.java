package com.sig_tuercasfc.domain;

//Esta clase hereda de la clase Personas, por lo tanto, todos los atributos de la clase Personas serán heredados por la clase Jugadores
public class Jugadores extends Personas{

    public Jugadores() {
    }

    public Jugadores(int cedula, String nombres, String apellidos, int telefono, String correo, String direccion, double sueldo) {
        super(cedula, nombres, apellidos, telefono, correo, direccion, sueldo);
    }

    @Override
    public String toString() {
        return "Jugadores{" +
                "cedula=" + cedula +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}
