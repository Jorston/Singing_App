package com.example.app.data;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.example.app.Modelos.Fichaje;

public class Escrituras {
    private Context context;
    private String fileName = "registros";
    ArrayList<Fichaje> fichajes = new ArrayList<Fichaje>();
    ArrayList<Fichaje> antiguosFichajes = new ArrayList<>();

    public void writeToFile(Fichaje persona, Context context) {
        this.context = context;
        fichajes.add(persona);

        try {
            FileInputStream fos2 = context.openFileInput(fileName);
            ObjectInputStream ois2 = new ObjectInputStream(fos2);
            try {
                antiguosFichajes = (ArrayList<Fichaje>) ois2.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (!antiguosFichajes.isEmpty()) {
                for (Fichaje fichaje : antiguosFichajes) {
                    fichajes.add(fichaje);
                }
            }
            ois2.close();
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(fichajes);
            os.close();
            fos.close();
        } catch (
                Exception e) {
            try {
                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(fichajes);
                os.close();
                fos.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void print(Context context) throws IOException {
        this.context = context;
        try {
            FileInputStream fos2 = context.openFileInput(fileName);
            ObjectInputStream ois2 = new ObjectInputStream(fos2);
            try {
                antiguosFichajes = (ArrayList<Fichaje>) ois2.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (!antiguosFichajes.isEmpty()) {
                for (Fichaje fichaje : antiguosFichajes) {
                    System.out.println("Mail: " + fichaje.getMail() + " Hora: " + fichaje.getHoraFichada() + " Tipo de fichaje: " + fichaje.getTipoFichaje());
                }
            }
        } catch (Exception e) {}
    }
}
