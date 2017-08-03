import { getJSON } from '../../../utils/apiRequest';

// ------------------------------------
// Constants
// ------------------------------------
export const SEARCH_TRACKS = 'SEARCH_TRACKS';

// ------------------------------------
// Actions
// ------------------------------------

export const search = (query) => {
  return (dispatch, getState) => {
    return new Promise((resolve) => {
      console.log(query);
      getJSON(`tracks/${query}`)
        .then((response) => {
          dispatch({
            type    : SEARCH_TRACKS,
            payload : response
          });
          resolve();
        });
    });
  };
};

export const actions = {
  search
};

// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [SEARCH_TRACKS] : (state, action) => {
    console.log(action.payload);
    return action.payload;
  }
};

// ------------------------------------
// Reducer
// ------------------------------------
const initialState = [];
export default function searchReducer (state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type];

  return handler ? handler(state, action) : state;
}
