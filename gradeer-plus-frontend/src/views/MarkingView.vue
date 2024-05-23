<script setup>
import { ref } from 'vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import 'highlight.js/lib/common';
import hljsVuePlugin from "@highlightjs/vue-plugin";
import { useRouter, useRoute } from 'vue-router'
import {
  getMergedSolution,
  getAssignmentChecksById,
  storeCheckResults
} from '@/api/submissions'

const router = useRouter()
const route = useRoute()
const id = route.query.id
const student = route.query.student

const highlightjs = hljsVuePlugin.component

const code = ref("")

const loadMergedSolution = async (student) => {
  await getMergedSolution(student).then(response => {
    code.value = response
  })
}

loadMergedSolution(student)

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

getAssignmentChecksById(id).then(response => {
  if (response.length != 0) {
    checks.value = response
    checks.value.forEach(check => {
      check.result = parseFloat(Number(5).toFixed(1))
    })
  } else {
    checks.value = [initalForm]
  }
})

const updateMark = (index) => {
  checks.value[index].result = parseFloat(Number(checks.value[index].result).toFixed(1))
}

const submit = async () => {
  await storeCheckResults(checks.value).then(response => {
    if (response.status == '200') {
      alert("Check Results Submitted!")
      back()
    }
  })
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
            <input id="mark-slider" v-model="checks[index].result" type="range"
              class="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer" min="0" max="10" step="0.5"
              @input="updateMark(index)">
            <div class="text-right text-lg">{{ checks[index].result }}/10</div>
          </div>
        </div>
        <div class="flex justify-between mt-5">
          <BaseButtons class="mt-5">
            <BaseButton color="info" label="Submit" @click="submit" />
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
