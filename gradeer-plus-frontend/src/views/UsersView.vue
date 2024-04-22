<script setup>
import SectionMain from '@/components/SectionMain.vue'
import {
  mdiAccountMultiple
} from '@mdi/js'
import CardBox from '@/components/CardBox.vue'
import BaseLevel from '@/components/BaseLevel.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitleLineWithButton from '@/components/SectionTitleLineWithButton.vue'
import { computed, ref } from 'vue'
import { getAllUsers, addUser } from '@/api/users'
import { useRouter } from 'vue-router'
import OptionStatus from '@/components/OptionStatus.vue'

// const router = useRouter()

const users = ref([])

getAllUsers().then(response => {
  console.log(response)
  users.value = response
})

// const isModalActive = ref(false)

// const isModalDangerActive = ref(false)

const perPage = ref(5)

const currentPage = ref(0)

const usersPaginated = computed(() =>
  users.value.slice(perPage.value * currentPage.value, perPage.value * (currentPage.value + 1))
)

const numPages = computed(() => Math.ceil(users.value.length / perPage.value))

const currentPageHuman = computed(() => currentPage.value + 1)

const pagesList = computed(() => {
  const pagesList = []

  for (let i = 0; i < numPages.value; i++) {
    pagesList.push(i)
  }

  return pagesList
})

const funcAddUser = (name, role) => {
  addUser(name, role)
  getAllUsers().then(response => {
    users.value = response
  })
}

// const updateStatus = (newStatus, index) => {
//   if (assignments.value[index]) {
//     assignments.value[index].status = newStatus
//     // update to the database
//   }
// }

// const editAssignmentCheck = (id, module, name) => {
//   router.push({ name: 'Check', query: { id: id, module: module, name: name } })
// }

// const enterAssignmentSubmissions = (id) => {
//   router.push({ name: 'Submission', query: { id: id } })
// }

</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitleLineWithButton :icon="mdiAccountMultiple" title="Users" main>
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
                <th class="text-center">Name</th>
                <th class="text-center">Role</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr v-for="(user, index) in usersPaginated" :key="user.id">
                <td data-label="Name" class="text-center">
                  {{ user.name }}
                </td>
                <td data-label="Role" class="text-center">
                  {{ user.role }}
                </td>

                <td class="before:hidden lg:w-1 whitespace-nowrap">
                  <BaseButtons type="justify-start lg:justify-end" no-wrap>
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
