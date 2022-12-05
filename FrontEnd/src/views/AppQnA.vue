<template>
  <b-container class="bv-example-row mt-3 text-center">
    <div class="marginTop" style="visibility: hidden">marginarea</div>
    <!-- <h3 class="underline-hotpink">
      <b-icon icon="journals"></b-icon> Board Service
    </h3> -->
    <router-view class="my-5"></router-view>
  </b-container>
</template>
<script>
export default {
  name: "AppQnA",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
}
.marginTop {
  margin-top: 80px;
}
</style>
