package com.example.signozodiacal;


import static android.Manifest.permission.CAMERA;

import android.Manifest;
import android.hardware.Camera;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import android.widget.VideoView;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView edad;
    EditText editTextNombre;
    Button fecha;
    TextView fechaa;
    TextView fechanac;
    TextView textViewResultado;
    TextView tvZodiacMessage;
    TextView tvZodiacSign;
    Date fechaactual = new Date();
    VideoView video1;
    VideoView video2;
    VideoView video3;
    VideoView video4;
    VideoView video5;
    VideoView video6;
    VideoView video7;
    VideoView video8;
    VideoView video9;
    VideoView video10;
    VideoView video11;
    VideoView video12;
    ImageView ImagenFoto;
    Button ButtonFoto;
    String rutaImagen;
    final int FOTO_CONST = 1;
    int aa = 0;
    int ma = 0;
    int anio = 0;
    int mes = 0;

    /* renamed from: día  reason: contains not printable characters */
    int f0da = 0;

    ImageView boton;
    Camera camara;
    Camera.Parameters parametros;
    boolean isFlash = false, isOn = false;

    @Override
    public void onStop() {
        super.onStop();
        if (camara != null) {
            camara.release();
            camara = null;
        }
    }

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton=(ImageView) findViewById(R.id.boton);
        if(ActivityCompat.checkSelfPermission(this, CAMERA)!=PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA, },1000); }
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            camara=Camera.open();
            parametros=camara.getParameters();
            isFlash=true;
        }

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFlash) {
                    if (isOn) {
                        boton.setImageResource(R.drawable.linterna2);
                        parametros.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camara.setParameters(parametros);
                        camara.startPreview();
                        isOn = true;
                    } else {
                        boton.setImageResource(R.drawable.linterna);
                        parametros.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camara.setParameters(parametros);
                        camara.stopPreview();
                        isOn = false;
                    }
                }
            }
        });

        this.tvZodiacMessage = findViewById(R.id.tvZodiacMessage);
        this.editTextNombre = findViewById(R.id.editTextNombre);
        this.textViewResultado = findViewById(R.id.textViewResultado);
        this.tvZodiacSign = findViewById(R.id.tvZodiacSign);
        this.fecha = findViewById(R.id.button);
        this.fechaa = findViewById(R.id.fechaactual);
        this.fechanac = findViewById(R.id.fechanacimiento);
        this.edad = findViewById(R.id.edad);
        this.fecha.setOnClickListener(this);

        ImagenFoto = (ImageView) findViewById(R.id.ImagenFoto);
        ButtonFoto = (Button) findViewById(R.id.BotonFoto);
        ButtonFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
           abrirCamara();
            }
        });



        video1 = findViewById(R.id.video1);
        video2 = findViewById(R.id.video2);
        video3 = findViewById(R.id.video3);
        video4 = findViewById(R.id.video4);
        video5 = findViewById(R.id.video5);
        video6 = findViewById(R.id.video6);
        video7 = findViewById(R.id.video7);
        video8 = findViewById(R.id.video8);
        video9 = findViewById(R.id.video9);
        video10 = findViewById(R.id.video10);
        video11 = findViewById(R.id.video11);
        video12 = findViewById(R.id.video12);

        video1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aries));
        video2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tauro));
        video3.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cancer));
        video4.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.capricornio));
        video5.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.geminis));
        video6.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.leo));
        video7.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.libra));
        video8.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.piscis));
        video9.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sagitario));
        video10.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.virgo));
        video11.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.acuario));
        video12.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.escorpio));

        MediaController mc = new MediaController(this);
        video1.setMediaController(mc);
        mc.setAnchorView(video1);
        MediaController nc = new MediaController(this);
        video2.setMediaController(nc);
        nc.setAnchorView(video2);
        MediaController nn = new MediaController(this);
        video3.setMediaController(nn);
        nn.setAnchorView(video3);
        MediaController ma = new MediaController(this);
        video4.setMediaController(ma);
        ma.setAnchorView(video4);
        MediaController me = new MediaController(this);
        video5.setMediaController(me);
        me.setAnchorView(video5);
        MediaController mi = new MediaController(this);
        video6.setMediaController(mi);
        mi.setAnchorView(video6);
        MediaController mo = new MediaController(this);
        video7.setMediaController(mo);
        mo.setAnchorView(video7);
        MediaController nu = new MediaController(this);
        video8.setMediaController(nu);
        nu.setAnchorView(video8);
        MediaController mu = new MediaController(this);
        video9.setMediaController(mu);
        mu.setAnchorView(video9);
        MediaController no = new MediaController(this);
        video10.setMediaController(no);
        no.setAnchorView(video10);
        MediaController ne = new MediaController(this);
        video11.setMediaController(ne);
        ne.setAnchorView(video11);
        MediaController ni = new MediaController(this);
        video12.setMediaController(ni);
        ni.setAnchorView(video12);


    }

    private void abrirCamara (){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
     //   if (intent.resolveActivity(getPackageManager())!=null) {
File imagenArchivo=null;
try {
    imagenArchivo=crearImagen();
}
catch (IOException ex){
    Log.e("Error", ex.toString());
}
      if (imagenArchivo !=null) {
          Uri fotoUri = FileProvider.getUriForFile(this, "com.example.signozodiacal", imagenArchivo);
          intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
          startActivityForResult(intent, 1);
      }
        //}
        }
        protected void onActivityResult(int requestcode, int resultcode, Intent data){
       super.onActivityResult(requestcode,resultcode,data);
            if (requestcode==1 && resultcode==RESULT_OK){
          //Bundle extras=data.getExtras();
          Bitmap imgBitmap= BitmapFactory.decodeFile(rutaImagen);
         ImagenFoto.setImageBitmap(imgBitmap);
}
        }

  private File crearImagen () throws  IOException{
        String nombreImagen="foto";
        File directorio=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen=File.createTempFile(nombreImagen,".jpg", directorio);
  rutaImagen=imagen.getAbsolutePath();
  return  imagen;

    }


        @Override // android.view.View.OnClickListener
        public void onClick (View v){

        v.getId();
        Calendar ca = Calendar.getInstance();
        this.anio = ca.get(1);
        this.mes = ca.get(2);
        int i = ca.get(5);
        String fecha = getResources().getString(R.string.text_current_date);
        this.fechaa.setText(fecha + i + "/" + (this.mes + 1) + "/" + this.anio);
        String saludo = getResources().getString(R.string.text_hello);
        String nombre = this.editTextNombre.getText().toString();
        this.textViewResultado.setText(saludo + " " + nombre + "!");
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() { //
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public void onDateSet(DatePicker datePicker, int i2, int mesi, int i3) {
                int mesActual = mesi + 1;
                String valueOf = i3 < 10 ? "0" + String.valueOf(i3) : String.valueOf(i3);
                String mesFormateado = mesActual < 10 ? "0" + String.valueOf(mesActual) : String.valueOf(mesActual);
                String fechaNac = MainActivity.this.getResources().getString(R.string.text_birthdate);
                MainActivity.this.fechanac.setText(fechaNac + valueOf + "/" + mesFormateado + "/" + i2);
                MainActivity.this.aa = i2;
                MainActivity.this.ma = Integer.parseInt(mesFormateado);
                TextView textView = MainActivity.this.edad;
                MainActivity mainActivity = MainActivity.this;
                textView.setText(mainActivity.calcular(mainActivity.anio, MainActivity.this.mes + 1, MainActivity.this.aa, MainActivity.this.ma));
                MainActivity.this.updateZodiacSign();

                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Aries")) {
                    video1.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Tauro")) {
                    video2.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Cancer")) {
                    video3.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Capricornio")) {
                    video4.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Geminis")) {
                    video5.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Leo")) {
                    video6.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Libra")) {
                    video7.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Piscis")) {
                    video8.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Sagitario")) {
                    video9.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Virgo")) {
                    video10.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Acuario")) {
                    video11.start();
                }
                if (MainActivity.this.tvZodiacSign.getText().toString().equals("Escorpio")) {
                    video12.start();
                }
            }
        }, this.anio, this.mes, i);
        recogerFecha.show();
    }


    public String calcular(int a, int m, int aa, int ma) {
        int i;
        int meses;
        if (ma <= m) {
            i = a - aa;
            meses = m - ma;
        } else {
            i = (a - aa) - 1;
            meses = 12 - (ma - m);
        }
        String age = getResources().getString(R.string.text_age);
        return age + i + "años" + meses + "meses";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateZodiacSign() {
        String[] zodiacSigns = {"Capricornio", "Acuario", "Piscis", "Aries", "Tauro", "Géminis", "Cáncer", "Leo", "Virgo", "Libra", "Escorpio", "Sagitario"};
        String[] zodiacMessages = {"Si exagerásemos nuestras alegrías como lo hacemos con nuestras penas, nuestros problemas perderían su importancia", "Todo hombre debe decidir si caminará a la luz del altruismo creativo o en la obscuridad del egoísmo destructivo", "La amabilidad en palabras crea confianza", "Pies ¿para qué los quiero si tengo alas para volar", "Lo que no te mata, te hace más fuerte", "Me gustan los amigos que tienen pensamientos independientes, porque suelen hacerte ver los problemas desde todos los ángulos", "El amor es intensidad y por esto es una distensión del tiempo: estira los minutos y los alarga como siglos", "Con el tiempo y la madurez, descubrirás que tienes dos manos; una para ayudarte a ti misma y otra para ayudar a los demás", "Si he hecho descubrimientos invaluables, ha sido más por tener paciencia que cualquier otro talento", "La vida es una obra de teatro que no permite ensayos", "Creo en la determinación humana", "No somos seres humanos teniendo una experiencia espiritual"};
        int[] zodiacDates = {20, 19, 20, 20, 21, 21, 22, 23, 23, 23, 22, 21};
        int zodiacIndex = this.mes;
        if (this.f0da < zodiacDates[this.mes] && zodiacIndex - 1 < 0) {
            zodiacIndex = 11;
        }
        String zodiacSign = zodiacSigns[zodiacIndex];
        String zodiacMessage = zodiacMessages[zodiacIndex];
        String zodiacSig = getResources().getString(R.string.text_zodiac_sign);
        String zodiacMensaje = getResources().getString(R.string.text_zodiac_message);
        this.tvZodiacSign.setText(zodiacSig + zodiacSign);
        this.tvZodiacMessage.setText(zodiacMensaje + zodiacMessage);
    }

}