<template>
  <div class="inner-form">
        <div class="input-field first-wrap">
        <div class="input-select">
            <select id="sel1" data-trigger="" name="choices-single-defaul">
            <option placeholder="">전체</option>
            <option>서울시 어쩌구</option>
            </select>
            <select id="sel2" data-trigger="" name="choices-single-defaul">
            <option placeholder="">전체</option>
            <option>동 입력</option>
            </select>
        </div>
        </div>
        <div class="input-field second-wrap">
        <input id="search" type="text" placeholder="아파트 이름 검색" />
        </div>
        <div class="input-field third-wrap">
        <button class="btn-search" type="button">
            <span>SEARCH</span>
        </button>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>
.inner-form {
    display: -ms-flexbox;
    display: flex;
    width: 900px;
    height: 60px;
    -ms-flex-pack: justify;
    justify-content: space-between;
    -ms-flex-align: center;
    align-items: center;
    box-shadow: 0px 8px 20px 0px rgb(0 0 0 / 15%);
    border-radius: 5px;
}
.input-select select {
    width: 150px;
    height: 60px;
    border: none;
}
#sel2 {
    margin-left: -2px;
}
#search {
    width: 500px;
    height: 60px;
    border: none;
    margin-left: -4px;
}
.btn-search {
    border: none;
    display: block;
    text-align: center;
    cursor: pointer;
    text-transform: uppercase;
    outline: none;
    overflow: hidden;
    position: relative;
    color: #fff;
    font-weight: 700;
    font-size: 15px;
    background-color: #222;
    padding: 20px 20px;
    border-radius: 0px 5px 5px 0px;
}
.btn-search:after {
    content: "";
    position: absolute;
    left: 0;
    top: 0;
    height: 400%;
    width: 300%;
    background: #005555;
    -webkit-transition: all .5s ease-in-out;
    transition: all .5s ease-in-out;
    -webkit-transform: translateX(-98%) translateY(-25%) rotate(45deg);
    transform: translateX(-98%) translateY(-25%) rotate(45deg);
}
.btn-search:hover:after {
   -webkit-transform: translateX(-9%) translateY(-25%) rotate(45deg);
    transform: translateX(-9%) translateY(-25%) rotate(45deg);
}
.btn-search span {
  position: relative; 
  z-index: 1;
}
</style>