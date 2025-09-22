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
        <swiper-slide v-for="(s, i) in slides" :key="i">
          <picture>
            <!-- 移动端竖版图 -->
            <source media="(max-width: 768px)" :srcset="s.m" />
            <!-- 默认 PC 横版图 -->
            <img :src="s.pc" :alt="`slide-${i+1}`" />
          </picture>
        </swiper-slide>
      </swiper>
      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
    </div>

    <div class="star-show-section">
      <div class="star-show-title" v-html="$t('celebrityTitleHtml')"></div>
      <div class="star-show-list">
        <div class="star-show-item" v-for="(img, idx) in starImages" :key="idx">
          <img :src="img" alt="" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { Swiper, SwiperSlide } from "swiper/vue";
import { Autoplay, Navigation } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/autoplay";

const modules = [Autoplay, Navigation];

const slides = [
  {
    pc: new URL('@/assets/images/01.png', import.meta.url).href,
    m:  new URL('@/assets/images/011.png', import.meta.url).href,
  },
  {
    pc: new URL('@/assets/images/02.png', import.meta.url).href,
    m:  new URL('@/assets/images/012.png', import.meta.url).href,
  },
  {
    pc: new URL('@/assets/images/03.png', import.meta.url).href,
    m:  new URL('@/assets/images/013.png', import.meta.url).href,
  },
  {
    pc: new URL('@/assets/images/04.png', import.meta.url).href,
    m:  new URL('@/assets/images/014.png', import.meta.url).href,
  },
];

const starImages = [
  new URL('@/assets/images/明星1.png', import.meta.url).href,
  new URL('@/assets/images/明星2.png', import.meta.url).href,
  new URL('@/assets/images/明星3.png', import.meta.url).href,
  new URL('@/assets/images/明星4.png', import.meta.url).href,
];
</script>
<style scoped>
.swiper-container {
  position: relative;
  width: 100%;
  height: 100vh;
}
.mySwiper {
  width: 100%;
  height: 100%;
}
.mySwiper picture,
.mySwiper img {
  width: 100%;
  height: 100%;
  display: block;
}
.mySwiper img {
  object-fit: cover; /* PC 端横图铺满 */
}

/* 导航按钮 */
.swiper-button-next,
.swiper-button-prev {
  color: #f39c12;
  width: 50px;
  height: 50px;
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
.swiper-button-next { right: 20px; }
.swiper-button-prev { left: 20px; }
.swiper-button-next::after,
.swiper-button-prev::after { font-size: 24px; }

.star-show-section {
  background: #000;
  padding: 120px 0 40px 0;
  text-align: center;
}
.star-show-title {
  text-align: center;
  font-size: 1.7rem;
  font-weight: bold;
  margin-bottom: 100px;
  letter-spacing: 2px;
}
.star-show-title ::v-deep .zh { color: #fff; font-size: 1.5rem; }
.star-show-title ::v-deep .en { color: #ffb100; font-size: 1.5rem; margin-left: 0.3em; }
.star-show-list {
  display: flex;
  justify-content: center;
  align-items: stretch;
  gap: 10px;
  flex-wrap: wrap;
  width: 100%;
  margin: 0 auto;
}
.star-show-item {
  flex: 1 1 22%;
  max-width: 2000px;
  background: #000;
  overflow: hidden;
  box-shadow: none;
  transition: box-shadow 0.3s;
}
.star-show-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  background: #000;
  transition: transform 0.3s ease;
}
.star-show-item:hover img { transform: scale(1.1); z-index: 2; }

@media (max-width: 1024px) {
  .swiper-container { height: 70vh; }
  .star-show-list { gap: 20px; max-width: 90%; margin: 0 auto; }
  .star-show-item { flex: 0 0 calc(50% - 10px); max-width: calc(50% - 10px); }
}

/* 移动端：竖图更友好的纵横比和按钮尺寸 */
@media (max-width: 768px) {
  .swiper-container {
    height: auto;
    aspect-ratio: 9 / 16; /* 适配竖版图 */
  }
  .mySwiper { height: auto; aspect-ratio: 9 / 16; }
  .mySwiper img { height: auto; aspect-ratio: 9 / 16; object-fit: cover; }

  .swiper-button-prev,
  .swiper-button-next {
    width: 36px;
    height: 36px;
    background-color: rgba(0, 0, 0, 0.4);
  }
  .swiper-button-prev::after,
  .swiper-button-next::after { font-size: 18px; }

  .star-show-section { padding: 60px 0 20px 0; }
  .star-show-title { margin-bottom: 40px; }
  .star-show-list { gap: 20px; max-width: 95%; }
  .star-show-item { flex: 0 0 100%; max-width: 90%; margin: 0 auto; }
}

@media (max-width: 480px) {
  .star-show-list { flex-direction: column; gap: 20px; }
  .star-show-item { flex-basis: 99%; max-width: 90%; }
  .star-show-title { font-size: 1.3rem; margin-bottom: 30px; }
  .star-show-title ::v-deep .zh,
  .star-show-title ::v-deep .en { font-size: 1.2rem; }
}
</style>
