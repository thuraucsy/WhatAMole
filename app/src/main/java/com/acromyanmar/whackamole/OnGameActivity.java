package com.acromyanmar.whackamole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Game start activity
 *
 * @author AcroMyanmar
 */
public class OnGameActivity extends Activity implements OnClickListener {

    /**
     * getting point for whacking a mole
     */
    private static final int GET_POINT_WHACK_MOLE = 100;

    /**
     * Game total time
     */
    private float gameTime_ = 60.00F;
    /**
     * text view for time
     */
    private TextView timeTextView_;
    /**
     * text view for Score
     */
    private TextView scoreTextView_;
    /**
     * Game Timer
     */
    private Timer gameTimer_;
    /**
     * handler for game thread
     */
    private Handler mHandler_ = new Handler();
    /**
     * image button array for moles
     */
    private ImageButton[][] moleBtnArray_ = new ImageButton[3][3];
    /**
     * next mole shown base time
     */
    private float nextMoleShownTime_ = 60.00F;
    /**
     * Total Score
     */
    private int score_ = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_game);

        // Put mole as a button
        moleBtnArray_[0][0] = (ImageButton) findViewById(R.id.imageButton1);
        moleBtnArray_[0][1] = (ImageButton) findViewById(R.id.imageButton2);
        moleBtnArray_[0][2] = (ImageButton) findViewById(R.id.imageButton3);
        moleBtnArray_[1][0] = (ImageButton) findViewById(R.id.imageButton4);
        moleBtnArray_[1][1] = (ImageButton) findViewById(R.id.imageButton5);
        moleBtnArray_[1][2] = (ImageButton) findViewById(R.id.imageButton6);
        moleBtnArray_[2][0] = (ImageButton) findViewById(R.id.imageButton7);
        moleBtnArray_[2][1] = (ImageButton) findViewById(R.id.imageButton8);
        moleBtnArray_[2][2] = (ImageButton) findViewById(R.id.imageButton9);

        // **************STEP 1 Implementation for Tap event *********************
        moleBtnArray_[0][0].setOnClickListener(this);
        moleBtnArray_[0][0].setClickable(false);
        moleBtnArray_[0][1].setOnClickListener(this);
        moleBtnArray_[0][1].setClickable(false);
        moleBtnArray_[0][2].setOnClickListener(this);
        moleBtnArray_[0][2].setClickable(false);
        moleBtnArray_[1][0].setOnClickListener(this);
        moleBtnArray_[1][0].setClickable(false);
        moleBtnArray_[1][1].setOnClickListener(this);
        moleBtnArray_[1][1].setClickable(false);
        moleBtnArray_[1][2].setOnClickListener(this);
        moleBtnArray_[1][2].setClickable(false);
        moleBtnArray_[2][0].setOnClickListener(this);
        moleBtnArray_[2][0].setClickable(false);
        moleBtnArray_[2][1].setOnClickListener(this);
        moleBtnArray_[2][1].setClickable(false);
        moleBtnArray_[2][2].setOnClickListener(this);
        moleBtnArray_[2][2].setClickable(false);



        timeTextView_ = (TextView) findViewById(R.id.timeText);
        scoreTextView_ = (TextView) findViewById(R.id.scoreText);

        printMole();

        gameStart();
    }

    @Override
    public void onClick(View view) {

        // **************STEP 1 Implementation for Tap event *********************

        ImageButton clickedBtn;
        clickedBtn = (ImageButton) findViewById(view.getId());
        clickedBtn.setImageResource(R.drawable.hole);

        clickedBtn.setClickable(false);

        this.score_ = getPoint(this.score_);
        scoreTextView_.setText(String.format("%1$05d", OnGameActivity.this.score_));


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

    /**
     * Start game.
     */
    private void gameStart() {

        if (gameTimer_ != null) {
            return;
        }

        // **************STEP 2 Implementation for Timer *********************
     gameTimer_ = new Timer(true);

      gameTimer_.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
              mHandler_.post(new Runnable() {
                  @Override
                  public void run() {
//                      1 + (int) (Math.random() * 9);
                      gameTime_ -= 0.01;
                      BigDecimal bi = new BigDecimal(gameTime_);
                      float outputValue = bi.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                      if (nextMoleShownTime_ > outputValue)
                      {
                          printMole();
                          nextMoleShownTime_ = calculateNextMoleTime(outputValue);
                      }

                      if (outputValue <= 0) {
                          gameOver();
                      }

                      timeTextView_.setText("0:" + Float.toString(outputValue));
                  }
              });
          }
      }, 1000, 10);


//        gameTime_ -= 0.05;

        // **************STEP 3 Implementation for Connecting Tap and Timer *********************


    }

    /**
     * Game over
     */
    private void gameOver() {
        if (this.gameTimer_ != null) {
            this.gameTimer_.cancel();
            this.gameTimer_ = null;
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("Score", this.score_);

        startActivity(intent);
        finish();
    }

    /**
     * Showing a mole
     */
    private void printMole() {
        int row = (int) (Math.random() * 3);
        int col = (int) (Math.random() * 3);
        if (!this.moleBtnArray_[row][col].isClickable()) {
            this.moleBtnArray_[row][col].setImageResource(R.drawable.button_mole);
            this.moleBtnArray_[row][col].setClickable(true);
        }
    }

    /**
     * set next time for mole shown
     *
     * @param currentTime current time
     * @return next shown time for a mole
     */
    public float calculateNextMoleTime(float currentTime) {
        return currentTime - (float) Math.random() * 2;
    }

    /**
     * Point getting
     *
     * @param score current score
     */
    public int getPoint(int score) {
        return score + GET_POINT_WHACK_MOLE;
    }
}

