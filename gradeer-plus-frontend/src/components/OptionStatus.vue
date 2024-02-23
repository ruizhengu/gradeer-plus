<script setup>
import { computed, ref, watchEffect } from 'vue'

const props = defineProps({
  status: String
});

const localStatus = ref(props.status);

const emit = defineEmits(['update:status']);

watchEffect(() => {
  localStatus.value = props.status;
});

function handleStatusChange() {
  emit('update:status', localStatus.value);
}

const statusClass = computed(() => {
  switch (localStatus.value) {
    case 'Done':
      return 'block w-full py-2 px-3 border border-gray-300 bg-green-200 rounded-md shadow-sm focus:outline-none focus:ring-primary-500 focus:border-primary-500';
    case 'Not Started':
      return 'block w-full py-2 px-3 border border-gray-300 bg-red-200 rounded-md shadow-sm focus:outline-none focus:ring-primary-500 focus:border-primary-500';
    case 'In Progress':
      return 'block w-full py-2 px-3 border border-gray-300 bg-yellow-200 rounded-md shadow-sm focus:outline-none focus:ring-primary-500 focus:border-primary-500';
    default:
      return '';
  }
})
</script>

<template>
  <div>
    <select v-model="localStatus" :class="statusClass" @change="handleStatusChange">
      <option value="Done">Done</option>
      <option value="Not Started">Not Started</option>
      <option value="In Progress">In Progress</option>
    </select>
  </div>
</template>
