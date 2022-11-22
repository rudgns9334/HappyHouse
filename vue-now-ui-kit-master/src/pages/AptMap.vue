<template>
  <div id="app">
    <navbar
      position="fixed"
      type="primary"
      menu-classes="ml-auto"
      style="
        margin-right: 350px;
        margin-left: 350px;
        margin-top: 10px;
        border-radius: 20px;
        margin-left: 300px;
        margin-right: 300px;
      "
    >
      <template>
        <router-link v-popover:popover1 class="navbar-brand" to="/">
          <img src="img/main-logo.png" alt="" style="width: 100px" />
        </router-link>
      </template>
      <template slot="navbar-menu">
        <li class="nav-item">
          <a class="nav-link" href="https://www.creative-tim.com/product/vue-now-ui-kit" target="_blank">
            <p>Login</p>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="https://www.creative-tim.com/product/vue-now-ui-kit" target="_blank">
            <p>Logout</p>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="https://www.creative-tim.com/product/vue-now-ui-kit" target="_blank">
            <p>Register</p>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="https://www.creative-tim.com/product/vue-now-ui-kit" target="_blank">
            <p>MyPage</p>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="https://www.creative-tim.com/product/vue-now-ui-kit" target="_blank">
            <p>Notice</p>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="https://www.creative-tim.com/product/vue-now-ui-kit" target="_blank">
            <p>Event</p>
          </a>
        </li>
      </template>
    </navbar>
    <div class="left-side-bar">
      <div class="searchDiv">
        <div class="input-select">
          <apt-search-bar></apt-search-bar>
        </div>
      </div>
      <ul id="category">
        <li id="BK9" data-order="0">
          <span class="category_bg bank"></span>
          <strong>은행</strong>
        </li>
        <li id="MT1" data-order="1">
          <span class="category_bg mart"></span>
          <strong>마트</strong>
        </li>
        <li id="PM9" data-order="2">
          <span class="category_bg pharmacy"></span>
          <strong>약국</strong>
        </li>
        <li id="OL7" data-order="3">
          <span class="category_bg oil"></span>
          <strong>주유소</strong>
        </li>
        <li id="CE7" data-order="4">
          <span class="category_bg cafe"></span>
          <strong>카페</strong>
        </li>
        <li id="CS2" data-order="5">
          <span class="category_bg store"></span>
          <strong>편의점</strong>
        </li>
      </ul>
      <div class="aptListDiv">
        <apt-list></apt-list>
      </div>
    </div>
    <!-- 지도타입 컨트롤 div 입니다 -->
    <div class="custom_typecontrol radius_border">
      <span id="btnMap" class="selected_btn" @click="setMapType('roadmap')">지도</span>
      <span id="btnSkyview" class="notselected_btn" @click="setMapType('skyview')">스카이뷰</span>
    </div>
    <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
    <div class="custom_zoomcontrol radius_border">
      <span @click="zoomIn()"><strong>+</strong></span>
      <span @click="zoomOut()"><strong>-</strong></span>
    </div>
    <div class="kakaomap" ref="map"></div>
  </div>
</template>

<script>
import AptList from "./components/AptList.vue";
import AptSearchBar from "./components/AptSearchBar";
import {Navbar} from "@/components";
import {mapState, mapMutations} from "vuex";
const aptStore = "aptStore";

