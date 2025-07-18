<template>
  <div class="financial-report">
    <!-- 顶部横幅 -->
    <div class="banner">
      <div class="banner-title">
        <h1>{{ $t('financialReport.title') }}</h1>
        <div class="subtitle">{{ $t('financialReport.description') }}</div>
      </div>
    </div>

    <!-- 内容部分 -->
    <div class="content">
      <div class="toolbar">
        <!-- 年份标题 -->
        <h3 class="year-title">{{ selectedYear === 'all' ? $t('financialReport.all') : selectedYear }}</h3>

        <!-- 年份下拉框 -->
        <select v-model="selectedYear" class="select">
          <option value="all">{{ $t('financialReport.all') }}</option>
          <option v-for="year in yearOptions" :key="year" :value="year">{{ year }}</option>
        </select>
      </div>

      <!-- 列表展示 -->
      <div v-for="item in visibleList" :key="item.id" class="list-item">
        <a
          :href="item.filePath"
          target="_blank"
          rel="noopener"
          class="file-link"
        >
          <span class="file-name">{{ item.name }}</span>
          <span class="file-date">{{ formatDate(item.createTime) }}</span>
        </a>
      </div>

      <!-- 无数据提示 -->
      <div v-if="visibleList.length === 0" class="no-data">
        {{ $t('financialReport.noData') }}
      </div>

      <!-- 加载更多按钮 -->
      <div v-if="visibleList.length < filteredList.length" class="load-more-container">
        <button class="load-more-btn" @click="loadMore">{{ $t('financialReport.loadMore')}}</button>
      </div>
    </div>
  </div>
</template>

<script>
import { getFinancialList } from '@/api/finance'

export default {
  name: 'FinancialReport',
  data() {
    return {
      list: [],
      selectedYear: 'all',
      yearOptions: [],
      visibleCount: 10 // 默认显示数量
    }
  },
  computed: {
    filteredList() {
      if (this.selectedYear === 'all') {
        return this.list
      }
      return this.list.filter(item => item.createTime?.slice(0, 4) === this.selectedYear)
    },
    visibleList() {
      return this.filteredList.slice(0, this.visibleCount)
    }
  },
  created() {
    this.fetchList()
  },
  methods: {
    async fetchList() {
      const res = await getFinancialList()
      this.list = res.rows || []

      const years = new Set(this.list.map(item => item.createTime?.slice(0, 4)))
      this.yearOptions = Array.from(years)
    },
    formatDate(dateStr) {
      return dateStr?.slice(0, 10) || ''
    },
    loadMore() {
      this.visibleCount += 10
    }
  }
}
</script>

<style scoped>
.financial-report {
  background: #000;
  min-height: 100vh;
  color: #fff;
}

.banner {
  background: url('@/assets/images/banner.jpeg') center/cover no-repeat;
  height: 260px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.banner-title {
  text-align: center;
  color: #fff;
}

.banner-title h1 {
  font-size: 2.5rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
}

.subtitle {
  color: #ffc107;
  font-size: 1.2rem;
}

.content {
  max-width: 1100px;
  margin: auto;
  background: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  padding: 40px 32px 32px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3);
}

.toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.year-title {
  font-size: 1.1rem;
  font-weight: bold;
  color: #fff;
  margin: 0;
}

.select {
  background: transparent;
  color: #fff;
  padding: 6px 12px;
  border-radius: 4px;
  border: 1px solid #fff;
  outline: none;
  font-size: 1rem;
  cursor: pointer;
}

.select option {
  background: #000;
  color: #fff;
}

.list-item {
  margin-top: 20px;
  border-bottom: 1px solid #222;
  display: flex;
  align-items: center;
}

.file-link {
  display: flex;
  justify-content: space-between;
  width: 100%;
  color: #ffc107;
  text-decoration: none;
  font-size: 1.1rem;
  transition: color 0.2s;
}

.file-link:hover .file-name {
  color: #fff;
  text-decoration: underline;
}

.file-name {
  font-weight: 700;
  color: #ffc107;
}

.file-date {
  color: #aaa;
  font-size: 0.95rem;
}

.no-data {
  text-align: center;
  color: #888;
  margin: 40px 0;
}

.load-more-container {
  text-align: center;
  margin-top: 30px;
}

.load-more-btn {
  padding: 10px 30px;
  background: transparent;
  border: 1px solid #ffc107;
  color: #ffc107;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s;
}

.load-more-btn:hover {
  background: #ffc107;
  color: #000;
}
</style>
