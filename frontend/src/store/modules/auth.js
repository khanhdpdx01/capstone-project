import AuthService from "../../services/AuthService";
const service = new AuthService();

const state = {
  isLogin: false,
  user: {},
};

const mutations = {
  SET_IS_LOGIN(state, isLogin) {
    state.isLogin = isLogin;
  },
  SET_USER(state, user) {
    state.user = user;
  },
};

const actions = {
  async login({ commit }, info) {
    try {
      const res = await service.login(info);
      console.log(res.data);
      if (res.status === 200 && res.data) {
        commit("SET_IS_LOGIN", true);
        commit("SET_USER", res.data);
      }
    } catch (err) {
      commit("SET_IS_LOGIN", false);
    }
  },
};

const getters = {
  hasLogin: (state) => state.isLogin,
  getUser: (state) => state.user,
  getUserId: (state) => state.user.userId,
  getAccessToken: (state) => state.user.accessToken,
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
};
