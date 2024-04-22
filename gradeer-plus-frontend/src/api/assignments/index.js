import axios from 'axios'

export const listAll = async () => {
  try {
    return await axios.get('http://localhost:8080/assignments').then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const getCheckById = async (id) => {
  try {
    const params = {
      id: id
    }
    return await axios.get('http://localhost:8080/assignments/check', { params }).then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const updateCheckById = async (id, check) => {
  try {
    const headers = {
      'Content-Type': 'application/json',
    }
    return await axios.post(`http://localhost:8080/assignments/check?id=${id}`,check, { headers }).then(response => response)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}
