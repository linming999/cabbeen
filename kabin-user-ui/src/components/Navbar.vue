<template>
  <div class="navbar">
    <!-- Logo -->
    <div class="logo">
      <router-link to="/">
        <img src="@/assets/images/顶部logo.png" alt="Cabbeen Logo" />
      </router-link>
    </div>

    <!-- PC端导航菜单 -->
    <div class="nav-menu" v-if="!isMobile">
      <!-- 精品店 -->
      <div
        class="nav-item dropdown"
        :class="{ active: isActive('/store') }"
        @click="handleMainNavClick('store')"
      >
        <span>{{ $t("navbar.store") }}</span>
        <ul class="dropdown-menu" v-show="showDropdown.store">
          <li @click.stop="goTo('/store/men')">
            {{ $t("navbar.menFashion") }}
          </li>
          <li @click.stop="goTo('/store/shoes')">
            {{ $t("navbar.cabbeenShoes") }}
          </li>
          <li @click.stop="goTo('/store/trend')">
            {{ $t("navbar.trendAlbum") }}
          </li>
          <li @click.stop="goTo('/store/new')">
            {{ $t("navbar.newArrivals") }}
          </li>
        </ul>
      </div>

      <!-- 常规导航 -->
      <router-link
        to="/about"
        class="nav-item"
        :class="{ active: isActive('/about') }"
      >
        {{ $t("navbar.about") }}
      </router-link>
      <router-link
        to="/offline-store"
        class="nav-item"
        :class="{ active: isActive('/offline-store') }"
      >
        {{ $t("navbar.offlineStore") }}
      </router-link>
      <router-link
        to="/find-store"
        class="nav-item"
        :class="{ active: isActive('/find-store') }"
      >
        {{ $t("navbar.findStore") }}
      </router-link>

      <!-- 联系我们 -->
      <div
        class="nav-item dropdown"
        :class="{ active: isActive('/contact') }"
        @click="handleMainNavClick('contact')"
      >
        <span>{{ $t("navbar.contact") }}</span>
        <ul class="dropdown-menu" v-show="showDropdown.contact">
          <li @click.stop="goToAnchor('/contact', '#cooperation')">
            {{ $t("navbar.cooperation") }}
          </li>
          <li @click.stop="goToAnchor('/contact', '#recruitment')">
            {{ $t("navbar.recruitment") }}
          </li>
        </ul>
      </div>

      <router-link
        to="/financial-report"
        class="nav-item"
        :class="{ active: isActive('/financial-report') }"
      >
        {{ $t("navbar.financialReport") }}
      </router-link>
    </div>

    <!-- PC端右侧 -->
    <div class="navbar-right" v-if="!isMobile">
      <div class="lang-link" @click="toggleLang">
        {{ $i18n.locale === "en" ? "中文" : "EN" }}
      </div>
      <button class="login-btn" @click="loginDialogVisible = true">
        {{ $t("navbar.login") }}
      </button>
    </div>

    <!-- 汉堡按钮（移动端） -->
    <div class="hamburger" v-if="isMobile" @click="toggleMenu">
      <span v-if="!menuOpen">&#9776;</span>
      <span v-else>&#10005;</span>
    </div>

    <!-- 移动端菜单 -->
    <transition name="slide-fade">
      <div class="mobile-menu" v-if="isMobile && menuOpen">
        <div class="mobile-item" @click="handleMobileNavigate('/about')">
          {{ $t("navbar.about") }}
        </div>
        <div
          class="mobile-item"
          @click="handleMobileNavigate('/offline-store')"
        >
          {{ $t("navbar.offlineStore") }}
        </div>
        <div class="mobile-item" @click="handleMobileNavigate('/find-store')">
          {{ $t("navbar.findStore") }}
        </div>

        <!-- 联系我们子菜单 -->
        <div class="mobile-item dropdown" @click="toggleMobileContactSub">
          <div class="dropdown-title">
            <span>{{ $t("navbar.contact") }}</span>
            <span class="arrow">{{ mobileContactSubOpen ? "▲" : "▼" }}</span>
          </div>
          <transition name="expand">
            <ul class="dropdown-menu" v-show="mobileContactSubOpen">
              <li @click.stop="goToAnchor('/contact', '#cooperation')">
                {{ $t("navbar.cooperation") }}
              </li>
              <li @click.stop="goToAnchor('/contact', '#recruitment')">
                {{ $t("navbar.recruitment") }}
              </li>
            </ul>
          </transition>
        </div>
        <router-link to="/financial-report" class="mobile-item">{{
          $t("navbar.financialReport")
        }}</router-link>

        <div
          class="mobile-item"
          @click="handleMobileNavigate('/financial-report')"
        >
          {{ $t("navbar.financialReport") }}
        </div>

        <!-- 卡宾精品子菜单 -->
        <div class="mobile-item dropdown" @click="toggleSubMenu">
          <div class="dropdown-title">
            <span>{{ $t("navbar.store") }}</span>
            <span class="arrow">{{ subMenuOpen ? "▲" : "▼" }}</span>
          </div>
          <transition name="expand">
            <ul class="dropdown-menu" v-show="subMenuOpen">
              <li @click.stop="handleMobileNavigate('/store/men')">
                {{ $t("navbar.menFashion") }}
              </li>
              <li @click.stop="handleMobileNavigate('/store/shoes')">
                {{ $t("navbar.cabbeenShoes") }}
              </li>
              <li @click.stop="handleMobileNavigate('/store/trend')">
                {{ $t("navbar.trendAlbum") }}
              </li>
              <li @click.stop="handleMobileNavigate('/store/new')">
                {{ $t("navbar.newArrivals") }}
              </li>
            </ul>
          </transition>
        </div>

        <!-- 登录和语言 -->
        <div class="mobile-bottom">
          <div class="lang-link" @click="toggleLang">
            {{ $i18n.locale === "en" ? "中文" : "EN" }}
          </div>
          <button class="login-btn" @click="loginDialogVisible = true">
            {{ $t("navbar.login") }}
          </button>
        </div>
      </div>
    </transition>

    <!-- 登录弹窗 -->
    <div v-if="loginDialogVisible" class="dialog-mask">
      <div class="dialog">
        <div class="dialog-header">
          <span>Login</span>
          <span class="close-btn" @click="loginDialogVisible = false"
            >&times;</span
          >
        </div>
        <div class="dialog-body">
          <input
            v-model="loginForm.username"
            type="text"
            placeholder="Username"
            class="dialog-input"
          />
          <input
            v-model="loginForm.password"
            type="password"
            placeholder="Password"
            class="dialog-input"
          />
        </div>
        <div class="dialog-footer">
          <button class="dialog-btn cancel" @click="loginDialogVisible = false">
            Cancel
          </button>
          <button class="dialog-btn confirm" @click="handleLogin">Login</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Navbar",
  data() {
    return {
      isMobile: false,
      menuOpen: false,
      subMenuOpen: false,
      loginDialogVisible: false,
      mobileContactSubOpen: false, //
      loginForm: {
        username: "",
        password: "",
      },
      // 下拉菜单显示状态
      showDropdown: {
        contact: false,
        store: false,
      },
      // 点击时间记录
      lastClick: {
        contact: 0,
        store: 0,
      },
    };
  },
  mounted() {
    this.checkWindow();
    window.addEventListener("resize", this.checkWindow);
  },
  beforeUnmount() {
    window.removeEventListener("resize", this.checkWindow);
  },
  methods: {
    isActive(path) {
      return this.$route.path === path && path !== "/";
    },
    checkWindow() {
      this.isMobile = window.innerWidth < 768;
      if (!this.isMobile) {
        this.menuOpen = false;
        this.subMenuOpen = false;
      }
    },
    toggleLang() {
      this.$i18n.locale = this.$i18n.locale === "en" ? "zh" : "en";
      document.title = this.$t("title");
    },
    toggleMenu() {
    this.menuOpen = !this.menuOpen;
    // 关闭其他可能打开的下拉菜单
    this.subMenuOpen = false;
    this.mobileContactSubOpen = false;
  },
    handleLogin() {
      if (!this.loginForm.username || !this.loginForm.password) {
        alert("Please enter username and password");
        return;
      }
      alert(`Welcome, ${this.loginForm.username}!`);
      this.loginDialogVisible = false;
      this.loginForm.username = "";
      this.loginForm.password = "";
    },

    // 核心逻辑：点击展开或跳转
    handleMainNavClick(key) {
      const now = Date.now();
      if (now - this.lastClick[key] < 500) {
        // 快速双击 => 跳转主页面
        this.$router.push(`/${key}`);
        this.showDropdown[key] = false;
      } else {
        // 展开/收起菜单
        this.showDropdown[key] = !this.showDropdown[key];
        // 收起其他菜单
        Object.keys(this.showDropdown).forEach((k) => {
          if (k !== key) this.showDropdown[k] = false;
        });
      }
      this.lastClick[key] = now;
    },

    goTo(path) {
      this.$router.push(path);
      // 可选：关闭所有下拉菜单
      Object.keys(this.showDropdown).forEach(
        (k) => (this.showDropdown[k] = false)
      );
    },

    closeMobileMenu() {
      this.menuOpen = false;
      this.subMenuOpen = false;
      this.mobileContactSubOpen = false;
    },
    handleMobileNavigate(path) {
      this.$router.push(path);
      this.closeMobileMenu();
    },
    goToAnchor(path, hash) {
      if (this.$route.path === path) {
        // 已经在该页面，只需要滚动
        const target = document.querySelector(hash);
        if (target) {
          target.scrollIntoView({ behavior: "smooth" });
        }
      } else {
        // 还没跳转，跳转后自动滚动
        this.$router.push({ path, hash });
      }
      this.closeMobileMenu();
      // 收起下拉菜单
      Object.keys(this.showDropdown).forEach(
        (k) => (this.showDropdown[k] = false)
      );
    },
    toggleSubMenu() {
      this.subMenuOpen = !this.subMenuOpen;
      // 如果需要互斥效果（打开一个时关闭另一个），可以添加：
      this.mobileContactSubOpen = false;
    },
    toggleMobileContactSub() {
      this.mobileContactSubOpen = !this.mobileContactSubOpen;
    },
  },
};
</script>

