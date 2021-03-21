package lab03.eim.systems.cs.pub.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends AppCompatActivity {
    final public static int PERMISSION_REQUEST_CALL_PHONE = 1;

    private EditText numberEditText;
    private ImageButton deleteButton, callButton, cancelButton;
    private Button oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton,
            eightButton, nineButton, zeroButton, starButton, hashButton;

    private DeleteButtonClickListener deleteButtonClickListener = new DeleteButtonClickListener();
    private class DeleteButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            String phoneNumber = numberEditText.getText().toString();
            if (phoneNumber.length() > 0) {
                numberEditText.setText(phoneNumber.substring(0, phoneNumber.length() - 1));
            }
        }
    }

    private DialButtonClickListener dialButtonClickListener = new DialButtonClickListener();
    private class DialButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            numberEditText.setText(numberEditText.getText() + ((Button) v).getText().toString());
        }
    }

    private CallButtonClickListener callButtonClickListener = new CallButtonClickListener();
    private class CallButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        PhoneDialerActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        PERMISSION_REQUEST_CALL_PHONE);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + numberEditText.getText().toString()));
                startActivity(intent);
            }
        }
    }

    private HangUpButtonClickListener hangUpButtonClickListener = new HangUpButtonClickListener();
    private class HangUpButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        numberEditText = (EditText) findViewById(R.id.phone_number_edit);
        deleteButton = (ImageButton) findViewById(R.id.delete_btn);
        deleteButton.setOnClickListener(deleteButtonClickListener);

        oneButton = (Button) findViewById(R.id.button_one);
        oneButton.setOnClickListener(dialButtonClickListener);
        twoButton = (Button) findViewById(R.id.button_two);
        twoButton.setOnClickListener(dialButtonClickListener);
        threeButton = (Button) findViewById(R.id.button_three);
        threeButton.setOnClickListener(dialButtonClickListener);
        fourButton = (Button) findViewById(R.id.button_four);
        fourButton.setOnClickListener(dialButtonClickListener);
        fiveButton = (Button) findViewById(R.id.button_five);
        fiveButton.setOnClickListener(dialButtonClickListener);
        sixButton = (Button) findViewById(R.id.button_six);
        sixButton.setOnClickListener(dialButtonClickListener);
        sevenButton = (Button) findViewById(R.id.button_seven);
        sevenButton.setOnClickListener(dialButtonClickListener);
        eightButton = (Button) findViewById(R.id.button_eight);
        eightButton.setOnClickListener(dialButtonClickListener);
        nineButton = (Button) findViewById(R.id.button_nine);
        nineButton.setOnClickListener(dialButtonClickListener);
        zeroButton = (Button) findViewById(R.id.button_zero);
        zeroButton.setOnClickListener(dialButtonClickListener);
        starButton = (Button) findViewById(R.id.button_star);
        starButton.setOnClickListener(dialButtonClickListener);
        hashButton = (Button) findViewById(R.id.button_hash);
        hashButton.setOnClickListener(dialButtonClickListener);

        callButton = (ImageButton) findViewById(R.id.call_btn);
        callButton.setOnClickListener(callButtonClickListener);
        cancelButton = (ImageButton) findViewById(R.id.cancel_btn);
        cancelButton.setOnClickListener(hangUpButtonClickListener);
    }
}