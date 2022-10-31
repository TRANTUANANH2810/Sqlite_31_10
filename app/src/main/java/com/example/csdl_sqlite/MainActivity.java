package com.example.csdl_sqlite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.csdl_sqlite.adapter.employeeAdapter;
import com.example.csdl_sqlite.model.Employee;
import com.example.csdl_sqlite.sqlite.DBHelper;
import com.example.csdl_sqlite.sqlite.EmployeeDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private employeeAdapter employyeeAdapter;
    private ListView lvEmloyees;
    private String employeeId;
    private List<Employee> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DBHelper dbHelper = new DBHelper(this);
       // SQLiteDatabase database = dbHelper.getReadableDatabase();
       // database.close();

        findViewById(R.id.btnedit).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);
        findViewById(R.id.btndelete).setOnClickListener(this);

        lvEmloyees = findViewById(R.id.lvEmployee);

        EmployeeDao dao = new EmployeeDao(this);
         list = dao.getAll();
        employyeeAdapter = new employeeAdapter(this, list);
        lvEmloyees.setAdapter(employyeeAdapter);
        lvEmloyees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee emp = list.get(i);
                employeeId = emp.getId();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostResume() {
        super.onPostResume();

        EmployeeDao dao = new EmployeeDao(this);
       List<Employee> UpdatedList = dao.getAll();
        list.clear();
       UpdatedList.forEach(item -> list.add(item));
       employyeeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddOrEditEmployeeActivity.class);

        switch (view.getId()){
            case R.id.btnInsert:
                startActivity(intent);
                break;
            case R.id.btnedit:
                if (employeeId == null){
                    Toast.makeText(this, "Emlpoyee id must be salected", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("id", employeeId);
                intent.putExtra("data",bundle);
                startActivity(intent);
                break;
            case R.id.btndelete:
                if(employeeId== null){
                    Toast.makeText(this, "Emlpoyee id must be salected",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                EmployeeDao dao = new EmployeeDao(this);
                dao.delete(employeeId);
                employeeId = null;
                onResume();

                Toast.makeText(this,"Employee was dalete", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}