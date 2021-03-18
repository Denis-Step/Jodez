import {
  RECEIVE_TOKEN,
  RECEIVE_WORDS,
  CALL_WORDS,
  REVEAL_WORD,
  RECEIVE_GAME_STATE
} from "./actionTypes";
import {PlayerState, WordsState} from "../types/types";

export interface WordsStore extends PlayerState {
  auth: {
    authenticated: boolean;
    token: null | string;
  },
  words: WordsState;
  isFetching: boolean;
}

const initialState: WordsStore = {
  auth: {
    authenticated: false,
    token: null,
  },
  winner: "none",
  turn: "red",
  action: "spymaster",
  attemptsLeft: 0,
  redPoints: 0,
  bluePoints: 0,
  words: [],
  hint: "",
  isFetching: false,
};

export function words_reducer(state = initialState, action): WordsStore {
  console.log(state);
  console.log(action.type);
  switch (action.type) {
    case RECEIVE_TOKEN:
      return Object.assign({}, state, {
        auth: {
          authenticated: true,
          token: action.token,
        },
      });

    case RECEIVE_WORDS:
      console.log(action.words);
      return Object.assign({}, state, {
        words: action.words,
        isFetching: false,
      });
    case RECEIVE_GAME_STATE:
      console.log(action.gameInfo);
      return Object.assign({}, state, {
        winner: action.gameInfo.winner,
        action: action.gameInfo.action,
        turn: action.gameInfo.turn,
        attemptsLeft: action.gameInfo.attemptsLeft,
        redPoints: action.gameInfo.redPoints,
        bluePoints: action.gameInfo.bluePoints,
        hint: action.gameInfo.hint,
        isFetching: false,
      });
    case CALL_WORDS:
      return Object.assign({}, state, {
        isFetching: true,
      });
    case REVEAL_WORD:
      return Object.assign({}, state, {
        isFetching: true,
      });
    default:
      return state;
  }
}
