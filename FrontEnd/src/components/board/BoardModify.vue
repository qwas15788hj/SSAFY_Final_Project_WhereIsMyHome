<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert show><h3>글수정</h3></b-alert>
      </b-col>
    </b-row> -->
    <h1 class="board-title">공지사항 수정</h1>
    <board-input-item type="modify" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardModify",
  components: {
    BoardInputItem,
  },
};
</script>

<style>
h1.board-title:after {
  content: "";
  display: block;
  width: 80px;
  border-bottom: 5px solid #1667c9;
  margin: 20px auto;
}
</style>
