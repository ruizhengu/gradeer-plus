import {
  mdiAccountCircle,
  mdiLock,
  mdiCalendarCheck
} from '@mdi/js'

export default [
  {
    to: '/',
    label: 'Assignments',
    icon: mdiCalendarCheck
  },
  // {
  //   to: '/check',
  //   label: 'Check',
  //   icon: mdiSquareEditOutline
  // {
  //   to: '/ui',
  //   label: 'UI',
  //   icon: mdiTelevisionGuide
  // },
  // {
  //   to: '/responsive',
  //   label: 'Responsive',
  //   icon: mdiResponsive
  // },
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
