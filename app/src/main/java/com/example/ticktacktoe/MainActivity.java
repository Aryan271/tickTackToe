package com.example.ticktacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    boolean player1turn = true;
    private int roundCount;
    int player1points = 0;
    int player2points = 0;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer1 = findViewById(R.id.player_one);
        textViewPlayer2 = findViewById(R.id.player_two);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String ButtonId = "btn" + i + j;
                int resID = getResources().getIdentifier(ButtonId, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }}
        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });

    }

    @Override
    public void onClick(View v) {

        if(!((Button) v).getText().toString().equals(""))
        {
            return;
        }
        if(player1turn)
        {
            ((Button) v).setText("X");
        }
        else
        {
            ((Button) v).setText("O");
        }
        roundCount++;
        if(win()) {
            if (player1turn) {
                player1wins();
            } else {
                player2wins();
            }
        }
        else if(roundCount == 9)
        {
            draw();
        }
        else
        {
            player1turn = !player1turn;
        }

    }
    private boolean win()
    {
        String[][] block = new String[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                block[i][j] = buttons[i][j].getText().toString();
            }
        }
        for(int i =0;i<3;i++)
        {
            if(block[i][0].equals(block[i][1]) && block[i][0].equals(block[i][2]) && !block[i][0].equals(""))
                return true;
        }
        for(int j =0;j<3;j++)
        {
            if(block[0][j].equals(block[1][j]) && block[0][j].equals(block[2][j]) && !block[0][j].equals(""))
                return true;
        }
        if(block[0][0].equals(block[1][1]) && block[0][0].equals(block[2][2]) && !block[0][0].equals(""))
        {
            return true;
        }
        if(block[0][2].equals(block[1][1]) && block[0][2].equals(block[2][0]) && !block[0][2].equals(""))
        {
            return true;
        }
        return false;
    }
    private void player1wins()
    {
        player1points++;
        Toast.makeText(this, "Player 1 wins!!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void player2wins()
    {
        player2points++;
        Toast.makeText(this, "Player 2 wins!!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw()
    {
        Toast.makeText(this, "Draw!!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointsText()
    {
        textViewPlayer1.setText("Player 1: " + player1points);
        textViewPlayer2.setText("Player 2: " + player2points);
    }
    private void resetBoard()
    {
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1turn = true;
    }

    private void resetGame() {
        player1points = 0;
        player2points = 0;
        resetBoard();
        updatePointsText();
    }
}
