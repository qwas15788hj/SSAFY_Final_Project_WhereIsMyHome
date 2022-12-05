<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1 mt-5">
      <b-col class="text-right title-button">
        <b-button
          variant="primary"
          @click="moveModifyArticle"
          class="mr-2 btn btn-primary btn-lg button-size"
          v-if="userInfo.userid === qna.userid"
          >수정</b-button
        >
        <b-button
          variant="danger"
          size="sm"
          @click="deleteArticle"
          class="btn btn-primary btn-lg button-size"
          v-if="userInfo.userid === `admin` || userInfo.userid === qna.userid"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`
          <div class='titlenews'>
            <div class='news-title'><h4><span class='title-title'>제목: ${qna.title}</span></h4>
                    <span class='title-time'>${qna.time}</span></div>
              <div class='title-writer'><h6>작성자: ${qna.userid}</h6></div>
            </div>
            `"
          class="mb-2"
          border-variant="white"
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <div>
      <b-row>
        <b-col>
          <div
            class="answer_total text-left"
            v-if="answer[0].answer_content != null"
          >
            <hr class="line" />
            <div>
              <span class="answer_id_ti">{{ answer[0].answer_id }}</span> |
              <span class="answer_time_ti">{{ answer[0].answer_time }}</span>
            </div>
            <div class="answer_content text-left">
              {{ answer[0].answer_content }}
            </div>
          </div>
          <hr />
          <div
            v-if="qna.answer_content == null && userInfo.userid != `admin`"
          ></div>
          <b-button
            v-if="qna.answer_content == null && userInfo.userid == `admin`"
            variant="outline-info"
            size="sm"
            @click="moveAnswerWriteArticle"
            class="mr-2"
            >답변 작성</b-button
          >
        </b-col>
      </b-row>
    </div>
    <b-button
      v-if="qna.answer_content != null && userInfo.userid == `admin`"
      variant="outline-info"
      @click="moveAnswerModifyArticle"
      class="mr-2 btn btn-lg button-size"
      >답변 수정</b-button
    >
    <!-- <b-col class="text-left"> -->
    <b-button
      variant="outline-primary"
      @click="moveList"
      class="btn btn-lg button-size"
      >목록</b-button
    >
    <!-- </b-col> -->
  </b-container>
</template>

<script>
import { getArticle } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnADetail",
  data() {
    return {
      qna: {},
      answer: [],
      // fields: [
      //   { key: "answer_id", label: "작성자", tdClass: "tdClass" },
      //   { key: "answer_content", label: "답변", tdClass: "tdSubject" },
      //   { key: "answer_time", label: "작성일", tdClass: "tdClass" },
      // ],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.qna.content) return this.qna.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.board_no;
    getArticle(
      param,
      ({ data }) => {
        this.qna = data;
        this.answer.push({
          answer_id: "admin",
          answer_content: data.answer_content,
          answer_time: data.answer_time,
        });
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "qnamodify",
        params: { board_no: this.qna.board_no },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      console.log("삭제 QnA 번호>>>>>>>>>>>" + this.qna.board_no);
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "qnadelete",
          params: { board_no: this.qna.board_no },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
    moveAnswerWriteArticle() {
      this.$router.push({
        name: "qnaanswerwrite",
        params: { board_no: this.qna.board_no },
      });
    },
    moveAnswerModifyArticle() {
      this.$router.push({
        name: "qnaanswermodify",
        params: { board_no: this.qna.board_no },
      });
    },
  },
};
</script>

<style>
/* #titlenews {
  display: flex;
  justify-content: space-between;
  vertical-align: top;
}
.title-writer {
  margin-top: 20%;
  margin-right: 20%;
}
.title-time {
  margin-top: 1%;
}
.line {
  margin-bottom: 3%;
}
.answer_total {
  margin-top: 3%;
  margin-left: 3%;
  margin-bottom: 3%;
}
.answer_id {
  margin-right: 3%;
}
.answer_time {
  margin-left: 3%;
}
.answer_content {
  margin-top: 2%;
} */
/* .card-header {
  background-color: white;
} */
.news-title {
  width: 100%;
  height: 1px;
}
.title-title {
  width: 80%;
  text-align: left;
  float: left;
}
.title-time {
  width: 20%;
  float: right;
  font-size: 15px;
}
.title_writer {
  text-align: left;
}
.button-size {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 25px;
}
</style>