<style scoped>
/* ========================== */
/*      导航栏原有样式        */
/* ========================== */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #1a1a1a;
  color: white;
  position: relative;
  z-index: 1000;
}

.logo img {
  height: 40px;
}

.nav-menu {
  display: flex;
  gap: 40px;
}

.nav-item {
  color: white;
  font-weight: bold;
  text-decoration: none;
}

.nav-item:hover {
  color: #f39c12;
}

.dropdown {
  position: relative;
}

.dropdown-menu {
  position: absolute;
  top: 150%;
  left: 0;
  min-width: 160px;
  background-color: #1a1a1a;
  border-radius: 4px;
  padding: 0;
  list-style: none;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  z-index: 999;
  display: block; /* 保证 v-show 有效 */
}

.dropdown:hover .dropdown-menu {
  display: block;
}

.dropdown-menu li {
  padding: 10px 16px;
  color: white;
  cursor: pointer;
}
.dropdown-menu li:hover {
  background-color: #f39c12;
  color: black;
}

.dropdown-title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
}

.arrow {
  font-size: 12px;
}

.navbar-right {
  display: flex;
  align-items: center;
  gap: 15px;
}

.lang-link {
  cursor: pointer;
  font-weight: bold;
}

.login-btn {
  color: white;
  font-weight: bold;
  padding: 6px 16px;
  border: 1px solid white;
  border-radius: 9999px;
  background-color: transparent;
  transition: all 0.3s ease;
  cursor: pointer;
}

