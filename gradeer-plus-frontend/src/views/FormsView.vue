<script setup>
import { reactive, ref } from 'vue'
import { mdiBallotOutline, mdiAccount, mdiUpload, mdiPlus, mdiDelete } from '@mdi/js'
import SectionMain from '@/components/SectionMain.vue'
import CardBox from '@/components/CardBox.vue'
import CardBoxComponentTitle from '@/components/CardBoxComponentTitle.vue'
import FormCheckRadioGroup from '@/components/FormCheckRadioGroup.vue'
import FormFilePicker from '@/components/FormFilePicker.vue'
import FormField from '@/components/FormField.vue'
import FormControl from '@/components/FormControl.vue'
import BaseDivider from '@/components/BaseDivider.vue'
import BaseButton from '@/components/BaseButton.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import SectionTitle from '@/components/SectionTitle.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitleLineWithButton from '@/components/SectionTitleLineWithButton.vue'
import NotificationBarInCard from '@/components/NotificationBarInCard.vue'

// const typeOptions = [
//   { id: 1, label: 'ManualCheck' },
// ]

const form = reactive({
  type: 'ManualCheck',
  name: 'Reporting quality (to console) of answers to the questions',
  prompt: 'Reporting quality (to console) of answers to the questions:',
  weight: 1,
  maxRange: 10,
  feedbackValues: [
    { 'score': 1.0, 'feedback': 'Outstanding reporting of answers to questions to console with excellent formatting.' },
    { 'score': 0.8, 'feedback': 'Good reporting of answers to the questions to console with some formatting issues.' },
    { 'score': 0.5, 'feedback': 'Adequate reporting of answers to the questions to console but poor readability .' },
    { 'score': 0.0, 'feedback': 'Poor (or no) reporting of answers to the questions to console.' }
  ],
  arbitraryFeedback: 'False',
  priority: 38,
  checkGroup: 'FD Reporting'
})

const customElementsForm = reactive({
  checkbox: ['lorem'],
  radio: 'one',
  switch: ['one'],
  file: null
})

const submit = () => {
  //
}

const formStatusWithHeader = ref(true)

const formStatusCurrent = ref(0)

const formStatusOptions = ['info', 'success', 'danger', 'warning']

const formStatusSubmit = () => {
  formStatusCurrent.value = formStatusOptions[formStatusCurrent.value + 1]
    ? formStatusCurrent.value + 1
    : 0
}

const uploadFile = () => {
  console.log("upload file")
}
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiBallotOutline" title="Forms example" main>
        <BaseButton target="_blank" :icon="mdiUpload" label="Upload Checks" color="contrast" rounded-full small
          @click="uploadFile" />
      </SectionTitleLineWithButton>
      <CardBox form @submit.prevent="submit">
        <CardBoxComponentTitle title="Check 1" />

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
          <div v-for="(item, index) in form.feedbackValues" :key="index" class="mb-4 p-4 border border-black rounded-lg">
            <div class="flex flex-row items-center mb-2">
              <label for="score-{{ index }}" class="font-semibold flex-grow-0 w-1/6">Score</label>
              <input id="score-{{ index }}" v-model="item.score" type="number" step="0.1" min="0" max="1"
                class="p-2 border rounded flex-grow-0 w-1/3" />
            </div>
            <div class="flex flex-row items-center mb-2">
              <label for="feedback-{{ index }}" class="font-semibold flex-grow-0 w-1/6">Feedback</label>
              <input id="feedback-{{ index }}" v-model="item.feedback" rows="1"
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
        </div>

        <BaseButtons>
          <BaseButton color="success" label="Add" :icon="mdiPlus" />
          <BaseButton color="danger" label="Delete" :icon="mdiDelete" />
        </BaseButtons>

        <template #footer>
          <BaseButtons>
            <BaseButton type="submit" color="info" label="Submit" />
            <BaseButton type="reset" color="info" outline label="Reset" />
          </BaseButtons>
        </template>
      </CardBox>
    </SectionMain>

    <SectionTitle>Custom elements</SectionTitle>

    <SectionMain>
      <CardBox>
        <FormField label="Checkbox">
          <FormCheckRadioGroup v-model="customElementsForm.checkbox" name="sample-checkbox"
            :options="{ lorem: 'Lorem', ipsum: 'Ipsum', dolore: 'Dolore' }" />
        </FormField>

        <BaseDivider />

        <FormField label="Radio">
          <FormCheckRadioGroup v-model="customElementsForm.radio" name="sample-radio" type="radio"
            :options="{ one: 'One', two: 'Two' }" />
        </FormField>

        <BaseDivider />

        <FormField label="Switch">
          <FormCheckRadioGroup v-model="customElementsForm.switch" name="sample-switch" type="switch"
            :options="{ one: 'One', two: 'Two' }" />
        </FormField>

        <BaseDivider />

        <FormFilePicker v-model="customElementsForm.file" label="Upload" />
      </CardBox>

      <SectionTitle>Form with status example</SectionTitle>

      <CardBox class="md:w-7/12 lg:w-5/12 xl:w-4/12 shadow-2xl md:mx-auto" is-form is-hoverable
        @submit.prevent="formStatusSubmit">
        <NotificationBarInCard :color="formStatusOptions[formStatusCurrent]"
          :is-placed-with-header="formStatusWithHeader">
          <span><b class="capitalize">{{ formStatusOptions[formStatusCurrent] }}</b> state</span>
        </NotificationBarInCard>
        <FormField label="Fields">
          <FormControl v-model="form.name" :icon-left="mdiAccount" help="Your full name" placeholder="Name" />
        </FormField>

        <template #footer>
          <BaseButton label="Trigger" type="submit" color="info" />
        </template>
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>
