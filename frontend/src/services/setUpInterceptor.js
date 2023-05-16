import http from "./index";

const setup = (router, localStorage) => {
  http.interceptors.request.use(
    (config) => {
      const token = localStorage.getItem("access_token");

      if (token) {
        config.headers["Authorization"] = `Bearer ${token}`;
      }

      return config;
    },

    (error) => {
      return Promise.reject(error);
    }
  ),
    http.interceptors.response.use(
      (res) => {
        return res;
      },
      async (err) => {
        if (err.response?.status === 401) {
          router.push({ path: "/" }).catch(() => {});
          localStorage.remove("access_token");
          localStorage.removeItem("vuex");
        }

        return Promise.reject(err);
      }
    );
};

export default setup;
