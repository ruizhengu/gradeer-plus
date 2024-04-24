<script setup>
import SectionMain from '@/components/SectionMain.vue'
import {
  mdiBookPlusOutline
} from '@mdi/js'
import BaseButton from '@/components/BaseButton.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitle from '@/components/SectionTitle.vue'
import { ref } from 'vue'
import FormField from '@/components/FormField.vue'
import { addAssignment } from '@/api/assignments'
import { useRouter } from 'vue-router'

const router = useRouter()

const config = ref({
  module: "",
  year: new Date().getFullYear(),
  name: "",
  progress: 0
})

const back = () => {
  router.back()
}

const createAssignment = async () => {
  await addAssignment(config.value.module, config.value.year, config.value.name, config.value.progress)
  back()
}

</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitle :icon="mdiBookPlusOutline" title="Create Assignment" main>
      </SectionTitle>
      <FormField v-model="config.module" SectionTitle label="Module" />
      <FormField v-model="config.year" SectionTitle label="Year" />
      <FormField v-model="config.name" SectionTitle label="Name" />
      <div class="flex justify-end">
        <BaseButton color="success" label="Create" @click="createAssignment" />
      </div>
    </SectionMain>
  </LayoutAuthenticated>
</template>
