import React, { Component } from 'react';
import blank from './../blank.png';
import cross from './../x.png';
import nought from './../o.png';

class GameCell extends Component{

	constructor(props){
		super(props);
	}

	render(){
		let gamePiece;
		switch(this.props.value){
			case 0:
			gamePiece =  <img alt="blank" src={blank}></img>;
			break;
			case 1:
			gamePiece =  <img alt="cross" src={cross}></img>;
			break;
			case 2:
			gamePiece = <img alt="nought" src={nought}></img>;
			break;
		}
		return(<div>{gamePiece}{this.props.row}{this.props.col}</div>);
	}

}

const cellStyle = {
	display: "inline-block"
};

const titleStyle = {
	textAlign: "center"
};

class GameBoard extends Component{

	constructor(props){
		super(props)
		this.state = {
			board: [[0,1,1,2],[2,1,0,1],[1,0,0,2],[1,0,2,1]]
		};
	}	

	render(){
		return(
			<div>
				<h1 style={titleStyle}>Tic Tac Toe</h1>
				{this.state.board.map((row, rowId)=>{
					return(
						<div>
							{row.map((cell, colId)=>{
								return(
									<div style={cellStyle}>
										<GameCell value={cell} row={rowId} col={colId} />
									</div>
								);
							})}
						<br/>
						</div>
					);
				})}
			</div>
		);
	}
}

export default GameBoard;