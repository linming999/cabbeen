import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/styles/base.css';
import i18n from './i18n'; // 引入 vue-i18n 配置
import 'swiper/swiper-bundle.css';
import axios from 'axios';

// 创建 Vue 3 应用实例
const app = createApp(App);

// 将 axios 挂载到 app.config.globalProperties 上，组件中可以用 this.$axios 访问
app.config.globalProperties.$axios = axios;

// 使用路由和 i18n
app.use(router);
app.use(i18n);

// 挂载应用
app.mount('#app');