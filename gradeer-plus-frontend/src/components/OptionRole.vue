<script setup>
import { ref, watchEffect } from 'vue'

const props = defineProps({
  role: String
})

const localRole = ref(props.role)

const emit = defineEmits(['update:role'])

watchEffect(() => {
  localRole.value = props.role;
})

function handleRoleChange() {
  emit('update:role', localRole.value)
}
</script>

<template>
  <div>
    <select v-model="localRole" @change="handleRoleChange">
      <option value="lecturer">Lecturer</option>
      <option value="marker">Marker</option>
    </select>
  </div>
</template>
