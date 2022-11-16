<template>
  <div>
    <div class="kakaomap" ref="map"></div>
  </div>
</template>

<script>
export default {
  props: ["options"],
  mounted() {
    let kakao = window.kakao;
    var container = this.$refs.map;

    const {center, level} = this.options;
    const mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(center.lat, center.lng),
      level,
    });
    console.log(mapInstance);
  },
};
</script>

<style></style>
