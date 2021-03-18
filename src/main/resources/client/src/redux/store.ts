import { createStore, applyMiddleware } from "redux";
import thunkMiddleware from "redux-thunk";
import { words_reducer } from "./reducer";

const store = createStore(
  words_reducer,
  applyMiddleware(
    thunkMiddleware // lets us dispatch() functions
  )
);
export default store;
