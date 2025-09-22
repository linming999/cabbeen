<template>
  <div class="product-detail-page" v-if="product">
    <!-- 返回按钮 -->

    <div class="main-section">
      <!-- 主图横向展示 -->
      <swiper
        class="hermes-swiper"
        :slides-per-view="isMobile ? 1 : 'auto'"
        :space-between="0"
        :pagination="{
          el: '.hermes-swiper-pagination',
          clickable: true,
          bulletClass: 'swiper-pagination-bullet',
          bulletActiveClass: 'swiper-pagination-bullet-active',
        }"
        :modules="[Pagination, FreeMode]"
        :free-mode="!isMobile"
        @swiper="onSwiperReady"
        @slide-change="onSlideChange"
      >
      <button 
        class="back-button"
        @click="handleBack"
        slot="container-start"
      >
        ← 返回
      </button>
        <swiper-slide
          v-for="(img, i) in currentStyle.images"
          :key="i"
          class="hermes-slide"
          :style="{
            width: isMobile ? '100%' : '761px',
            height: isMobile ? 'auto' : '1000px',
          }"
        >
          <img
            :src="img"
            :alt="`商品大图${i + 1}`"
            class="hermes-main-image"
            draggable="false"
          />
        </swiper-slide>

        <!-- 移除 ghost 部分，避免留白 -->
        <div class="hermes-swiper-pagination" slot="pagination"></div>
      </swiper>

      <!-- PC端缩略图切换栏 -->
      <div class="hermes-thumbs-bar" v-if="!isMobile">
        <div
          v-for="(img, i) in currentStyle.images"
          :key="i"
          class="hermes-thumb"
          :class="{ active: currentImageIndex === i }"
          @click="goToSlide(i)"
        >
          <img :src="img" :alt="'缩略图' + (i + 1)" />
        </div>
      </div>

      <!-- 信息卡片 -->
      <div class="info-box" :class="{ 'mobile-info-box': isMobile }">
        <h2>{{ product.name }}</h2>
        <p class="product-price">¥{{ product.price }}</p>

        <div class="color-row">
          <span class="color-label">颜色：</span>
          <span class="color-value">{{ currentStyle?.style }}</span>
        </div>
        <div class="style-list">
          <div
            v-for="style in product.styles"
            :key="style.style"
            class="style-item"
            :class="{ active: currentStyle?.style === style.style }"
            @click="selectStyle(style)"
          >
            <img :src="style.cover" />
          </div>
        </div>

        <p class="product-desc">{{ product.description }}</p>
        </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick, onBeforeUnmount } from "vue";  
import { useRoute, useRouter } from "vue-router";
import request from "@/utils/request";
import { Swiper, SwiperSlide } from "swiper/vue";
import { Pagination, FreeMode } from "swiper/modules";
import "swiper/css";
import "swiper/css/pagination";

const route = useRoute();
const router = useRouter();

const product = ref(null);
const currentStyle = ref({});
const currentImage = ref("");
const mainSwiper = ref(null);
const currentImageIndex = ref(0);
const isMobile = ref(window.innerWidth <= 768);

const handleResize = () => {
  isMobile.value = window.innerWidth <= 768;
};

onMounted(() => {
  window.addEventListener('resize', handleResize);
  fetchDetail();
});

onBeforeUnmount(() => {
  window.removeEventListener('resize', handleResize);
});

const setSwiper = (swiper) => {
  mainSwiper.value = { swiper };
};

const onSwiperReady = (swiperInstance) => {
  mainSwiper.value = swiperInstance;
};
const goToSlide = (i) => {
  if (mainSwiper.value && typeof mainSwiper.value.slideTo === "function") {
    mainSwiper.value.slideTo(i);
  } else {
    console.warn("Swiper not ready!", mainSwiper.value);
  }
};

