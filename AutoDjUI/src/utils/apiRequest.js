import 'whatwg-fetch';

const BASE_URL = 'http://localhost:8080/';

export const getJSON = (path) => {
  return fetch(BASE_URL + path, {
    headers: {
      'Authorization': localStorage.getItem('accessToken'),
      'X-spotify-refresh-token': localStorage.getItem('refreshToken')
    }
  })
  .then(checkStatus)
  .then((response) => {
    return response.json();
  });
};

export const postJSON = (path, body) => {
  return fetch(BASE_URL + path, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('accessToken'),
      'X-spotify-refresh-token': localStorage.getItem('refreshToken')
    },
    body: JSON.stringify(body)
  })
  .then(checkStatus)
  .then((response) => {
    return response.json();
  });
};

// =====================================

const checkStatus = (response) => {
  if (!response.ok) {
    throw Error(response.statusText);
  }
  return response;
};
