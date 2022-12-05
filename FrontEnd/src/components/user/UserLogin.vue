<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          title="로그인"
          class="card text-center mt-3 height:100%"
          style="max-width: 40rem"
          align="left"
        >
          <hr />

          <b-form class="cardform text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group
              class="cardformgroup"
              label="아이디:"
              label-for="userid"
            >
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group
              class="cardformgroup"
              label="비밀번호:"
              label-for="userpwd"
            >
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <div class="cardformgroup text-right">
              <b-button
                class="btn btn-primary btn-lg button-size1"
                type="button"
                variant="primary"
                @click="confirm"
                >로그인</b-button
              >
              <b-button
                type="button"
                variant="secondary"
                class="btn btn-primary btn-lg button-size2"
                @click="movePage"
                >회원가입</b-button
              >
            </div>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  created() {
    this.loginchangeall(false);
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, [
      "userConfirm",
      "getUserInfo",
      "loginchangeall",
    ]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style scpoed>
.card {
  background-color: rgba(255, 255, 255, 0.5);
  height: 100%;
}
.card .cardform .cardformgroup {
  margin: 30px auto;
}
.button-size1 {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 15px;
  margin-right: 1.5%;
}
.button-size2 {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 15px;
}
</style>
