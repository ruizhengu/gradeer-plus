<script setup>

defineProps({
  id: String,
  label: String,
  modelValue: [String, Number, Boolean],
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
  step: {
    type: [String, Number],
    default: '0.1'
  },
  min: {
    type: [String, Number],
    default: '0'
  },
  max: {
    type: [String, Number],
    default: '1'
  },
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
      :step="step" :min="min" :max="max" @input="updateValue" />
  </div>
</template>
