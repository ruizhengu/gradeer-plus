<script setup>
import { mdiCalendarCheck } from '@mdi/js'
import SectionMain from '@/components/SectionMain.vue'
import { mdiEye, mdiTrashCan, mdiLocationEnter } from '@mdi/js'
import CardBox from '@/components/CardBox.vue'
// import CardBoxModal from '@/components/CardBoxModal.vue'
import BaseLevel from '@/components/BaseLevel.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitleLineWithButton from '@/components/SectionTitleLineWithButton.vue'
import { computed, ref } from 'vue'
import { listAll } from '@/api/assignments'
import { useRouter } from 'vue-router'
import OptionStatus from '@/components/OptionStatus.vue'

const router = useRouter()

const assignments = ref([])

listAll().then(response => {
  assignments.value = response
})

// const isModalActive = ref(false)

// const isModalDangerActive = ref(false)

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

const updateStatus = (newStatus, index) => {
  if (assignments.value[index]) {
    assignments.value[index].status = newStatus
    // update to the database
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
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiCalendarCheck" title="Assignments" main>
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
                <th class="text-center">Module</th>
                <th class="text-center">Year</th>
                <th class="text-center">Name</th>
                <th class="text-center">Status</th>
                <th class="text-center">Progress</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr v-for="(assignment, index) in assignmentPaginated" :key="assignment.id">
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
                  <OptionStatus :status="assignment.status"
                    @update:status="(newStatus) => updateStatus(newStatus, index)" />
                </td>
                <td data-label="Progress" class="lg:w-32">
                  <progress class="flex w-2/5 self-center lg:w-full" max="100" :value="assignment.progress">
                    {{ assignment.progress }}
                  </progress>
                </td>

                <td class="before:hidden lg:w-1 whitespace-nowrap">
                  <BaseButtons type="justify-start lg:justify-end" no-wrap>
                    <BaseButton color="success" :icon="mdiLocationEnter" small label="Submissions"
                      @click="enterAssignmentSubmissions(assignment.id)" />
                    <BaseButton color="info" :icon="mdiEye" label="Checks" small=""
                      @click="editAssignmentCheck(assignment.id, assignment.module, assignment.name)" />
                    <BaseButton color="danger" :icon="mdiTrashCan" small label="Delete"
                      @click="isModalDangerActive = true" />
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
