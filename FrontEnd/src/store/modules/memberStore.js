import jwtDecode from "jwt-decode";
import router from "@/router";
import {
  login,
  findById,
  tokenRegeneration,
  logout,
  Register,
  deleteUser,
  updateUser,
} from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false, // 로그인 상태
    isLoginError: false, //로그인 가능 여부
    userInfo: null, //로그인 사용자 정보
    isValidToken: false,
    isRegisterError: false, //빈칸 여부
    isRegisterCheckIdError: false, //아이디 중복 여부
    isEmailCheckError: false, //이메일 여부
    isRegister: false, //회원가입 상태
    isRemoveUser: false, //유저 삭제 상태
    isUpateUserError: false, //정보 수정 빈칸 확인 여부
    isUpdateUser: false, //정보 수정 상태
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_REGISTER_ERROR: (state, isRegisterError) => {
      state.isRegisterError = isRegisterError;
    },
    SET_IS_REGISTER_CHECK_ID_ERROR: (state, isRegisterCheckIdError) => {
      state.isRegisterCheckIdError = isRegisterCheckIdError;
    },
    SET_IS_REGISTER: (state, isRegister) => {
      state.isRegister = isRegister;
    },
    SET_IS_EMAIL_CHECK_ERROR: (state, isEmailCheckError) => {
      state.isEmailCheckError = isEmailCheckError;
    },
    SET_IS_REMOVE_USER: (state, isRemoveUser) => {
      state.isRemoveUser = isRemoveUser;
    },
    SET_IS_UPDATE_USER_ERROR: (state, isUpateUserError) => {
      state.isUpateUserError = isUpateUserError;
    },
    SET_IS_UPDATE_USER: (state, isUpdateUser) => {
      state.isUpdateUser = isUpdateUser;
    },
  },
  actions: {
    changeupdate({ commit }, flag) {
      commit("SET_IS_UPDATE_USER_ERROR", flag);
      commit("SET_IS_EMAIL_CHECK_ERROR", flag);
    },
    loginchangeall({ commit }, flag) {
      commit("SET_IS_LOGIN_ERROR", flag);
    },
    changeall({ commit }, flag) {
      commit("SET_IS_REGISTER_CHECK_ID_ERROR", flag);
      commit("SET_IS_EMAIL_CHECK_ERROR", flag);
      commit("SET_IS_REGISTER_ERROR", flag);
    },
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            console.log(data);
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async removeUser({ commit }, userid) {
      await deleteUser(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_REMOVE_USER", true);
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("유저 삭제 실패!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async updateUser({ commit }, user) {
      console.log("asdasdasdaddasd");
      console.log(user.userid);
      console.log(user.username);
      console.log(user.userpwd);
      console.log(user.email);
      if (!null_check(user)) {
        //빈칸 있을 경우
        console.log("빈칸 있음!");
        commit("SET_IS_UPDATE_USER_ERROR", true);
        commit("SET_IS_EMAIL_CHECK_ERROR", false);
        commit("SET_IS_UPDATE_USER", false);
      } else if (!email_check(user.email)) {
        //이메일 형식 틀렸을 경우
        console.log("이메일 형식에 맞게 작성해주세요!");
        commit("SET_IS_UPDATE_USER_ERROR", false);
        commit("SET_IS_EMAIL_CHECK_ERROR", true);
        commit("SET_IS_UPDATE_USER", false);
      } else {
        commit("SET_IS_UPDATE_USER_ERROR", false);
        commit("SET_IS_EMAIL_CHECK_ERROR", false);
        await updateUser(
          user,
          ({ data }) => {
            if (data.message === "success") {
              console.log("성공~~!!");
              commit("SET_IS_UPDATE_USER", true);
              commit("SET_USER_INFO", user);
            } else {
              console.log("정보 수정 실패!!!");
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },

    async RegisterUserConfirm({ commit }, user) {
      if (!null_check(user)) {
        //빈칸 있을 경우
        console.log("빈칸 있음!");
        commit("SET_IS_REGISTER_ERROR", true);
        commit("SET_IS_REGISTER_CHECK_ID_ERROR", false);
        commit("SET_IS_EMAIL_CHECK_ERROR", false);
        commit("SET_IS_REGISTER", false);
      } else if (!email_check(user.email)) {
        //이메일 형식 틀렸을 경우
        console.log("이메일 형식에 맞게 작성해주세요!");
        commit("SET_IS_REGISTER_ERROR", false);
        commit("SET_IS_REGISTER_CHECK_ID_ERROR", false);
        commit("SET_IS_EMAIL_CHECK_ERROR", true);
        commit("SET_IS_REGISTER", false);
      } else {
        commit("SET_IS_REGISTER_ERROR", false);
        commit("SET_IS_EMAIL_CHECK_ERROR", false);
        await Register(
          user,
          ({ data }) => {
            console.log("회원가입 데이터:>>" + data);
            if (data === 2) {
              //아이디 중복!!
              commit("SET_IS_REGISTER_CHECK_ID_ERROR", true);
              commit("SET_IS_REGISTER", false);
            } else {
              //가입 성공!!
              commit("SET_IS_REGISTER_CHECK_ID_ERROR", false);
              commit("SET_IS_REGISTER", true);
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },

    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log(
        "토큰 재발급 >> 기존 토큰 정보 : {}",
        sessionStorage.getItem("access-token")
      );
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },

    async userLogout({ commit }, userid) {
      await logout(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

function email_check(email) {
  var reg_email =
    // eslint-disable-next-line
    /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
  if (!reg_email.test(email)) {
    return false;
  } else {
    return true;
  }
}

function null_check(user) {
  console.log("null_check.js>>!!");
  console.log(user.userid);
  console.log(user.username);
  console.log(user.userpwd);
  console.log(user.email);
  if (
    user.userid == "" ||
    user.userid == null ||
    user.username == "" ||
    user.username == null ||
    user.userpwd == "" ||
    user.userpwd == null ||
    user.email == "" ||
    user.email == null
  ) {
    return false;
  } else {
    return true;
  }
}

export default memberStore;
