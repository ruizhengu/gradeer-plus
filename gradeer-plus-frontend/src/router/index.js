import { createRouter, createWebHistory } from 'vue-router'
import Assignment from '@/views/AssignmentsView.vue'
import Check from '@/views/CheckView.vue'
import Submission from '@/views/SubmissionView.vue'
import Marking from '@/views/MarkingView.vue'
import AssignmentCreation from '@/views/AssignmentCreationView.vue'
import Markers from '@/views/MarkersView.vue'
import Users from '@/views/UsersView.vue'

const routes = [
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
    name: 'assignment-check',
    component: Check,
    props: true
  },
  {
    path: '/submission',
    name: 'submission',
    component: Submission
  },
  {
    path: '/marking',
    name: 'marking',
    component: Marking
  },
  {
    path: '/assignments/create',
    name: 'assignment-create',
    component: AssignmentCreation
  },
  {
    path: '/markers',
    name: 'markers',
    component: Markers
  },
  {
    path: '/users',
    name: 'users',
    component: Users
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
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { top: 0 };
    }
  },
})

export default router
