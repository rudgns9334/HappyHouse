<template>
  <div class="alerm" v-bind:class="{ animation: isAnimation }">
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
    <p>ㅇㅇㅇㅇ</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isAnimation: true,
    };
  },
};
</script>

<style>
.alerm {
  position: fixed;
  top: 75px;
  right: 0;
  float: right;
  width: 300px;
  height: 500px;
  z-index: 10;
  overflow: auto;
  background: red;
}

.animation {
  animation: fadeInDown 1s;
}
@keyframes fadeInDown {
  0% {
    opacity: 0;
    transform: translate3d(0, -100%, 0);
  }
  to {
    opacity: 1;
    transform: translateZ(0);
  }
}
</style>
