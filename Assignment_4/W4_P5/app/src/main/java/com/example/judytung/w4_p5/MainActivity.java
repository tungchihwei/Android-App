package com.example.judytung.w4_p5;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_A, btn_B, btn_C, btn_D, btn_E, btn_F, btn_G, btn_H, btn_I,
            btn_J, btn_K, btn_L, btn_M, btn_N, btn_O, btn_P, btn_Q, btn_R,
            btn_S, btn_T, btn_U, btn_V, btn_W, btn_X, btn_Y, btn_Z, btn_new;

    TextView txt_word, txt_empty, txt_hint;

    ImageView img_hangman;

    String word = "";
    String Entered = "";
    String hint = "";
    int hang = 0;
    int win = 0;
    int orientation;
    int land = 0;
    int ran_num = 0;
    int get = 0;

    Map<String, String> word_blank = new HashMap<>();
    Map<String, String> Hints = new HashMap<>();
    ArrayList<String> words = new ArrayList<>();

    private static final String IMAGE_RESOURCE = "image-resource";
    private int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_A = (Button) findViewById(R.id.btn_A);
        btn_B = (Button) findViewById(R.id.btn_B);
        btn_C = (Button) findViewById(R.id.btn_C);
        btn_D = (Button) findViewById(R.id.btn_D);
        btn_E = (Button) findViewById(R.id.btn_E);
        btn_F = (Button) findViewById(R.id.btn_F);
        btn_G = (Button) findViewById(R.id.btn_G);
        btn_H = (Button) findViewById(R.id.btn_H);
        btn_I = (Button) findViewById(R.id.btn_I);
        btn_J = (Button) findViewById(R.id.btn_J);
        btn_K = (Button) findViewById(R.id.btn_K);
        btn_L = (Button) findViewById(R.id.btn_L);
        btn_M = (Button) findViewById(R.id.btn_M);
        btn_N = (Button) findViewById(R.id.btn_N);
        btn_O = (Button) findViewById(R.id.btn_O);
        btn_P = (Button) findViewById(R.id.btn_P);
        btn_Q = (Button) findViewById(R.id.btn_Q);
        btn_R = (Button) findViewById(R.id.btn_R);
        btn_S = (Button) findViewById(R.id.btn_S);
        btn_T = (Button) findViewById(R.id.btn_T);
        btn_U = (Button) findViewById(R.id.btn_U);
        btn_V = (Button) findViewById(R.id.btn_V);
        btn_W = (Button) findViewById(R.id.btn_W);
        btn_X = (Button) findViewById(R.id.btn_X);
        btn_Y = (Button) findViewById(R.id.btn_Y);
        btn_Z = (Button) findViewById(R.id.btn_Z);
        btn_new = (Button) findViewById(R.id.btn_new);

        txt_word = (TextView) findViewById(R.id.txt_word);
        txt_empty = (TextView) findViewById(R.id.txt_empty);

        orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            txt_hint = (TextView) findViewById(R.id.txt_hint) ;
            land = 1;
        }
        else
            land = 0;

        img_hangman = (ImageView) findViewById(R.id.img_hangman);
        image = R.drawable.hang0;

        word_blank.put("apple", "_ _ _ _ _");
        word_blank.put("tiger", "_ _ _ _ _");
        word_blank.put("banana", "_ _ _ _ _ _");
        word_blank.put("pink", "_ _ _ _");
        word_blank.put("cake", "_ _ _ _");
        word_blank.put("dog", "_ _ _");
        word_blank.put("green", "_ _ _ _ _");

        Hints.put("apple", "Food");
        Hints.put("tiger", "Animal");
        Hints.put("banana", "Food");
        Hints.put("pink", "Color");
        Hints.put("cake", "Food");
        Hints.put("dog", "Animal");
        Hints.put("green", "Color");

        words.add("apple");
        words.add("tiger");
        words.add("banana");
        words.add("pink");
        words.add("cake");
        words.add("dog");
        words.add("green");


        if (savedInstanceState != null) {
            txt_word.setText(savedInstanceState.getString("entered_show", ""));
            word = savedInstanceState.getString("word_now", "");
            hint = savedInstanceState.getString("hint_now", "");
            if (land == 1)
                txt_hint.setText(hint);
            Entered = savedInstanceState.getString("word_enter", "");
            hang = savedInstanceState.getInt("hang_num", 0);
            win = savedInstanceState.getInt("win_num", 0);
            ran_num = savedInstanceState.getInt("ran", 0);
            image = savedInstanceState.getInt(IMAGE_RESOURCE, R.drawable.hang0);
            get = savedInstanceState.getInt("get_same", 0);
            img_hangman.setImageResource(image);
            btn_A.setEnabled(savedInstanceState.getBoolean("btnA_Enabled", true));
            btn_B.setEnabled(savedInstanceState.getBoolean("btnB_Enabled", true));
            btn_C.setEnabled(savedInstanceState.getBoolean("btnC_Enabled", true));
            btn_D.setEnabled(savedInstanceState.getBoolean("btnD_Enabled", true));
            btn_E.setEnabled(savedInstanceState.getBoolean("btnE_Enabled", true));
            btn_F.setEnabled(savedInstanceState.getBoolean("btnF_Enabled", true));
            btn_G.setEnabled(savedInstanceState.getBoolean("btnG_Enabled", true));
            btn_H.setEnabled(savedInstanceState.getBoolean("btnH_Enabled", true));
            btn_I.setEnabled(savedInstanceState.getBoolean("btnI_Enabled", true));
            btn_J.setEnabled(savedInstanceState.getBoolean("btnJ_Enabled", true));
            btn_K.setEnabled(savedInstanceState.getBoolean("btnK_Enabled", true));
            btn_L.setEnabled(savedInstanceState.getBoolean("btnL_Enabled", true));
            btn_M.setEnabled(savedInstanceState.getBoolean("btnM_Enabled", true));
            btn_N.setEnabled(savedInstanceState.getBoolean("btnN_Enabled", true));
            btn_O.setEnabled(savedInstanceState.getBoolean("btnO_Enabled", true));
            btn_P.setEnabled(savedInstanceState.getBoolean("btnP_Enabled", true));
            btn_Q.setEnabled(savedInstanceState.getBoolean("btnQ_Enabled", true));
            btn_R.setEnabled(savedInstanceState.getBoolean("btnR_Enabled", true));
            btn_S.setEnabled(savedInstanceState.getBoolean("btnS_Enabled", true));
            btn_T.setEnabled(savedInstanceState.getBoolean("btnT_Enabled", true));
            btn_U.setEnabled(savedInstanceState.getBoolean("btnU_Enabled", true));
            btn_V.setEnabled(savedInstanceState.getBoolean("btnV_Enabled", true));
            btn_W.setEnabled(savedInstanceState.getBoolean("btnW_Enabled", true));
            btn_X.setEnabled(savedInstanceState.getBoolean("btnX_Enabled", true));
            btn_Y.setEnabled(savedInstanceState.getBoolean("btnY_Enabled", true));
            btn_Z.setEnabled(savedInstanceState.getBoolean("btnZ_Enabled", true));
        }
        else {
            //Get the word of the game
            Random ran = new Random();
            int w = ran.nextInt(7);
            ran_num = w;
            word = words.get(w);
            txt_word.setText(word_blank.get(word)); //set the blank of the word
            hint = "HINT: " + Hints.get(word);
            if (land == 1)
                txt_hint.setText(hint); //set the Hint of the word

            Entered = word_blank.get(word);
        }
        btn_new.setOnClickListener(this);
        btn_A.setOnClickListener(this);
        btn_B.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn_D.setOnClickListener(this);
        btn_E.setOnClickListener(this);
        btn_F.setOnClickListener(this);
        btn_G.setOnClickListener(this);
        btn_H.setOnClickListener(this);
        btn_I.setOnClickListener(this);
        btn_J.setOnClickListener(this);
        btn_K.setOnClickListener(this);
        btn_L.setOnClickListener(this);
        btn_M.setOnClickListener(this);
        btn_N.setOnClickListener(this);
        btn_O.setOnClickListener(this);
        btn_P.setOnClickListener(this);
        btn_Q.setOnClickListener(this);
        btn_R.setOnClickListener(this);
        btn_S.setOnClickListener(this);
        btn_T.setOnClickListener(this);
        btn_U.setOnClickListener(this);
        btn_V.setOnClickListener(this);
        btn_W.setOnClickListener(this);
        btn_X.setOnClickListener(this);
        btn_Y.setOnClickListener(this);
        btn_Z.setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("word_now", word);
        outState.putString("word_enter", Entered);
        outState.putString("hint_now", hint);
        outState.putString("entered_show", txt_word.getText().toString());
        outState.putInt("screen_land", land);
        outState.putInt("hang_num", hang);
        outState.putInt("win_num", win);
        outState.putInt("get_same", get);
        outState.putInt(IMAGE_RESOURCE, image);
        outState.putInt("ran", ran_num);
        outState.putBoolean("btnA_Enabled", btn_A.isEnabled());
        outState.putBoolean("btnB_Enabled", btn_B.isEnabled());
        outState.putBoolean("btnC_Enabled", btn_C.isEnabled());
        outState.putBoolean("btnD_Enabled", btn_D.isEnabled());
        outState.putBoolean("btnE_Enabled", btn_E.isEnabled());
        outState.putBoolean("btnF_Enabled", btn_F.isEnabled());
        outState.putBoolean("btnG_Enabled", btn_G.isEnabled());
        outState.putBoolean("btnH_Enabled", btn_H.isEnabled());
        outState.putBoolean("btnI_Enabled", btn_I.isEnabled());
        outState.putBoolean("btnJ_Enabled", btn_J.isEnabled());
        outState.putBoolean("btnK_Enabled", btn_K.isEnabled());
        outState.putBoolean("btnL_Enabled", btn_L.isEnabled());
        outState.putBoolean("btnM_Enabled", btn_M.isEnabled());
        outState.putBoolean("btnN_Enabled", btn_N.isEnabled());
        outState.putBoolean("btnO_Enabled", btn_O.isEnabled());
        outState.putBoolean("btnP_Enabled", btn_P.isEnabled());
        outState.putBoolean("btnQ_Enabled", btn_Q.isEnabled());
        outState.putBoolean("btnR_Enabled", btn_R.isEnabled());
        outState.putBoolean("btnS_Enabled", btn_S.isEnabled());
        outState.putBoolean("btnT_Enabled", btn_T.isEnabled());
        outState.putBoolean("btnU_Enabled", btn_U.isEnabled());
        outState.putBoolean("btnV_Enabled", btn_V.isEnabled());
        outState.putBoolean("btnW_Enabled", btn_W.isEnabled());
        outState.putBoolean("btnX_Enabled", btn_X.isEnabled());
        outState.putBoolean("btnY_Enabled", btn_Y.isEnabled());
        outState.putBoolean("btnZ_Enabled", btn_Z.isEnabled());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_new: {
                //Get the word of the game
                //String tmp = word;

                if(ran_num == 6)
                    ran_num = 1;
                else
                    ran_num += 1;

                word =  words.get(ran_num);
                Entered = word_blank.get(word);
                txt_word.setText(Entered); //set the blank of the word
                hint = "HINT: " + Hints.get(word);
                if (land == 1)
                    txt_hint.setText(hint);// set the Hint of the word

                hang = 0;
                win = 0;
                hangman();

                btn_A.setEnabled(true);
                btn_B.setEnabled(true);
                btn_C.setEnabled(true);
                btn_D.setEnabled(true);
                btn_E.setEnabled(true);
                btn_F.setEnabled(true);
                btn_G.setEnabled(true);
                btn_H.setEnabled(true);
                btn_I.setEnabled(true);
                btn_J.setEnabled(true);
                btn_K.setEnabled(true);
                btn_L.setEnabled(true);
                btn_M.setEnabled(true);
                btn_N.setEnabled(true);
                btn_O.setEnabled(true);
                btn_P.setEnabled(true);
                btn_Q.setEnabled(true);
                btn_R.setEnabled(true);
                btn_S.setEnabled(true);
                btn_T.setEnabled(true);
                btn_U.setEnabled(true);
                btn_V.setEnabled(true);
                btn_W.setEnabled(true);
                btn_X.setEnabled(true);
                btn_Y.setEnabled(true);
                btn_Z.setEnabled(true);

                break;
            }
            case R.id.btn_A: {
                while(true){
                    int index = word.indexOf("a");
                    if(index == -1) {
                        if(get == 1){
                            get = 0;
                            btn_A.setEnabled(false);
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_A.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_B: {
                while(true){
                    int index = word.indexOf("b");
                    if(index == -1) {
                        if(get == 1){
                            btn_B.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_B.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_C: {
                while(true){
                    int index = word.indexOf("c");
                    if(index == -1) {
                        if(get == 1){
                            btn_C.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_C.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_D: {
                while(true){
                    int index = word.indexOf("d");
                    if(index == -1) {
                        if(get == 1){
                            btn_D.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_D.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_E: {
                while(true){
                    int index = word.indexOf("e");
                    if(index == -1) {
                        if(get == 1){
                            btn_E.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_E.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_F: {
                while(true){
                    int index = word.indexOf("f");
                    if(index == -1) {
                        if(get == 1){
                            btn_F.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_F.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_G: {
                while(true){
                    int index = word.indexOf("g");
                    if(index == -1) {
                        if(get == 1){
                            btn_G.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_G.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_H: {
                while(true){
                    int index = word.indexOf("h");
                    if(index == -1) {
                        if(get == 1){
                            btn_H.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_H.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_I: {
                while(true){
                    int index = word.indexOf("i");
                    if(index == -1) {
                        if(get == 1){
                            btn_I.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_I.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_J: {
                while(true){
                    int index = word.indexOf("j");
                    if(index == -1) {
                        if(get == 1){
                            btn_J.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_J.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_K: {
                while(true){
                    int index = word.indexOf("k");
                    if(index == -1) {
                        if(get == 1){
                            btn_K.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_K.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_L: {
                while(true){
                    int index = word.indexOf("l");
                    if(index == -1) {
                        if(get == 1){
                            btn_L.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_L.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_M: {
                while(true){
                    int index = word.indexOf("m");
                    if(index == -1) {
                        if(get == 1){
                            btn_M.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_M.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_N: {
                while(true){
                    int index = word.indexOf("n");
                    if(index == -1) {
                        if(get == 1){
                            btn_N.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_N.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_O: {
                while(true){
                    int index = word.indexOf("o");
                    if(index == -1) {
                        if(get == 1){
                            btn_O.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_O.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_P: {
                while(true){
                    int index = word.indexOf("p");
                    if(index == -1) {
                        if(get == 1){
                            btn_P.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_P.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_Q: {
                while(true){
                    int index = word.indexOf("q");
                    if(index == -1) {
                        if(get == 1){
                            btn_Q.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_Q.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_R: {
                while(true){
                    int index = word.indexOf("r");
                    if(index == -1) {
                        if(get == 1){
                            btn_R.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_R.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_S: {
                while(true){
                    int index = word.indexOf("s");
                    if(index == -1) {
                        if(get == 1){
                            btn_S.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_S.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_T: {
                while(true){
                    int index = word.indexOf("t");
                    if(index == -1) {
                        if(get == 1){
                            btn_T.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_T.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_U: {
                while(true){
                    int index = word.indexOf("u");
                    if(index == -1) {
                        if(get == 1){
                            btn_U.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_U.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_V: {
                while(true){
                    int index = word.indexOf("V");
                    if(index == -1) {
                        if(get == 1){
                            btn_V.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_V.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_W: {
                while(true){
                    int index = word.indexOf("W");
                    if(index == -1) {
                        if(get == 1){
                            btn_W.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_W.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_X: {
                while(true){
                    int index = word.indexOf("x");
                    if(index == -1) {
                        if(get == 1){
                            btn_X.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_X.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_Y: {
                while(true){
                    int index = word.indexOf("y");
                    if(index == -1) {
                        if(get == 1){
                            btn_Y.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_Y.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
            case R.id.btn_Z: {
                while(true){
                    int index = word.indexOf("z");
                    if(index == -1) {
                        if(get == 1){
                            btn_Z.setEnabled(false);
                            get = 0;
                            break;
                        }
                        hang += 1;
                        hangman();
                        btn_Z.setEnabled(false);
                        break;
                    }
                    else {
                        get = 1;
                        win += 1;
                        winning(index);
                    }
                }
                break;
            }
        }
    }
    private void hangman()
    {
        if(hang == 0){
            image = R.drawable.hang0;
            img_hangman.setImageResource(R.drawable.hang0);
        }

        else if(hang == 1) {
            image = R.drawable.hang1;
            img_hangman.setImageResource(R.drawable.hang1);
        }
        else if(hang == 2) {
            image = R.drawable.hang2;
            img_hangman.setImageResource(R.drawable.hang2);
        }
        else if(hang == 3) {
            image = R.drawable.hang3;
            img_hangman.setImageResource(R.drawable.hang3);
        }
        else if(hang == 4) {
            image = R.drawable.hang4;
            img_hangman.setImageResource(R.drawable.hang4);
        }
        else if(hang == 5) {
            image = R.drawable.hang5;
            img_hangman.setImageResource(R.drawable.hang5);
        }
        else if(hang == 6){
            image = R.drawable.hang6;
            img_hangman.setImageResource(R.drawable.hang6);
            btn_A.setEnabled(false);
            btn_B.setEnabled(false);
            btn_C.setEnabled(false);
            btn_D.setEnabled(false);
            btn_E.setEnabled(false);
            btn_F.setEnabled(false);
            btn_G.setEnabled(false);
            btn_H.setEnabled(false);
            btn_I.setEnabled(false);
            btn_J.setEnabled(false);
            btn_K.setEnabled(false);
            btn_L.setEnabled(false);
            btn_M.setEnabled(false);
            btn_N.setEnabled(false);
            btn_O.setEnabled(false);
            btn_P.setEnabled(false);
            btn_Q.setEnabled(false);
            btn_R.setEnabled(false);
            btn_S.setEnabled(false);
            btn_T.setEnabled(false);
            btn_U.setEnabled(false);
            btn_V.setEnabled(false);
            btn_W.setEnabled(false);
            btn_X.setEnabled(false);
            btn_Y.setEnabled(false);
            btn_Z.setEnabled(false);
        }
    }
    private void winning(int index)
    {
        char[] tmp_enter = Entered.toCharArray();
        tmp_enter[index*2] = word.charAt(index);
        Entered = String.valueOf(tmp_enter);
        txt_word.setText(Entered);

        char[] tmp_word = word.toCharArray();
        tmp_word[index] = '0';
        word = String.valueOf(tmp_word);

        if(win == word.length()){
            Toast toast = Toast.makeText(getApplicationContext(), "YOU WIN! GO START A NEW GAME!", Toast.LENGTH_LONG);
            toast.show();
            btn_A.setEnabled(false);
            btn_B.setEnabled(false);
            btn_C.setEnabled(false);
            btn_D.setEnabled(false);
            btn_E.setEnabled(false);
            btn_F.setEnabled(false);
            btn_G.setEnabled(false);
            btn_H.setEnabled(false);
            btn_I.setEnabled(false);
            btn_J.setEnabled(false);
            btn_K.setEnabled(false);
            btn_L.setEnabled(false);
            btn_M.setEnabled(false);
            btn_N.setEnabled(false);
            btn_O.setEnabled(false);
            btn_P.setEnabled(false);
            btn_Q.setEnabled(false);
            btn_R.setEnabled(false);
            btn_S.setEnabled(false);
            btn_T.setEnabled(false);
            btn_U.setEnabled(false);
            btn_V.setEnabled(false);
            btn_W.setEnabled(false);
            btn_X.setEnabled(false);
            btn_Y.setEnabled(false);
            btn_Z.setEnabled(false);
        }
    }
}
