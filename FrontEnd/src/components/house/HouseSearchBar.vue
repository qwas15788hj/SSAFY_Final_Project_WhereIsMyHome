<template>
  <div id="root">
    <form id="searchForm" method="post" action="${root }/home/list">
      <div>
        <div>
          <!-- 시도가 변경될때마다 구군 가져옴 -->
          <select class="select" id="sido" name="sido" @change="getgugun($event)">
            <option :key="i" :value="sido.code" v-for="(sido, i) in sidoList">
              {{ sido.name }}
            </option>
          </select>
          <select class="select" ref="gugun" id="gugun" name="gugun" @change="getdong($event)">
            <option :key="i" :value="gugun.code" v-for="(gugun, i) in gugunList">
              {{ gugun.name }}
            </option>
          </select>
          <select class="select" id="dong" name="dong" @change="getdongcode($event)">
            <option :key="i" :value="dong.code" v-for="(dong, i) in dongList">
              {{ dong.name }}
            </option>
          </select>
          <button class="selectBtn" type="button" name="list-btn" @click="getAptList">조회하기</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
// import HouseAptInfo from "@/components/house/HouseAptInfo.vue";
const aptStore = "aptStore";
// BackEnd로 보낼 주소
//맵 url
const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
//주소 url
export default {
  name: "HouseSearchBar",
  components: {
    // KakaoMap,
    // HouseAptInfo,
  },
  data() {
    return {
      //모든 시도 가져오기 위함
      sido: "*00000000",
      //api에서 받아온 시도 목록
      sidoList: [
        {
          code: "",
          name: "--시도 선택--",
        },
      ],
      //api에서 받아온 해당 시도에 대한 구군 목록
      gugunList: [
        {
          code: "",
          name: "--구군 선택--",
        },
      ],
      //api에서 받아온 해당 구군에 대한 동 목록
      dongList: [
        {
          code: "",
          name: "--동 선택--",
        },
      ],
      dongcode: "",
    };
  },
  computed: {
    ...mapState(aptStore, ["dongCode", "aptInfo", "aptDeals"]),
  },
  created() {
    //동코드 초기화
    this.CLEAR_DONGCODE();
    //아파트 리스트 초기화
    this.CLEAR_APT_INFO();
    //아파트 거래목록 초기화
    this.CLEAR_APT_DEALS();

    //열리면 시도 정보 얻어오기
    console.log("시도 정보 얻어옴");
    let params = `regcode_pattern=${this.sido}&is_ignore_zero=true`;
    fetch(`${url}?${params}`)
      .then((response) => response.json())
      .then((data) => {
        // console.log(data);
        this.sidoList = this.sidoList.concat(data.regcodes);

        // // console.log("저장한 시도정보");
        // console.log(this.sidoList);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  mounted() {},
  methods: {
    ...mapMutations(aptStore, [
      "CLEAR_DONGCODE",
      "SET_DONGCODE",
      "CLEAR_APT_INFO",
      "SET_APT_INFO",
      "CLEAR_APT_DEALS",
      "SET_APT_DEALS",
    ]),
    initOption(listname, sel) {
      this.listname = [
        {
          code: "",
          name: `-- ${sel} 선택--`,
        },
      ];
      // console.log("초기화 >> ");
      // console.log(this.listname);
      return this.listname;
    },
    getgugun(event) {
      //리스트 초기화
      this.gugunList = this.initOption("gugunList", "구군");
      this.dongList = this.initOption("dongList", "동");

      //시도가 바뀔때마다 해당 구군을 가져옴
      console.log("선택한 시도 코드" + event.target.value);

      if (event.target.value) {
        //값이 있는 시도를 선택했을 때

        //초기 선택값 구군선택으로 지정
        let regcode = event.target.value.substr(0, 2) + "*00000";
        let params = `regcode_pattern=${regcode}&is_ignore_zero=true`;

        fetch(`${url}?${params}`)
          .then((response) => response.json())
          .then((data) => {
            //경기도 수원시 / 경기도 수원시 장안구에서 기준이 되는 수원시에 대한 처리
            for (let i = 0; i < data.regcodes.length; i++) {
              if (i != data.regcodes.length - 1) {
                //마지막 값이 아니라면
                if (
                  data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
                  data.regcodes[i].name.split(" ").length != data.regcodes[i + 1].name.split(" ").length
                ) {
                  //i   : 경기도 수원시
                  //i+1 : 경기도 수원시 장안구
                  // 수원시가 같고, 공백 기준으로 나눈 길이가 같지 않을 때
                  data.regcodes.splice(i, 1); //경기도 수원시 지우고
                  i--; //인덱스 감소
                }
              }
            } //for
            // console.log(data);
            //구군 리스트 저장
            this.gugunList = this.gugunList.concat(data.regcodes);
            //시도 정보 자르기
            this.gugunList.forEach(function (gugun) {
              if (gugun.code) {
                //구군선택 다음부터 진행
                if (gugun.name.split(" ").length == 2) {
                  //경기도 평택시
                  gugun.name = gugun.name.split(" ")[1];
                } else {
                  //경기도 수원시 장안구
                  gugun.name = gugun.name.split(" ")[1] + " " + gugun.name.split(" ")[2];
                }
              }
            });
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        //'시도 선택'을 선택했을 때 구군 정보 초기화. 동 정보 초기화
        this.gugunList = this.initOption("gugunList", "구군");
        this.dongList = this.initOption("dongList", "동");
      }
    },
    getdong(event) {
      //초기화
      this.dongList = this.initOption("dongList", "동");
      // console.log(this.dongList);
      //구군이 바뀔때마다 해당 동을 가져옴
      console.log("선택한 구군 코드" + event.target.value);
      if (event.target.value) {
        //값이 있는 구군을 선택했을 때
        let regcode = event.target.value.substr(0, 5) + "*";
        let params = `regcode_pattern=${regcode}&is_ignore_zero=true`;

        fetch(`${url}?${params}`)
          .then((response) => response.json())
          .then((data) => {
            this.dongList = this.dongList.concat(data.regcodes);
            let idx = 2; //기본 : ㅇㅇ도 ㅇㅇ시 ㅇㅇ동
            this.dongList.forEach(function (dong) {
              if (dong.code) {
                //동선택 이후부터 진행
                if (dong.name.split(" ").length != 3) {
                  //ㅇㅇ도 ㅇㅇ시 ㅇㅇ읍 ㅇㅇ리 || ㅇㅇ도 ㅇㅇ시 ㅇㅇ구 ㅇㅇ동
                  idx = 3;
                }
                dong.name = dong.name.split(" ")[idx];
              }
            });
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        //'구군 선택'을 선택했을 때 동 정보 초기화
        this.dongList = this.initOption("dongList", "동");
      }
    },
    getdongcode(event) {
      //선택한 동코드 저장
      console.log("선택한 동 코드" + event.target.value);
      this.dongcode = event.target.value;
    },
    getAptList() {
      //조회하기 누름
      //아파트 리스트 초기화
      this.CLEAR_APT_INFO();
      //아파트 거래목록 초기화
      this.CLEAR_APT_DEALS();
      //동 갱신
      this.SET_DONGCODE(this.dongcode);
    },
  },
};
</script>

<style>
#root {
  width: 1490px;
  border-radius: 30px;
  background-color: white;
  padding: 5px;
}
select::-ms-expand {
  display: none;
}
.select {
  -o-appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;

  width: 150px;
  height: 35px;
  background: url("@/assets/down-arrow.png") calc(100% - 5px) center no-repeat;
  background-size: 20px;
  padding: 5px 30px 5px 20px;
  border-radius: 30px;
  outline: 0 none;

  margin: 5px 10px;
}
.select option {
  background: rgb(255, 255, 255);
  color: rgb(0, 0, 0);
  padding: 3px 0;
}
.selectBtn {
  width: 110px;
  height: 35px;
  padding: 5px 20px;
  border-radius: 30px;
  border: 0;
  outline: 0 none;
  margin: 5px 10px;
  vertical-align: middle;
  background-color: #407bff;
  color: white;
}
.select,
.selectBtn {
  font-size: 0.9em;
}

.select option {
  font-size: 1em;
}
</style>