export default {
  name: "App",
  data() {
    return {
      lat: 37.5666805,
      lng: 126.9784147,
      level: 7,
      kakaomap: "",
    };
  },
  props: {
    idx: Number,
  },
  components: {
    Navbar,
    AptList,
    AptSearchBar,
  },
  computed: {
    ...mapState(aptStore, ["sidos", "guguns", "houses", "clickHouse", "isCenter"]),
  },
  watch: {
    houses(house) {
      let kakao = window.kakao;
      var container = this.$refs.map;
      if (house.length == 0 && !this.isCenter) {
        this.$store.state.aptStore.isCenter = true;
        var mapOption = {
          center: new kakao.maps.LatLng(this.lat, this.lng),
          level: this.level,
        };

        var map = new kakao.maps.Map(container, mapOption);

        return;
      }

      var latSum = 0.0;
      var lngSum = 0.0;

      house.forEach(h => {
        latSum += parseFloat(h.lat);
        lngSum += parseFloat(h.lng);
      });
      var latAvg = latSum / house.length;
      var lngAvg = lngSum / house.length;

      var mapOption = {
        center: new kakao.maps.LatLng(latAvg, lngAvg),
        level: 5,
      };

      var map = new kakao.maps.Map(container, mapOption);

      var positions = [];
      house.forEach(h => {
        positions.push({
          content: `<div class="modal-body" style="height: 300px; width: 600px;">
                    <h5>{{h.aptName}}</h5>
                    <hr>
                    <h5>아파트 상세</h5>
                    <ul>
                        <li>House Address: {{h.houseAddress}}</li>
                        <li>Deal Amount: {{h.dealAmount}}</li>
                        <li>Area: {{h.area}}</li>
                      </ul>
                    <hr>
                    <div class="modal-footer" style=" position: absolute; bottom: 0; right: 0; border: none;">
                      <button type="button" class="btn btn-primary">WISH</button>
                    </div>
                  </div>`,
          latlng: new kakao.maps.LatLng(h.lat, h.lng),
          clickable: true,
        });
      });

      }
    },
  },
  methods: {
    ...mapMutations(aptStore, ["SET_CONTAINER"]),
    // 지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
    setMapType(maptype) {
      let kakao = window.kakao;

      var roadmapControl = document.getElementById("btnMap");
      var skyviewControl = document.getElementById("btnSkyview");
      if (maptype === "roadmap") {
        this.kakaomap.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
        roadmapControl.className = "selected_btn";
        console.log(roadmapControl.className);
        skyviewControl.className = "notselected_btn";
      } else {
        this.kakaomap.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
        skyviewControl.className = "selected_btn";
        roadmapControl.className = "notselected_btn";
      }
    },
    // 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
    zoomIn() {
      this.kakaomap.setLevel(this.kakaomap.getLevel() - 1);
    },

    // 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
    zoomOut() {
      this.kakaomap.setLevel(this.kakaomap.getLevel() + 1);
    },
  },
};
</script>

<style>
.input-select {
  margin-right: 5px;
}
.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}
.custom_typecontrol {
  position: absolute;
  top: 10px;
  right: 10px;
  overflow: hidden;
  width: 130px;
  height: 30px;
  margin: 0;
  padding-top: 5px;
  z-index: 20;
  font-size: 12px;
  font-family: "Malgun Gothic", "맑은 고딕", sans-serif;
}

#btnMap {
  padding-left: 18px;
  padding-right: 18px;
}
#btnSkyview {
  padding-left: 10px;
  padding-right: 10px;
}
.custom_typecontrol .selected_btn {
  color: #fff;
  background: #005555;
  background: linear-gradient(#005555, #028383);
  padding-top: 15px;
  padding-bottom: 15px;
  cursor: pointer;
}
.custom_typecontrol .notselected_btn {
  background: #fff;
  background: linear-gradient(#fff, #e6e6e6);
  padding-top: 15px;
  padding-bottom: 15px;
  cursor: pointer;
}
.notselected_btn:hover {
  background: #f5f5f5;
  background: linear-gradient(#f5f5f5, #e3e3e3);
}
.custom_zoomcontrol {
  position: absolute;
  top: 50px;
  right: 10px;
  width: 36px;
  height: 80px;
  overflow: hidden;
  z-index: 20;
  background-color: #f5f5f5;
  font-size: 25px;
}
.custom_zoomcontrol span {
  display: block;
  width: 36px;
  height: 40px;
  text-align: center;
  cursor: pointer;
}
.custom_zoomcontrol span:first-child {
  border-bottom: 1px solid #bfbfbf;
}
#aptName {
  font-size: 20px;
}
.menu li {
  list-style: none;
}
.aptListDiv {
  margin-top: 70px;
  margin-left: 10px;
  margin-right: 10px;
  height: 68%;
  overflow: auto;
}
.searchDiv {
  margin-left: 30px;
  margin-top: 10px;
}
.searchBar {
  width: 210px;
  height: 35px;
  margin-left: 10px;
  margin-right: 20px;
  border-top: 1px solid #005555;
  border-left: 1px solid #005555;
  border-bottom: 3px solid #005555;
  border-right: 3px solid #005555;
  z-index: 10;
}
.kakaomap {
  height: 100vh;
  overflow: hidden;
  position: relative;
}
.left-side-bar {
  position: fixed;
  top: 0;
  width: 400px;
  height: 80%;
  background-color: rgba(255, 255, 255, 0.842);
  transition: left 0.3s;
  z-index: 2;
  margin-left: 20px;
  margin-top: 120px;
  border-radius: 50px;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  right: 50px;
  top: 120px;
  border: none;
  background: rgba(255, 255, 255, 0);
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border: none;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  margin-top: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url("/img/map-category.png") no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
</style>
