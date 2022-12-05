<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원정보 수정</h3></b-alert>
      </b-col>
    </b-row> -->
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          class="text-center mt-3"
          style="max-width: 40rem height:100%"
          align="left"
          title="회원정보 수정"
        >
          <hr />
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isUpateUserError"
              >빈칸 없이 작성해주세요.</b-alert
            >
            <b-alert show variant="danger" v-if="isEmailCheckError"
              >이메일 형식에 맞게 작성해주세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                readonly
                id="userid"
                v-model="userInfo.userid"
                required
                @keyup.enter="update"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                type="text"
                id="username"
                v-model="userInfo.username"
                required
                placeholder="이름 입력...."
                @keyup.enter="update"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="userInfo.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="update"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="userInfo.email"
                required
                placeholder="이메일 입력...."
                @keyup.enter="update"
              ></b-form-input>
            </b-form-group>
            <div class="text-right">
              <b-button
                type="button"
                variant="primary"
                class="m-1 btn btn-primary btn-lg button-size"
                @click="update"
                >정보 수정</b-button
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
  name: "UserUpdate",

  computed: {
    ...mapState(memberStore, [
      "userInfo",
      "isUpateUserError",
      "isUpdateUser",
      "isEmailCheckError",
    ]),
  },
  created() {
    this.changeupdate(false);
  },

  methods: {
    ...mapActions(memberStore, ["updateUser", "changeupdate"]),
    async update() {
      await this.updateUser(this.userInfo);
      if (this.isUpdateUser) {
        this.$router.push({ name: "mypage" });
      }
    },
  },
};
</script>

<style>
.card {
  height: 100%;
}

.button-size {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 25px;
}
</style>
