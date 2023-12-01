import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router';

// highlight.js
import 'highlight.js/styles/github-dark.css'
import hljs from 'highlight.js/lib/core'
import java from 'highlight.js/lib/languages/java'
import hljsVuePlugin from "@highlightjs/vue-plugin"

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

hljs.registerLanguage('java', java)

const vuetify = createVuetify({
  components,
  directives,
})

const app = createApp(App)
app.use(router)
app.use(vuetify)
app.use(hljsVuePlugin)
app.mount("#app")

