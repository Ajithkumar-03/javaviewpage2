package com.example.javaviewpage2ajith;

import static com.example.javaviewpage2ajith.AppData.quotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"----------->oncCreate");

        viewPager2 = findViewById(R.id.viewPager2);

        TextAdapter textAdapter =  new TextAdapter();
         viewPager2.setAdapter(textAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG,"---------->onCreateOptionMenu");
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG,"------------>onOptionsItemSelected");
        switch (item.getItemId()){
            case R.id.shar_quotes_id:
                Log.d(TAG,"------------->Share Quotes");
                ShareQuotes();
                break;
            case R.id.go_to_id:
                Log.d(TAG, "------------>go_to_id");
                displayChoosePage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShareQuotes(){
        Log.d(TAG,"------------>ShareQuotes");

        int SelectedQuotesIndex = viewPager2.getCurrentItem();

        Log.d(TAG,"----------------->Selected Quotes Index:"+SelectedQuotesIndex);
        Log.d(TAG,"----------------->selected Quotes"+ quotes[SelectedQuotesIndex]);

        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.putExtra(Intent.EXTRA_TEXT, quotes[SelectedQuotesIndex]);
        startActivity(Intent.createChooser(myIntent,"shar via"));
    }

    private void displayChoosePage(){
        Log.d(TAG, "----------------->displayChoosePage: ");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Go to selected page");

        View view = LayoutInflater.from(this).inflate(R.layout.alter_dialog, null);

        EditText edPageNumber = view.findViewById(R.id.editTextTextPersonName);
        edPageNumber.setHint("Enter page no (1- "+ quotes.length + ")");

        builder.setView(view);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                viewPager2.setCurrentItem(Integer.parseInt(edPageNumber.getText().toString())-1);

            }
        });

        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}