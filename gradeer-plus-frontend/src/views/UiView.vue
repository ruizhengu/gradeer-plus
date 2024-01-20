<script setup>
import { ref } from 'vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import CodeInspector from '@/components/CodeInspector.vue'

const mark = ref(5)
mark.value = Number(mark.value).toFixed(1)

const updateMark = () => {
  // mark.value = Number(mark.value).toFixed(1)
}

</script>

<template>
  <LayoutAuthenticated>
    <div class="flex justify-between">
      <div class="container flex-1 mx-auto p-4">
        <h2 class="text-2xl font-bold mb-4">Reporting quality (to console) of answers to the questions:</h2>
        <div class="mb-6">
          <div class="text-sm mb-2">| >=0.0 | >=0.0 | Poor (or no) reporting of answers to the questions to console.</div>
          <div class="text-sm mb-2">| >=5.0 | >=0.5 | Adequate reporting of answers to the questions to console but poor
            readability.</div>
          <div class="text-sm mb-2">| >=8.0 | >=0.8 | Good reporting of answers to the questions to console with some
            formatting issues.</div>
          <div class="text-sm mb-2">| >=10.0 | >=1.0 | Outstanding reporting of answers to questions to console with
            excellent formatting.</div>
        </div>
        <div>
          <label for="mark-slider" class="block text-lg font-medium text-gray-700">Mark</label>
          <input id="mark-slider" v-model="mark" type="range"
            class="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer" min="0" max="10" step="0.5"
            @input="updateMark">
          <div class="text-right text-lg">{{ mark }}/10</div>
        </div>
      </div>
      <CodeInspector class="flex-1" />
    </div>
  </LayoutAuthenticated>
</template>
