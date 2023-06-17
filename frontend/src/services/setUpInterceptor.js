import http from "./index";
import { useToast } from 'vue-toastification';

const toast = useToast();

const setup = (router, store) => {
  http.interceptors.request.use(
    (config) => {
      const token = store.state.auth.user?.accessToken;

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
        
      console.log('Error');
        if (err.response?.status === 401) {
          toast.error("Phiên hết hạn. Vui lòng đăng nhập lại!");
          router.push({ path: "/" }).catch(() => {});
          localStorage.removeItem("vuex");
        } else {
          toast.error("Có lỗi hê thống. Xin vui lòng thử lại!");
        }

        return Promise.reject(err);
      }
    );
};

export default setup;
