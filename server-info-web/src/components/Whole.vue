<template>
  <div id="wrapper">
    <!-- 页面头部部分 -->
    <div class="header">
      <div class="logo">服务器监控</div>
      <!-- 水平一级菜单 -->
      <div>
        <el-menu
            text-color="#000000"
            active-text-color="#3989fa"
            :default-active="toIndex"
            @select="handleSelect">
          <el-menu-item v-for="(item, index) in itemList" :index="item.path" :key="index">
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </el-menu>
      </div>
    </div>
    <!-- 页面左侧二级菜单栏，和主体内容区域部分 -->
    <el-main>
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>

export default {
  data() {
    return {
      itemList: [    // 水平一级菜单栏的菜单
        {path: '/', title: '首页'},
        {path: '/golang', title: 'golang服务器监控'},
        {path: '/java', title: 'java服务器监控'},
      ],
    }
  },
  computed: {
    toIndex() {  // 根据路径绑定到对应的一级菜单，防止页面刷新重新跳回第一个
      return '/' + this.$route.path.split('/')[1];
    },
  },
  methods: {
    handleSelect(path) {  // 切换菜单栏
      this.$router.push({
        path: path
      });
    },
  }
}
</script>

<style scoped>
#wrapper {
  width: 100%;
  height: 100%;
  background: #f0f0f0;
}

.header {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 50px;
  font-size: 22px;
  background: #f0f0f0;
}

.header .logo {
  text-align: left;
  margin-left: 40px;
  margin-top: 20px;
  height: 35px;
  width: 200px;
  vertical-align: middle;
}

.el-menu.el-menu {
  border-bottom: none !important;
  float: left;
  margin-top: 30px;
  margin-left: 20px;
  background: transparent;
}

.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 2px solid #3989fa;
  color: #3989fa;
  font-weight: bold;
}

.el-menu--horizontal > .el-menu-item {
  font-size: 16px;
  margin: 0 15px;
}

</style>