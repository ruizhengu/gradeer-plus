<script setup>
import { computed, ref } from 'vue'
import { useMainStore } from '@/stores/main'
import 'highlight.js/lib/common';
import hljsVuePlugin from "@highlightjs/vue-plugin";

const mainStore = useMainStore()

const items = computed(() => mainStore.code)

const highlightjs = hljsVuePlugin.component
const code = items.value
</script>

<template>
  <highlightjs language="java" :code="code" />
</template>
