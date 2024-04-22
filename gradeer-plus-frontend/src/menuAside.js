import {
  mdiAccountCircle,
  mdiLock,
  mdiCalendarCheck,
  mdiAccountCowboyHat,
  mdiAccountHardHat
} from '@mdi/js'

export default [
  {
    to: '/',
    label: 'Assignments',
    icon: mdiCalendarCheck
  },
  {
    to: '/lecturers',
    label: 'Lecturers',
    icon: mdiAccountCowboyHat
  },
  {
    to: '/markers',
    label: 'Markers',
    icon: mdiAccountHardHat
  },
  // {
  //   to: '/',
  //   label: 'Styles',
  //   icon: mdiPalette
  // },
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
