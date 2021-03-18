import React from "react";
import { useRouteMatch } from "react-router-dom";
import Title from "./Title";
import Board from "./Board";
import StateBox from "./StateBox";

export interface MatchParams {
  id: string;
}

const Game = (): JSX.Element => {
  const match = useRouteMatch<MatchParams>("/games/:id") as {params: MatchParams};
  console.log(match)
  const game_ID = match.params.id;
  
  return (
    <div className={"main"}>
      <Title />
      <StateBox game_ID={game_ID} />
      <Board game_ID={game_ID} />
    </div>
  );
}

export default Game;