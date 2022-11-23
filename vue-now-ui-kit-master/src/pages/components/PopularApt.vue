<template>
  <div class="section">
    <div class="container">
      <div class="row mb-5 align-items-center">
        <div class="col-lg-6">
          <h2 class="font-weight-bold text-primary heading">Pupular Property</h2>
        </div>
        <div class="col-lg-6 text-lg-end">
          <p>
            <router-link class="nav-link" to="/apt">
              <a class="btn btn-primary text-white py-3 px-4">See All Property</a>
            </router-link>
          </p>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <div class="property-slider-wrap">
            <div class="property-slider">
              <div class="property-item">
                <a href="/login" class="img">
                  <img src="/images/img_1.jpg" alt="Image" class="img-fluid" />
                </a>

                <div class="property-content">
                  <div class="price mb-2"><span>13,500만원</span></div>
                  <div>
                    <span class="d-block mb-2 text-black-50">서울특별시 종로구 충신동 62-2</span>
                    <span class="city d-block mb-3">충신동 CS타워</span>

                    <div class="specs d-flex mb-4">
                      <span class="d-block d-flex align-items-center me-3">
                        <span class="caption">면적: 22.17</span>
                      </span>
                    </div>

                    <a href="/login" class="btn btn-primary py-2 px-3">See details</a>
                  </div>
                </div>
              </div>
              <!-- .item -->

              <div class="property-item">
                <a href="/login" class="img">
                  <img src="images/img_2.jpg" alt="Image" class="img-fluid" />
                </a>

                <div class="property-content">
                  <div class="price mb-2"><span>26,000만원</span></div>
                  <div>
                    <span class="d-block mb-2 text-black-50"
                      >서울특별시 종로구 행촌동 사직로 21</span
                    >
                    <span class="city d-block mb-3">행촌동 대성맨션</span>

                    <div class="specs d-flex mb-4">
                      <span class="d-block d-flex align-items-center me-3">
                        <span class="caption">면적: 19.27</span>
                      </span>
                    </div>

                    <a href="/login" class="btn btn-primary py-2 px-3">See details</a>
                  </div>
                </div>
              </div>
              <!-- .item -->

              <div class="property-item">
                <a href="/login" class="img">
                  <img src="images/img_3.jpg" alt="Image" class="img-fluid" />
                </a>

                <div class="property-content">
                  <div class="price mb-2"><span>139,900만원</span></div>
                  <div>
                    <span class="d-block mb-2 text-black-50"
                      >서울특별시 종로구 무악동 통일로18길 9</span
                    >
                    <span class="city d-block mb-3">무악동 인왕산아이파크</span>

                    <div class="specs d-flex mb-4">
                      <span class="d-block d-flex align-items-center me-3">
                        <span class="icon-bed me-2"></span>
                        <span class="caption">면적: 84.858</span>
                      </span>
                    </div>

                    <a href="/login" class="btn btn-primary py-2 px-3">See details</a>
                  </div>
                </div>
              </div>
              <!-- .item -->

              <!-- .item -->
            </div>

            <div id="property-nav" class="controls" tabindex="0" aria-label="Carousel Navigation">
              <span class="prev" data-controls="prev" aria-controls="property" tabindex="-1"
                >Prev</span
              >
              <span class="next" data-controls="next" aria-controls="property" tabindex="-1"
                >Next</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
