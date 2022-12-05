<template>
  <b-container class="bv-example-row mt-3">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
    <!-- <b-row>
      <b-col>
        <b-alert show><h3>QnA</h3></b-alert>
      </b-col>
    </b-row> -->
    <h1 class="board-title">QnA</h1>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button
          class="mt-3 btn-lg button-size"
          variant="outline-primary"
          @click="moveWrite()"
          v-if="userInfo.userid != `admin`"
          >QnA 작성</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          class="mt-4"
          hover
          :items="qna"
          :fields="fields"
          @row-clicked="viewArticle"
        >
          <template #cell(title)="data">
            <router-link
              :to="{
                name: 'qnaview',
                params: { board_no: data.item.board_no },
              }"
            >
              {{ data.item.title }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <div class="page-navigation">
      <div class="page-nav">
        <span class="page-total">전체 페이지 수: {{ this.totalCount }}</span> |
        <span class="page-now">현재 페이지 수: {{ this.nowPage }}</span>
      </div>
      <div>
        <div class="input-group"></div>
        <input
          class="w2-input w2-border w3-round-large"
          v-model="param.pg"
          @keyup.enter="movepage"
        />
        <b-button
          class="btn-lg button-size1"
          variant="outline-primary"
          @click="movepage"
          >이동</b-button
        >
      </div>
    </div>
  </b-container>
</template>

<script>
import { listArticle, getBoardTotalCount } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnAList",
  data() {
    return {
      qna: [],
      totalCount: "",
      fields: [
        { key: "board_no", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "time", label: "작성일", tdClass: "tdClass" },
      ],
      param: {
        pg: "1",
        spp: "20",
        key: null,
        word: null,
      },
      nowPage: "1",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    listArticle(
      this.param,
      ({ data }) => {
        console.log(data);
        this.qna = data;
      },
      (error) => {
        console.log(error);
      }
    );
    getBoardTotalCount(
      ({ data }) => {
        this.totalCount = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    movepage() {
      (this.param.pg = Math.min(this.param.pg, this.totalCount)),
        listArticle(
          this.param,
          ({ data }) => {
            this.qna = data;
          },
          (error) => {
            console.log(error);
          }
        );
      this.nowPage = this.param.pg;
    },

    moveWrite() {
      this.$router.push({ name: "qnawrite" });
    },
    viewArticle(qna) {
      this.$router.push({
        name: "qnaview",
        params: { board_no: qna.board_no },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
h1.board-title:after {
  content: "";
  display: block;
  width: 80px;
  border-bottom: 5px solid #1667c9;
  margin: 20px auto;
}
.page-navigation {
  float: center;
  margin-bottom: 50px;
}

.page-total {
  margin-right: 10px;
}

.page-now {
  margin-left: 10px;
}

.page-nav {
  margin-bottom: 10px;
}

.page-move {
  margin-left: 3px;
}

.button {
  border-radius: 20px;
  background-color: #407bff;
  color: white;
  /* padding: 10px 10px; */
  /* width: 80px; */

  /* font-size: 15px; */
}
.button-size {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 25px;
}
.button-size1 {
  font-size: 10px;
  border-radius: 30px;
  padding: 8px 12px;
}
</style>
