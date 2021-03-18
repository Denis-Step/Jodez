import React, { useCallback, useEffect } from "react";
import Cell from "./Cell";
import { chooseWord, refreshState } from "./redux/actions";
import { useSelector, useDispatch } from "react-redux";

interface BoardProps {
  game_ID: string;
}

const Board = (props: BoardProps): JSX.Element => {
  const gameState = useSelector((state) => {
    return { words: state.words, turn: state.turn };
  });
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(refreshState(props.game_ID));
  }, []);

  // @TODO: MODIFY ASAP TO TAKE PLAYER TEAM AND NOT STATE TEAM
  const handleClick = (word) => {
    dispatch(chooseWord(props.game_ID, gameState.turn, word));
  };

  if (gameState.words.length < 1) {
    console.log("empty");
    return <></>;
  } else {
    const cells: JSX.Element[] = [];

    for (const word in gameState.words) {
      cells.push(
        <Cell
          key={word}
          word={word}
          seen={gameState.words[word]}
          onClick={(event) => {
            handleClick(word);
          }}
        />
      );
    }

    return <div className={"board"}>{cells}</div>;
  }
};

export default Board;
