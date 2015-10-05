package com.acromyanmar.whackamole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * Result activity after game over
 *
 * @author AcroMyanmar
 */
public class ResultActivity extends Activity implements OnClickListener {

    /**
     * score text view
     */
    private TextView scoreTextView_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent preIntent = getIntent();
        scoreTextView_ = (TextView) findViewById(R.id.resultScore);
        scoreTextView_.setText(Integer.toString(preIntent.getIntExtra("Score", 00000000)));


        ImageButton btn = (ImageButton) findViewById(R.id.imageButton1);
        btn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        finish();

    }
}

