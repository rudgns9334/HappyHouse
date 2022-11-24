<template>
  <div>
    <mobile-menu></mobile-menu>
    <div class="page-header page-header-more-small page-inner">
      <parallax class="page-header-image" style="background-image: url('img/hero_bg_3.jpg')"> </parallax>
      <div class="container">
        <div class="row justify-content-center align-items-center">
          <div class="col-lg-9 text-center" style="margin-bottom: 250px">
            <h1 class="heading">Review</h1>
          </div>
        </div>
      </div>
    </div>
    <div class="limiter">
      <div class="container-login100">
        <div class="input-form col-md-8 mx-auto">
          <form class="login100-form validate-form">
            <span class="login100-form-title p-b-48">
              <i class="zmdi zmdi-font"></i>
            </span>

            <form novalidate>
              <div class="mb-6">
                <input type="text" class="form-control" id="userEmail" placeholder="Email" />
              </div>
              <br />
              <div id="stars">
                <fieldset>
                  <input type="radio" name="rating" value="5" id="rate5" /><label id="star" for="rate5">⭐</label>
                  <input type="radio" name="rating" value="4" id="rate4" /><label id="star" for="rate4">⭐</label>
                  <input type="radio" name="rating" value="3" id="rate3" /><label id="star" for="rate3">⭐</label>
                  <input type="radio" name="rating" value="2" id="rate2" /><label id="star" for="rate2">⭐</label>
                  <input type="radio" name="rating" value="1" id="rate1" /><label id="star" for="rate1">⭐</label>
                </fieldset>
              </div>
              <br />
              <div class="mb-6">
                <label for="review">Review</label>
                <textarea class="form-control" id="review" rows="5"></textarea>
              </div>
              <br />
            </form>
            <div class="container-login100-form-btn">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <br />
                <a class="login100-form-btn register-btn" id="btnRegist" style="width: 300px">
                  <span style="color: white">Register</span>
                </a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {Parallax} from "@/components";
import MobileMenu from "./components/MobileMenu.vue";

export default {
  name: "event",
  components: {
    Parallax,
    MobileMenu,
  },
};
</script>

<style>
.heading {
  color: #fff;
  font-size: clamp(1.5rem, 2.5vw, 2.5rem);
  margin-bottom: 20px;
  margin-top: 50px;
  font-weight: 600;
}
#stars fieldset {
  display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
  direction: rtl; /* 이모지 순서 반전 */
  border: 0; /* 필드셋 테두리 제거 */
}
#stars fieldset legend {
  text-align: left;
}
#stars input[type="radio"] {
  display: none; /* 라디오박스 감춤 */
}
#stars label {
  font-size: 3em; /* 이모지 크기 */
  color: transparent; /* 기존 이모지 컬러 제거 */
  text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
}
#stars label:hover {
  text-shadow: 0 0 0 #ffc107; /* 마우스 호버 */
}
#stars label:hover ~ label {
  text-shadow: 0 0 0 #ffc107; /* 마우스 호버 뒤에오는 이모지들 */
}
#stars input[type="radio"]:checked ~ label {
  text-shadow: 0 0 0 #ffc107; /* 마우스 클릭 체크 */
}
</style>
