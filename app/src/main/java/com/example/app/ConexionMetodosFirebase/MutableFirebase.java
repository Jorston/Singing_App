package com.example.app.ConexionMetodosFirebase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MutableFirebase{

   /* private MutableLiveData<String> mText;

    public MutableFirebase() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    /* public void WriteMsgFirebase(String msg){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference msg_reference = db.child("dam2m8enviomensajes").child("mensajes").push();
        msg_reference.setValue(new Mensaje("usuario inventado",
                "pVBEOgINbVOGdkne7Sjhg3ycW6h2", msg));
        db.child("dam2m8enviomensajes").child("usuarios").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("mensaje")
                .setValue(msg_reference.getKey());

    }
    */
}
