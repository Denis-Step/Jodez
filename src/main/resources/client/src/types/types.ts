
export type Team = "red" | "blue";
export type Action = "chooser" | "spymaster"
export type CurrTurn = "blue-spymaster" | "blue-chooser" | "red-spymaster" | "red-chooser";

export type Word = {
    [key: string]: "hidden" | "blue-revealed" | "red-revealed" | "bomb-revealed" | "neutral-revealed";
}

export type WordsState = Word[];

export interface PlayerState {
    attemptsLeft: number;
    bluePoints: number;
    redPoints: number;
    hint: string;
    action: Action;
    turn: Team;
    winner: Team | "none";
}