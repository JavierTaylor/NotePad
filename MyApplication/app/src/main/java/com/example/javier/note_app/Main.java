package com.example.javier.note_app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main extends ActionBarActivity {

    Button load;

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").

        String[] names_of = fileList(); //arr.length
        int name_size = names_of.length;

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this, R.layout.my_layout, names_of);

        ListView names = (ListView)findViewById(R.id.LIST);
        names.setAdapter(nameAdapter);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.LIST);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] names_of = fileList();
                String file_name = names_of[position];
                final int data_block = 100;

                try {
                    FileInputStream fis = openFileInput(file_name);
                    InputStreamReader isr = new InputStreamReader(fis);
                    char[] data_stuff = new char[data_block];
                    String final_data = "";
                    int size;



                    try {
                        while ((size = isr.read(data_stuff)) > 0) {
                            String read_data = String.copyValueOf(data_stuff, 0, size);
                            final_data += read_data;
                            data_stuff = new char[data_block];

                        }
                        Toast.makeText(getBaseContext(), "Data loaded!", Toast.LENGTH_SHORT).show();

                        //public void OnClick(View v){
                        Intent new_intent = new Intent(Main.this, LoadedFile.class);
                        new_intent.putExtra("Final", final_data);
                        new_intent.putExtra("Name", file_name);
                        startActivity(new_intent);
                        //     }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void new_file(View view)  {
        Intent intent = new Intent(this,NewFile.class);
        startActivity(intent);
    }

    /*public void showFiles(View view){
        PopupMenu files = new PopupMenu(this, view);
        MenuInflater menuInflater = files.getMenuInflater();
        menuInflater.inflate(R.menu.file_selector, files.getMenu());
        files.show();
    }*/


}
