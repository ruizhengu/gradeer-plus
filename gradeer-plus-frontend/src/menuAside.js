import {
  mdiAccountCircle,
  mdiLock,
  mdiCalendarCheck,
  mdiAccountHardHat,
  mdiAccountMultiple
} from '@mdi/js'

export default [
  {
    to: '/',
    label: 'Assignments',
    icon: mdiCalendarCheck
  },
  {
    to: '/markers',
    label: 'Markers',
    icon: mdiAccountHardHat
  },
  {
    to: '/users',
    label: 'Users',
    icon: mdiAccountMultiple
  },
  {
    to: '/profile',
    label: 'Profile',
    icon: mdiAccountCircle
  },
  {
    to: '/login',
    label: 'Login',
    icon: mdiLock
  }
]
