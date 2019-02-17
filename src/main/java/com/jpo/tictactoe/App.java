package com.jpo.tictactoe;

import java.util.List;
import java.util.ArrayList;
import static spark.Spark.*;

public class App 
{
	public static List<GameBoard> GameRooms = new ArrayList();

    public static void main( String[] args )
    {

        post("/newroom/:size", (req,res)->{
        	int nextRoomId = GameRooms.size();
        	GameRooms.add(nextRoomId, new GameBoard(nextRoomId, Integer.parseInt(req.params(":size"))));
        	return "Room " + nextRoomId + "created";
        });

        post("/room/:id", (req,res)->{
        	if(req.queryParams("row") != null && req.queryParams("col") != null){

        		int row = Integer.parseInt(req.queryParams("row"));
        		int col = Integer.parseInt(req.queryParams("col"));

	        	int roomId = Integer.parseInt(req.params(":id"));
	        	GameBoard currentBoard = GameRooms.get(roomId);
	        	if(currentBoard != null){
	        		currentBoard.playerTurn(row, col);
	        		currentBoard.printBoard();
	        		return "Joining room " + roomId;
	        	} else{
	        		return "Room not found";
	        	}
        	}
        	return "Invalid parameters";
        });

        get("/room/:id", (req,res)->{
        	int roomId = Integer.parseInt(req.params(":id"));
        	GameBoard currentBoard = GameRooms.get(roomId);
        	if(currentBoard != null){
        		currentBoard.printBoard();
        		return "Joining room " + roomId;
        	} else{
        		return "Room not found";
        	}
        });
    }
}
