<template>
  <el-main>
    <el-menu :router="true" mode="horizontal">
      <el-menu-item index="/">
        <router-link to="/">Home</router-link>
      </el-menu-item>
      <el-menu-item index="/about">
        <router-link to="/about">About</router-link>
      </el-menu-item>
    </el-menu>
    <router-view></router-view>
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
