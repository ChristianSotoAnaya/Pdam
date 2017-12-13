package com.example.a2106088.amaru;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class CrearGrupoActivity extends AppCompatActivity implements View.OnClickListener {

    Button crearGrupo;
    Button cancelar;
    Button btnFotoCrearGrupo;
    ImageView imageView;
    EditText edtNombreCrearGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);
        imageView = (ImageView) findViewById(R.id.imageFoto);
        edtNombreCrearGrupo = (EditText) findViewById(R.id.edtNombreCrearGrupo);
        crearGrupo = (Button) findViewById(R.id.btnCrearGrupo);
        cancelar = (Button) findViewById(R.id.btnCancelarCrearGrupo);
        btnFotoCrearGrupo = (Button) findViewById(R.id.btnFotoCrearGrupo);
        btnFotoCrearGrupo.setOnClickListener(this);
        crearGrupo.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==btnFotoCrearGrupo.getId()){
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

        else if(v.getId()==crearGrupo.getId()){
            Intent inicio = new Intent();
            BitmapDrawable bitmapDrawable = ((BitmapDrawable) imageView.getDrawable());
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageInByte = stream.toByteArray();

            Post post = new Post(imageInByte, edtNombreCrearGrupo.getText().toString());
            Bundle memoria = new Bundle();
            memoria.putSerializable("post", post);
            inicio.putExtras(memoria);
            setResult(Activity.RESULT_OK,inicio);
            finish();
        }

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
