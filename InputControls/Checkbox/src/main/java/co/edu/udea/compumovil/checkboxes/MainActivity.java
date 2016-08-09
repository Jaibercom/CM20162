package co.edu.udea.compumovil.checkboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkbox1;
    private CheckBox checkbox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
    }

    public void onCheckboxClicked(View view) {

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox1:
                if (checked)
                // Put some meat on the sandwich
                    checkbox1.setText("I'm checked");
                else
                    checkbox1.setText(this.getString(R.string.im_not_checked_string));
                // Remove the meat
                break;
            case R.id.checkbox2:
                if (checked)
                    checkbox2.setText("I'm checked");
                else
                    checkbox2.setText(this.getString(R.string.im_not_checked_string));
                break;

        }

    }

}
