<template>
  <div>
    <!-- 轮播图部分 -->
    <div class="swiper-container">
      <swiper
        :slidesPerView="1"
        :spaceBetween="0"
        :loop="true"
        :autoplay="{
          delay: 3000,
          disableOnInteraction: false,
          pauseOnMouseEnter: true,
        }"
        :navigation="{
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        }"
        :modules="modules"
        class="mySwiper"
      >
        <swiper-slide
          ><img src="@/assets/images/大图1.png" alt="Image 1"
        /></swiper-slide>
        <swiper-slide
          ><img src="@/assets/images/大图3.png" alt="Image 2"
        /></swiper-slide>
        <swiper-slide
          ><img src="@/assets/images/大图2.png" alt="Image 3"
        /></swiper-slide>
        <swiper-slide
          ><img src="@/assets/images/大图4.png" alt="Image 4"
        /></swiper-slide>
      </swiper>
      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
    </div>

    <!-- 关于卡宾 -->
    <section class="about-section">
      <div class="about-bg">
        <img
          src="@/assets/images/关于卡宾背景图.png"
          alt="卡宾背景"
          class="bg-image"
        />
      </div>
      <div class="about-container">
        <div class="text-content">
          <div class="about-heading">
            <div class="about-subtitle-en">ABOUT CABBEEN</div>
            <div class="about-subtitle-zh">集团介绍</div>
          </div>
          <div class="about-text">
            <p v-for="(text, index) in aboutParagraphs" :key="index">
              {{ text }}
            </p>
          </div>
        </div>
        <div class="values-mobile-inner" v-if="isMobile">
          <transition name="fade" mode="out-in">
            <div class="value-slide" :key="currentIndex">
              <h3>{{ $t(values[currentIndex].title) }}</h3>
              <p>{{ $t(values[currentIndex].description) }}</p>
            </div>
          </transition>
        </div>
        <!-- 品牌视频标题（仅移动端显示） -->
        <div class="video-title-mobile" v-if="isMobile">
          <div class="video-title-en">BRAND VIDEO</div>
          <div class="video-title-zh">品牌视频</div>
        </div>
        <div class="video-container">
          <video controls class="video-player">
            <source src="@/assets/images/关于卡宾-视频.mp4" type="video/mp4" />
            您的浏览器不支持 HTML5 视频。
          </video>
        </div>
      </div>
    </section>

    <!-- 品牌价值观 -->
    <section class="values-section">
      <div class="values-container">
        <!-- PC端展示四图网格 -->
        <div class="values-grid">
          <div class="value-item" v-for="(item, index) in values" :key="index">
            <img
              :src="require(`@/assets/images/价值观${index + 1}.png`)"
              :alt="$t(item.title)"
              class="value-image"
            />
            <div class="value-caption">
              <h3>{{ $t(item.title) }}</h3>
              <p>{{ $t(item.description) }}</p>
            </div>
          </div>
        </div>

        <!-- 移动端自动轮播文字 -->
        <div class="values-mobile" v-if="isMobile">
          <transition name="fade" mode="out-in">
            <div class="value-slide" :key="currentIndex">
              <h3>{{ $t(values[currentIndex].title) }}</h3>
              <p>{{ $t(values[currentIndex].description) }}</p>
            </div>
          </transition>
        </div>
      </div>
    </section>
  </div>
  <!-- 品牌新闻模块 -->
  <!-- 品牌新闻模块 -->
  <section class="news-section">
    <div class="news-container">
      <div class="news-title">
        <span class="zh">品牌新闻</span>
        <span class="en">/NEWS</span>
      </div>
      <div class="news-list">
        <!-- 修改后的跳转路径，每条新闻指向对应独立路由 -->
        <router-link
          v-for="(news, index) in newsList"
          :key="index"
          :to="news.link"
          class="news-item"
          tag="div"
        >
          <div class="news-image-wrapper">
            <img :src="news.image" :alt="news.title" class="news-image" />
          </div>
          <div class="news-content">
            <h3 class="news-title">{{ news.title }}</h3>
            <p class="news-summary">{{ news.summary }}</p>
            <div class="news-meta">
              <span class="news-date">{{ news.date }}</span>
              <span class="news-views"
                >👁 {{ news.views }}{{ $t("about.look") }}</span
              >
            </div>
          </div>
        </router-link>
      </div>
    </div>
  </section>
</template>

<script>
import { Swiper, SwiperSlide } from "swiper/vue";
import { Autoplay, Navigation } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/autoplay";

import { ref, onMounted, onBeforeUnmount, computed } from "vue"; // ✅ 补全 computed
import { useI18n } from "vue-i18n"; // ✅ 补全导入
import newsThumb1 from '@/assets/images/新闻缩略图1.png';
import newsThumb2 from '@/assets/images/新闻缩略图2.png';
import newsThumb3 from '@/assets/images/新闻缩略图3.png';

