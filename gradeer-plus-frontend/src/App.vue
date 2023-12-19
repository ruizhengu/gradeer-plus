<template>
  <el-main>
    <div class="flex main-container">
      <SideBar />
      <router-view></router-view>
    </div>
  </el-main>
</template>

<style>
#app {
  text-align: center;
  color: var(--ep-text-color-primary);
}

.main-container {
  height: calc(100vh - var(--ep-menu-item-height) - 3px);
}
</style>
