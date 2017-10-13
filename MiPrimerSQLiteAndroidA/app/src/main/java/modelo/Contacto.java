package modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by alcohonsilver on 05/10/17.
 */

public class Contacto implements Parcelable{
    int id;
    String nombre;
    String correo_electronico;
    String twitter;
    String telefono;
    Date fecha_nacimiento;

    public Contacto() {

    }

    public int getId() {
        return id;
    }

    public Contacto(int id, String nombre, String correo_electronico, String twitter, String telefono, Date fecha_nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.correo_electronico = correo_electronico;
        this.twitter = twitter;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    //Methods from the Parceable Interface

    public Contacto(Parcel in){
        readFromParcel(in);
    }

    @Override
    public String toString() {
        return this.nombre + "\n" + this.correo_electronico;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(correo_electronico);
        parcel.writeString(twitter);
        parcel.writeString(telefono);
        parcel.writeLong(fecha_nacimiento.getTime());

    }

    private void readFromParcel(Parcel in){
        nombre = in.readString();
        correo_electronico = in.readString();
        twitter = in.readString();
        telefono = in.readString();
        fecha_nacimiento = new Date(in.readLong());
    }

    public static final Parcelable.Creator<Contacto> CREATOR
            = new Parcelable.Creator<Contacto>(){
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        public Contacto[] newArray(int size){
            return new Contacto[size];
        }
    };

}
