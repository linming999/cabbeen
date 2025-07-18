<template>
  <div class="store-map-page">
    <!-- 顶部按钮选择 -->
    <div class="store-selector">
      <el-button
        v-for="(store, key) in storeMap"
        :key="key"
        :class="['store-btn', selectedKey === key ? 'active' : '']"
        @click="switchStore(key)"
      >
        {{ store.name }}
      </el-button>
    </div>

    <!-- 主体内容 -->
    <div class="main-content">
      <!-- 左侧地图 -->
      <div class="map-box">
        <div id="map"></div>
      </div>

      <!-- 右侧信息 -->
      <div class="info-box">
        <h2>{{ currentStore.name }}</h2>

        <div class="info-item">
          <img src="@/assets/images/位置.png" class="icon" alt="位置" />
          <span>{{ currentStore.address }}</span>
        </div>

        <div class="info-item">
          <img src="@/assets/images/时间.png" class="icon" alt="营业时间" />
          <span>{{ currentStore.openTime }}</span>
        </div>

        <div class="info-item">
          <img src="@/assets/images/电话.png" class="icon" alt="电话" />
          <span>{{ currentStore.phone }}</span>
        </div>

        <div class="info-item">
          <img src="@/assets/images/邮箱.png" class="icon" alt="邮箱" />
          <span>{{ currentStore.email }}</span>
        </div>

        <div class="info-item">
          <img src="@/assets/images/Whatapp .png" class="icon" alt="Whatsapp" />
          <span>{{ currentStore.Whatapp }}</span>
        </div>

        <!-- 📢 社交媒体跳转图标区域 -->
        <div class="social-icons">
          <a :href="currentStore.socialLinks.facebook" target="_blank">
            <img src="@/assets/images/facebook.png" alt="Facebook" />
          </a>
          <a :href="currentStore.socialLinks.ig" target="_blank">
            <img src="@/assets/images/IG.png" alt="Instagram" />
          </a>
          <a :href="currentStore.socialLinks.tiktok" target="_blank">
            <img src="@/assets/images/tiktok.png" alt="TikTok" />
          </a>
        </div>

        <div class="info-actions">
          <el-button class="go-btn" @click="goToMap" round>
            🚗 {{ $t("offlineStore.navigateBtn") }}
          </el-button>
        </div>
      </div>
    </div>
    <!-- PC端展示：图网格+弹窗 -->
    <div v-if="!isMobile && selectedKey === 'store1'" class="store-images">
      <h3 class="store-image-title">
        <span class="yellow">/ Store Images</span>
      </h3>
      <div class="image-grid">
        <img
          v-for="i in 9"
          :key="i"
          :src="require(`@/assets/images/storeimages/pvbj/${i}.png`)"
          alt="门店图片"
          class="store-image"
          @click="
            showImageDialog(
              require(`@/assets/images/storeimages/pvbj/${i}.png`)
            )
          "
        />
      </div>
    </div>

    <div v-if="!isMobile && selectedKey === 'store2'" class="store-images">
      <h3 class="store-image-title">
        <span class="yellow">/ Store Images</span>
      </h3>
      <div class="image-grid">
        <img
          v-for="i in 9"
          :key="i"
          :src="require(`@/assets/images/storeimages/sv/${i}.png`)"
          alt="门店图片"
          class="store-image"
          @click="
            showImageDialog(require(`@/assets/images/storeimages/sv/${i}.png`))
          "
        />
      </div>
    </div>

    <!-- 移动端轮播展示 -->
    <div v-if="isMobile" class="store-images-mobile">
      <h3 class="store-image-title">
        /<span class="yellow">Store Images</span>
      </h3>
      <swiper
        :modules="[Autoplay]"
        :space-between="12"
        :slides-per-view="1"
        :loop="true"
        :autoplay="{ delay: 3000, disableOnInteraction: false }"
      >
        <swiper-slide v-for="i in 9" :key="i">
          <img
            class="mobile-store-image"
            :src="
              selectedKey === 'store1'
                ? require(`@/assets/images/storeimages/pvbj/${i}.png`)
                : require(`@/assets/images/storeimages/sv/${i}.png`)
            "
            alt="门店大图"
          />
        </swiper-slide>
      </swiper>
    </div>

    <!-- 弹窗大图仅PC端保留 -->
    <div
      v-if="previewVisible && !isMobile"
      class="dialog-mask"
      @click.self="closeImageDialog"
    >
      <div class="dialog image-dialog">
        <div class="dialog-header">
          <span>{{ $t("offlineStore.real") }}</span>
          <span class="close-btn" @click="closeImageDialog">&times;</span>
        </div>
        <div class="dialog-body image-dialog-body">
          <img :src="previewImage" alt="门店预览图" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed, onUnmounted, nextTick } from "vue";
import { Swiper, SwiperSlide } from "swiper/vue";
import "swiper/css";
import { Autoplay } from "swiper/modules";

