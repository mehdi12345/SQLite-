  package com.example.sqliteproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

  public class MainActivity extends AppCompatActivity {
    DatabaseHelper DB;
    EditText edtNom,edtPrenom,edtAge,edtid;
    Button addData,viewAll,Update,Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB=new DatabaseHelper(this);
        edtNom=findViewById(R.id.edtNom);
        edtPrenom=findViewById(R.id.edtPrenom);
        edtAge=findViewById(R.id.edtAge);
        edtid=findViewById(R.id.edtId);
        addData=findViewById(R.id.AddData);
        viewAll=findViewById(R.id.ViewAll);
        Update=findViewById(R.id.Update);
        Delete=findViewById(R.id.Delete);
        AddData();
        GetAllData();
        Update();
        Delete();
    }
    public void AddData(){
        addData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        boolean isInserted=DB.insert(edtNom.getText().toString(),edtPrenom.getText().toString(),Integer.parseInt(edtAge.getText().toString()));
                        if(isInserted){
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }
    public void GetAllData(){
        viewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Cursor res = DB.getAllData();
                        if(res.getCount()==0){
                            showMessage("Error","No Data Was Found");
                            Toast.makeText(MainActivity.this, "No Data Was Found", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            StringBuffer buffer=new StringBuffer();
                            while (res.moveToNext()){
                                buffer.append("\nid : "+res.getString(0)+"\nNom : "+res.getString(1)+"\nPrenom : "+
                                        res.getString(2)+"\nAge : "+res.getString(3)+"\n");
                            }
                            showMessage("Data",buffer.toString());
                        }
                    }
                }
        );
    }
      public void Update(){
          Update.setOnClickListener(
                  new View.OnClickListener(){
                      @Override
                      public void onClick(View view) {
                          boolean isUpdated= DB.update(edtid.getText().toString(),edtNom.getText().toString(),edtPrenom.getText().toString(),
                                  Integer.parseInt(edtAge.getText().toString()));
                          if(isUpdated){
                              Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                          }else{
                              Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                          }
                      }
                  }
          );
      }
      public void Delete(){
          Delete.setOnClickListener(
                  new View.OnClickListener(){
                      @Override
                      public void onClick(View view) {
                          Integer DeletedRow= DB.Delete(edtid.getText().toString());
                          if(DeletedRow>0){
                              Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                          }else{
                              Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                          }
                      }
                  }
          );
      }
    public void showMessage(String title,String Message){
        AlertDialog.Builder Builder = new AlertDialog.Builder(this);
        Builder.setCancelable(true);
        Builder.setTitle(title);
        Builder.setMessage(Message);
        Builder.show();
    }
}