package com.example.a2106088.amaru;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtUsuario;
    EditText edtClave;
    EditText edtClaveRepetida;
    EditText edtNombre;
    EditText edtApellido;
    EditText edtEdad;
    EditText edtCorreo;
    Button btnRegistro;
    ImageView imageView;
    SharedPreferences infoUsuarios;

    Set usuarios;
    int totalUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        edtUsuario=(EditText) findViewById(R.id.edtUsuario);
        edtClave=(EditText) findViewById(R.id.edtClave);
        edtClaveRepetida=(EditText) findViewById(R.id.edtClave);
        edtNombre=(EditText) findViewById(R.id.edtNombre);
        edtApellido=(EditText) findViewById(R.id.edtApellido);
        edtEdad=(EditText) findViewById(R.id.edtEdad);
        edtCorreo=(EditText) findViewById(R.id.edtCorreo);
        imageView = (ImageView) findViewById(R.id.imageView9);

        btnRegistro=(Button) findViewById(R.id.Registrarse);
        btnRegistro.setOnClickListener(this);

        infoUsuarios=this.getSharedPreferences("asd", Context.MODE_PRIVATE);
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==btnRegistro.getId()) {
            String usuario = edtUsuario.getText().toString();
            String clave = edtClave.getText().toString();
            String RClave = edtClaveRepetida.getText().toString();
            String nombre = edtNombre.getText().toString();
            String apellido = edtApellido.getText().toString();
            String edad = edtEdad.getText().toString();
            String correo = edtCorreo.getText().toString();
            if (!clave.equals(RClave)) {
                Toast.makeText(this, "No coinciden las claves", Toast.LENGTH_LONG).show();
            } else {
                SharedPreferences.Editor editor = infoUsuarios.edit();
                editor.putString("usuario", usuario);
                editor.putString("clave", clave);
                editor.putString("nombre", nombre);
                editor.putString("apellido", apellido);
                editor.putString("edad", edad);
                editor.putString("correo", correo);
                editor.commit();
                Intent siguiente = new Intent();

/*
                BitmapDrawable bitmapDrawable = ((BitmapDrawable) imageView.getDrawable());
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imageInByte = stream.toByteArray();
*/
                Bundle memoria = new Bundle();
                //Post post = new Post(imageInByte,"");
                //memoria.putSerializable("image", post);
                siguiente.putExtras(memoria);
                setResult(Activity.RESULT_OK, siguiente);
                finish();
            }
        }
    }

    public void adjuntarFotoRegistro(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Where do you want to get the image?")
                .setCancelable(false)
                .setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 0);
                    }
                })
                .setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 1);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageView.setImageBitmap(imageBitmap);
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(selectedImage);
                }
                break;
        }
    }
}
