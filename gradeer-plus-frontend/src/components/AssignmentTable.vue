<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="year" label="Year" min-width="150" />
    <el-table-column prop="module" label="Module" min-width="120" />
    <el-table-column prop="assignment" label="Assignment" min-width="120" />
    <el-table-column fixed="right" label="Operations" min-width="120">
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick">Setup</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
const handleClick = () => {
  console.log('click')
}

const tableData = [
  {
    year: '2023',
    module: 'COM1003',
    assignment: 'cafe',
  },
  {
    year: '2023',
    module: 'COM1003',
    assignment: 'footie',
  },
]
</script>