export default {
  name: "Home",
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    const modules = [Autoplay, Navigation];
    const { t, locale, getLocaleMessage } = useI18n();
    const newsThumbs = [newsThumb1, newsThumb2, newsThumb3];

    const aboutParagraphs = computed(() => {
      const raw = getLocaleMessage(locale.value).about?.intro;
      return Array.isArray(raw) ? raw : [String(raw)]; // 防御性写法
    });

    const values = computed(() => {
      return getLocaleMessage(locale.value).values?.items || [];
    });

    const newsList = computed(() => {
      return getLocaleMessage(locale.value).news?.cards?.map((card, index) => ({
        ...card,
        date: [
          "2025-06-08", 
          "2013-10-28", 
          "2007-02-03"
        ][index],
        views: [835, 1210, 1985][index],
        image: newsThumbs[index],
        link: ["/news/malaysia", "/news/hk-listing", "/news/ny-show"][index]
      })) || [];
    });

    const currentIndex = ref(0);
    const isMobile = ref(false);
    let intervalId;

    const startAutoScroll = () => {
      intervalId = setInterval(() => {
        currentIndex.value = (currentIndex.value + 1) % values.value.length;
      }, 3000);
    };

    const checkMobile = () => {
      isMobile.value = window.innerWidth <= 768;
    };

    onMounted(() => {
      checkMobile();
      startAutoScroll();
      window.addEventListener("resize", checkMobile);
    });

    onBeforeUnmount(() => {
      clearInterval(intervalId);
      window.removeEventListener("resize", checkMobile);
    });

    return {
      t,
      locale,
      aboutParagraphs,
      modules,
      values,
      newsList,
      currentIndex,
      isMobile,
    };
  },
};
</script>

<style scoped>
/* 轮播图样式保持不变 */
.swiper-container {
  position: relative;
  width: 100%;
  height: auto; /* 视口高度 */
  background-color: #000; /* 添加黑色背景 */
  overflow: hidden; /* 防止内容溢出 */
}

.mySwiper {
  width: 100%;
  height: 100%;
}

.mySwiper img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 关键属性 - 填充整个容器 */
  object-position: center; /* 图片居中显示 */
}

.swiper-button-next,
.swiper-button-prev {
  color: #f39c12;
  width: 40px;
  height: 40px;
  background: rgba(0, 0, 0, 0.5);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  z-index: 10;
}

.swiper-button-next {
  right: 20px;
}

.swiper-button-prev {
  left: 20px;
}

.swiper-button-next::after,
.swiper-button-prev::after {
  font-size: 20px;
}

/* 关于卡宾模块样式 - 精确匹配最新设计图 */
.about-section {
  position: relative;
  width: 100%;
  min-height: 600px;
  display: flex;
  align-items: center;
  overflow: hidden;
}

.about-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
}

.bg-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.about-container {
  display: flex;
  max-width: 1500px;
  margin: 0 auto;
  padding: 40px;
  gap: 60px;
  align-items: stretch; /* 使两侧高度一致 */
}

.text-content {
  flex: 1;
  display: flex;
  flex-direction: column;
}

/* 通用标题样式 */
.about-heading {
  text-align: center;
  margin-bottom: 20px;
}

/* 英文标题 */
.about-subtitle-en {
  font-size: 20px;
  font-weight: bold;
  color: #ffb100;
  font-family: "Source Sans Pro", sans-serif;
  margin-bottom: 5px;
}

/* 中文标题 */
.about-subtitle-zh {
  font-size: 18px;
  font-weight: bold;
  color: #ffffff;
}

.about-text {
  flex: 1;
  font-size: 14px;
  line-height: 2;
  color: white;
  text-align: justify;
  display: flex;
  flex-direction: column;
  justify-content: center; /* 垂直居中 */
}

.about-text p {
  text-indent: 2em; /* 首行缩进2字符 */
  margin-bottom: 0; /* 无段落间隔 */
}

.copyright {
  font-size: 14px;
  color: rgba(255, 255, 255, 0.7);
  margin-top: auto; /* 将版权信息推到底部 */
  padding-top: 20px;
}

.video-container {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.video-player {
  width: 90%;
  height: 90%;
  max-height: 400px;
  object-fit: contain;
  margin-top: 30px; /* 向下移动 30px */
}

.highlight-yellow {
  color: #ffb100; /* 或 #ffd100 你更喜欢哪个 */
  font-weight: bold;
}
/* 修改后的核心价值观展示栏目样式 */
.values-section {
  width: 100%;
  padding: 40px 0;
  background-color: #000000;
  color: white;
}

.values-container {
  max-width: 1800px;
  margin: 0 auto;
  padding: 0 20px;
}

.values-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 15px;
}

.value-item {
  position: relative;
  border-radius: 8px;
  overflow: hidden;
  aspect-ratio: 401/301;
  border: 1px solid #f39c12;
}

.value-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.value-caption {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7); /* 半透明黑色背景 */
  color: white;
  padding: 15px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transform: translateY(30%);
  transition: all 0.4s ease;
  text-align: center;
}

.value-item:hover .value-caption {
  opacity: 1;
  transform: translateY(0);
}

.value-caption h3 {
  color: #f39c12;
  font-size: 20px;
  margin-bottom: 10px;
}

