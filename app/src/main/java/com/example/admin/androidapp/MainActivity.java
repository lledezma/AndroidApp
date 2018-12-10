package com.example.admin.androidapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button[][] buttons = new Button[3][3];
	private boolean player1Turn = true;
	private int roundCount;
	private int player1Points;
	private int player2Points;
	private TextView textViewPlayer1;
	private TextView textViewPlayer2;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		textViewPlayer1 = findViewById(R.id.text_view_player1);
		textViewPlayer2 = findViewById(R.id.text_view_player2);


		for (int i = 0; i < 3; i++) {
			//board
			for (int j = 0; j < 3; j++) {
				String buttonID = "button_" + i + j;
				int resID = getResources().getIdentifier(buttonID, "id",getPackageName());
				buttons[i][j] =findViewById(resID);
				buttons[i][j].setOnClickListener(this);
			}
		}
		Button buttonReset = findViewById(R.id.button_reset);
		buttonReset.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View V){

			}
		});
	}

	@Override
	public void onClick(View v) {
		if(!((Button) v).getText().toString().equals("")) {
			return;
		}

		if(player1Turn){
			((Button) v).setText("X");
		}else{
			((Button) v).setText("O");
		}

		roundCount++;

		if(checkForWin()){
			if (player1Turn) {
				player1wins();
			}else {
				player2Wins();
			}
		}else if (roundCount == 9){
			draw();
		}else{
			player1Turn = !player1Turn;
		}

	}
	private boolean checkForWin() {
		String[][] GameBoard = new String[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				GameBoard[i][j] = buttons[i][j].getText().toString();

			}
		}
		for(int i =0;i<3; i++){
			if(GameBoard[i][0].equals(GameBoard[i][1])
					&& GameBoard[i][0].equals(GameBoard[i][2])
					&& !GameBoard[i][0].equals("")){
				return true;
			}
		}
		for(int i =0;i<3; i++){
			if(GameBoard[0][i].equals(GameBoard[1][i])
					&& GameBoard[0][i].equals(GameBoard[2][i])
					&& !GameBoard[0][i].equals("")){
				return true;
			}

		}
		if(GameBoard[0][0].equals(GameBoard[1][1])
				&& GameBoard[0][0].equals(GameBoard[2][2])
				&& !GameBoard[0][0].equals("")){
			return true;
		}

		if(GameBoard[0][2].equals(GameBoard[1][1])
				&& GameBoard[0][2].equals(GameBoard[2][0])
				&& !GameBoard[0][2].equals("")){
			return true;
		}
		return false;
	}
	private void player1wins() {
		player1Points++;
		Toast.makeText(this, "player 1 wins!", Toast.LENGTH_SHORT).show();
		updatePointsText();
		resetBoard();
	}
	private void player2Wins(){
			player2Points++;
			Toast.makeText(this, "player 2 wins!", Toast.LENGTH_SHORT).show();
			updatePointsText();
			resetBoard();
	}
	private void draw(){
		Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
		resetBoard();
	}
	private  void updatePointsText(){
		textViewPlayer1.setText("Player 1: " + player1Points);
		textViewPlayer2.setText("Player 2: " + player2Points);
	}
	private void resetBoard() {
		for(int i = 0; i <3; i++){
			for (int j =0; j<3;j++){
				buttons[i][j].setText("");
			}
		}

		roundCount = 0;
		player1Turn = true;
	}
}
