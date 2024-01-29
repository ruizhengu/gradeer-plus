import axios from 'axios'

export const getAllAssignments = async () => {  
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
