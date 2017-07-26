import { getJSON } from '../../../utils/apiRequest';

// ------------------------------------
// Constants
// ------------------------------------
export const LOG_IN_CALLBACK = 'LOG_IN_CALLBACK';

// ------------------------------------
// Actions
// ------------------------------------
export const logInCallback = (accessToken, state) => {
  return (dispatch, getState) => {
    return new Promise((resolve) => {
      getJSON('auth?code=' + accessToken + "&state=" + state)
        .then((response) => {
          dispatch({
            type    : LOG_IN_CALLBACK,
            payload : response
          });
          resolve();
        });
    });
  };
};

export const logIn = () => {
  return (dispatch, getState) => {
    return new Promise((resolve) => {
      getJSON('auth')
        .then((response) => {
          if (response.authorizeURL) {
            window.location = response.authorizeURL;
          }
        });
    });
  };
};

export const actions = {
  logIn,
  logInCallback
};

// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [LOG_IN_CALLBACK] : (state, action) => {
    window.location.search = '';
    localStorage.setItem('accessToken', action.payload.accessToken);
    localStorage.setItem('refreshToken', action.payload.refreshToken);
    return action.payload;
  }
};

// ------------------------------------
// Reducer
// ------------------------------------
let initialState = 0;
export default function loginReducer (state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type];

  return handler ? handler(state, action) : state;
}
