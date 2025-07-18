<template>
  <div class="app-container">
    <Navbar />
    <router-view />
    <Footer />
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';

export default {
  components: {
    Navbar,
    Footer
  },
  mounted() {
    // 设置页面初始标题
    document.title = this.$t('title');
  },
  watch: {
    // 监听语言变化，当语言切换时更新页面标题
    '$i18n.locale': function(newLocale) {
      document.title = this.$t('title');
    }
  }
};
</script>
<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* 让 router-view 的内容撑开容器 */
main {
  flex: 1;
}
</style>
