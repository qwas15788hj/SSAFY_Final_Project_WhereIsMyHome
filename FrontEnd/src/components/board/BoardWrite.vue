<template>
  <b-container class="bv-example-row mt-3">
    <h1 class="board-title">공지사항 작성</h1>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
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
