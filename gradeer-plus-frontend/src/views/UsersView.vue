<script setup>
import SectionMain from '@/components/SectionMain.vue'
import {
  mdiAccountMultiple,
  mdiPlus,
  mdiTrashCan
} from '@mdi/js'
import CardBox from '@/components/CardBox.vue'
import CardBoxModal from '@/components/CardBoxModal.vue'
import BaseLevel from '@/components/BaseLevel.vue'
import BaseButtons from '@/components/BaseButtons.vue'
import BaseButton from '@/components/BaseButton.vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import SectionTitle from '@/components/SectionTitle.vue'
import { computed, ref } from 'vue'
import { fetchAllUsers, addUser, deleteUser } from '@/api/users'
import OptionRole from '@/components/OptionRole.vue'


const users = ref([])

fetchAllUsers().then(response => {
  users.value = response
})

const modalAddActive = ref(false)

const perPage = ref(10)

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

const newUserName = ref("")
const newUserRole = ref("")

const updateRole = (newRole) => {
  newUserRole.value = newRole
}

const funcDeleteUser = async (id) => {
  await deleteUser(id)
  await fetchAllUsers().then(response => {
    users.value = response
  })
}

const addNewUser = async () => {
  await addUser(newUserName.value, newUserRole.value)
  await fetchAllUsers().then(response => {
    users.value = response
  })
  newUserName.value = ""
  newUserRole.value = ""
  modalAddActive.value = false
}

</script>

<template>
  <LayoutAuthenticated>
    <SectionMain>
      <SectionTitle :icon="mdiAccountMultiple" title="Users" main>
      </SectionTitle>

      <CardBox class="mb-6" has-table>
        <div>
          <CardBoxModal v-model="modalAddActive" title="Add User" button="info" has-cancel button-label="Add"
            @submitForm="addNewUser">
            <form @submit.prevent="addNewUser">
              <div class="flex items-center mb-2">
                <label :class="`flex-none`" :style="`width: 15%`">Name</label>
                <input v-model="newUserName" type="text" required>
              </div>
              <div class="flex items-center mb-2">
                <label :class="`flex-none`" :style="`width: 15%`">Role</label>
                <OptionRole v-model="newUserRole" @update:role="(newRole) => updateRole(newRole)" />
              </div>
            </form>
          </CardBoxModal>

          <table>
            <thead>
              <tr>
                <th class="text-center">Name</th>
                <th class="text-center">Role</th>
                <th />
              </tr>
            </thead>
            <tbody>
              <tr v-for="user in usersPaginated" :key="user.id">
                <td data-label="Name" class="text-center">
                  {{ user.name }}
                </td>
                <td data-label="Role" class="text-center">
                  {{ user.role }}
                </td>

                <td class="before:hidden lg:w-1 whitespace-nowrap">
                  <BaseButtons type="justify-start lg:justify-end" no-wrap>
                    <BaseButton color="danger" :icon="mdiTrashCan" small label="Delete"
                      @click="funcDeleteUser(user.id)" />
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
      <BaseButton color="success" label="Add" :icon="mdiPlus" @click="modalAddActive = true" />

    </SectionMain>
  </LayoutAuthenticated>
</template>
