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
        <li class="nav-item" v-show="!isLogin">
          <router-link class="nav-link" to="/login">
            <p>Login</p>
          </router-link>
        </li>
        <li class="nav-item" v-show="isLogin">
          <a class="nav-link" @click="goLogout">
            <p>Logout</p>
          </a>
        </li>
        <li class="nav-item" v-show="!isLogin">
          <router-link class="nav-link" to="/register">
            <p>Register</p>
          </router-link>
        </li>
        <li class="nav-item" v-show="isLogin">
          <router-link class="nav-link" to="/profile">
            <p>MyPage</p>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/notice">
            <p>Notice</p>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/event">
            <p>Event</p>
          </router-link>
        </li>
        <li class="nav-item" v-show="isLogin">
          <span
            @click="callAlerm"
            class="material-symbols-outlined"
            :class="[{newIcon: isNew}, {noNewIcon: !isNew}]"
            style="cursor: pointer; padding-top: 7px"
          >
            circle_notifications
          </span>
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
        <apt-list @detail="showAptDetail"></apt-list>
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
import {mapState, mapMutations, mapActions} from "vuex";
const aptStore = "aptStore";
const userStore = "userStore";
const bookMarkStore = "bookMarkStore";
const alermStore = "alermStore";

export default {
  name: "App",
  data() {
    return {
      lat: 37.5666805,
      lng: 126.9784147,
      level: 7,
      kakaomap: "",
      aptName: "",
      aptAddress: "",
      aptPrice: "",
      aptArea: "",
      aptFloor: "",
      latAvg: 0.0,
      lngAvg: 0.0,
      iwContents: [],
      index: 0,
    };
  },
  components: {
    Navbar,
    AptList,
    AptSearchBar,
  },
  mounted() {
    this.SET_ISCENTER();
  },
  computed: {
    ...mapState(aptStore, ["sidos", "guguns", "houses", "clickHouse", "isCenter", "lats", "lngs"]),
    ...mapState(userStore, ["isLogin", "user"]),
    ...mapState(alermStore, ["isAlerm", "isNew"]),
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
        this.kakaomap = map;

        // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
        var placeOverlay = new kakao.maps.CustomOverlay({zIndex: 1}),
          contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
          markers = [], // 마커를 담을 배열입니다
          currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다
        var selectedCategory = [];
        // 카테고리별 마커
        var BKMarkers = [],
          MTMarkers = [],
          PMMarkers = [],
          OLMarkers = [],
          CEMarkers = [],
          CSMarkers = [];

        // 장소 검색 객체를 생성합니다
        var ps = new kakao.maps.services.Places(map);

        // 지도에 idle 이벤트를 등록합니다
        kakao.maps.event.addListener(map, "idle", searchPlaces);

        // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
        contentNode.className = "placeinfo_wrap";

        // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
        // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
        addEventHandle(contentNode, "mousedown", kakao.maps.event.preventMap);
        addEventHandle(contentNode, "touchstart", kakao.maps.event.preventMap);

        // 커스텀 오버레이 컨텐츠를 설정합니다
        placeOverlay.setContent(contentNode);

        // 각 카테고리에 클릭 이벤트를 등록합니다
        addCategoryClickEvent();

        // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
        function addEventHandle(target, type, callback) {
          if (target.addEventListener) {
            target.addEventListener(type, callback);
          } else {
            target.attachEvent("on" + type, callback);
          }
        }

        // 카테고리 검색을 요청하는 함수입니다
        function searchPlaces() {
          if (!currCategory) {
            return;
          }

          // 커스텀 오버레이를 숨깁니다
          placeOverlay.setMap(null);

          // 지도에 표시되고 있는 마커를 제거합니다
          removeMarker();

          ps.categorySearch(currCategory, placesSearchCB, {useMapBounds: true});
        }

        // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
        function placesSearchCB(data, status, pagination) {
          if (status === kakao.maps.services.Status.OK) {
            // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
            displayPlaces(data);
          } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
          } else if (status === kakao.maps.services.Status.ERROR) {
            // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
          }
        }

        // 지도에 마커를 표출하는 함수입니다
        function displayPlaces(places) {
          // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
          // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
          var order = document.getElementById(currCategory).getAttribute("data-order");

          for (var i = 0; i < places.length; i++) {
            // 마커를 생성하고 지도에 표시합니다
            var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

            // 마커와 검색결과 항목을 클릭 했을 때
            // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
            (function (marker, place) {
              kakao.maps.event.addListener(marker, "click", function () {
                displayPlaceInfo(place);
              });
            })(marker, places[i]);
          }
        }

        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        function addMarker(position, order) {
          var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
            imgOptions = {
              spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
              spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
              offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
              position: position, // 마커의 위치
              image: markerImage,
            });

          marker.setMap(map); // 지도 위에 마커를 표출합니다
          if (currCategory == "BK9") {
            BKMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
          } else if (currCategory == "MT1") {
            MTMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
          } else if (currCategory == "PM9") {
            PMMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
          } else if (currCategory == "OL7") {
            OLMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
          } else if (currCategory == "CE7") {
            CEMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
          } else if (currCategory == "CS2") {
            CSMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
          }

          return marker;
        }

        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        function removeMarker(id) {
          if (id == "BK9") {
            for (var i = 0; i < BKMarkers.length; i++) {
              BKMarkers[i].setMap(null);
            }
            BKMarkers = [];
          } else if (id == "MT1") {
            for (var i = 0; i < MTMarkers.length; i++) {
              MTMarkers[i].setMap(null);
            }
            MTMarkers = [];
          } else if (id == "PM9") {
            for (var i = 0; i < PMMarkers.length; i++) {
              PMMarkers[i].setMap(null);
            }
            PMMarkers = [];
          } else if (id == "OL7") {
            for (var i = 0; i < OLMarkers.length; i++) {
              OLMarkers[i].setMap(null);
            }
            OLMarkers = [];
          } else if (id == "CE7") {
            for (var i = 0; i < CEMarkers.length; i++) {
              CEMarkers[i].setMap(null);
            }
            CEMarkers = [];
          } else if (id == "CS2") {
            for (var i = 0; i < CSMarkers.length; i++) {
              CSMarkers[i].setMap(null);
            }
            CSMarkers = [];
          }
        }
        // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
        function displayPlaceInfo(place) {
          var content =
            '<div class="placeinfo">' +
            '   <a class="title" href="' +
            place.place_url +
            '" target="_blank" title="' +
            place.place_name +
            '">' +
            place.place_name +
            "</a>";

          if (place.road_address_name) {
            content +=
              '    <span title="' +
              place.road_address_name +
              '">' +
              place.road_address_name +
              "</span>" +
              '  <span class="jibun" title="' +
              place.address_name +
              '">(지번 : ' +
              place.address_name +
              ")</span>";
          } else {
            content += '    <span title="' + place.address_name + '">' + place.address_name + "</span>";
          }

          content += '    <span class="tel">' + place.phone + "</span>" + "</div>" + '<div class="after"></div>';

          contentNode.innerHTML = content;
          placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
          placeOverlay.setMap(map);
        }

        // 각 카테고리에 클릭 이벤트를 등록합니다
        function addCategoryClickEvent() {
          var category = document.getElementById("category"),
            children = category.children;

          for (var i = 0; i < children.length; i++) {
            children[i].onclick = onClickCategory;
          }
        }

        // 카테고리를 클릭했을 때 호출되는 함수입니다
        function onClickCategory() {
          var id = this.id,
            className = this.className;

          placeOverlay.setMap(null);

          if (className === "on") {
            currCategory = "";
            this.className = "";
            selectedCategory.pop(id);
            removeMarker(id);
          } else {
            currCategory = id;
            this.className = "on";
            selectedCategory.push(id);
          }
        }
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

      this.latAvg = latAvg;
      this.lngAvg = lngAvg;

      var mapOption = {
        center: new kakao.maps.LatLng(latAvg, lngAvg),
        level: 5,
      };

      var map = new kakao.maps.Map(container, mapOption);
      this.kakaomap = map;

      var positions = [];
      var iwContent = [];
      var lats = [];
      var lngs = [];
      house.forEach(h => {
        positions.push({
          latlng: new kakao.maps.LatLng(h.lat, h.lng),
          clickable: true,
        });
        lats.push(h.lat);
        lngs.push(h.lng);

        this.aptName = h.aptName;
        this.aptAddress = h.houseAddress;
        this.aptPrice = h.dealAmount;
        this.aptFloor = h.floor;
        this.aptArea = h.area;
        /*
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        */
        this.aptArea = h.area;
        iwContent = `<div class="modal-body" style="width: 400px; height: 280px">
                      <h5>${this.aptName}아파트 ${this.aptFloor}층</h5>
                      <p>${this.aptAddress}</p>
                      <hr>
                      <p>거래 금액 : ${this.aptPrice}</p>
                      <p>전용 면적 : ${this.aptArea}m<sup>2</sup></p>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-primary test" data-no="${h.no}">WISH</button>
                      </div>
                    </div>`;
        this.iwContents.push(iwContent);
      });

      this.$store.state.aptStore.lats = lats;
      this.$store.state.aptStore.lngs = lngs;
      var markers = [];
      for (let i = 0; i < positions.length; i++) {
        var marker = new kakao.maps.Marker({
          position: positions[i].latlng,
          index: i,
        });
        markers.push(marker);
        marker.setMap(map);
      }

      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      var placeOverlay = new kakao.maps.CustomOverlay({zIndex: 1}),
        contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers = [], // 마커를 담을 배열입니다
        currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다
      var selectedCategory = [];
      // 카테고리별 마커
      var BKMarkers = [],
        MTMarkers = [],
        PMMarkers = [],
        OLMarkers = [],
        CEMarkers = [],
        CSMarkers = [];

      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(map);

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(map, "idle", searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      addEventHandle(contentNode, "mousedown", kakao.maps.event.preventMap);
      addEventHandle(contentNode, "touchstart", kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      placeOverlay.setContent(contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      addCategoryClickEvent();

      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
          target.addEventListener(type, callback);
        } else {
          target.attachEvent("on" + type, callback);
        }
      }

      // 카테고리 검색을 요청하는 함수입니다
      function searchPlaces() {
        if (!currCategory) {
          return;
        }

        // 커스텀 오버레이를 숨깁니다
        placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        ps.categorySearch(currCategory, placesSearchCB, {useMapBounds: true});
      }

      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
      function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
          displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
        } else if (status === kakao.maps.services.Status.ERROR) {
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
      }

      // 지도에 마커를 표출하는 함수입니다
      function displayPlaces(places) {
        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document.getElementById(currCategory).getAttribute("data-order");

        for (var i = 0; i < places.length; i++) {
          // 마커를 생성하고 지도에 표시합니다
          var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          (function (marker, place) {
            kakao.maps.event.addListener(marker, "click", function () {
              displayPlaceInfo(place);
            });
          })(marker, places[i]);
        }
      }

      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
      function addMarker(position, order) {
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
          imgOptions = {
            spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
          marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage,
          });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        if (currCategory == "BK9") {
          BKMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
        } else if (currCategory == "MT1") {
          MTMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
        } else if (currCategory == "PM9") {
          PMMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
        } else if (currCategory == "OL7") {
          OLMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
        } else if (currCategory == "CE7") {
          CEMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
        } else if (currCategory == "CS2") {
          CSMarkers.push(marker); // 배열에 생성된 마커를 추가합니다
        }

        return marker;
      }

      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
      function removeMarker(id) {
        if (id == "BK9") {
          for (var i = 0; i < BKMarkers.length; i++) {
            BKMarkers[i].setMap(null);
          }
          BKMarkers = [];
        } else if (id == "MT1") {
          for (var i = 0; i < MTMarkers.length; i++) {
            MTMarkers[i].setMap(null);
          }
          MTMarkers = [];
        } else if (id == "PM9") {
          for (var i = 0; i < PMMarkers.length; i++) {
            PMMarkers[i].setMap(null);
          }
          PMMarkers = [];
        } else if (id == "OL7") {
          for (var i = 0; i < OLMarkers.length; i++) {
            OLMarkers[i].setMap(null);
          }
          OLMarkers = [];
        } else if (id == "CE7") {
          for (var i = 0; i < CEMarkers.length; i++) {
            CEMarkers[i].setMap(null);
          }
          CEMarkers = [];
        } else if (id == "CS2") {
          for (var i = 0; i < CSMarkers.length; i++) {
            CSMarkers[i].setMap(null);
          }
          CSMarkers = [];
        }
      }
      // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
      function displayPlaceInfo(place) {
        var content =
          '<div class="placeinfo">' +
          '   <a class="title" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          "</a>";

        if (place.road_address_name) {
          content +=
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            "</span>" +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ")</span>";
        } else {
          content += '    <span title="' + place.address_name + '">' + place.address_name + "</span>";
        }

        content += '    <span class="tel">' + place.phone + "</span>" + "</div>" + '<div class="after"></div>';

        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(map);
      }

      // 각 카테고리에 클릭 이벤트를 등록합니다
      function addCategoryClickEvent() {
        var category = document.getElementById("category"),
          children = category.children;

        for (var i = 0; i < children.length; i++) {
          children[i].onclick = onClickCategory;
        }
      }

      // 카테고리를 클릭했을 때 호출되는 함수입니다
      function onClickCategory() {
        var id = this.id,
          className = this.className;

        placeOverlay.setMap(null);

        if (className === "on") {
          currCategory = "";
          this.className = "";
          selectedCategory.pop(id);
          removeMarker(id);
        } else {
          currCategory = id;
          this.className = "on";
          selectedCategory.push(id);
        }
      }
      return;
    },
  },

  methods: {
    ...mapMutations(aptStore, ["SET_CONTAINER", "SET_ISCENTER"]),
    ...mapActions(userStore, ["logout"]),
    ...mapActions(bookMarkStore, ["bookMarkInsert"]),
    ...mapActions(alermStore, ["alermList", "alermReadAll"]),
    ...mapMutations(alermStore, ["SET_IS_ALERM", "INIT"]),

    // 지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
    setMapType(maptype) {
      let kakao = window.kakao;
      idx;
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
    showAptDetail(idx) {
      this.index = idx;
      let kakao = window.kakao;
      var lat = this.$store.state.aptStore.lats[idx];
      var lng = this.$store.state.aptStore.lngs[idx];
      var moveLatLon = new kakao.maps.LatLng(lat, lng);
      this.kakaomap.setCenter(moveLatLon);

      var positions = new kakao.maps.LatLng(lat, lng);

      var marker = new kakao.maps.Marker({
        position: positions,
        clickable: true,
      });
      var iwContent = this.iwContents[idx],
        iwRemoveable = true;

      var infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable,
      });
      var $this = this;
      infowindow.open(this.kakaomap, marker);
      document.querySelectorAll(".test").forEach(t => {
        // console.log(t);
        t.addEventListener("click", function (e) {
          console.log(e.target.dataset.no);
          $this.AddWishList(e.target.dataset.no);
        });
      });
    },
    zoomIn() {
      this.kakaomap.setLevel(this.kakaomap.getLevel() - 1);
    },
    zoomOut() {
      this.kakaomap.setLevel(this.kakaomap.getLevel() + 1);
    },
    AddWishList(idx) {
      var seq = this.user.userSeq;
      var index = idx;
      index *= 1;

      const body = {
        userSeq: seq,
        dealNo: index,
      };
      this.bookMarkInsert(body);
    },
    callAlerm() {
      this.SET_IS_ALERM(!this.isAlerm);
      if (!this.isAlerm) {
        this.alermReadAll();
      }
    },
    goLogout() {
      this.INIT();
      this.logout();
    },
    checkAlarm() {
      if (this.isLogin) {
        this.alermList();
      }
      setInterval(() => {
        if (this.isLogin) {
          this.alermList();
        }
      }, 10000);
    },
  },
  created() {
    this.checkAlarm();
  },
};
</script>

<style>
.material-symbols-outlined {
  font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 48;
}

.noNewIcon {
  color: #fff;
}
.newIcon {
  color: red;
}
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
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #005555;
  background: #005555 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px
    center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