.value-caption p {
  font-size: 14px;
  line-height: 1.8;
  max-width: 90%;
}
/**新闻模块样式 */
.news-section {
  background: #000;
  padding: 80px 20px;
  color: white;
}

.news-container {
  max-width: 1600px;
  margin: 0 auto;
}

.news-title {
  font-size: 2.4rem; /* 更大字号 */
  font-weight: bold;
  text-align: center;
  margin-bottom: 50px; /* ✅ 设置合理的下边距 */
  line-height: 1.6;
}

.news-title .zh {
  color: white;
  font-size: 25px;
}

.news-title .en {
  color: #ffb100;
  margin-left: 10px;
  font-size: 25px;
}

.news-list {
  display: flex;
  flex-wrap: wrap;  /* 允许换行，避免超出屏幕 */
  justify-content: center;
  gap: 30px;
  padding: 0 40px; /* ✅ 添加左右留白 */
  box-sizing: border-box;
}

.news-item {
  flex: 1 1 32%;
  background: #1c1c1c;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  min-width: 300px;
  max-width: 100%;
  padding: 15px 15px 20px; /* ✅ 缩小内边距，保留上下空隙 */
  margin-top: 20px; /* ✅ 使每个卡片顶部有留白 */
}

.news-image-wrapper {
  width: 100%;
  height: 230px; /* ✅ 原来是 65%，改为固定高度 */
  overflow: hidden;

  margin-bottom: 15px; /* ✅ 增加图片和内容之间的间距 */
}
.news-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}

.news-image:hover {
  transform: scale(1.05);
}

/* ✅ 内容区域样式 */
.news-content {
  padding: 20px;
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.news-title {
  font-size: 16px;
  font-weight: bold;
  color: #ffb100;
  line-height: 1.5;
  margin-bottom: 10px;
}

.news-summary {
  font-size: 13px;
  color: #ccc;
  line-height: 1.6;
  flex-grow: 1;
  margin-bottom: 15px;
}

/* ✅ 底部时间 + 阅读数 */
.news-meta {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  color: #888;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  padding-top: 10px;
}

/* 移动端文字轮播样式 */
.values-mobile {
  display: none;
  text-align: center;
  padding: 30px 10px;
}
.value-slide {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  text-align: center;
}

.value-slide h3 {
  font-size: 20px;
  color: #f39c12;
  margin-bottom: 10px;
}

.value-slide p {
  color: white;
  font-size: 14px;
  line-height: 1.8;
}

.values-mobile-inner {
  width: 100%;
  max-width: 370px;
  height: 200px;
  padding: 30px 10px;
  margin: 0 auto;
  border-radius: 8px;
  box-sizing: border-box;
}
.values-mobile-inner .value-slide {
  color: white;
}

.values-mobile-inner .value-slide h3 {
  color: #f39c12;
  font-size: 18px;
  margin-bottom: 10px;
}

.values-mobile-inner .value-slide p {
  font-size: 14px;
  line-height: 1.6;
  color: white;
}
/* 自适应下的视频标题样式 */
.video-title-mobile {
  text-align: center;
  margin: 30px 0 10px;
}

.video-title-mobile .video-title-en {
  font-size: 18px;
  font-weight: bold;
  color: #ffb100; /* 黄色 */
}

.video-title-mobile .video-title-zh {
  font-size: 20px;
  font-weight: bold;
  color: #ffffff; /* 白色 */
  margin-top: 5px;
}

/* 仅移动端启用 */
@media (max-width: 768px) {
  .video-player {
    width: 100%;
    height: auto;
    max-width: 400px;
    max-height: 350px;
    margin: 0 auto;
    display: block;
  }

  .video-container {
    display: flex;
    justify-content: center;
  }

  .values-mobile-inner {
    display: block;
  }
  .values-grid {
    display: none;
  }

  .values-mobile {
    display: none !important;
  }

  .about-text p:not(:first-of-type) {
    display: none;
  }

  .about-container {
    flex-direction: column;
    gap: 20px;
  }

  .video-container {
    margin-top: 30px;
  }
  .news-list {
    flex-direction: column; /* 竖排布局 */
    align-items: center;  /* 使新闻卡片在竖排时居中 */
    padding: 0 10px;  /* 为了适应小屏幕，增加左右内边距 */
  }

  .news-item {
    flex: 1 1 auto;  /* 取消 PC 端的宽度限制，适应小屏 */
    max-width: 90%; /* 最大宽度90%，避免过宽 */
    margin: 10px 0; /* 添加间隔 */
  }

  /* 可选：对新闻标题和摘要的字体大小进行调整 */
  .news-title {
    font-size: 18px;
  }

  .news-summary {
    font-size: 14px;
  }

  /* 使得新闻模块的标题也能适应移动端 */
  .news-title .zh, .news-title .en {
    font-size: 22px;
  }
}

/* 淡入淡出动画 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.swiper-container {
  height: auto; /* 让高度根据内容自适应 */
}

.mySwiper {
  height: auto;
}

.mySwiper img {
  height: auto;
  width: 100%;
  object-fit: contain; /* 或者改为 cover 效果会更饱满 */
  object-position: top;
  display: block;
}
</style>
