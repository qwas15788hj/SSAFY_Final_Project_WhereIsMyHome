<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자:" label-for="userid">
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="qna.userid"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="title-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="qna.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="qna.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <div class="row justify-content-center">
          <b-button
            type="submit"
            variant="primary"
            class="m-1 btn btn-primary btn-lg button-size"
            v-if="this.type === 'register'"
            >등록</b-button
          >
          <b-button
            type="submit"
            variant="primary"
            class="m-1 btn btn-primary btn-lg button-size"
            v-else
            >수정</b-button
          >
          <b-button
            type="reset"
            variant="danger"
            class="m-1 btn btn-primary btn-lg button-size"
            >취소</b-button
          >
        </div>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeArticle, getArticle, modifyArticle } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnAInputItem",
  data() {
    return {
      qna: {
        board_no: 0,
        userid: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.board_no;
      getArticle(
        param,
        ({ data }) => {
          this.qna = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
    if (this.type === "register") {
      this.qna.userid = this.userInfo.userid;
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.qna.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.qna.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.qna.board_no = 0;
      this.qna.title = "";
      this.qna.content = "";
      this.moveList();
    },
    registArticle() {
      console.log("호출>>>>>>>");
      let param = {
        userid: this.qna.userid,
        title: this.qna.title,
        content: this.qna.content,
      };
      writeArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      let param = {
        board_no: this.qna.board_no,
        userid: this.qna.userid,
        title: this.qna.title,
        content: this.qna.content,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style>
.button-size {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 25px;
}
</style>
