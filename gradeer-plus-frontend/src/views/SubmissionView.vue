<script setup>
import { mdiBookAccountOutline, mdiFountainPenTip } from '@mdi/js'
import SectionMain from '@/components/SectionMain.vue'
import CardBox from '@/components/CardBox.vue'
import BaseLevel from '@/components/BaseLevel.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitleLineWithButton from '@/components/SectionTitleLineWithButton.vue'
import { computed, ref } from 'vue'
import { getAllSubmissionByAssignment } from '@/api/submissions'
import { useRouter, useRoute } from 'vue-router'
import OptionStatus from '@/components/OptionStatus.vue'

const router = useRouter()
const route = useRoute()
const id = route.query.id

const submissions = ref([])

getAllSubmissionByAssignment(id).then(response => {
  submissions.value = response
})

const perPage = ref(5)

const currentPage = ref(0)

const submissionPaginated = computed(() =>
  submissions.value.slice(perPage.value * currentPage.value, perPage.value * (currentPage.value + 1))
)

const numPages = computed(() => Math.ceil(submissions.value.length / perPage.value))

const currentPageHuman = computed(() => currentPage.value + 1)

const pagesList = computed(() => {
  const pagesList = []

  for (let i = 0; i < numPages.value; i++) {
    pagesList.push(i)
  }

  return pagesList
})

const updateStatus = (newStatus, index) => {
  console.log(submissions.value[index])
  if (submissions.value[index]) {
    submissions.value[index].status = newStatus
    console.log(submissions.value[index].status)
    // update to the database
  }
}

const back = () => {
  router.back()
}
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiBookAccountOutline" title="Submissions" main>
      </SectionTitleLineWithButton>

      <CardBox class="mb-6" has-table>
        <div>
          <table>
            <thead>
              <tr>
                <th class="text-center">Student</th>
                <th class="text-center">Status</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr v-for="(submission, index) in submissionPaginated" :key="submission.id">
                <td data-label="Module" class="text-center">
                  {{ submission.student }}
                </td>
                <td data-label="Status">
                  <OptionStatus :status="submission.status"
                    @update:status="(newStatus) => updateStatus(newStatus, index)" />
                </td>
                <td class="before:hidden lg:w-1 whitespace-nowrap">
                  <BaseButtons type="justify-start lg:justify-end" no-wrap>
                    <BaseButton color="success" :icon="mdiFountainPenTip" small label="Marking" />
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
      <div class="flex justify-end">
        <BaseButton color="info" label="Back" @click="back" />
      </div>
    </SectionMain>
  </LayoutAuthenticated>
</template>
