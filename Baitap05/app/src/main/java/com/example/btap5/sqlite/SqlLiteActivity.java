package com.example.btap5.sqlite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btap5.DatabaseHandler;
import com.example.btap5.R;
import com.example.btap5.adapter.NotesAdapter;
import com.example.btap5.model.NotesModel;

import java.util.ArrayList;

public class SqlLiteActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sql_lite);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Khởi tạo DatabaseHandler
        databaseHandler = new DatabaseHandler(this, "Notes.sqlite", null, 1);

        // Khởi tạo danh sách và adapter
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(this, R.layout.row_notes, arrayList);

        // Ánh xạ ListView
        listView = findViewById(R.id.listView1);
        listView.setAdapter(adapter);

        // Khởi tạo database và tải dữ liệu
        InitDatabaseSQLite();
        databaseSQLite();
    }

    private void InitDatabaseSQLite() {
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes (Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }

    private void databaseSQLite() {
        arrayList.clear(); // Xóa danh sách cũ trước khi nạp dữ liệu mới
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            arrayList.add(new NotesModel(id, name));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAddNotes) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SqlLiteActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_notes, null);

        EditText editText = dialogView.findViewById(R.id.editTextName);
        Button buttonAdd = dialogView.findViewById(R.id.buttonThem);
        Button buttonHuy = dialogView.findViewById(R.id.buttonHuy);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                if (name.equals("")) {Toast.makeText(SqlLiteActivity.this, "Vui lòng nhập tên Notes", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseHandler.QueryData("INSERT INTO Notes VALUES(null, '" + name + "')" );
                    Toast.makeText(SqlLiteActivity.this, "Đã thêm Notes", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    databaseSQLite();//gọi hàm load lại dũ Liệu
                }
            }
        });
        buttonHuy.setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void DialogCapNhatNotes(String name, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SqlLiteActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_notes, null);

        EditText editText = dialogView.findViewById(R.id.editTextName);
        Button buttonEdit = dialogView.findViewById(R.id.buttonThem);
        Button buttonHuy = dialogView.findViewById(R.id.buttonHuy);

        editText.setText(name);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                databaseHandler.QueryData("UPDATE Notes SET NameNotes ='" + name + "' WHERE Id = '" + id + "'");
                Toast.makeText(SqlLiteActivity.this,"Đã cập nhật Notes thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                databaseSQLite();//gọi hàm Load Lại dũ Liệu
            }
        });
        buttonHuy.setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void DialogDelete(String name, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa Notes " + name + " này không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHandler.QueryData("DELETE FROM Notes WHERE Id = '" + id + "'");
                Toast.makeText(SqlLiteActivity.this, "Đã xóa Notes " + name + " thành công", Toast.LENGTH_SHORT).show();
                databaseSQLite();//gọi hàm load lại dũ Liệu
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
