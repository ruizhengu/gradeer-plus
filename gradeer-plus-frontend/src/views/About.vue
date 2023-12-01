<template>
  <div>
    <h1>About Page</h1>
    <div class="highlight">
      <highlightjs language="java" :code="code" />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import 'highlight.js/lib/common';
import hljsVuePlugin from "@highlightjs/vue-plugin";

export default defineComponent({
  name: 'About',
  data() {
    return {
      code: `
public static void main(String[] args) {
  Pattern textPattern = Pattern.compile("text\\((.*)\\)");

  String selector = "text(hello)";

  Matcher matcher = textPattern.matcher(selector);
  if (matcher.matches()) {
      System.out.println(matcher.group(1));
  }
}
      `
    }
  },
  components: {
    highlightjs: hljsVuePlugin.component
  }
});
</script>

<style lang="scss">
.highlight {
  text-align: left
}
</style>
