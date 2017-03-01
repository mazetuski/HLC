package maze.huerto;

/**
 * Created by maze on 09/02/2017.
 */

public class Hortaliza {
    private String nombre;
    private String descripcion;
    private String descripcionLarga;
    private int imagenRuta;

    public Hortaliza(String nombre, String descripcion, String descripcionLarga, int imagen){
        setNombre(nombre);
        setDescripcion(descripcion);
        setImagenRuta(imagen);
        setDescripcionLarga(descripcionLarga);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(int imagenRuta) {
        this.imagenRuta = imagenRuta;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }
}
