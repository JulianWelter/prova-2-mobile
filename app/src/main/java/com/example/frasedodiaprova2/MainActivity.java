package com.example.frasedodiaprova2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.frase);
        try{
            SQLiteDatabase DB= openOrCreateDatabase("DBprova",MODE_PRIVATE,null);

            DB.execSQL("CREATE TABLE IF NOT EXISTS frases (id INTEGER PRIMARY KEY AUTOINCREMENT, frase VARCHAR )");
/*            DB.execSQL("INSERT INTO frases(frase) VALUES('Que o dia comece bem e termine ainda melhor')");
            DB.execSQL("INSERT INTO frases(frase) VALUES('sorrisos bobos, uma mente tranquila e um coração cheio de paz')");
            DB.execSQL("INSERT INTO frases(frase) VALUES('Imagine uma nova história para a sua vida e acredite nela')");
            DB.execSQL("INSERT INTO frases(frase) VALUES('Nem todos os dias são bons, mas há algo bom em cada dia.')");
            DB.execSQL("INSERT INTO frases(frase) VALUES('Levanta, sacode a poeira e dá a volta por cima.')");*/
            Cursor cursor = DB.rawQuery("SELECT id, frase from frases ", null);
            int quantidade=cursor.getCount();
            int random = new Random().nextInt(quantidade)+1;
            cursor= DB.rawQuery("Select id , frase from frases where id = ?", new String[] {String.valueOf(random)});

           // cursor=DB.rawQuery(consulta, null);
            int indiceId=cursor.getColumnIndex("id");
            int indiceFrase=cursor.getColumnIndex("frase");
            cursor.moveToFirst();
            while (cursor!=null){
                String frase =cursor.getString(indiceFrase);
                String id =cursor.getString(indiceId);
                Log.i("ID: ",  id + " / frase "+ frase+ " / random "+ random);
                textView.setText(frase);
                cursor.moveToNext();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}