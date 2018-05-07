import {combineEpics, ofType} from "redux-observable";
import { mergeMap } from "rxjs/operators";
import { authenticateEpic$ } from './Authentication';


const firstEpic = action$ =>
  action$.pipe(
    ofType("TEST"),
    mergeMap(() => {
      return {type: "T"}
    })
  );

export const rootEpic = combineEpics(
  firstEpic,
  authenticateEpic$

);