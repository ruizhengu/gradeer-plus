import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '~/views/Home.vue';
import About from '~/views/About.vue';
import Setup from '~/views/Setup.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/about',
    name: 'about',
    component: About
  },
  {
    path: '/setup',
    name: 'setup',
    component: Setup
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
