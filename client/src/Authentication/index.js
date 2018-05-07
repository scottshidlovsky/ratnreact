import {ofType} from "redux-observable";
import {exhaustMap, map, catchError} from "rxjs/operators";
import {of} from 'rxjs';
import service from '../api';

export const AUTHENTICATE = '[Auth] Login';
export function authenticate(username, password) {
  return {
    type: AUTHENTICATE,
    payload: {
      username,
      password
    }
  }
}
export const AUTHENTICATE_SUCCESS = '[Auth] Login Success';
export function authenticationSucess() {
  return {
    type: AUTHENTICATE_SUCCESS,
  }
}

export const AUTHENTICATE_FAILURE = '[Auth] Login failure';
export function authenticationFailure() {
  return {
    type: AUTHENTICATE_FAILURE
  }
}

const initialState = {
  authenticated: false,
  username: null,
  error: false
};
export function authenticationReducer(state = initialState, action) {
  switch(action.type) {
    case AUTHENTICATE:
      return { ...state, username: action.payload.username, error: false };
    case AUTHENTICATE_SUCCESS:
      return { ...state, authenticated: true, error: false};
    case AUTHENTICATE_FAILURE:
      return { ...state, authenticated: false, username: null, error: true };
    default:
      return state;
  }
}

export const authenticateEpic$ = action$ => {
  return action$.pipe(
    ofType(AUTHENTICATE),
    exhaustMap(action => {
      return service('login', {
        body: JSON.stringify({username: action.payload.username, password: action.payload.password}), // must match 'Content-Type' header
        headers: {
          'content-type': 'application/json'
        },
        method: 'POST',
      }, false).then((response) => {
        if (!response.ok) {
          throw new Error('Login failed');
        }
        return response.json();
      });
    }),
    map(result => {
      sessionStorage.token = result.token;
      return authenticationSucess();
    }),
    catchError((err) => {
      return of(authenticationFailure());
    })
  )
} ;