<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row> -->
    <b-row>
      <b-col></b-col>
      <b-col cols="7">
        <b-card
          class="text-center"
          style="max-width: 40rem height: 100%"
          align="center"
          title="회원가입"
        >
          <hr />
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isRegisterCheckIdError"
              >이미 사용 중인 아이디입니다.</b-alert
            >
            <b-alert show variant="danger" v-if="isRegisterError"
              >빈칸 없이 작성해주세요.</b-alert
            >
            <b-alert show variant="danger" v-if="isEmailCheckError"
              >이메일 형식에 맞게 작성해주세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="register"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                type="text"
                id="username"
                v-model="user.username"
                required
                placeholder="이름 입력...."
                @keyup.enter="register"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="register"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="user.email"
                required
                placeholder="이메일 입력...."
                @keyup.enter="register"
              ></b-form-input>
            </b-form-group>
            <div class="text-right">
              <b-button
                type="button"
                variant="secondary"
                class="btn btn-primary btn-lg button-size"
                @click="register"
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
import { mapState, mapActions, mapMutations } from "vuex";
const memberStore = "memberStore";

export default {
  name: "UserRegister",
  data() {
    return {
      user: {
        userid: null,
        username: null,
        userpwd: null,
        email: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, [
      "isRegisterError",
      "isRegisterCheckIdError",
      "isRegister",
      "isEmailCheckError",
    ]),
  },
  created() {
    this.changeall(false);
    // this.SET_IS_REGISTER_CHECK_ID_ERROR(false),
    // this.SET_IS_EMAIL_CHECK_ERROR(false),
    // this.SET_IS_REGISTER_ERROR(false),
  },

  methods: {
    ...mapActions(memberStore, ["RegisterUserConfirm", "changeall"]),
    ...mapMutations(memberStore, [
      "SET_IS_REGISTER_CHECK_ID_ERROR",
      "SET_IS_EMAIL_CHECK_ERROR",
      "SET_IS_REGISTER_ERROR",
    ]),
    async register() {
      await this.RegisterUserConfirm(this.user);
      if (this.isRegister) {
        alert("회원 가입 성공!!");
        this.$router.push({ name: "login" });
      }
    },
  },
};
</script>

<style scoped>
.card {
  height: 100%;
}
.button-size {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 25px;
}
</style>
