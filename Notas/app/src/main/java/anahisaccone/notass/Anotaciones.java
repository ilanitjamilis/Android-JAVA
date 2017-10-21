package anahisaccone.notass;

public class Anotaciones {
    String Anotacion;
    String Fecha;
    Boolean Realizado;

    Anotaciones()
    {
    }

    Anotaciones(String Anotacion, String Fecha, Boolean Realizado)
    {
        this.Anotacion=Anotacion;
        this.Fecha=Fecha;
        this.Realizado=Realizado;
    }

    public void setAnotacion(String Anotacion){this.Anotacion=Anotacion;}
    public void setFecha(String fecha){this.Fecha= fecha;}
    public void setRealizado (Boolean Realizado){this.Realizado=Realizado;}
    public String getAnotacion(){return Anotacion;}
    public String getFecha() {return Fecha;}
    public Boolean getRealizado(){return Realizado;}

}