const storeMap = {
  store1: {
    name: "Cabbeen卡宾@ Pavilion Bukit Jalil",
    lat: 3.05125,
    lng: 101.67022,
    address:
      "Lot2.2100,Pavilion, Bukit Jalil, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur",
    openTime: "10:00 - 22:00",
    phone: "+60 19-620 3028",
    email: "cabbeen.pvbj@ycinvest.com",
    Whatapp: "+60 19-620 3028",
    link: "https://maps.google.com/?q=Cabbeen,+Pavilion+Bukit+Jalil",
    socialLinks: {
      facebook: "https://www.facebook.com/share/16UZ5ywBbH/",
      tiktok: "https://www.tiktok.com/@cabbeen.pvbj",
      ig: "https://www.instagram.com/cabbeen.pvbj/",
    },
  },
  store2: {
    name: "Cabbeen卡宾@ Sunway Velocity",
    lat: 3.12806,
    lng: 101.72393,
    address:
      "LOT 2-46, SECOND FLOOR, SUNWAY VELOCITY MALL, Lingkaran SV, SUNWAY VELOCITY, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur",
    openTime: "10:00 - 22:00",
    phone: "+60 19-620 3026",
    email: "cabbeen.velocity@ycinvest.com",
    Whatapp: "+60 19-620 3028",
    link: "https://maps.google.com/?q=Cabbeen卡宾+@+Sunway+Velocity",
    socialLinks: {
      facebook: "https://www.facebook.com/share/18r3T295sB/",
      tiktok:
        "https://www.tiktok.com/@cabbeen.sunway.ve?_t=ZS-8vx8u7JvMLI&_r=1",
      ig: "https://www.instagram.com/cabbeensunwayvelocity/",
    },
  },
};

const selectedKey = ref("store1");
const currentStore = computed(() => storeMap[selectedKey.value]);

let map, marker, infoWindow;

const previewVisible = ref(false);
const previewImage = ref("");

function showImageDialog(imgSrc) {
  previewImage.value = imgSrc;
  previewVisible.value = true;
}

function closeImageDialog() {
  previewVisible.value = false;
}

const isMobile = ref(false);
onMounted(() => {
  isMobile.value = window.innerWidth < 768;
  window.addEventListener("resize", () => {
    isMobile.value = window.innerWidth < 768;
  });
});
const handleResize = () => {
  if (map) {
    setTimeout(() => {
      window.google.maps.event.trigger(map, "resize");
      map.setCenter(marker.getPosition());
    }, 150);
  }
};

onMounted(() => {
  window.addEventListener("resize", handleResize);
  waitForGoogleMaps(() => {
    initMap(currentStore.value);
  });
});

onUnmounted(() => {
  window.removeEventListener("resize", handleResize);
});

// 等待 Google Maps 脚本加载完成
function waitForGoogleMaps(callback) {
  if (window.google && window.google.maps) {
    nextTick(callback);
  } else {
    const interval = setInterval(() => {
      if (window.google && window.google.maps) {
        clearInterval(interval);
        nextTick(callback);
      }
    }, 100);
  }
}

function initMap(store) {
  const mapElement = document.getElementById("map");

  map = new window.google.maps.Map(mapElement, {
    center: { lat: store.lat, lng: store.lng },
    zoom: 15,
  });

  marker = new window.google.maps.Marker({
    position: { lat: store.lat, lng: store.lng },
    map,
    title: store.name,
  });

  infoWindow = new window.google.maps.InfoWindow({
    content: getInfoWindowContent(store),
  });

  marker.addListener("click", () => {
    infoWindow.open(map, marker);
  });

  // 初次加载触发 resize
  setTimeout(() => {
    window.google.maps.event.trigger(map, "resize");
    map.setCenter({ lat: store.lat, lng: store.lng });
  }, 300);
}

function switchStore(key) {
  selectedKey.value = key;
  const store = storeMap[key];
  const newPos = { lat: store.lat, lng: store.lng };

  map.setCenter(newPos);
  marker.setPosition(newPos);
  marker.setTitle(store.name);
  infoWindow.setContent(getInfoWindowContent(store));

  setTimeout(() => {
    window.google.maps.event.trigger(map, "resize");
    map.setCenter(newPos);
  }, 100);
}

function getInfoWindowContent(store) {
  return `<div style="font-size:14px;">
    <strong>${store.name}</strong><br/>
    地址：${store.address}<br/>
    <a href="${store.link}" target="_blank">导航到此</a>
  </div>`;
}

function goToMap() {
  window.open(currentStore.value.link, "_blank");
}
</script>

<style scoped>
.store-map-page {
  padding: 50px 20px 50px 20px;
  background-color: #000000;
}

.store-selector {
  margin-bottom: 16px;
  display: flex;
  gap: 12px;
}

.store-btn {
  color: #ffffff;
  border: 1px solid #ffffff88;
  background-color: transparent;
  padding: 8px 20px;
  font-weight: bold;
  border-radius: 6px;
  transition: all 0.3s ease;
}

