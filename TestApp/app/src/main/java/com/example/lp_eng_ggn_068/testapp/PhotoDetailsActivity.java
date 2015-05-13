package com.example.lp_eng_ggn_068.testapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PhotoDetailsActivity extends ActionBarActivity implements LocationListener {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    ImageView clickedImage;
    TextView locationDetailsText;
    DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        setTitle("App Name");
        db = new DataBaseHandler(this);
        clickedImage = (ImageView) findViewById(R.id.imageView);
        clickedImage.setImageBitmap(Singleton.getInstance().getClick());

        locationDetailsText = (TextView) findViewById(R.id.locationText);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L,
                500.0f, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            locationDetailsText.setText("Lat: " + location.getLatitude() + ", Long: " + location.getLongitude() + "\n" + "Address: " + address + ", " + city + ", " + country);
        } catch (IOException e) {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void onSaveClick(View view) {
        if (locationDetailsText.getText().toString().equalsIgnoreCase("Loading..."))
            Toast.makeText(getBaseContext(), "Please wait for the location to get updated.",
                    Toast.LENGTH_SHORT).show();
        else
            new InsertRecordTask().execute("");
    }

    public void onCancelClick(View view) {
        finish();
    }

    public class InsertRecordTask extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;
        String responseString;

        protected void onPreExecute() {
            dialog = ProgressDialog.show(PhotoDetailsActivity.this, "Inserting Record",
                    "Saving..", true);
        }

        @Override
        protected String doInBackground(String... params) {
            // convert bitmap to byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Singleton.getInstance().getClick().compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte imageInByte[] = stream.toByteArray();

            // Inserting Record
            Log.d("Insert: ", "Inserting ..");
            SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy HH:mm:ss");
            Date resultdate = new Date(System.currentTimeMillis());
            db.addRecord(new Record(locationDetailsText.getText().toString(), imageInByte, "" + sdf.format(resultdate)));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "";
        }

        protected void onPostExecute(String page) {

            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            finish();
            Toast.makeText(getBaseContext(), "Record Inserted",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
