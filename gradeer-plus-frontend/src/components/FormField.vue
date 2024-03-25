<script setup>

defineProps({
  id: String,
  label: String,
  modelValue: [String, Number, Boolean],
  labelWidth: {
    type: String,
    default: '20'
  },
  inputWidth: {
    type: String,
    default: '80'
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
  <div class="flex items-center mb-2">
    <label :class="`font-bold flex-none`" :style="`width: ${labelWidth}%`">{{ label }}</label>
    <input :id="id" :value="modelValue" :type="type" :class="`p-2 border rounded flex-grow`" :step="step" :min="min"
      :max="max" :style="`width: ${inputWidth}%`" @input="updateValue" />
  </div>
</template>
