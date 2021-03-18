import React from "react";

interface CellProps {
  seen: boolean;
  word: string;
  onClick: React.MouseEventHandler<HTMLElement>;
}

const Cell = (props: CellProps): JSX.Element => {
  let revealed = "";
  if (props.seen == false) {
    revealed = "cell not-revealed";
  } else {
    revealed = "cell " + props.seen;
  }

  return (
    <div id={props.word} onClick={props.onClick} className={revealed}>
      {props.word}
    </div>
  );
}

export default Cell
