import axios from 'axios'

export const fetchAllSubmissionByAssignment = async (id) => {
  try {
    const params = {
      assignment_id: id
    }
    return await axios.get('http://localhost:8080/submissions', { params }).then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const fetchSubmissionWithAssignmentAndMarker = async (id, marker) => {
  try {
    const params = {
      assignment_id: id,
      marker: marker
    }
    return await axios.get('http://localhost:8080/submissions/assignmentAndMarker', { params }).then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const getAssignmentChecksById = async (id) => {
  try {
    const params = {
      id: id
    }
    return await axios.get('http://localhost:8080/submissions/checks', { params }).then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const loadSubmissionPath = async (path) => {
  try {
    return await axios.post('http://localhost:8080/submissions/loadPath', encodeURIComponent(path.value)).then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const getMergedSolution = async (student) => {
  try {
    return await axios.post('http://localhost:8080/submissions/mergedSolution', student).then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const storeSubmission = async (student, assignment_id, grade, status, marker) => {
  try {
    return await axios.post(`http://localhost:8080/submissions/save?student=${student}&grade=${grade}&assignment_id=${assignment_id}&status=${status}&marker=${marker}`).then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const storeGrade = async (submission_id, checkResults) => {
  try {
    return await axios.post(`http://localhost:8080/submissions/storeGrade?submission_id=${submission_id}`, checkResults).then(response => response)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const storeFeedback = async (submission_id, checkResults) => {
  try {
    return await axios.post(`http://localhost:8080/submissions/storeFeedback?submission_id=${submission_id}`, checkResults).then(response => response)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const updateStatus = async (submission_id, status) => {
  try {
    return await axios.post(`http://localhost:8080/submissions/status?submission_id=${submission_id}&status=${status}`).then(response => response)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}
