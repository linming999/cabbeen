<template>
  <div class="cabben-page">
    <div class="brand-header">
      <div class="logo-container">
        <img src="@/assets/images/logo.png" class="brand-logo"/>
      </div>
      <h1>CABBEN Malaysia</h1>
      <p class="brand-subtitle">A DESIGNER BRAND OF CHINA</p>
    </div>
    
    <div class="store-locations">
      <p class="store-address">📍 Pavilion Bukit Jalil (Lot 2.21.00)</p>
      <p class="store-address">📍 Sunway Velocity (Lot 2-46)</p>
    </div>
    
    <div class="social-media">
      <a href="https://www.xiaohongshu.com/user/profile/67d001e4000000000e01088f?..."
         target="_blank"
         class="social-link">小红书</a>
      <a href="https://www.instagram.com/cabbeen.my?igsh=aWhpaXl1ODVuY2Jw"
         target="_blank"
         class="social-link">Instagram</a>
      <a href="https://www.tiktok.com/@cabbeen.my?_t=ZS-8wrTVR0GPPs&_r=1"
         target="_blank"
         class="social-link">TikTok</a>
      <a href="https://www.facebook.com/share/16TA7wUjTk/?mibextid=wwXIfr"
         target="_blank"
         class="social-link">Facebook</a>
      <a href="https://whatsapp.com/channel/0029VbATZrZEawdjq9UsEJ03"
         target="_blank"
         class="social-link">WhatsApp Channel</a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CabbenLandingPage'
};
</script>

<style scoped>
.cabben-page {
  max-width: 100%;
  padding: 20px;
  font-family: 'PingFang SC', 'Microsoft YaHei', sans-serif;
  color: #ffffff;
  line-height: 1.6;
  text-align: center;
  background-color: #1a1a1a;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  box-sizing: border-box;
}

.brand-header {
  margin-bottom: 30px;
}

.logo-container {
  width: 80px;
  height: 80px;
  margin: 0 auto 15px;
  border-radius: 50%;
  overflow: hidden;
  border: 1px solid #ffffff;
}

.brand-logo {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.brand-header h1 {
  font-size: clamp(20px, 5vw, 28px); /* 响应式字体大小 */
  font-weight: 700;
  margin-bottom: 5px;
  letter-spacing: 0.5px;
}

.brand-subtitle {
  font-size: clamp(12px, 3vw, 16px);
  color: #9ca39c;
  margin-top: 0;
}

.store-locations {
  margin-bottom: 30px;
}

.store-address {
  font-size: clamp(14px, 4vw, 18px);
  margin: 10px 0;
  color: #ec4899;
}

.social-media {
  display: flex;
  flex-direction: column;
  gap: 12px;
  max-width: 300px;
  width: 90%;
  margin: 0 auto;
}

.social-link {
  display: block;
  text-align: center;
  font-size: clamp(14px, 4vw, 16px);
  color: #ffffff;
  text-decoration: none;
  padding: 12px 0;
  border-radius: 6px;
  transition: all 0.3s ease;
  background-color: #2c3e50;
  font-weight: 500;
}

.social-link:hover {
  background-color: #34495e;
  transform: translateY(-2px);
}

/* 响应式调整 */
@media (max-width: 480px) {
  .cabben-page {
    padding: 20px 15px;
  }
  
  .logo-container {
    width: 70px;
    height: 70px;
  }
  
  .social-media {
    width: 100%;
  }
}
</style>