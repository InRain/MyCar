package com.car.my;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.car.my.entities.Vehicle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class VehicleListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Vehicle> vehicles = new ArrayList<>();

    public VehicleListAdapter(Context c, List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return vehicles.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null)
            v = inflater.inflate(R.layout.single_vehicle, null);

        TextView vehicleName = (TextView) v.findViewById(R.id.vehicle_name);
        TextView vehicleRun = (TextView) v.findViewById(R.id.vehicle_run);
        TextView vehicleNotif = (TextView) v.findViewById(R.id.vehicle_notif);

        Vehicle vehicle = vehicles.get(position);

        vehicleName.setText(vehicle.getName());
        vehicleRun.setText("Текущий пробег: " + String.valueOf(vehicle.getCurrentRun()));
        vehicleNotif.setText("time to change oil");

        return v;
    }
}

public class VehicleListActivity extends AppCompatActivity {

    private List<Vehicle> vehicles = new ArrayList<>();
    private VehicleListAdapter vehicleListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder addVehicleDialog = new AlertDialog.Builder(VehicleListActivity.this);
                LayoutInflater dialogInflater = getLayoutInflater();
                View layout = dialogInflater.inflate(R.layout.vehicle_add_dialog,(ViewGroup)findViewById(R.id.add_vehicle_root));

                final EditText name = (EditText) layout.findViewById(R.id.vehicle_name);
                final EditText initialRun = (EditText) layout.findViewById(R.id.vehicle_initial_run);
                final EditText currentRun = (EditText) layout.findViewById(R.id.vehicle_current_run);


                addVehicleDialog.setTitle(R.string.add_vehicle);

                addVehicleDialog.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(VehicleListActivity.this,"added",Toast.LENGTH_LONG).show();
                        vehicles.add(new Vehicle(1,name.getText().toString(),Long.parseLong(initialRun.getText().toString()),Long.parseLong(currentRun.getText().toString())));
                        dialog.dismiss();

                    }
                });
                addVehicleDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                addVehicleDialog.setView(layout);
                addVehicleDialog.create();
                addVehicleDialog.show();



            }
        });

        vehicles.add(new Vehicle(1, "Honda Shadow 1100", 6400, 25700));
        vehicles.add(new Vehicle(2, "Niva", 0, 1000));
        vehicles.add(new Vehicle(3, "Matiz", 0, 130000));
        vehicles.add(new Vehicle(3, "RF400", 0, 0));


        // init listview
        vehicleListAdapter = new VehicleListAdapter(this, vehicles);
        ListView vehicleListView = (ListView) findViewById(R.id.vehicle_list_view);
        vehicleListView.setAdapter(vehicleListAdapter);
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
}
