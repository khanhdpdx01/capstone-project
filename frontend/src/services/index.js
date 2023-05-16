import axios from "axios";

export default axios.create({
  baseURL: window.VUE_APP_SERVICE_ENDPOINT,
  // headers: {
  //   'Content-Type': 'application/json; charset=utf-8'
  // },
  timeout: 15000,
  withCredentials: true,
});
