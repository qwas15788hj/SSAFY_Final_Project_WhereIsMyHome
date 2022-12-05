<template>
  <div class="commentMain">
    <!-- 댓글 작성하기 -->

    <!-- 닫기 -->
    <div class="iconarea mb-4">
      <b-icon class="bicon" @click="goWrite" icon="pencil-square" font-scale="1.3"></b-icon>
      <b-icon class="bicon" @click="closeComment" icon="x" font-scale="1.5"></b-icon>
    </div>
    <div class="commentwritearea mb-4" v-if="isWriteComment">
      <form>
        <!-- <input type="text" disabled v-model="userInfo.username" /> -->
        <textarea
          class="textarea mb-2"
          v-model="wirteCommentInfo.content"
          @keyup.enter="submitComment"
          rows="1"
          placeholder="댓글을 작성해주세요..."
        />
        <div class="text-right">
          <button class="submitbtn px-3 py-1" type="button" @click="submitComment">작성하기</button>
        </div>
      </form>
    </div>
    <hr />
    <!-- 댓글목록 -->
    <div v-if="aptComment.length == 0">아직 작성된 댓글이 없습니다.</div>
    <div v-else id="scrollbar" class="commentlistarea scrollbar">
      <div class="commentlist text-left my-1" v-for="(comment, index) in aptComment" :key="index">
        <div class="cusername">
          {{ comment.userid }}
        </div>
        <div class="ccontent">
          {{ comment.content }}
        </div>
        <div class="ctime">
          {{ comment.time }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import { listComment } from "@/api/apt";
import axios from "axios";

// const addr = "http://localhost:9999/vue/map";
const memberStore = "memberStore";
const aptStore = "aptStore";

export default {
  name: "HouseComment",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(aptStore, ["aptInfo", "aptComment", "isComment", "isWriteComment"]),
  },
  data() {
    return {
      flag: false,
      wirteCommentInfo: {
        aptcode: "",
        userid: "",
        content: "",
      },
      apt: "",
    };
  },
  created() {
    //처음 열리면 댓글창 닫음
    this.SET_IS_COMMNET(false);
    //처음 열리면 댓글작성창 닫음
    this.SET_IS_WRITE_COMMENT(false);
  },
  watch: {
    isComment() {
      //댓글창이 닫힐때마다 댓글작성창도 닫고 댓글작성내역도 초기화
      if (!this.isComment) {
        this.SET_IS_WRITE_COMMENT(false);
        this.wirteCommentInfo.content = null;
      } else {
        //댓글 불러오기
        this.getCommentList();
      }
    },
    isWriteComment() {
      //댓글 작성하기 창이 닫히면 입력창도 닫아줌
      if (!this.isWriteComment) {
        //입력창 초기화
        this.wirteCommentInfo.content = null;
        //댓글 불러오기
        this.getCommentList();
        if (this.aptComment.length != 0) {
          let height = document.getElementById("scrollbar");
          height.style.height = "90%";
        }
      } else {
        //댓글작성창이 열려있고
        //댓글 목록이 있으면
        if (this.aptComment.length != 0) {
          let height = document.getElementById("scrollbar");
          height.style.height = "70%";
        }
      }
    },
  },
  methods: {
    ...mapMutations(aptStore, ["SET_APT_COMMENT", "CLEAR_APT_COMMENT", "SET_IS_COMMNET", "SET_IS_WRITE_COMMENT"]),
    showWrite() {
      //댓글 작성하기 누르면 창 뜨게/닫게
      this.SET_IS_WRITE_COMMENT(!this.isWriteComment);
      this.wirteCommentInfo.content = null;
    },
    closeComment() {
      //X 버튼 눌러서 댓글 닫기
      //지금 댓글작성창 닫음
      // this.SET_IS_WRITE_COMMENT(false);
      //상위 댓글창 닫음
      this.SET_IS_COMMNET(false);
      //댓글 입력창도 초기화
      // this.wirteCommentInfo.content = null;
    },
    goWrite() {
      //로그인한 사용자인지 확인
      if (this.userInfo == null) {
        alert("로그인을 해주세요!");
        this.$router.push({ name: "login" });
      } else {
        //댓글 작성 누르면 입력 폼 보여주기
        this.showWrite();
      }
    },
    async submitComment() {
      if (this.wirteCommentInfo.content == null) {
        alert("댓글을 작성해주세요!");
      } else {
        //DB에 보낼 나머지 정보 설정
        console.log("아파트코드");
        console.log(this.aptInfo.aptcode);
        if (this.aptInfo != null) {
          this.wirteCommentInfo.aptcode = this.aptInfo.aptcode;
          this.wirteCommentInfo.userid = this.userInfo.userid;
          console.log("설정한 댓글 정보");
          console.log(this.wirteCommentInfo);
        }

        //DB에 전송
        const response1 = await axios.post(process.env.VUE_APP_API_BASE_URL + "/comment", this.wirteCommentInfo);
        console.log(response1);
        if (response1.data == "success") {
          //댓글 작성 완료
          alert("댓글이 정상적으로 등록되었습니다!");
          this.SET_IS_WRITE_COMMENT(false);
        } else {
          alert("댓글 작성 중 오류 발생!");
          this.$router.go();
        }
      }
    },
    getCommentList() {
      listComment(this.aptInfo.aptcode, ({ data }) => {
        console.log("지금 아파트의 댓글 목록");
        console.log(data);
        this.SET_APT_COMMENT(data);
      });
    },
  },
};
</script>

<style>
.commentMain {
  width: 350px;
  height: 620px;
  border-radius: 20px;
  background-color: white;
  padding: 30px;
  box-shadow: 0 5px 10px 0 rgb(0 0 0 / 10%);
}
.iconarea {
  display: flex;
  justify-content: space-between;
}
.bicon {
  cursor: pointer;
}
.textarea {
  display: block;
  box-sizing: border-box;
  width: 100%;
  overflow-y: hidden;
  border: 0;
  outline: 1px solid gray;
  background: white;
  border-radius: 20px;
  padding: 10px;
  font-size: 0.95em;
}
.submitbtn {
  border: 1px solid transparent;
  color: #ffffff !important;
  background: #407bff;
  border-radius: 20px;
  font-size: 0.95em;
}
.commentlistarea {
  height: 90%;
  overflow: scroll;
  overflow-x: hidden;
}
.commentlist .cusername {
  color: gray;
  font-size: 0.95em;
}
.commentlist .ctime {
  color: gray;
  font-size: 0.9em;
}
.commentlist .ccontent {
  color: black;
  font-size: 1em;
}
/* Customize website's scrollbar like Mac OS
Not supports in Firefox and IE */

/* total width */
.scrollbar::-webkit-scrollbar {
  background-color: #fff;
  width: 16px;
}

/* background of the scrollbar except button or resizer */
.scrollbar::-webkit-scrollbar-track {
  background-color: #fff;
}
.scrollbar::-webkit-scrollbar-track:hover {
  background-color: #f4f4f4;
}

/* scrollbar itself */
.scrollbar::-webkit-scrollbar-thumb {
  background-color: #babac0;
  border-radius: 16px;
  border: 5px solid #fff;
}
.scrollbar::-webkit-scrollbar-thumb:hover {
  background-color: #a0a0a5;
  border: 4px solid #f4f4f4;
}

/* set button(top and bottom of the scrollbar) */
.scrollbar::-webkit-scrollbar-button {
  display: none;
}
</style>
