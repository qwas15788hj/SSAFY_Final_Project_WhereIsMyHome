<template>
  <div id="map"></div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import axios from "axios";
const aptStore = "aptStore";
// BackEnd로 보낼 주소
// const addr = "http://localhost:9999/vue/map";
//주소 url
const url1 = "https://dapi.kakao.com/v2/local/geo/coord2address.json?";
const url2 = "&input_coord=WGS84";
export default {
  name: "KakaoMap",
  data() {
    return {
      //데이터베이스에서 받아온 경도위도아파트코드 배열
      lnglatList: [],
      //현재 맵에 표시된 마커들
      markers: [],
    };
  },
  computed: {
    ...mapState(aptStore, ["dongCode", "aptInfo", "aptDeals"]),
  },
  watch: {
    dongCode() {
      console.log("watch >> 업데이트 된 동코드");
      console.log(this.dongCode);
      this.showMarkers();
    },
  },
  updated() {
    console.log("updated >> 업데이트 된 동코드");
    console.log(this.dongCode);
    this.showMarkers();
  },
  mounted() {
    //맵 띄우기
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapMutations(aptStore, [
      "CLEAR_DONGCODE",
      "SET_DONGCODE",
      "CLEAR_APT_INFO",
      "SET_APT_INFO",
      "CLEAR_APT_DEALS",
      "SET_APT_DEALS",
    ]),
    initMap() {
      //맵 생성과 카테고리별 장소 검색
      //맵 표시할 div
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      //지도 생성
      this.map = new kakao.maps.Map(container, options);
    },
    async showMarkers() {
      //검색한 동에 대한 아파트 정보를 맵에 띄움
      console.log("검색할 동코드 >> " + this.dongCode);
      //DB로 동코드에 해당하는 아파트 목록 가져옴
      const response1 = await axios.get(process.env.VUE_APP_API_BASE_URL + `/info/${this.dongCode}`);
      this.lnglatList = response1.data;
      console.log("받아온 현재 동의 아파트 목록");
      console.log(response1);
      // console.log(this.lnglatList);

      if (response1.data.length == 0) {
        alert("2019년 이후의 검색 정보가 없습니다.");
        //페이지 새로고침
        this.$router.go();
      }

      //맵 생성
      //마커초기화
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = this.lnglatList.map((position) => new kakao.maps.LatLng(position.lat, position.lng));

      //마커
      // let idx = 0;
      if (positions.length > 0) {
        //마커 생성
        this.markers = positions.map((position, index) => {
          const marker = new kakao.maps.Marker({
            map: this.map,
            title: this.lnglatList[index].apartmentname,
            position,
          });

          //클릭 이벤트
          kakao.maps.event.addListener(marker, "click", async () => {
            //마커 title을 출력합니다.
            console.log(marker.getTitle());

            //도로명주소를 받아옵니다.
            const addressUrl = url1 + `x=${this.lnglatList[index].lng}&y=${this.lnglatList[index].lat}` + url2;
            const response2 = await axios.get(addressUrl, {
              headers: {
                Authorization: `KakaoAK ${process.env.VUE_APP_KAKAOMAP_REST_KEY}`,
              },
            });
            //도로명 주소를 추가합니다
            this.lnglatList[index].address = response2.data.documents[0].road_address.address_name;
            console.log("도로명주소가 추가된 lnglat");
            console.log(this.lnglatList[index]);

            //state에 지역의 아파트 정보들 저장
            this.SET_APT_INFO(this.lnglatList[index]);

            console.log("vuex 아파트인포");
            console.log(this.aptInfo);

            //선택한 아파트의 거래목록을 불러옵니다.
            const response3 = await axios.get(process.env.VUE_APP_API_BASE_URL + `/deals/${this.aptInfo.aptcode}`);
            console.log(this.aptInfo.apartmentname + "의 거래 목록");
            console.log(response3);

            //날짜 설정
            response3.data.forEach((deal) => {
              let y = deal.dealYear.substring(2, 5);
              let m = deal.dealMonth;
              if (m.length == 1) {
                m = "0" + m;
              }
              let d = deal.dealDay;
              if (d.length == 1) {
                d = "0" + d;
              }
              deal.fullDay = `${y}.${m}.${d}`;
            });

            //state에 아파트의 거래 목록들 저장
            this.SET_APT_DEALS(response3.data);
            console.log("state에 저장된 아파트 거래 목록");
            console.log(this.aptDeals);
          });

          return marker;
        });

        //중심 위치 변경
        const bounds = positions.reduce((bounds, latlng) => bounds.extend(latlng), new kakao.maps.LatLngBounds());

        this.map.setBounds(bounds);
      }
    },
  },
};
</script>

<style scope>
div {
  margin: 0 auto;
}
#map {
  width: 1500px;
  height: 700px;
  border-radius: 30px;
}
</style>
