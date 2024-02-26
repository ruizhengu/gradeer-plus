<script setup>
import { ref } from 'vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import 'highlight.js/lib/common';
import hljsVuePlugin from "@highlightjs/vue-plugin";
import { useRouter, useRoute } from 'vue-router'
import { getCodeById, getAssignmentChecksById } from '@/api/submissions'

const router = useRouter()
const route = useRoute()
const id = route.query.id

const highlightjs = hljsVuePlugin.component

const code = ref("")
getCodeById(id).then(response => {
  code.value = response
})

const initalForm = {
  type: '',
  name: '',
  prompt: '',
  weight: 1,
  maxRange: 10,
  feedbackValues: [
    { 'score': 1.0, 'feedback': '' },
    { 'score': 0.8, 'feedback': '' },
    { 'score': 0.5, 'feedback': '' },
    { 'score': 0.0, 'feedback': '' }
  ],
  arbitraryFeedback: 'False',
  priority: 0,
  checkGroup: ''
}

const checks = ref([])
const marks = ref([])
getAssignmentChecksById(id).then(response => {
  if (response.length != 0) {
    checks.value = response
    for (var i = 0; i < checks.value.length; i++) {
      marks.value.push(Number(5).toFixed(1))
    }
  } else {
    checks.value = [initalForm]
    marks.value = [5]
  }
})

const updateMark = (index) => {
  marks.value[index] = Number(marks.value[index]).toFixed(1)
  console.log(marks)
}

const back = () => {
  router.back()
}
</script>

<template>
  <LayoutAuthenticated>
    <div class="flex h-screen overflow-hidden">
      <div class="flex flex-col flex-1 overflow-y-auto">
        <div v-for="(check, index) in checks" :key="index" class="container mx-auto p-4">
          <h2 class="text-2xl font-bold mb-4">{{ check.prompt }}</h2>
          <div v-for="(feedbacks, indexFeedback) in check.feedbackValues" :key="indexFeedback" class="mb-6">
            <div class="text-sm mb-2">{{ feedbacks.score }}. {{ feedbacks.feedback }}</div>
          </div>
          <div>
            <label for="mark-slider" class="block text-lg font-medium text-gray-700">Mark</label>
            <input id="mark-slider" v-model="marks[index]" type="range"
              class="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer" min="0" max="10" step="0.5"
              @input="updateMark(index)">
            <div class="text-right text-lg">{{ marks[index] }}/10</div>
          </div>
        </div>
        <div class="flex justify-between mt-5">
          <BaseButtons class="mt-5">
            <BaseButton type="submit" color="info" label="Submit" />
            <BaseButton color="info" outline label="Reset" />
          </BaseButtons>
          <BaseButton class="mt-5" color="info" label="Back" @click="back" />
        </div>
      </div>
      <div class="flex-1 overflow-y-auto">
        <highlightjs language="java" :code="code" class="flex-1" />
      </div>
    </div>
  </LayoutAuthenticated>
</template>
