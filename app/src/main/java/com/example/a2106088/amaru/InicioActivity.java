package com.example.a2106088.amaru;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtUsuario;
    ImageButton btnPerfil;
    Button btnCrearGrupo;
    Button btnKarateInicio;
    Button button11;
    byte[] image;
    SharedPreferences infoUsuarios;
    ListView listaClases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_v2);
        txtUsuario = (TextView) findViewById(R.id.txtNombre);
        listaClases = (ListView) findViewById(R.id.listaClases);
        btnPerfil = (ImageButton)  findViewById(R.id.btnPerfil);
        btnCrearGrupo = (Button) findViewById(R.id.btnCrearGrupo);
        btnKarateInicio = (Button) findViewById(R.id.btnKarateInicio);
        button11 = (Button) findViewById(R.id.button11);
        btnPerfil.setOnClickListener(this);
        btnCrearGrupo.setOnClickListener(this);
        btnKarateInicio.setOnClickListener(this);


        Intent anterior = getIntent();
        Bundle memoria = anterior.getExtras();
        txtUsuario.setText(memoria.getString("usuario"));
        image = (byte[]) memoria.getSerializable("image");
        if (!(image==null)){
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            btnPerfil.setImageBitmap(bmp);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==btnPerfil.getId()){
            Intent perfil = new Intent(InicioActivity.this, PerfilActivity.class);
            startActivityForResult(perfil,1);

        }else if(v.getId()==btnCrearGrupo.getId()){
            Intent perfil = new Intent(InicioActivity.this, CrearGrupoActivity.class);
            startActivityForResult(perfil,2);

        }else if (v.getId()==btnKarateInicio.getId() || v.getId()==button11.getId()){
            Intent grupo = new Intent(InicioActivity.this, Grupo.class);
            startActivityForResult(grupo,3);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==2) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle memoria = data.getExtras();
                Post post = (Post) memoria.getSerializable("post");
                String[] asd = new String[1];
                asd[0]=post.getMessage();
                CustomListAdapter adapter = new CustomListAdapter(InicioActivity.this,asd,post.getBites());
                listaClases.setAdapter(adapter);
                txtUsuario.setText(memoria.getString("usuario"));
            }
        }
    }

}
