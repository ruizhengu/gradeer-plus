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
    return await axios.get(`http://localhost:8080/assignments/${id}/check`).then(response => response.data)
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
    const data = {
      "id": id,
      "check": check
    }
    console.log(data)
    return await axios.post(`http://localhost:8080/assignments/${id}/check`, {},
    {
      params: data
    }).then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}
