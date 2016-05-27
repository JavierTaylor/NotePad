package com.example.javier.note_app;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import android.content.Context;
import java.lang.String;


public class NewFile extends ActionBarActivity {


    Button save;
    EditText data, name;
    String Text, FileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_file);

        save = (Button) findViewById(R.id.SAVE);
        data = (EditText) findViewById(R.id.DATA);

        name = (EditText) findViewById(R.id.NAME);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileName = name.getText().toString();
                Text = data.getText().toString();

                if (FileName.matches("")) {
                    Toast.makeText(getBaseContext(), "Input a file name!", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        FileOutputStream fou = openFileOutput(FileName, MODE_WORLD_READABLE);//FileName "text.txt
                        OutputStreamWriter osw = new OutputStreamWriter(fou);
                        try {
                            osw.write(Text);
                            osw.flush();
                            osw.close();
                            Toast.makeText(getBaseContext(), "Text Saved to " + FileName, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                name = (EditText) findViewById(R.id.NAME);
                FileName = name.getText().toString();

                if(FileName.matches("")) {
                    Toast.makeText(this, "Specify a file", Toast.LENGTH_SHORT).show();
                }
                else {
                    deleteFile(FileName);    //"text.txt"
                    Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
            else {
            }
        }
    }

    public void delete(View view){
        Intent intent = new Intent(this, Pop.class);
        startActivityForResult(intent, 1);
    }

    public void email(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        data = (EditText) findViewById(R.id.DATA);
        String text = data.getText().toString();
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);

        startActivity(Intent.createChooser(emailIntent, "Send note with:"));
    }

/* This was to copy the file to the clipboard, but it is no longer necessary.
    I have kept all of this stuff just to show myself how to do it in the future if need be

    public void share(View view) {
        TextView text = (TextView) findViewById(R.id.DATA);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setText(text.getText());
        Toast.makeText(this, "Text Copied!", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, Share.class);
        //startActivity(intent);
    }
*/

}