const onSlideChange = (swiper) => {
  currentImageIndex.value = swiper.activeIndex;
};
// 处理返回逻辑
const handleBack = () => {
  // 从路由查询参数中获取滚动位置
  const scrollY = route.query.scrollY || 0;
  router.push({
    path: '/store', // 你的商品列表页路由
    query: { scrollY }
  });
};
// 获取商品详情
const fetchDetail = async () => {
  const id = route.params.id;
  try {
    const res = await request.get(`/goods/good/detail/${id}`);
    product.value = res.data;

    if (res.data && res.data.styles && res.data.styles.length > 0) {
      currentStyle.value = res.data.styles[0];
      currentImage.value = res.data.styles[0].images?.[0] || "";
    } else {
      currentStyle.value = {};
      currentImage.value = "";
    }
  } catch (e) {
    console.error("加载商品详情失败", e);
  }
};

const selectStyle = async (style) => {
  currentStyle.value = style;
  currentImageIndex.value = 0;
  await nextTick();
  mainSwiper.value?.swiper?.slideTo(0);
};
</script>

<style scoped>
body,
html {
  height: 100%;
  background: #f6f1e7;
}

.product-detail-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: #f6f1e7;
}

/* 新增返回按钮样式 */
.top-left-button {
  position: fixed;
  top: 20px;
  left: 20px;
  z-index: 100;
  background: rgba(0, 0, 0, 0.5);
  color: white;
  border: none;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  cursor: pointer;
}


.back-button {
  position: fixed;
  top: 20px;
  left: 20px;
  z-index: 100;
  background: rgba(0, 0, 0, 0.5);
  color: white;
  border: none;
  border-radius: 20px;
  padding: 8px 16px;
  font-size: 16px;
  cursor: pointer;
  display: none; /* 默认隐藏 */
  white-space: nowrap;
  backdrop-filter: blur(5px); /* 毛玻璃效果 */
}

.main-section {
  flex: 1 0 auto;
  background: #f6f1e7;
  position: relative;
  width: 100vw;
  margin: 0;
  padding: 0;
  min-height: 0;
}

.image-gallery {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  overflow-x: auto;
  padding: 0px;
  scroll-snap-type: x mandatory;
  background-color: #111;
}

.image-gallery img {
  flex-shrink: 0;
  width: 760px;
  height: 1060px;
  object-fit: cover;
  background: #111;
  scroll-snap-align: start;
  cursor: pointer;
  border: none;
  margin: 0;
}
.image-gallery img.active {
  border-color: #f39c12;
}

.image-gallery::-webkit-scrollbar,
.thumbnail-bar::-webkit-scrollbar {
  display: none;
}

.image-gallery,
.thumbnail-bar {
  -ms-overflow-style: none;
  scrollbar-width: none;
}

/* 缩略图切换栏 */
.thumbnail-bar {
  position: absolute;
  bottom: 24px;
  left: 24px;
  display: flex;
  gap: 8px;
  z-index: 10;
}

.thumbnail-bar img {
  width: 60px;
  height: 80px;
  object-fit: cover;
  border: 2px solid transparent;
  border-radius: 4px;
  cursor: pointer;
}
.thumbnail-bar img.active {
  border-color: #f39c12;
}

/* 商品信息卡片 */
.info-box {
  width: 380px;
  padding: 24px;
  background: rgba(0, 0, 0, 0.5);
  color: #fff;
  position: absolute;
  top: 50%;
  left: 75%;
  transform: translateY(-80%);
  z-index: 10;
}

/* 商品名称 */
.info-box h2 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 16px;
}

/* 商品价格 */
.product-price {
  font-size: 14px;
  color: #e67e22;
  margin: 10px 0;
}

.style-title {
  margin-top: 16px;
  margin-bottom: 6px;
}

.style-list {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  font-size: 14px;
}

.style-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
  margin-right: 8px;
}

.style-item.active img {
  border: 2px solid #fff;
}

.style-label {
  margin-top: 4px;
  font-size: 12px;
  color: #fff;
  text-align: center;
}

.style-list img {
  width: 60px;
  height: 80px;
  object-fit: cover;
  border: 2px solid transparent;
  cursor: pointer;
}
.style-list img.active {
  border-color: #fff;
}

