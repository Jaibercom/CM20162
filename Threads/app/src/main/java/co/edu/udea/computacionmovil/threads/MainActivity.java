package co.edu.udea.computacionmovil.threads;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSinHilos;
    private Button btnHilo;
    private Button btnAsyncTask;
    private Button btnCancelar;
    private Button btnAsyncDialog;
    private ProgressBar pbarProgreso;

    private ProgressDialog pDialog;

    private MiTareaAsincronaProgressBar tarea1;
    private MiTareaAsincronaDialog tarea2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSinHilos = (Button)findViewById(R.id.btnSinHilos);
        btnHilo = (Button)findViewById(R.id.btnHilo);
        btnAsyncTask = (Button)findViewById(R.id.btnAsyncTask);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnAsyncDialog = (Button)findViewById(R.id.btnAsyncDialog);
        pbarProgreso = (ProgressBar)findViewById(R.id.pbarProgreso);

        //Se asignan eventos a los botones
        btnSinHilos.setOnClickListener(this);
        btnHilo.setOnClickListener(this);
        btnAsyncTask.setOnClickListener(this);
        btnAsyncDialog.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSinHilos:
                handlerBtnSinHilos();
                break;
            case R.id.btnHilo:
                handlerBtnHilo();
                break;
            case R.id.btnAsyncTask:
                handlerBtnAsyncTask();
                break;
            case R.id.btnAsyncDialog:
                handlerBtnAsyncDialog();
                break;
            case R.id.btnCancelar:
                if (tarea1 != null && !tarea1.isCancelled()){
                    tarea1.cancel(true);
                }else{
                    Toast.makeText(MainActivity.this, "No ha iniciado un AsyncTask", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //Manejador ejemplo Sin Hilos
    private void handlerBtnSinHilos(){
        pbarProgreso.setMax(100);
        pbarProgreso.setProgress(0);
        for(int i=1; i<=10; i++) {
            iniciarContador();
            pbarProgreso.incrementProgressBy(10);
        }
        Toast.makeText(MainActivity.this, "Tarea finalizada", Toast.LENGTH_SHORT).show();
    }

    //Manejador ejemplo Con Hilos
    private void handlerBtnHilo() {
        new Thread(new Runnable() {
            public void run() {
                pbarProgreso.post(new Runnable() {
                    public void run() {
                        pbarProgreso.setProgress(0);
                    }
                });

                for (int i = 1; i <= 10; i++) {
                    iniciarContador();
                    pbarProgreso.post(new Runnable() {
                        public void run() {
                            pbarProgreso.incrementProgressBy(10);
                        }
                    });
                }

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "Tarea finalizada", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    //Manejador ejemplo Con AyncTask y ProgressBar
    private void handlerBtnAsyncTask(){
        tarea1 = new MiTareaAsincronaProgressBar();
        tarea1.execute();
    }

    //Manejador ejemplo Con AyncTask y Dialog
    private void handlerBtnAsyncDialog(){
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setMessage("Procesando...");
        pDialog.setCancelable(true);
        pDialog.setMax(100);

        tarea2 = new MiTareaAsincronaDialog();
        tarea2.execute();
    }

    private void iniciarContador() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }


    //Clase para implementar AyncTask en ProgressBar
    private class MiTareaAsincronaProgressBar extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=10; i++) {
                iniciarContador();

                publishProgress(i*10);

                if(isCancelled())break;
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];
            pbarProgreso.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {
            pbarProgreso.setMax(100);
            pbarProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Toast.makeText(MainActivity.this, "Tarea finalizada", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Tarea cancelada", Toast.LENGTH_SHORT).show();
        }
    }

    //Clase para implementar AyncTask en Dialog
    private class MiTareaAsincronaDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=10; i++) {
                iniciarContador();

                publishProgress(i*10);

                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];
            pDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {

            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiTareaAsincronaDialog.this.cancel(true);
                }
            });

            pDialog.setProgress(0);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }
}
