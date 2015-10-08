package EJ5AD;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class EJ5AD {

    DataInputStream dis;
    DataOutputStream dos;
    int cantidad;
    int total, leido, restante;

    public static void main(String[] args) {

        try {
            EJ5AD obj = new EJ5AD();
            obj.Escribir("Esto es una cadena");
            obj.proceder("Esto es uno cadena");

        } catch (IOException ex) {
            System.err.println("Error en el main.");
        }
    }

    public void Escribir(String str) throws IOException {
       try{
        dis = new DataInputStream(new BufferedInputStream(new FileInputStream("/home/oracle/Desktop/Pruebas/texto5.txt")));
        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/home/oracle/Desktop/Pruebas/texto5.txt")));
        dos.writeChars(str); //Sin esto, no se vuelca el contenido del buffer al fichero.
        dos.flush();//Mantiene el fichero abierto para que puedas seguir leyendo
        cantidad = dis.available();/*Con .available() guardamos la cantidad disponible para leer.*/
        String linea ="";
           for (int i = 0; i < str.length(); i++) {
               linea = linea + dis.readChar();
           }
        System.out.println("writeChar ha escrito: " + linea);
        System.out.println("Cantidad escrita: " + cantidad + " bytes.\n\n");
        dos.writeChars(str);
        dos.flush();
        cantidad = dis.available(); //Sobreescribimos la cantidad que vamos a leer y que ocupa la linea.
        String linea2 ="";
           for (int i = 0; i < str.length(); i++) {
               linea2 = linea2 + dis.readChar();
           }
        System.out.println("writeChar ha escrito: " + linea2);
        
        System.out.println("Cantidad escrita: " + cantidad + " bytes.\n\n");
        System.out.println("Bytes totales escritos: " + dos.size() + "\n");
        dos.close();
        dis.close();
        }catch(IOException ex){
            System.out.println("Error: "+ex);
        }
    }

    public void proceder(String str) throws IOException {
        try {
            
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream("/home/oracle/Desktop/Pruebas/texto5.txt")));
            total = dis.available();
            String linea ="";
            for (int i = 0; i < str.length(); i++) {
               linea = linea + dis.readChar();
            }
            System.out.println("Comprobacion de todos los bytes por leer --> " + total + "\n");
            System.out.println("Primera linea leida en Char: " + linea);
            leido = dis.available();
            restante = total - leido;
            
            String linea2 ="";
            for (int i = 0; i < str.length(); i++) {
               linea2 = linea2 + dis.readChar();
            }
            System.out.println("Cantidad de bytes leidos: " + leido);
            System.out.println("Segunda linea leida en Char: " + linea2);
            System.out.println("Cantidad de bytes restantes: " + restante);
            System.out.println("\n Comprobacion de que se ha leido todo: Bytes restantes --> " + dis.available());
           
        } catch (FileNotFoundException ex) {
            System.err.println("Error: "+ex);
        } catch (IOException ex) {
            System.err.println("Error de I/O." + ex);
        } finally {
            dis.close();
            dos.close();
        }
    }
   
}
