package taweesak.com.myappqrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv_carType,tv_namePlate,tv_country,tv_dateTime,tv_carBrand,tv_carฺBodyNumber;

    private IntentIntegrator qrScan;
    private ImageView qrCode;
    //private TextView tvName,tvSurname,tvPhone,tvEmail,tvLineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrCode = findViewById(R.id.scanQrcode);
        qrScan = new IntentIntegrator(this);

        /*tvName = findViewById(R.id.tvName);
        tvSurname = findViewById(R.id.tvSurname);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvLineId = findViewById(R.id.tvLineId);*/

        tv_carType = findViewById(R.id.tv_carType);
        tv_namePlate = findViewById(R.id.tv_namePlate);
        tv_country = findViewById(R.id.tv_country);
        tv_dateTime = findViewById(R.id.tv_dateTime);
        tv_carBrand = findViewById(R.id.tv_carBrand);
        tv_carฺBodyNumber = findViewById(R.id.tv_carฺBodyNumber);

        qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult resultQrcode = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (resultQrcode != null) {
            //if qrcode has nothing in it
            if (resultQrcode.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(resultQrcode.getContents());
                    //setting values to textviews
                    tv_carType.setText("ประเภทรถ : "+obj.getString("carType"));
                    tv_namePlate.setText("ทะเบียนรถ : "+obj.getString("namePlate"));
                    tv_country.setText("จังหวัด : "+obj.getString("country"));
                    tv_dateTime.setText("วัน เดือน ปี : "+obj.getString("dateTime"));
                    tv_carBrand.setText("ยี่ห้อรถ : "+obj.getString("carBrand"));
                    tv_carฺBodyNumber.setText("เลขตัวถัง : "+obj.getString("carฺBodyNumber"));

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(this, resultQrcode.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


}
