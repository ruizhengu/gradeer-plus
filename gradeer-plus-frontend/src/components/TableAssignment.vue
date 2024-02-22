<script setup>
import { computed, ref } from 'vue'
import { mdiEye, mdiTrashCan, mdiLocationEnter } from '@mdi/js'
import CardBoxModal from '@/components/CardBoxModal.vue'
import BaseLevel from '@/components/BaseLevel.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import { listAll } from '@/api/assignments'
import { useRouter } from 'vue-router'

const router = useRouter()

const assignments = ref([])

listAll().then(response => {
  assignments.value = response
})

// const isModalActive = ref(false)

const isModalDangerActive = ref(false)

const perPage = ref(5)

const currentPage = ref(0)

const assignmentPaginated = computed(() =>
  assignments.value.slice(perPage.value * currentPage.value, perPage.value * (currentPage.value + 1))
)

const numPages = computed(() => Math.ceil(assignments.value.length / perPage.value))

const currentPageHuman = computed(() => currentPage.value + 1)

const pagesList = computed(() => {
  const pagesList = []

  for (let i = 0; i < numPages.value; i++) {
    pagesList.push(i)
  }

  return pagesList
})

const statusClass = (status) => {
  switch (status) {
    case 'Done':
      return 'block w-full py-2 px-3 border border-gray-300 bg-green-200 rounded-md shadow-sm focus:outline-none focus:ring-primary-500 focus:border-primary-500';
    case 'Not Started':
      return 'block w-full py-2 px-3 border border-gray-300 bg-red-200 rounded-md shadow-sm focus:outline-none focus:ring-primary-500 focus:border-primary-500';
    case 'In Progress':
      return 'block w-full py-2 px-3 border border-gray-300 bg-yellow-200 rounded-md shadow-sm focus:outline-none focus:ring-primary-500 focus:border-primary-500';
  }
}

const editAssignmentCheck = (id, module, name) => {
  router.push({ name: 'Check', query: { id: id, module: module, name: name } })
}

const enterAssignmentSubmissions = (id) => {
  router.push({ name: 'Submission', query: { id: id } })
}

</script>

<template>
  <div>
    <CardBoxModal v-model="isModalDangerActive" title="Please confirm" button="danger" has-cancel>
      <p>Lorem ipsum dolor sit amet <b>adipiscing elit</b></p>
      <p>This is sample modal</p>
    </CardBoxModal>

    <table>
      <thead>
        <tr>
          <th class="text-center">Module</th>
          <th class="text-center">Year</th>
          <th class="text-center">Name</th>
          <th class="text-center">Status</th>
          <th class="text-center">Progress</th>
          <th />
        </tr>
      </thead>
      <tbody>
        <tr v-for="assignment in assignmentPaginated" :key="assignment.id">
          <td data-label="Module" class="text-center">
            {{ assignment.module }}
          </td>
          <td data-label="Year" class="text-center">
            {{ assignment.year }}
          </td>
          <td data-label="Name" class="text-center">
            {{ assignment.name }}
          </td>
          <td data-label="Status">
            <select v-model="assignment.status" :class="statusClass(assignment.status)">
              <option value="Done">Done</option>
              <option value="Not Started">Not Started</option>
              <option value="In Progress">In Progress</option>
            </select>
          </td>
          <td data-label="Progress" class="lg:w-32">
            <progress class="flex w-2/5 self-center lg:w-full" max="100" :value="assignment.progress">
              {{ assignment.progress }}
            </progress>
          </td>

          <td class="before:hidden lg:w-1 whitespace-nowrap">
            <BaseButtons type="justify-start lg:justify-end" no-wrap>
              <BaseButton color="success" :icon="mdiLocationEnter" small
                @click="enterAssignmentSubmissions(assignment.id)" />
              <BaseButton color="info" :icon="mdiEye"
                @click="editAssignmentCheck(assignment.id, assignment.module, assignment.name)" />
              <BaseButton color="danger" :icon="mdiTrashCan" small @click="isModalDangerActive = true" />
            </BaseButtons>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="p-3 lg:px-6 border-t border-gray-100 dark:border-slate-800">
      <BaseLevel>
        <BaseButtons>
          <BaseButton v-for="page in pagesList" :key="page" :active="page === currentPage" :label="page + 1"
            :color="page === currentPage ? 'lightDark' : 'whiteDark'" small @click="currentPage = page" />
        </BaseButtons>
        <small>Page {{ currentPageHuman }} of {{ numPages }}</small>
      </BaseLevel>
    </div>
  </div>
</template>
