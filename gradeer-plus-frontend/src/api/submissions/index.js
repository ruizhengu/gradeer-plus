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

export const getCodeById = async (id) => {
  try {
    const params = {
      id: id
    }
    return await axios.get('http://localhost:8080/submissions/code', { params }).then(response => response.data)
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
