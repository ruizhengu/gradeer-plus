import { createApp } from "vue";
import App from "./App.vue";
import router from './router';


// import "~/styles/element/index.scss";

import ElementPlus from "element-plus";
// import all element css, uncommented next line
import "element-plus/dist/index.css";

// or use cdn, uncomment cdn link in `index.html`

// highlight.js
import 'highlight.js/styles/github-dark.css'
import hljs from 'highlight.js/lib/core'
import java from 'highlight.js/lib/languages/java'
import hljsVuePlugin from "@highlightjs/vue-plugin"

import "~/styles/index.scss";
import "uno.css";

// If you want to use ElMessage, import it.
import "element-plus/theme-chalk/src/message.scss";

hljs.registerLanguage('java', java)

const app = createApp(App);
app.use(ElementPlus);
app.use(router)
app.use(hljsVuePlugin)
app.mount("#app");
