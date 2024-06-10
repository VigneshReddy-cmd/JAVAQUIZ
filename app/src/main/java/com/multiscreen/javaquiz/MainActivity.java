package com.multiscreen.javaquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.color.utilities.Score;

public class MainActivity extends AppCompatActivity {
    private Button yes;
    private Button no;
    private TextView question;
    private String[] questions = {"", "Does Java support Pointers?", "Are lambdas and streams introduced in Java 9?",
            "The implicit value of an object is NULL?", "int a=5, b=6; int x = ++a - b--;\nOutput is 0?", "A functional interface must contain only one method?","Does Bhanu Passes Exam?"};
    private boolean[] answers = {true, false, false, true, false,false};
    private int index;
    private int score;
    private TextView score_vis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        question = findViewById(R.id.input);

        index = 1;
        score = 0;
        question.setText(index + "." + questions[index]);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index <5 && answers[index]) {
                    score++;
                }
                if (index < questions.length - 1) {
                    index++;
                    question.setText(index + "." + questions[index]);
                } else {
                    showScore();
                    disableButtons();
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index<questions.length && !answers[index]) {
                    score++;
                }
                if (index < questions.length - 1) {
                    index++;
                    question.setText(index + "." + questions[index]);
                } else {
                    showScore();
                    disableButtons();
                }
            }
        });
    }

    private void showScore() {
       score_vis=findViewById(R.id.score);
       score_vis.setText("Contest Ended your Score is "+ score);
    }
    private void disableButtons() {
        yes.setOnClickListener(null);
        no.setOnClickListener(null);
    }
}
