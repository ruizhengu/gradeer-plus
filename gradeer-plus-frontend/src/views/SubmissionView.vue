<script setup>
import {
  mdiBookAccountOutline,
  mdiFountainPenTip,
  mdiSelect
} from '@mdi/js'
import SectionMain from '@/components/SectionMain.vue'
import CardBox from '@/components/CardBox.vue'
import CardBoxModal from '@/components/CardBoxModal.vue'
import BaseLevel from '@/components/BaseLevel.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitle from '@/components/SectionTitle.vue'
import { computed, ref } from 'vue'
import {
  // fetchAllSubmissionByAssignment,
  loadSubmissionPath,
  storeSubmission
} from '@/api/submissions'
import { useRouter, useRoute } from 'vue-router'


const router = useRouter()
const route = useRoute()
const id = route.query.id

const submissions = ref([])

// TODO could store the loaded submissions to the database?
// TODO only fetch the data with the current marker
// fetchAllSubmissionByAssignment(id).then(response => {
//   submissions.value = response
//   console.log(response)
// })

const perPage = ref(10)

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
      return 'block w-full py-2 border text-center bg-green-200 rounded-md shadow-sm'
    case 'Not Started':
      return 'block w-full py-2 border text-center bg-red-200 rounded-md shadow-sm'
    case 'In Progress':
      return 'block w-full py-2 border text-center bg-yellow-200 rounded-md shadow-sm'
    default:
      return 'block w-full py-2 border text-center rounded-md shadow-sm'
  }
}

const enterMarkingView = (id, student) => {
  router.push({ name: 'marking', query: { id: id, student: student } })
}

const back = () => {
  router.back()
}

const modalAddActive = ref(false)

const submissionFolder = ref("")

const saveSubmissions = async () => {
  // TODO also save the marker data
  await storeSubmission("apiTest", 0, parseInt(id), "Not Started", "ruizhen").then(response => {
    console.log(response)
  })
}

const selectSubmissions = async () => {
  // Send the path to backend
  await loadSubmissionPath(submissionFolder).then(response => {
    const formattedResposne = response.map(element => {
      return {
        status: "Not Started",
        student: element
      }
    })
    submissions.value = formattedResposne
  })
  saveSubmissions()
  modalAddActive.value = false
  submissionFolder.value = ""
}

</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitle :icon="mdiBookAccountOutline" title="Submissions" main>
        <BaseButton target="_blank" :icon="mdiSelect" label="Load Submissions" color="contrast" rounded-full small
          @click="modalAddActive = true" />
        <!-- <input ref="folderInput" webkitdirectory type="file" class="hidden" @change="handleFolderSelection" /> -->
      </SectionTitle>

      <CardBoxModal v-model="modalAddActive" title="Enter Path" button="info" has-cancel button-label="Load"
        @submitForm="selectSubmissions">
        <p>Please enter the path of the submission folder.</p>
        <form @submit.prevent="selectSubmissions">
          <div class="flex items-center mb-2">
            <label :class="`flex-none`" :style="`width: 15%`">Path</label>
            <input v-model="submissionFolder" type="text" required>
          </div>
        </form>
      </CardBoxModal>

      <CardBox class="mb-6" has-table>
        <div>
          <table>
            <thead>
              <tr>
                <th class="text-center">Student</th>
                <th class="text-center">Status</th>
                <th class="text-center">Grade</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr v-for="submission in submissionPaginated" :key="submission.id">
                <td data-label="Module" class="text-center">
                  {{ submission.student }}
                </td>
                <td data-label="Status">
                  <span :class="statusClass(submission.status)">
                    {{ submission.status }}
                  </span>
                </td>
                <td data-label="Grade" class="text-center">
                  {{ submission.grade === null ? 'N/A' : submission.grade }}
                </td>
                <td class="before:hidden lg:w-1 whitespace-nowrap">
                  <BaseButtons type="justify-start lg:justify-end" no-wrap>
                    <BaseButton color="success" :icon="mdiFountainPenTip" small label="Marking"
                      @click="enterMarkingView(id, submission.student)" />
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
