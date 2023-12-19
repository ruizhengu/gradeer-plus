<template>
  <el-menu :router="true" :collapse="isCollapse">
    <router-link to="/">
      <el-menu-item>
        <el-icon>
          <HomeFilled />
        </el-icon>
        <template #title>Home</template>
      </el-menu-item>
    </router-link>
    <router-link to="/about">
      <el-menu-item>
        <el-icon>
          <InfoFilled />
        </el-icon>
        <template #title>About</template>
      </el-menu-item>
    </router-link>
  </el-menu>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import {
  InfoFilled,
  HomeFilled
} from "@element-plus/icons-vue";

const isCollapse = ref(true);
</script>
