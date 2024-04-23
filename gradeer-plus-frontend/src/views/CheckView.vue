<script setup>
import { ref } from 'vue'
import {
  mdiBallotOutline,
  mdiUpload,
  mdiPlus,
  mdiDelete
} from '@mdi/js'
import SectionMain from '@/components/SectionMain.vue'
import CardBox from '@/components/CardBox.vue'
import CardBoxComponentTitle from '@/components/CardBoxComponentTitle.vue'
import FormField from '@/components/FormField.vue'
import FormHolder from '@/components/FormHolder.vue'
import BaseButton from '@/components/BaseButton.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitle from '@/components/SectionTitle.vue'
import { getCheckById, updateCheckById } from '@/api/assignments'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()
const id = route.query.id
const module = route.query.module
const name = route.query.name

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

getCheckById(id).then(response => {
  if (response.length != 0) {
    checks.value = response
  } else {
    checks.value = [initalForm]
  }
})

// const checksWithoutGeneralFeedback = computed(() => {
//   return checks.value.filter(check => check.name != 'Z__General')
// })

const submit = () => {
  updateCheckById(id, checks.value).then(response => {
    if (response.status == '200') {
      alert("Check Submitted!");
      back()
    }
  })
}

const fileInput = ref(null)
const uploadFile = () => {
  fileInput.value.click()
}

const handleFile = (event) => {
  const file = event.target.files[0];
  if (file && file.type === "application/json") {
    const reader = new FileReader();
    reader.onload = (e) => {
      const fileContent = e.target.result;
      try {
        const jsonData = JSON.parse(fileContent)
        checks.value.length = 0
        jsonData.forEach(item => checks.value.push(item))
      } catch (err) {
        console.error("Error parsing JSON:", err);
      }
    }
    reader.readAsText(file);
  } else {
    alert("Please upload a valid JSON file.")
  }
}

const addCheck = () => {
  checks.value.push(initalForm)
}

const deleteCheck = (index) => {
  if (checks.value.length > 1) checks.value.splice(index, 1);
  else alert("You cannot delete the last form.");
}

const reset = () => {
  checks.value.length = 0
  checks.value.push(initalForm)
}

const back = () => {
  router.back()
}
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitle :icon="mdiBallotOutline" :title="`Checks - ${module} - ${name}`" main>
        <BaseButton target="_blank" :icon="mdiUpload" label="Upload Checks" color="contrast" rounded-full small
          @click="uploadFile" />
        <input ref="fileInput" type="file" accept=".json" class="hidden" @change="handleFile" />
      </SectionTitle>

      <!-- <div class="flex items-center ps-4 border border-gray-200 rounded">
        <div class=" flex items-center h-5">
          <input id="helper-checkbox" aria-describedby="helper-checkbox-text" type="checkbox" value=""
            class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded">
        </div>
        <div class="ms-2 text-sm">
          <label for="helper-checkbox" class="font-medium text-gray-900">Z__General</label>
          <p id="helper-checkbox-text" class="text-xs font-normal text-gray-500">Additional general
            feedback for the solution</p>
        </div>
      </div> -->

      <CardBox v-for="(check, index) in checks" :key="index" check @submit.prevent="submit">
        <CardBoxComponentTitle SectionTitle :title="'Check ' + (index + 1)" />

        <FormField :id="`type-${index}`" v-model="check.type" SectionTitle label="Type" />
        <FormField :id="`name-${index}`" v-model="check.name" SectionTitle label="Name" />
        <FormField :id="`prompt-${index}`" v-model="check.prompt" SectionTitle label="Prompt" />

        <FormHolder SectionTitle label="Feedback Values">
          <div v-for="(item, id) in check.feedbackValues" :key="id" class="mb-4 p-4 border border-black rounded-lg">
            <FormField id="score-1" v-model="item.score" label="Score" type="number" step="0.1" min="0" max="1" />
            <FormField id="score-1" v-model="item.feedback" label="Feedback" />
          </div>
        </FormHolder>

        <FormField v-model="check.arbitraryFeedback" SectionTitle label="Arbitrary Feedback" />
        <FormField v-model="check.priority" SectionTitle label="Priority" />
        <FormField v-model="check.checkGroup" SectionTitle label="Check Group" />

        <BaseButtons class="mt-5 flex">
          <BaseButton v-if="index == checks.length - 1" color="success" label="Add" :icon="mdiPlus" @click="addCheck" />
          <BaseButton class="ml-2" color="danger" label="Delete" :icon="mdiDelete" @click="deleteCheck(index)" />
        </BaseButtons>
      </CardBox>
      <div class="flex justify-between mt-5">
        <BaseButtons class="mt-5">
          <BaseButton type="submit" color="info" label="Submit" @click="submit" />
          <BaseButton color="info" outline label="Reset" @click="reset" />
        </BaseButtons>
        <BaseButton class="mt-5" color="info" label="Back" @click="back" />
      </div>
    </SectionMain>
  </LayoutAuthenticated>
</template>
