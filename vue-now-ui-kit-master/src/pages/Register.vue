<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image" style="background-image: url('img/bg11.jpg')"></div>
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain>
            <div slot="header" class="logo-container">Register</div>
            <div></div>
            <fg-input
              class="no-border"
              placeholder="Name"
              addon-left-icon="now-ui-icons users_circle-08"
              v-model="$store.state.userStore.regist.userName"
              v-on:focus="$store.state.userStore.regist.isUserNameFocus = true"
              :error="$store.state.userStore.regist.userNameErrMsg"
              v-on:input="validateUserName"
            >
            </fg-input>

            <fg-input
              class="no-border"
              placeholder="Email"
              addon-left-icon="now-ui-icons ui-1_email-85"
              v-model="$store.state.userStore.regist.userEmail"
              v-on:focus="$store.state.userStore.regist.isUserEmailFocus = true"
              :error="$store.state.userStore.regist.userEmailErrMsg"
              v-on:input="validateUserEmail"
            >
            </fg-input>

            <fg-input
              type="password"
              class="no-border"
              placeholder="Password"
              addon-left-icon="now-ui-icons objects_key-25"
              v-model="$store.state.userStore.regist.userPassword"
              v-on:focus="$store.state.userStore.regist.isUserPasswordFocus = true"
              :error="$store.state.userStore.regist.userPasswordErrMsg"
              v-on:input="validateUserPassword"
            >
            </fg-input>

            <fg-input
              type="password"
              class="no-border"
              addon-left-icon="now-ui-icons objects_key-25"
              placeholder="Confirm"
              v-model="$store.state.userStore.regist.userPassword2"
              v-on:focus="$store.state.userStore.regist.isUserPassword2Focus = true"
              :error="$store.state.userStore.regist.userPassword2ErrMsg"
              v-on:input="validateUserPassword2"
            >
            </fg-input>
            <template slot="raw-content">
              <div class="card-footer text-center">
                <button @click="register" class="btn btn-primary btn-round btn-lg btn-block">Get Started</button>
              </div>
              <div class="pull-right">
                <h6>
                  <a href="/" class="link footer-link">Need Help?</a>
                </h6>
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
    <main-footer></main-footer>
  </div>
</template>
<script>
import {Card, Button, FormGroupInput} from "@/components";
import MainFooter from "@/layout/MainFooter";

import Vue from "vue";
import VueAlertify from "vue-alertify";
import {mapActions, mapMutations} from "vuex";
Vue.use(VueAlertify);

const userStore = "userStore";

export default {
  name: "login-page",
  bodyClass: "login-page",
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
  methods: {
    ...mapMutations(userStore, ["CLEAR_REGISTER"]),
    ...mapActions(userStore, [
      "validateUserName",
      "validateUserEmail",
      "validateUserPassword",
      "validateUserPassword2",
      "register",
    ]),
  },
  mounted() {
    this.CLEAR_REGISTER();
  },
};
</script>
<style></style>
