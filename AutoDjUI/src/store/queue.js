import _ from 'lodash';
// ------------------------------------
// Constants
// ------------------------------------
export const ADD_SONG_TO_QUEUE = 'ADD_SONG_TO_QUEUE';

// ------------------------------------
// Actions
// ------------------------------------

export const addTrackToQueue = (track) => {
  return (dispatch, getState) => {
    return new Promise((resolve) => {
      dispatch({
        type: ADD_SONG_TO_QUEUE,
        payload: track
      });
      resolve();
    });
  };
};

export const actions = {
  addTrackToQueue
};

// ------------------------------------
// Action Handlers
// ------------------------------------
const ACTION_HANDLERS = {
  [ADD_SONG_TO_QUEUE] : (state, action) => {
    const newState = _.cloneDeep(state);
    newState.push(action.payload);
    return newState;
  }
};

// ------------------------------------
// Reducer
// ------------------------------------
let initialState = [];
export default function queueReducer (state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type];

  return handler ? handler(state, action) : state;
}
