package com.example.sumit.webservice;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btn_invoke;
    EditText et_id;
    TextView tv_name;
    private Handler mHandler = new Handler();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_invoke = (Button) findViewById(R.id.btn_invoke);
        et_id = (EditText) findViewById(R.id.et_id);
        tv_name = (TextView) findViewById(R.id.tv_name);

        btn_invoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... voids) {
                        OkHttpClient client = new OkHttpClient();


                        Request request = new Request.Builder()
                                .url("http://192.168.1.101:1234/WebService.asmx")
                                .build();

                        Response response=null;

                        try
                        {
                            response=client.newCall(request).execute();
                            Log.d(TAG, "doInBackground() called with: "+"params=["+response.body().string()+"]");
                            return response.body().string();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        return null;
                    }
                }.execute();*/

                String input_id = et_id.getText().toString();
                String[] params = new String[]{"192.168.1.3:1234", input_id};
                new MyAsyncTask().execute(params);

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://example.in.example/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://example.in.example/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        public String SOAP_ACTION = "http://tempuri.org/studentName";
        //public String METHOD_NAME = "studentName";
        //public String NAME_SPACE = "http://tempuri.org/";
        // public String URL = "http://http://192.168.1.3:1234/WebService.asmx";
        public String OPERATION_NAME = "studentName";
        public String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        public String SOAP_ADDRESS;
        private SoapObject request;
        private HttpTransport httpTransport;
        private SoapSerializationEnvelope envelope;
        Object response = null;

        @Override
        protected String doInBackground(String... params) {
            SOAP_ADDRESS = "http://" + params[0] + "/WebService.asmx";
            request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);
            PropertyInfo pi = new PropertyInfo();
            pi.setName("ID");
            //pi.name = "ID";
            pi.setValue(Integer.parseInt(params[1]));


            pi.type = Integer.class;
            pi.setType(Integer.class);
            request.addProperty(String.valueOf(pi), response);

            //pi = new PropertyInfo();

            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            httpTransport = new HttpTransport(SOAP_ADDRESS);
            try {
                httpTransport.call(SOAP_ACTION, envelope);
                response = envelope.getResponse();
            } catch (Exception exp) {
                response = exp.getMessage();
            }
            return response.toString();
        }

        @Override
        protected void onPostExecute(final String result) {

            super.onPostExecute(result);

            // mHandler = new Handler();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    tv_name.setText(result);
                }
            });

        }

    }
}
