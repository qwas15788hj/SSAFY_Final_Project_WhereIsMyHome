<template>
  <div id="mainContainer">
    <div class="infotext">
      <div>아파트 실거래가 궁금해?</div>
      <div>
        지금 바로
        <span class="ml-3"> 찾아<span>방</span> !</span>
      </div>
    </div>
    <img src="@/assets/backimg.jpg" />
  </div>
</template>

<script>
export default {
  name: "AppMain",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
#mainContainer .infotext div {
  margin: 15px 0;
  letter-spacing: -1.5px;
}
#mainContainer .infotext div span,
#mainContainer .infotext div span span {
  font-size: 80px;
  /* letter-spacing: -1.5px; */
}
#mainContainer .infotext div span span {
  color: #407bff;
}
.infotext {
  color: white;
  font-size: 50px;
  font-weight: bold;
  position: absolute;
  top: 200px;
  left: 400px;
  text-align: left;
  text-shadow: 4px 4px 0px rgba(0, 0, 0, 0.5);
}
img {
  /* position: fixed;
  top: 0;
  left: 0; */
  height: 1000px;
  width: 100vw;
  /* height: 1080px; */
  /* margin: 0; */
  object-fit: cover;
  /* filter: brightness(50%); */
}
</style>