.store-btn:hover {
  border-color: #ffb100;
  background-color: #ffb10033;
  color: #ffb100;
}

.store-btn.active {
  background-color: #ffb100;
  color: #000000;
  border-color: #ffb100;
}

.main-content {
  display: flex;
  gap: 20px;
  flex-wrap: nowrap; /* ✅ 强制不换行 */
}

.map-box {
  flex: 0 0 65%;
  height: 500px;
  min-height: 300px;
  border-radius: 12px;
  position: relative;
}

#map {
  width: 100%;
  height: 100%;
  position: absolute;
  border-radius: 12px;
}

.info-box {
  flex: 0 0 35%;
  padding: 20px;
  color: #ffffff;
}

.info-box h2 {
  color: #ffffff;
  margin-top: 0;
}

.info-box p {
  margin: 10px 0;
  line-height: 1.6;
  color: #ffffff;
}

.info-actions {
  margin-top: 60px;
  text-align: center;
}

.go-btn {
  background-color: #ffb100;
  color: #000000;
  font-weight: bold;
  padding: 10px 24px;
  font-size: 16px;
  border-radius: 24px;
  box-shadow: 0 2px 8px rgba(241, 196, 15, 0.4);
  border: none;
  transition: all 0.3s ease;
}

.go-btn:hover {
  background-color: #ffd100;
  box-shadow: 0 4px 12px rgba(255, 209, 0, 0.6);
  transform: translateY(-1px);
}

.info-item {
  display: flex;
  align-items: center;
  margin: 12px 0;
  color: #ffffff;
}

.info-item .icon {
  width: 20px;
  height: 20px;
  margin-right: 10px;
}

.social-icons {
  margin-top: 30px;
  display: flex;
  gap: 16px;
  justify-content: flex-start;
}

.social-icons img {
  width: 30px;
  height: 30px;
  cursor: pointer;
  transition: transform 0.2s ease;
}

.social-icons img:hover {
  transform: scale(1.1);
}

/* ✅ 【新增】整体左右留白 */
.store-images {
  padding: 0 40px; /* 左右各留白40px */
}

/* ✅ 标题保持不变，仅作参考 */
.store-images .store-image-title {
  color: #ffffff;
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  margin: 40px 0 40px;
}

.yellow {
  color: #ffb100;
}

.store-images .image-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  margin-top: 20px;
  margin-bottom: 40px;
  width: 100%;
}

/* ✅ 图片外框容器 */
.store-images .image-item {
  width: 100%;
  aspect-ratio: 4 / 3; /* 强制 4:3 比例 */
  overflow: hidden;
  border-radius: 8px;
  position: relative;
}

.store-images .store-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
  transition: transform 0.3s ease; /* 添加过渡效果 */
  cursor: pointer; /* 可选：添加指针样式 */
}
.store-images .store-image:hover {
  transform: scale(1.05); /* 轻微放大5% */
}

/* 门店图片弹窗复用登录弹窗的遮罩和结构 */
.dialog-mask {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  backdrop-filter: blur(4px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000;
}

.dialog.image-dialog {
  width: auto;
  max-width: 90vw;
  max-height: 90vh;
  background-color: #1a1a1a;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.8);
}

.dialog-body.image-dialog-body {
  padding: 0;
  background-color: #000;
  display: flex;
  align-items: center;
  justify-content: center;
}

.dialog-body.image-dialog-body img {
  max-width: 100%;
  max-height: 80vh;
  object-fit: contain;
  border-radius: 0 0 12px 12px;
}

.dialog-header {
  padding: 16px 20px;
  background-color: #111;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: bold;
}

.close-btn {
  cursor: pointer;
  font-size: 20px;
  transition: color 0.3s;
}

.close-btn:hover {
  color: #ffb100;
}
.store-images-mobile {
  padding: 0 16px;
  margin-bottom: 40px;
}

.swiper-slide {
  height: 220px; /* 你可以根据实际调整为240/260 */
}

.mobile-store-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.store-image-title {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
  font-size: 20px;
  font-weight: bold;
  margin: 30px 0 20px;
}

.store-image-title span {
  color: #ffb100;
}

.store-image-title::before {
  content: "门店实景";
  color: #ffffff;
}
/* 响应式支持 */
@media screen and (max-width: 768px) {
  .main-content {
    flex-direction: column;
  }

  .map-box,
  .info-box {
    min-width: 100%;
    width: 100%;
  }

  .map-box {
    height: 300px !important;
    order: -1;
  }

  .store-selector {
    flex-direction: column;
    align-items: stretch;
  }

  .store-btn {
    width: 100%;
    text-align: left;
    padding-left: 16px;
  }

  .info-box {
    padding: 16px;
    font-size: 15px;
  }

  .info-box p {
    font-size: 14px;
  }

  .info-box h2 {
    font-size: 18px;
  }

  /* 移动端弹窗样式优化 */
  .store-images .store-image:hover {
    transform: none; /* 移动端不再放大 */
  }

  .store-images .store-image {
    cursor: default;
  }
}
</style>
