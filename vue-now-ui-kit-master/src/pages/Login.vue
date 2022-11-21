<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image" style="background-image: url('img/bg11.jpg')"></div>
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain>
            <div slot="header" class="logo-container"></div>

            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons ui-1_email-85"
              placeholder="Email"
              v-model="$store.state.userStore.user.userEmail"
            >
            </fg-input>

            <fg-input
              type="password"
              class="no-border input-lg"
              addon-left-icon="now-ui-icons objects_key-25"
              placeholder="Password"
              v-model="$store.state.userStore.user.userPassword"
            >
            </fg-input>

            <template slot="raw-content">
              <div class="card-footer text-center">
                <button class="btn btn-primary btn-round btn-lg btn-block" @click="login">
                  Login
                </button>
              </div>
              <div class="pull-left">
                <h6>
                  <router-link class="link footer-link" to="/register">
                    <strong>Create Account</strong>
                  </router-link>
                </h6>
              </div>
              <div class="pull-right">
                <h6>
                  <a href="#pablo" class="link footer-link">Need Help?</a>
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
import { Card, Button, FormGroupInput } from "@/components";
import MainFooter from "@/layout/MainFooter";

import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

import { mapActions } from "vuex";

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
    
    ...mapActions(userStore, ["login"]),
  },
};
</script>
<style scoped>
.card >>> .card-body {
  height: 250px;
}
</style>