.color-row {
  font-size: 14px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0 10px 0;
  border-bottom: 1px solid #d8d8d8;
  margin-bottom: 16px;
  color: #fff;
  letter-spacing: 1px;
}

.color-label {
  color: #fff;
  font-weight: 400;
}

.color-value {
  color: #fff;
  font-weight: 600;
  margin-left: 20px;
}

.product-desc {
  margin-top: 12px;
  font-size: 14px;
  color: #ccc;
}

.hermes-swiper {
  width: 100vw;
  height: 100%;
  background: transparent;
}

.hermes-slide {
  width: 761px !important;
  height: 100%;
  box-sizing: border-box;
}

.hermes-main-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  background: #fff;
  display: block;
  pointer-events: none;
  user-select: none;
  margin: 0;
}

.hermes-swiper-pagination {
  position: absolute;
  bottom: 18px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 2;
  display: flex;
  gap: 12px;
}
.swiper-pagination-bullet {
  width: 9px;
  height: 9px;
  border-radius: 50%;
  background: #bbb;
  opacity: 0.6;
  margin: 0 3px;
  transition: background 0.18s, opacity 0.18s;
}
.swiper-pagination-bullet-active {
  background: #333;
  opacity: 1;
}

.hermes-slide-ghost {
  width: 460px !important;
  height: 1000px !important;
  background: transparent !important;
  box-shadow: none !important;
  pointer-events: none !important;
}

.hermes-thumbs-bar {
  position: absolute;
  left: 24px;
  bottom: 24px;
  display: flex;
  gap: 4px;
  background: #fff;
  border-radius: 0;
  box-shadow: 0 2px 10px 0 rgba(0, 0, 0, 0.08);
  padding: 4px 6px;
  z-index: 11;
  max-width: 22vw;
  height: 50px;
}

.hermes-thumbs-bar::-webkit-scrollbar {
  display: none;
}
.hermes-thumbs-bar {
  scrollbar-width: none;
}

.hermes-thumb {
  width: 32px;
  height: 40px;
  overflow: hidden;
  border: 2px solid transparent;
  cursor: pointer;
  flex-shrink: 0;
  background: #fff;
  margin: 0;
  box-shadow: none;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2px;
}

.hermes-thumb img {
  max-width: 90%;
  max-height: 90%;
  object-fit: contain;
  display: block;
  background: #fff;
  margin: auto;
}
.hermes-thumb.active {
  border-color: #ff9800;
  box-shadow: 0 0 0 1px #222;
}



/* 移动端样式 */
@media (max-width: 768px) {
  .main-section {
    display: flex;
    flex-direction: column;
  }
  
  .hermes-swiper {
    height: auto;
    aspect-ratio: 3/4;
  }
  
  .hermes-slide {
    width: 100% !important;
    height: auto !important;
  }
  
  .hermes-main-image {
    width: 100%;
    height: auto;
    aspect-ratio: 3/4;
  }
  
  .info-box {
    position: static;
    width: 100%;
    transform: none;
    background: #fff;
    color: #000;
    padding: 16px;
    margin-top: 0;
  }
  
  .mobile-info-box {
    padding: 16px;
  }
  
  .mobile-info-box h2 {
    color: #000;
    font-size: 20px;
    margin-bottom: 8px;
  }
  
  .mobile-info-box .product-price {
    color: #000;
    font-size: 18px;
    margin: 8px 0;
  }
  
  .mobile-info-box .color-row,
  .mobile-info-box .color-label,
  .mobile-info-box .color-value {
    color: #000;
    border-bottom-color: #eee;
  }
  
  .mobile-info-box .product-desc {
    color: #666;
    margin: 12px 0;
  }
  
   .back-button {
    display: block;
    position: absolute; 
    left: 20px;
    z-index: 10;
    background: rgba(255, 255, 255, 0.1);
    color: #fff;
    padding: 6px 16px;
    border-radius: 20px;
    font-size: 16px;
  }

  .hermes-thumbs-bar {
    display: none;
  }

  .hermes-swiper {
    position: relative;
  }
}
</style>