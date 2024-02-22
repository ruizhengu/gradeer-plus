<script setup>
import { mdiBookAccountOutline } from '@mdi/js'
import SectionMain from '@/components/SectionMain.vue'
// import NotificationBar from '@/components/NotificationBar.vue'
// import TableAssignment from '@/components/TableAssignment.vue'
import { mdiEye, mdiTrashCan, mdiLocationEnter } from '@mdi/js'
import CardBox from '@/components/CardBox.vue'
// import CardBoxModal from '@/components/CardBoxModal.vue'
import BaseLevel from '@/components/BaseLevel.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitleLineWithButton from '@/components/SectionTitleLineWithButton.vue'
import { computed, ref } from 'vue'
import { getAllSubmissionByAssignment } from '@/api/submissions'
import { useRoute } from 'vue-router'

const route = useRoute()
const id = route.query.id

const submissions = ref([])

getAllSubmissionByAssignment(id).then(response => {
  submissions.value = response
})

// const isModalActive = ref(false)

const isModalDangerActive = ref(false)

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

// const editAssignmentCheck = (id, module, name) => {
//   router.push({ name: 'Check', query: { id: id, module: module, name: name } })
// }
</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiBookAccountOutline" title="Submissions" main>
      </SectionTitleLineWithButton>

      <CardBox class="mb-6" has-table>
        <div>
          <!-- <CardBoxModal v-model="isModalDangerActive" title="Please confirm" button="danger" has-cancel>
            <p>Lorem ipsum dolor sit amet <b>adipiscing elit</b></p>
            <p>This is sample modal</p>
          </CardBoxModal> -->

          <table>
            <thead>
              <tr>
                <th class="text-center">Student</th>
                <th class="text-center">Status</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr v-for="submission in submissionPaginated" :key="submission.id">
                <td data-label="Module" class="text-center">
                  {{ submission.student }}
                </td>
                <td data-label="Status">
                  <select v-model="submission.status" :class="statusClass(submission.status)">
                    <option value="Done">Done</option>
                    <option value="Not Started">Not Started</option>
                    <option value="In Progress">In Progress</option>
                  </select>
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
