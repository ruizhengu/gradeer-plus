<script setup>
import { reactive } from 'vue'
import LayoutAuthenticated from '@/layouts/LayoutAuthenticated.vue'
import CodeInspector from '@/components/CodeInspector.vue'



const forms = reactive(
  [
    {
      "type": "ManualCheck",
      "name": "Reporting quality (to console) of answers to the questions",
      "prompt": "Reporting quality (to console) of answers to the questions:",
      "weight": 1,
      "maxRange": 10,
      "feedbackValues": [
        { "score": 1.0, "feedback": "Outstanding reporting of answers to questions to console with excellent formatting." },
        { "score": 0.8, "feedback": "Good reporting of answers to the questions to console with some formatting issues." },
        { "score": 0.5, "feedback": "Adequate reporting of answers to the questions to console but poor readability ." },
        { "score": 0.0, "feedback": "Poor (or no) reporting of answers to the questions to console." }
      ],
      "arbitraryFeedback": false,
      "priority": 38,
      "checkGroup": "FD Reporting"
    },
    {
      "type": "ManualCheck",
      "name": "Reporting quality (to console) of answers to the queries",
      "prompt": "Reporting quality (to console) of answers to the queries:",
      "weight": 1,
      "maxRange": 10,
      "feedbackValues": [
        { "score": 1.0, "feedback": "Outstanding reporting of answers to queries to console with excellent formatting ." },
        { "score": 0.8, "feedback": "Good reporting of answers to the queries to console with some formatting issues ." },
        { "score": 0.5, "feedback": "Average reporting of answers to the queries to console but poor readability." },
        { "score": 0.0, "feedback": "Poor (or no) reporting of answers to the queries to console." }
      ],
      "arbitraryFeedback": false,
      "priority": 35,
      "checkGroup": "FD Queries Reporting"
    },
    {
      "type": "ManualCheck",
      "name": "Overall performance and design",
      "prompt": "Overall quality of GUI (layout of components, resizing, responsiveness):",
      "weight": 1,
      "maxRange": 10,
      "feedbackValues": [
        { "score": 1.0, "feedback": "Outstanding quality of GUI (layout of components, resizing, responsiveness to user interactions)." },
        { "score": 0.8, "feedback": "Good quality of GUI (layout of components, resizing, responsiveness to user interactions)." },
        { "score": 0.5, "feedback": "Average quality of GUI (layout of components, resizing, responsiveness to user interactions)." },
        { "score": 0.0, "feedback": "Poor quality of GUI (layout of components, resizing, responsiveness to user interactions)." }
      ],
      "arbitraryFeedback": false,
      "priority": 30,
      "checkGroup": "GUI Overall"
    },
    {
      "type": "ManualCheck",
      "name": "Overall performance of league and player details selection",
      "prompt": "Overall performance of league and player details selection, do they work as expected?",
      "weight": 1,
      "maxRange": 10,
      "feedbackValues": [
        { "score": 1.0, "feedback": "Outstanding performance of league and player details selection, GUI updates all panels and provides sensible results in an excellent fashion." },
        { "score": 0.8, "feedback": "Good performance of league and player details selection, GUI updates all panels and seems to provide sensible results with an adequately quality of presentation but some minor misbehaviour." },
        { "score": 0.5, "feedback": "Average performance of league and player details selection, GUI is not fully reliable and sometimes fails to show results adequately." },
        { "score": 0.0, "feedback": "Poor performance of league and player details selection, results shown either do not make sense or no results are shown." }
      ],
      "arbitraryFeedback": false,
      "priority": 29,
      "checkGroup": "GUI Overall Performance"
    },
    {
      "type": "ManualCheck",
      "name": "Overall performance of creation of filters (text area, add filter and clear filters)",
      "prompt": "Overall performance of creation of filters (text area, add filter and clear filters), do they work as expected?",
      "weight": 1,
      "maxRange": 10,
      "feedbackValues": [
        { "score": 1.0, "feedback": "Outstanding performance of creation of filters (text area, add filter and clear filters), GUI updates all panels and provides sensible results in an excellent fashion." },
        { "score": 0.8, "feedback": "Good performance of creation of filters (text area, add filter and clear filters), GUI updates all panels and seems to provide sensible results with an adequately quality of presentation but some minor misbehaviour." },
        { "score": 0.5, "feedback": "Average performance of creation of filters (text area, add filter and clear filters), GUI is not fully reliable and sometimes fails to show results adequately." },
        { "score": 0.0, "feedback": "Poor performance of creation of filters (text area, add filter and clear filters), results shown either do not make sense or no results are shown." }
      ],
      "arbitraryFeedback": false,
      "priority": 28,
      "checkGroup": "GUI Overall Performance"
    },
  ]
)

const getMarks = () => {
  var marks = []
  for (var i = 0; i < forms.length; i++) {
    marks.push(Number(5).toFixed(1))
  }
  return marks
}

const marks = reactive(getMarks())

const updateMark = (index) => {
  marks[index] = Number(marks[index]).toFixed(1)
  // console.log(marks)
}

</script>

<template>
  <LayoutAuthenticated>
    <div class="flex h-screen overflow-hidden">
      <div class="flex flex-col flex-1 overflow-y-auto">
        <div v-for="(form, index) in forms" :key="index" class="container mx-auto p-4">
          <h2 class="text-2xl font-bold mb-4">{{ form.prompt }}</h2>
          <div v-for="(feedbacks, indexFeedback) in form.feedbackValues" :key="indexFeedback" class="mb-6">
            <div class="text-sm mb-2">{{ feedbacks.score }}. {{ feedbacks.feedback }}</div>
          </div>
          <div>
            <label for="mark-slider" class="block text-lg font-medium text-gray-700">Mark</label>
            <input id="mark-slider" v-model="marks[index]" type="range"
              class="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer" min="0" max="10" step="0.5"
              @input="updateMark(index)">
            <div class="text-right text-lg">{{ marks[index] }}/10</div>
          </div>
        </div>
      </div>
      <div class="flex-1 overflow-y-auto">
        <CodeInspector class="flex-1" />
      </div>
    </div>
  </LayoutAuthenticated>
</template>
