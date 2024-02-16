<script setup>
import { reactive, ref } from 'vue'
import { mdiBallotOutline, mdiUpload, mdiPlus, mdiDelete } from '@mdi/js'
import SectionMain from '@/components/SectionMain.vue'
import CardBox from '@/components/CardBox.vue'
import CardBoxComponentTitle from '@/components/CardBoxComponentTitle.vue'
import FormField from '@/components/FormField.vue'
import BaseButton from '@/components/BaseButton.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitleLineWithButton from '@/components/SectionTitleLineWithButton.vue'
import { getCheckById, updateCheckById } from '@/api/assignments'
import { useRouter } from 'vue-router'

const props = defineProps(['id'])

const initalForm = () => ({
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
})

const check = ref([])

getCheckById(Number(props.id)).then(response => {
  check.value = response
})

const forms = reactive([initalForm()])

const submit = () => {
  console.log(forms)
  console.log(JSON.stringify(forms))

  updateCheckById(Number(props.id), JSON.stringify(forms)).then(response => {
    console.log(response)
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
        forms.length = 0
        jsonData.forEach(item => forms.push(item))
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
  forms.push(initalForm())
}

const deleteCheck = (index) => {
  if (forms.length > 1) forms.splice(index, 1);
  else alert("You cannot delete the last form.");

}

const reset = () => {
  forms.length = 0
  forms.push(initalForm())
}

const router = useRouter()

const back = () => {
  router.back()
}
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiBallotOutline" title="Forms example" main>
        <BaseButton target="_blank" :icon="mdiUpload" label="Upload Checks" color="contrast" rounded-full small
          @click="uploadFile" />
        <input ref="fileInput" type="file" accept=".json" class="hidden" @change="handleFile" />
      </SectionTitleLineWithButton>

      <CardBox v-for="(form, index) in forms" :key="index" form @submit.prevent="submit">
        <CardBoxComponentTitle :title="'Check ' + (index + 1)" />

        <div class="flex flex-row items-center mb-2">
          <label class="font-bold flex-grow-0 w-1/6">Type</label>
          <input v-model="form.type" class="p-2 border rounded flex-grow-0 w-1/3" />
        </div>

        <div class="flex flex-row items-center mb-2">
          <label class="font-bold flex-grow-0 w-1/6">Name</label>
          <input v-model="form.name" class="p-2 border rounded flex-grow-0 w-5/6" />
        </div>

        <div class="flex flex-row items-center mb-2">
          <label class="font-bold flex-grow-0 w-1/6">Prompt</label>
          <input v-model="form.prompt" class="p-2 border rounded flex-grow-0 w-5/6" />
        </div>

        <FormField label="Feedback Values">
          <div v-for="(item, id) in form.feedbackValues" :key="id" class="mb-4 p-4 border border-black rounded-lg">
            <div class="flex flex-row items-center mb-2">
              <label for="score-{{ id }}" class="font-semibold flex-grow-0 w-1/6">Score</label>
              <input id="score-{{ id }}" v-model="item.score" type="number" step="0.1" min="0" max="1"
                class="p-2 border rounded flex-grow-0 w-1/3" />
            </div>
            <div class="flex flex-row items-center mb-2">
              <label for="feedback-{{ id }}" class="font-semibold flex-grow-0 w-1/6">Feedback</label>
              <input id="feedback-{{ id }}" v-model="item.feedback" rows="1"
                class="p-2 border rounded flex-grow-0 w-5/6" />
            </div>
          </div>
        </FormField>

        <div class="flex flex-row items-center mb-2">
          <label class="font-bold flex-grow-0 w-1/6">Arbitrary Feedback</label>
          <input v-model="form.arbitraryFeedback" class="p-2 border rounded flex-grow-0 w-1/3" />
        </div>

        <div class="flex flex-row items-center mb-2">
          <label class="font-bold flex-grow-0 w-1/6">Priority</label>
          <input v-model="form.priority" class="p-2 border rounded flex-grow-0 w-1/3" />
        </div>

        <div class="flex flex-row items-center mb-2">
          <label class="font-bold flex-grow-0 w-1/6">Check Group</label>
          <input v-model="form.checkGroup" class="p-2 border rounded flex-grow-0 w-1/3" />
          <div class="ml-auto">
            <BaseButton v-if="index == forms.length - 1" color="success" label="Add" :icon="mdiPlus" @click="addCheck" />
            <BaseButton class="ml-2" color="danger" label="Delete" :icon="mdiDelete" @click="deleteCheck(index)" />
          </div>
        </div>
      </CardBox>
      <div class="flex justify-between mt-5">
        <BaseButtons class="mt-5">
          <BaseButton type="submit" color="info" label="Submit" @click="submit" />
          <BaseButton color="info" outline label="Reset" @click="reset" />
        </BaseButtons>
        <BaseButtons class="mt-5">
          <BaseButton color="info" label="Back" @click="back" />
        </BaseButtons>
      </div>
    </SectionMain>
  </LayoutAuthenticated>
</template>