.login-btn:hover {
  background-color: white;
  color: black;
}

.hamburger {
  display: none;
  font-size: 24px;
  cursor: pointer;
}

.mobile-menu {
  position: absolute;
  top: 60px;
  left: 0;
  width: 100%;
  background-color: #1a1a1a;
  display: flex;
  flex-direction: column;
  padding: 20px;
  z-index: 999;
}

.mobile-item {
  color: white;
  padding: 12px 0;
  font-weight: bold;
  cursor: pointer;
}

.mobile-item:hover {
  color: #f39c12;
}

.mobile-bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.nav-item.active {
  color: #f39c12;
  border-bottom: 2px solid #f39c12;
}

@media (max-width: 768px) {
  .nav-menu,
  .navbar-right {
    display: none;
  }
  .hamburger {
    display: block;
  }
}

.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.3s ease;
}
.slide-fade-enter-from,
.slide-fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.expand-enter-active,
.expand-leave-active {
  transition: max-height 0.3s ease;
}
.expand-enter-from,
.expand-leave-to {
  max-height: 0;
}
.expand-enter-to,
.expand-leave-from {
  max-height: 500px;
}

/* ========================== */
/*         登录弹窗           */
/* ========================== */
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

.dialog {
  width: 360px;
  background-color: #1a1a1a;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.8);
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
  color: #f39c12;
}

.dialog-body {
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.dialog-input {
  background-color: #333;
  border: none;
  outline: none;
  padding: 10px 14px;
  border-radius: 8px;
  color: white;
  font-size: 14px;
}

.dialog-input::placeholder {
  color: #aaa;
}

.dialog-footer {
  padding: 14px 20px;
  background-color: #111;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.dialog-btn {
  padding: 6px 16px;
  border: none;
  border-radius: 9999px;
  cursor: pointer;
  font-weight: bold;
  transition: all 0.3s ease;
}

.dialog-btn.cancel {
  background-color: transparent;
  color: white;
  border: 1px solid white;
}

.dialog-btn.cancel:hover {
  background-color: white;
  color: black;
}

.dialog-btn.confirm {
  background-color: #f39c12;
  color: black;
}

.dialog-btn.confirm:hover {
  background-color: #ffc107;
}
</style>
<style>
@media (max-width: 768px) {
  .nav-menu,
  .navbar-right {
    display: none;
  }
  .hamburger {
    display: block !important;
  }
}
</style>