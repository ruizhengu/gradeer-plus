<script setup>
import SectionMain from '@/components/SectionMain.vue'
import {
  mdiAccountHardHat,
  mdiFountainPenTip
} from '@mdi/js'
import CardBox from '@/components/CardBox.vue'
import BaseLevel from '@/components/BaseLevel.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitle from '@/components/SectionTitle.vue'
import { computed, ref } from 'vue'
import { listAll } from '@/api/assignments'
import { useRouter } from 'vue-router'

const router = useRouter()

const assignments = ref([])

listAll().then(response => {
  assignments.value = response
})

const perPage = ref(5)

const currentPage = ref(0)

const assignmentPaginated = computed(() =>
  assignments.value.slice(perPage.value * currentPage.value, perPage.value * (currentPage.value + 1))
)

const activeAssignments = computed(() => {
  return assignmentPaginated.value.filter(assignment => assignment.status != 'Done')
})

const numPages = computed(() => Math.ceil(assignments.value.length / perPage.value))

const currentPageHuman = computed(() => currentPage.value + 1)

const pagesList = computed(() => {
  const pagesList = []

  for (let i = 0; i < numPages.value; i++) {
    pagesList.push(i)
  }

  return pagesList
})

const enterSubmissionView = (id) => {
  router.push({ name: 'Submission', query: { id: id } })
}
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitle :icon="mdiAccountHardHat" title="Markers" main>
      </SectionTitle>

      <CardBox class="mb-6" has-table>
        <div>
          <table>
            <thead>
              <tr>
                <th class="text-center">Module</th>
                <th class="text-center">Year</th>
                <th class="text-center">Name</th>
                <th class="text-center">Progress</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr v-for="assignment in activeAssignments" :key="assignment.id">
                <td data-label="Module" class="text-center">
                  {{ assignment.module }}
                </td>
                <td data-label="Year" class="text-center">
                  {{ assignment.year }}
                </td>
                <td data-label="Name" class="text-center">
                  {{ assignment.name }}
                </td>
                <td data-label="Progress">
                  <div class="w-full bg-gray-200 rounded-full dark:bg-gray-700">
                    <div
                      class="bg-blue-600 text-xs font-medium text-blue-100 text-center p-0.5 leading-none rounded-full"
                      :style="{ width: assignment.progress + '%' }"> {{ assignment.progress }}%</div>
                  </div>
                </td>

                <td class="before:hidden lg:w-1 whitespace-nowrap">
                  <BaseButtons type="justify-start lg:justify-end" no-wrap>
                    <BaseButton color="info" :icon="mdiFountainPenTip" small label="Marking"
                      @click="enterSubmissionView(assignment.id)" />
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
      </CardBox>
    </SectionMain>
  </LayoutAuthenticated>
</template>
