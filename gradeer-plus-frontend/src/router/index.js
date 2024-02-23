import { createRouter, createWebHistory } from 'vue-router'
// import Style from '@/views/StyleView.vue'
// import Home from '@/views/HomeView.vue'
import Assignment from '@/views/AssignmentsView.vue'
import Ui from '@/views/UiView.vue'
import Check from '@/views/CheckView.vue'
import Submission from '@/views/SubmissionView.vue'

const routes = [
  // {
  //   meta: {
  //     title: 'Home'
  //   },
  //   path: '/',
  //   name: 'home',
  //   component: Home
  // },
  {
    meta: {
      title: 'Assignments'
    },
    path: '/',
    name: 'assignments',
    component: Assignment,
  },
  {
    path: '/assignments/check',
    name: 'Check',
    component: Check,
    props: true
  },
  {
    path: '/submission',
    name: 'Submission',
    component: Submission
  },
  {
    meta: {
      title: 'Profile'
    },
    path: '/profile',
    name: 'profile',
    component: () => import('@/views/ProfileView.vue')
  },
  {
    meta: {
      title: 'Ui'
    },
    path: '/ui',
    name: 'ui',
    component: Ui
  },
  {
    meta: {
      title: 'Responsive layout'
    },
    path: '/responsive',
    name: 'responsive',
    component: () => import('@/views/ResponsiveView.vue')
  },
  {
    meta: {
      title: 'Login'
    },
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginView.vue')
  },
  {
    meta: {
      title: 'Error'
    },
    path: '/error',
    name: 'error',
    component: () => import('@/views/ErrorView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  // scrollBehavior(to, from, savedPosition) {
  //   return savedPosition || { top: 0 }
  // }
})

export default router
