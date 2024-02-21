<script setup>
import { defineEmits } from 'vue'

defineProps({
  label: {
    type: String,
    default: null
  },
  modelValue: {
    type: [String, Number, Boolean],
    default: null
  },
  labelWidth: {
    type: String,
    default: '1/6'
  },
  inputWidth: {
    type: String,
    default: '1/3'
  },
  type: {
    type: String,
    default: 'text'
  },
  id: String
})

const emit = defineEmits(['update:modelValue']);

const updateValue = (event) => {
  emit('update:modelValue', event.target.value);
};

</script>

<template>
  <div class="flex flex-row items-center mb-2">
    <label :class="`font-bold flex-grow-0 w-${labelWidth}`">{{ label }}</label>
    <input :id="id" :value="modelValue" :type="type" :class="`p-2 border rounded flex-grow-0 w-${inputWidth}`"
      @input="updateValue" />
  </div>
</template>
