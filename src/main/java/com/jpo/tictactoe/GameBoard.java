package com.jpo.tictactoe;

class GameBoard {
	// Name-constants to represent the seeds and cell contents
	public static final int EMPTY = 0;
	public static final int CROSS = 1;
	public static final int NOUGHT = 2;

	// Name-constants to represent the various states of the game
	public static final int PLAYING = 0;
	public static final int DRAW = 1;
	public static final int CROSS_WON = 2;
	public static final int NOUGHT_WON = 3;

	protected int GameId;
	protected int[][] board;
	protected int currentState;  // the current state of the game                                // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
	protected int currentPlayer; // the current player (CROSS or NOUGHT)

	GameBoard(int _id, int size){
		this.GameId = _id;
		this.currentPlayer = CROSS;
		this.currentState = PLAYING;
		this.board = new int[size][size];
	}

	public boolean makeMove(int content, int row, int column){
		if(row < this.board.length && column < this.board[row].length){
			if(this.board[row][column] == EMPTY){
				this.board[row][column] = content;
				return true;
			}
		}
		return false;
	}

	protected int getCurrentPlayer(){
		return this.currentPlayer;
	}

	private void switchPlayer(){
		if(this.currentPlayer == CROSS){
			this.currentPlayer = NOUGHT;
		}else{
			this.currentPlayer = CROSS;
		}
	}

	private boolean hasWon(int content, int row, int column){
		boolean diagonalWin = false;
		boolean rowWin = false;
		boolean colWin = false;

		//check diagonal
		if(row == column){
			diagonalWin = true;
			for(int i = 0; i < this.board.length; i++){
				if(this.board[i][i] != content){
					diagonalWin = false;
					break;
				}
			}
		}
		if(diagonalWin){
			return true;
		}

		//check column
		for(int i = 0; i < this.board.length; i++){
			colWin = true;
			if(this.board[i][column] != content){
				colWin = false;
				break;
			}
		}
		if(colWin){
			return true;
		}

		//check row
		for(int i = 0; i < this.board[row].length; i++){
			rowWin = true;
			if(this.board[row][i] != content){
				rowWin = false;
				break;
			}
		}
		if(rowWin){
			return true;
		}

		return false;
	}

	private boolean isDraw(){
		for(int i = 0; i < this.board.length; i++){
			for(int j = 0; j < this.board[i].length; j++){
				if(this.board[i][j] != EMPTY){
					return false;
				}
			}
		}
		return true;
	}

	public boolean playerTurn(int row, int column){
		if(this.makeMove(this.getCurrentPlayer(), row, column)){
			if(this.hasWon(this.getCurrentPlayer(), row, column)){
				if(this.getCurrentPlayer() == NOUGHT){
					this.currentState = NOUGHT_WON;
				}
				else{
					this.currentState = CROSS_WON;
				}
			}else if(this.isDraw()){
				this.currentState = DRAW;
			}
			else{
				this.switchPlayer();
			}
			return true;
		}
		return false;
	}

	public int getState(){
		return this.currentState;
	}

	public void printBoard(){
		for(int row = 0; row < this.board.length;row++){
			System.out.println();
			for(int col = 0; col < this.board[row].length;col++){
				System.out.print(this.board[row][col]);
				if(col != this.board[row].length -1){
					System.out.print("|");
				}
			}
			if(row != this.board.length - 1){
				System.out.println();
				System.out.print("---------");
			}
		}
	}
}