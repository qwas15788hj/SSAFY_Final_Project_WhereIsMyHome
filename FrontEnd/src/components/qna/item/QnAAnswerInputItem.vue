<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자:" label-for="userid">
          <b-form-input
            id="userid"
            :disabled="true"
            v-model="userInfo.userid"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="answer_content-group"
          label="내용:"
          label-for="answer_content"
        >
          <b-form-textarea
            id="answer_content"
            v-model="qna.answer_content"
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
            >답변 등록</b-button
          >
          <b-button
            type="submit"
            variant="primary"
            class="m-1 btn btn-primary btn-lg button-size"
            v-else
            >답변 수정</b-button
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
import { writeAnswer, getArticle } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnAAnswerInputItem",
  data() {
    return {
      qna: {
        board_no: 0,
        answer_content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let param = this.$route.params.board_no;
    console.log(param);
    getArticle(
      param,
      ({ data }) => {
        console.log("data!!!!");
        console.log(data);
        this.qna = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.qna.answer_content &&
        ((msg = "답변 내용을 입력해주세요"),
        (err = false),
        this.$refs.answer_content.focus());

      if (!err) alert(msg);
      else this.writeAnswerArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.qna.answer_content = "";
      this.moveList();
    },
    writeAnswerArticle() {
      console.log("호출>>>>>>>");
      let param = {
        board_no: this.qna.board_no,
        answer_content: this.qna.answer_content,
      };
      writeAnswer(
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
    // modifyArticle() {
    //   let param = {
    //     answer_content: this.qna.answer_content,
    //   };
    //   modifyArticle(
    //     param,
    //     ({ data }) => {
    //       let msg = "수정 처리시 문제가 발생했습니다.";
    //       if (data === "success") {
    //         msg = "수정이 완료되었습니다.";
    //       }
    //       alert(msg);
    //       // 현재 route를 /list로 변경.
    //       this.moveList();
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },
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
