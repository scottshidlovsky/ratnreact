// import {fromPromise} from "rxjs";

export default function service(url, options, addToken = true) {
  const mergedOptions = {
    ...options,
    headers: {
      ...options.headers,
      token: sessionStorage.token
    }
  };
  return fetch(`/api/${url}`, mergedOptions);
}